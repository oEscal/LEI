package classes;

import java.util.*;

import static classes.HashFunctions.universalHash;
import static classes.HashFunctions.universalHashString;

public class Similarity {

    /****************
     *  parameters  *
     ****************/

    private double[][] jaccard_distance;    //when the Jaccard Distance is calculated, the results are stored in this variable
    private HashMap<Integer, double[][]> min_hash_distance;     //when the minhash distance is calculated, the results are stored in this variable
    private HashMap<Integer, double[][]> lsh_distance;    //when the LSH Distance is calculated, the results are stored in this variable
    private double time_jaccard_distance;  //when the Jaccard Distance is calculated, the running time in milliseconds of is stored in this variable
    private TreeMap<Integer, Double> time_min_hash_distance;   //when the minhash distance is calculated, the running time in milliseconds of is stored in this variable
    private TreeMap<Integer, Double> time_lsh_distance;   //when the lsh distance is calculated, the running time in milliseconds of is stored in this variable
    private ArrayList<ArrayList<Integer>> integer_set;
    private ArrayList<ArrayList<String>> string_set;


    /*****************
     *  constructor  *
     *****************/

    protected Similarity(ArrayList<ArrayList<Integer>> set1, ArrayList<ArrayList<String>> set2){
        if(set1 != null)
            this.integer_set = set1;
        else
            this.string_set = set2;

        min_hash_distance = new HashMap();
        lsh_distance = new HashMap();
        time_min_hash_distance = new TreeMap<>();
        time_lsh_distance = new TreeMap<>();
    }


    /***************************************************
     *  methods to create objects of class Similarity  *
     ***************************************************/

    public static Similarity SimilarityString(ArrayList<ArrayList<String>> set){
        return new Similarity(null, set);
    }

    public static Similarity SimilarityInteger(ArrayList<ArrayList<Integer>> set){
        return new Similarity(set, null);
    }


    /*************
     *  methods  *
     *************/

    /*
     *  Arguments:
     *      double threshold -> maximum value of the distance
     *      int t -> type of distance to calc:
     *                  1 -> Jaccard Distance
     *                  2 -> minhash Distance
     *                  3 -> LSH Distance
     *                  4 -> minhash Distance for shingles
     *      int num_hash -> number of hash functions to use when is calculated the minhash distance
     *
     *  Return structure:
     *      each line of the array is an array of length 3, in which the first two positions
     *      are the indices is set that have the distance of third position
     */
    public double[][] distanceInteger(double threshold, int t, int num_hash, int r, int k){
        ArrayList<double[]> data = new ArrayList<>();
        if((this.integer_set != null && (t == 1 || t == 2)) ||
                (this.string_set != null && (t == 4 || t == 3))){
            if(t == 1 || t == 2 || t == 4){
                double dists[][] = null;
                double time_init = System.currentTimeMillis();
                if(t == 1){
                    dists = jaccardDistance();
                    this.time_jaccard_distance = System.currentTimeMillis() - time_init;
                }
                if(t == 2){
                    dists = minHashDistance(num_hash, 1, 1);
                    this.time_min_hash_distance.put(num_hash, System.currentTimeMillis() - time_init);
                }
                if(t == 4){
                    dists = minHashDistance(num_hash, k, 2);
                    this.time_min_hash_distance.put(num_hash, System.currentTimeMillis() - time_init);
                }

                for(int n1 = 0; n1 < dists.length; n1++)
                    for(int n2 = n1 + 1; n2 < dists.length; n2++)
                        if(dists[n1][n2] < threshold){
                            double d[] = {(double)n1, (double)n2, dists[n1][n2]};
                            data.add(d);
                        }
                double result[][] = new double[data.size()][3];
                for(int num = 0; num < result.length; num++)
                    result[num] = data.get(num);

                return result;
            }
            if(t == 3){
                double result[][];
                double time_init = System.currentTimeMillis();
                result = lshDistanceString(num_hash, k, r, threshold);
                this.time_lsh_distance.put(num_hash, System.currentTimeMillis() - time_init);

                return result;
            }
            return null;
        }else
            throw new Error("Erro!\nFormato do set não aceite");
    }

    public double[][] distanceInteger(double threshold, int t){
        return distanceInteger(threshold, t, 500, 4, 1);
    }
    public double[][] distanceInteger(double threshold, int t, int hash_num, int r){
        return distanceInteger(threshold, t, hash_num, r, 1);
    }
    public double[][] distanceInteger(double threshold, int t, int hash_num){
        return distanceInteger(threshold, t, hash_num, 4, 1);
    }

