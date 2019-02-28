package tests.tests_for_similarity;

/*****************************************
 *  Teste do exercicio 2 e 3 do guiao 6  *
 *****************************************/

import classes.Similarity;
import processing.core.PApplet;
import tests.makeChart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class Test1 extends PApplet {

    private static makeChart.makeRunningTimePlot plot;
    private static Similarity s;

    public static void main(String args[]){
        String file = "src/tests/files/u.data";
        BufferedReader input_stream;
        TreeMap<Integer, ArrayList<Integer>> user_set= new TreeMap<>();

        try{
            input_stream = new BufferedReader(new FileReader(file));

            String str;
            while((str = input_stream.readLine()) != null){
                String actual[] = str.split("\t");
                int user = Integer.parseInt(actual[0]);
                int movie =  Integer.parseInt(actual[1]);
                ArrayList<Integer> all_movies;

                if((all_movies = user_set.get(user)) == null)
                    all_movies = new ArrayList<>();

                all_movies.add(movie);
                user_set.put(user, all_movies);
            }
        }catch(IOException e) {
            System.out.println("Erro ao abrir ficheiro");
        }


        //exercicio 2 do guião 7
        s = Similarity.SimilarityInteger(new ArrayList<> (user_set.values()));
        double results1[][] = s.distanceInteger(0.4, 1);

        System.out.println("\t\t\tDistância de Jaccard:\n> Tempo de execução: " +
                            s.getTime_jaccard_distance()/1000 +
                            " s");
        System.out.println("> Resultados: ");
        for(int num = 0; num < results1.length; num++)
            System.out.println("-> " + user_set.keySet().toArray()[(int)results1[num][0]] + " " +
                                user_set.keySet().toArray()[(int)results1[num][1]] + " " +
                                results1[num][2]);


        //exercicio 3 do guião 7
        for(int hash_num = 100; hash_num <= 1000; hash_num += 100){
            double results2[][] = s.distanceInteger(0.4, 2, hash_num);
            System.out.println("\n\n\n\t\t\tDistância obtida por MinHash usando " +
                                hash_num + " hash functions:\n> Tempo de execução: " +
                                s.getTime_min_hash_distance().get(hash_num)/1000 +
                                " s");
            System.out.println("> Resultados: ");
            for(int num = 0; num < results2.length; num++)
                System.out.println("-> " + user_set.keySet().toArray()[(int)results2[num][0]] + " " +
                                    user_set.keySet().toArray()[(int)results2[num][1]] + " " +
                                    results2[num][2]);
        }

        plot = new makeChart.makeRunningTimePlot();

        //add processing library
        PApplet.main("tests.tests_for_similarity.Test1");
    }

    public void settings() {
        size(1000, 500);
    }

    public void setup() {
        plot.setup(this, s.getTime_min_hash_distance());
    }

    public void draw() {
        background(0);
        fill(120);
        plot.draw(this, 70, 70, width - 100, height - 100);
    }

    public void mouseMoved() {
        plot.getChartData(this);
    }

}
