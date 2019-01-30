package tests.final_test;

import classes.Similarity;
import processing.core.PApplet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ReadLetters extends PApplet {
    /***************
     *  constants  *
     ***************/
    //max number of each letter to read from document
    private final int max_num_letter = 150;
    //number of hash functions to use in minhash
    private final int num_hash = 35;

    private TreeSet<Integer> data1_xy = new TreeSet<>();
    private TreeMap<Character, ArrayList<ArrayList<Integer>>> data2_xy = new TreeMap<>();

    private int lastX = -1, lastY = -1;
    private int count_read_data = 0;

    public static void main(String args[]){



        //add processing library
        PApplet.main("tests.final_test.ReadLetters");
    }

    public void settings() {
        size(500, 500);
    }

    public void setup() {
        background(0);
    }

    public void draw() {
        if(count_read_data == 1){
            //variable to count execution times
            long time;

            TreeMap<Integer, Double> min_distances= new TreeMap<>();
            String st;
            File file;
            BufferedReader br;
            file = new File("src/tests/final_test/data/handwritten_data_785.csv");

            for(int num = 0; num < 26; num++){
                data2_xy.put((char)((int)'A' + num), new ArrayList<>());
            }

            //to count time needed to read the file
            time = System.currentTimeMillis();
            try{
                br = new BufferedReader(new FileReader(file));
                while ((st = br.readLine()) != null) {
                    String all_numbers_string[] = st.split(",");
                    char actual_key = (char)((int)'A' + Integer.parseInt(all_numbers_string[0]));

                    if(data2_xy.get(actual_key).size() < max_num_letter){
                        data2_xy.get(actual_key).add(new ArrayList<>());
                        for(int n = 1; n < all_numbers_string.length; n++){
                            int actual_num = Integer.parseInt(all_numbers_string[n]);
                            if(actual_num != 0){
                                data2_xy.get(actual_key).get(data2_xy.get(actual_key).size() - 1).add((int)(Double.parseDouble(String.format("%d.%d", n % 28, n / 28))*100));
                            }
                        }
                    }
                }
                br.close();
            }catch(IOException e) {
                System.out.println("Erro ao abrir ficheiros");
            }
            System.out.printf("Ficheiro lido em %f segundos\n", (System.currentTimeMillis() - time)/1000.0);


            //count time needed to calc the letter's similarity
            time = System.currentTimeMillis();
            for(int num = 0; num < data2_xy.size(); num++){
                ArrayList<ArrayList<Integer>> data_xy = new ArrayList<>();
                data_xy.add(new ArrayList<>(data1_xy));

                double min_dist = Double.MAX_VALUE;
                for(int num2 = 0; num2 < data2_xy.get((char)((int)'A' + num)).size(); num2++){
                    if((char)((int)'A' + num) != 'K' && (char)((int)'A' + num) != 'Y' && (char)((int)'A' + num) != 'W'){
                        data_xy.add(new ArrayList<>(data2_xy.get((char)((int)'A' + num)).get(num2)));

                        Similarity s = Similarity.SimilarityInteger(data_xy);
                        double[][] distance_y = s.distanceInteger(1.1, 2, num_hash);

                        if(distance_y.length > 0)
                            min_dist = Double.min(min_dist, distance_y[0][2]);
                    }
                }
                min_distances.put(num, min_dist);
            }
            System.out.printf("Letra comparada em %f segundos\n", (System.currentTimeMillis() - time)/1000.0);


            TreeMap<Integer, Double> final_min_distances = new TreeMap<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return (int) (1 / (min_distances.get(o1) - min_distances.get(o2)));
                }
            });
            final_min_distances.putAll(min_distances);

            Integer simbs[] = final_min_distances.keySet().stream().toArray(Integer[]::new);
            Double values[] = final_min_distances.values().stream().toArray(Double[]::new);

            //print results in order
            System.out.println("\n\nSimilaridades (ordenadas das maiores para as menores):");
            for(int num = 0; num < simbs.length; num++){
                if((1 - values[num]) >= 0)
                    System.out.println((char) ((int)'A' + simbs[num]) + " -> " + (1 - values[num]));
            }
            noLoop();
        }
    }

    public void mouseDragged(){
        strokeWeight(2);
        stroke(255);
        if(lastX > 0 && lastY > 0)
            line(mouseX, mouseY, lastX, lastY);
        else
            point(mouseX, mouseY);
        lastX = mouseX;
        lastY = mouseY;

        int x_map = (int)map(lastX, 0, width, 0, 28);
        int y_map = (int)map(lastY, 0, height, 0, 28);
        data1_xy.add((int)(Double.parseDouble(String.format("%d.%d", x_map, y_map))*100));
        data1_xy.add((int)(Double.parseDouble(String.format("%d.%d", x_map + 1, y_map + 1))*100));
        data1_xy.add((int)(Double.parseDouble(String.format("%d.%d", x_map - 1, y_map - 1))*100));

    }

    public void mouseReleased(){
        lastX = lastY = -1;
    }

    public void keyTyped(){
        if((int)key == 10){
            count_read_data++;
        }
    }

}