    /*
     *  jaccardDistance() return the JaccardDistance of set's members
     */
    private double[][] jaccardDistance(){
        double result[][] = new double[this.integer_set.size()][this.integer_set.size()];
        for(int n1 = 0; n1 < this.integer_set.size(); n1++)
            for(int n2 = n1 + 1; n2 < this.integer_set.size(); n2++){
                HashSet<Integer> intersection = new HashSet<>(this.integer_set.get(n1));
                intersection.retainAll(new HashSet<>(this.integer_set.get(n2)));

                HashSet<Integer> union = new HashSet<>(this.integer_set.get(n1));
                union.addAll(new HashSet<>(this.integer_set.get(n2)));

                result[n1][n2] = 1 - (double)intersection.size()/(double)union.size();
            }

        return jaccard_distance = result;
    }

    /*
     *  minHashDistance() return the minhash distance of set's members
     *
     *  Arguments:
     *      int num_hash -> number of hash functions to use
     *      int k -> size of shingles
     *      int t -> argument to decide which minhash is to be calculated
     *              (for integers, t must be 1; for shingles, t must be other
     *               integer than 1)
     */
    private double[][] minHashDistance(int num_hash, int k, int t){
        double result[][];
        Integer h[][];

        if(t == 1){
            result = new double[this.integer_set.size()][this.integer_set.size()];
            h = calcSignaturesInteger(num_hash);
        }
        else{
            result = new double[this.string_set.size()][this.string_set.size()];
            h = calcSignaturesString(num_hash, k);
        }

        //  Calc of number of similar elements
        for(int num = 0; num < num_hash; num++){
            TreeSet<Integer> uniques = new TreeSet<>();
            HashMap<Integer, ArrayList<Integer>> indices_uniques = new HashMap<>();
            uniques.addAll(Arrays.asList(h[num]));

            for(int num2 = 0; num2 < h[num].length; num2++)
                if(uniques.contains(h[num][num2])){
                    ArrayList<Integer> add;
                    if((add = indices_uniques.get(uniques.headSet(h[num][num2]).size())) == null)
                        add = new ArrayList<>();
                    add.add(num2);
                    indices_uniques.put(uniques.headSet(h[num][num2]).size(), add);
                }

            for(int i = 0; i < uniques.size(); i++){
                for(int n1 = 0; n1 < indices_uniques.get(i).size(); n1++)
                    for(int n2 = n1 + 1; n2 < indices_uniques.get(i).size(); n2++)
                        result[indices_uniques.get(i).get(n1)][indices_uniques.get(i).get(n2)]++;
            }

        }

        for(int num = 0; num < result.length; num++)
            for(int num2 = 0; num2 < result.length; num2++)
                result[num][num2] = 1 - result[num][num2]/num_hash;

        min_hash_distance.put(num_hash, result);
        return result;
    }

    public double[][] lshDistanceString(int num_hash, int k, int r, double threshold){
        Integer[][] h = calcSignaturesString(num_hash, k);
        String bands[][] = new String[num_hash/r][this.string_set.size()];

        int size_hash_table = 100*this.string_set.size();
        TreeMap<Integer, String> hash_table[] = new TreeMap[size_hash_table];
        TreeSet<Integer> hash_table_indices = new TreeSet<>();

        TreeSet<int[]> candidate_pairs = new TreeSet<>(
                new Comparator<int[]>(){
                    @Override
                            public int compare(int[] a, int[] b){
                                if((a[0] == b[0] && a[1] == b[1]) || (a[0] == b[1] && a[1] == b[0]))
                                    return 0;
                                return a[0] - b[0];
            }
        }
        );

        //lockup fot candidate pairs
        for(int n = 0; n < num_hash; n++){
            if(n/r > (n - 1)/r)
                Arrays.fill(bands[n/r], "");
            for(int s = 0; s < this.string_set.size(); s++){
                bands[n/r][s] += h[n][s];
                if((n + 1)/r > (n)/r){
                    if(hash_table[HashFunctions.hashString(bands[n/r][s], size_hash_table)] == null){
                        int index = HashFunctions.hashString(bands[n/r][s], size_hash_table);
                        hash_table[index] = new TreeMap<>();
                        hash_table_indices.add(index);
                    }
                    if(!hash_table[HashFunctions.hashString(bands[n/r][s], size_hash_table)].containsKey(s)){
                        hash_table[HashFunctions.hashString(bands[n/r][s], size_hash_table)].put(s, bands[n/r][s]);

                        for(int num = 0; num < hash_table[HashFunctions.hashString(bands[n/r][s], size_hash_table)].size(); num++){
                            int actual_key = (Integer)hash_table[HashFunctions.hashString(bands[n/r][s], size_hash_table)].keySet().toArray()[num];
                            if( actual_key != s &&
                                    hash_table[HashFunctions.hashString(bands[n/r][s], size_hash_table)].get(actual_key).equals(bands[n/r][s])){
                                int new_element[] = {actual_key, s};
                                candidate_pairs.add(new_element);
                            }
                        }
                    }
                }
            }
        }

        ArrayList<int[]> candidate_pairs_list = new ArrayList<>(candidate_pairs);
        ArrayList<double[]> data = new ArrayList<>();

        for(int num = 0; num < candidate_pairs_list.size(); num++){
            ArrayList<ArrayList<String>> actual = new ArrayList<>();
            int actual_candidate_pair[] = candidate_pairs_list.get(num);
            actual.add(string_set.get(actual_candidate_pair[0]));
            actual.add(string_set.get(actual_candidate_pair[1]));
            Similarity s = Similarity.SimilarityString(actual);

            double distance[][] = s.distanceInteger(threshold, 4, num_hash);

            double actual_result[] = new double[3];
            if(distance.length > 0){
                actual_result[0] = actual_candidate_pair[0];
                actual_result[1] = actual_candidate_pair[1];
                actual_result[2] = distance[0][2];

                data.add(actual_result);
            }

        }

        double result[][] = new double[data.size()][3];
        for(int num = 0; num < result.length; num++)
            result[num] = data.get(num);

        return result;

    }

    private Integer[][] calcSignaturesInteger(int num_hash){
        Integer h[][] = new Integer[num_hash][this.integer_set.size()];

        int m = 30000;
        int a[] = new int[num_hash];
        int b[] = new int[num_hash];

        //  Calc of arguments for the universal hash function
        for(int num = 0; num < num_hash; num++){
            a[num] = (int)(Math.random()*m);
            b[num] = (int)(Math.random()*m);
        }

        //  Calc of signature
        for(int s = 0; s < this.integer_set.size(); s++)
            for(int n = 0; n < num_hash; n++){
                h[n][s] = Integer.MAX_VALUE;
                for(int num = 0; num < this.integer_set.get(s).size(); num++){
                    int hash = universalHash(this.integer_set.get(s).get(num), a[n], b[n]);
                    if(hash < h[n][s]){
                        h[n][s] = hash;
                    }
                }
            }

        return h;
    }

    /*************************************************************
     * Function to return a ArrayList of k shingles from a file  *
     * Arguments:                                                *
     *      int k->size of each shingle                          *
     ************************************************************/
    private ArrayList<ArrayList<String>> setShingles(int k) {
        ArrayList<ArrayList<String>> l = new ArrayList<>();
        StringBuilder br;
        for (int i = 0; i < this.string_set.size(); i++) {
            l.add(i, new ArrayList<>());
            int index = 0;
            while (true) {
                br = new StringBuilder();
                if (index > this.string_set.get(i).size() - k)
                    break;
                for (int num = index; num < k + index; num++)
                    br.append(this.string_set.get(i).get(num));
                l.get(i).add(br.toString());
                index++;
            }
        }
        return l;
    }
    /************************************************
     * Function to return a Array of signatures     *
     * Arguments:                                   *
     *      int num_hash->number of hash functions  *
     *      int k->size of each shingle             *
     ************************************************/
    private Integer[][] calcSignaturesString(int num_hash, int k) {
        ArrayList<ArrayList<String>> s = setShingles(k);
        Integer h[][] = new Integer[num_hash][s.size()];

        int m = 7187;
        int a[] = new int[num_hash];
        int b[] = new int[num_hash];

        //  Calc of arguments for the universal hash function
        for (int num = 0; num < num_hash; num++) {
            a[num] = (int) (Math.random() * m) + 1;
            b[num] = (int) (Math.random() * m) + 1;
        }
        int max = 0;
        for (int i = 0; i < s.size(); i++) {
            if (max < s.get(i).size())
                max = s.get(i).size();
        }
        int random_per_string[][] = new int[max][k];

        for (int i = 0; i < max; i++) {
            for (int j = 0; j < k; j++)
                random_per_string[i][j] = (int) (Math.random() * 20)+ 1;
        }

        for (int i = 0; i < s.size(); i++) {
            for (int n = 0; n < num_hash; n++) {
                int minimo=Integer.MAX_VALUE;
                for (int num = 0; num < s.get(i).size(); num++) {
                    int hash = universalHashString(s.get(i).get(num), a[n], b[n], random_per_string[num]);
                    if (hash < minimo) {
                        minimo=hash;
                    }
                }
                h[n][i]=minimo;
            }
        }
        return h;
    }

    /*************************
     *  getters and setters  *
     *************************/

    public double[][] getJaccard_distance() {
        return this.jaccard_distance;
    }

    public HashMap<Integer, double[][]> getMin_hash_distance() {
        return this.min_hash_distance;
    }

    public HashMap<Integer, double[][]> getLsh_distance() {
        return this.lsh_distance;
    }

    public TreeMap<Integer, Double> getTime_lsh_distance() {
        return this.time_lsh_distance;
    }

    public double getTime_jaccard_distance() {
        return this.time_jaccard_distance;
    }

    public TreeMap<Integer, Double> getTime_min_hash_distance() {
        return this.time_min_hash_distance;
    }
}
