package tests.final_test;

import classes.Counter;
import classes.CountingBloomFilter;
import classes.Similarity;
import processing.core.PApplet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class ReadWords extends PApplet {
    /***************
     *  constants  *
     ***************/
    //chose how to variate the padding of the window for each drawn letter
    private final double change_padding = 1.8;
    //max number of each letter to read from document
    private final int max_num_letter = 150;
    //number of hash functions to use in minhash
    private final int num_hash = 25;


    /*************************************************************************
     *  variables that depend or which value is obtained on menu() function  *
     *************************************************************************/
    //option identifier of what user wants to do
    private static int choice;
    //message written by the user to be compared to the guess given by the program
    private static String written_message;
    //number of hash functions to use in counting bloom filter
    private static int num_hash_cbf;
    //size of counting bloom filter
    private static int size_cbf;
    //counting bloom filter
    private static CountingBloomFilter b;
    //map what values where counted by counting bloom filter
    private static TreeSet<String> values_cbf = new TreeSet<>();
    //stochastic counter
    private static Counter hc;
    //probability of increment stochastic counter
    private static double prob_hc = 0;


    private TreeMap<Character, ArrayList<ArrayList<Integer>>> data2_xy = new TreeMap<>();
    private ArrayList<TreeSet<Integer>> data1_xy = new ArrayList<>();

    private int lastX = -1, lastY = -1;
    private int count_read_data = 0;
    private int biggestX = -1, biggestY = -1, smallestX = -1, smallestY = -1;
    private int index = -1;
    private boolean once = true;
    private ArrayList<Area> allAreas = new ArrayList<>();
    private TreeSet<Point> temp = new TreeSet<>();

    //to read the inputs from user's terminal
    private static Scanner input = new Scanner(System.in);

    public static void main(String args[]) {

        //show menu
        menu();
        if(choice == 5)
            b = new CountingBloomFilter(size_cbf, num_hash_cbf);
        System.out.println("\n");

        //add processing library
        PApplet.main("tests.final_test.ReadWords");
    }

    public void settings() {
        size(1700, 900);
    }

    public void setup() {
        background(0);
    }

    public void draw() {
        if (count_read_data == 1) {
            //variable to count execution times
            long time;

            double min_distances[] = new double[26];
            String st;
            File file;
            BufferedReader br;


            file = new File("src/tests/final_test/data/handwritten_data_785.csv");

            for (int num = 0; num < 26; num++) {
                data2_xy.put((char) ((int) 'A' + num), new ArrayList<>());
            }

            //to count time needed to read the file
            time = System.currentTimeMillis();

            if(choice == 6){
                try{
                    System.out.printf("Insira a probabilidade (entre 0 e 1) de o contador estocástico ser incrementado: ");
                    prob_hc = input.nextDouble();

                    if(prob_hc < 0 || prob_hc > 1){
                        System.out.println("Erro!\nA probabilidade deverá ser um número entre 0 e 1!\n");
                        System.exit(1);
                    }
                }catch (java.util.InputMismatchException e){
                    System.out.println("Formato inválido!\nO único formato aceite é double\n");
                    System.exit(1);
                }
            }
            try {
                //initialize stochastic counter
                if(choice == 6)
                    hc = new Counter(prob_hc);
                char last_key = ' ';

                br = new BufferedReader(new FileReader(file));
                while ((st = br.readLine()) != null) {
                    String all_numbers_string[] = st.split(",");
                    char actual_key = (char) ((int) 'A' + Integer.parseInt(all_numbers_string[0]));

                    if(choice == 5){
                        b.insert(Character.toString(actual_key));
                        values_cbf.add(Character.toString(actual_key));
                    }

                    if ((choice != 6 && data2_xy.get(actual_key).size() < max_num_letter) || (choice == 6 && hc.getValue()/prob_hc < max_num_letter)) {
                        data2_xy.get(actual_key).add(new ArrayList<>());
                        for (int n = all_numbers_string.length - 1; n > 0; n--) {
                            int actual_num = Integer.parseInt(all_numbers_string[n]);
                            if (actual_num != 0) {
                                data2_xy.get(actual_key).get(data2_xy.get(actual_key).size() - 1).add((int)(Double.parseDouble(String.format("%d.%d", n % 28, n / 28))*100));
                            }
                        }

                        if(choice == 6){
                            hc.incrementCounter();
                            last_key = actual_key;
                        }
                    }else
                        if(choice == 6 && last_key != actual_key)
                            hc.clearCounter();  //reinitialize stochastic counter
                }
                br.close();
            } catch (IOException e) {
                System.out.println("Erro ao abrir ficheiros");
            }
            if(choice == 2)
                System.out.printf("Ficheiro lido em %f segundos\n", (System.currentTimeMillis() - time)/1000.0);

            StringBuilder r = new StringBuilder();
            for (int i = 0; i < data1_xy.size(); i++) {
                //count time needed to calc each letter similarity
                time = System.currentTimeMillis();

                for (int num = 0; num < data2_xy.size(); num++) {
                    ArrayList<ArrayList<Integer>> data_xy = new ArrayList<>();
                    data_xy.add(new ArrayList<>(data1_xy.get(i)));

                    double min_dist = Double.MAX_VALUE;
                    for (int num2 = 0; num2 < data2_xy.get((char) ((int) 'A' + num)).size(); num2++) {
                        if ((char) ((int) 'A' + num) != 'K' && (char) ((int) 'A' + num) != 'Y' && (char) ((int) 'A' + num) != 'W') {
                            data_xy.add(new ArrayList<>(data2_xy.get((char) ((int) 'A' + num)).get(num2)));
                            Similarity s = Similarity.SimilarityInteger(data_xy);
                            double[][] distance_y = s.distanceInteger(1.1, 2, num_hash);

                            if (distance_y.length > 0)
                                min_dist = Double.min(min_dist, distance_y[0][2]);
                        }
                    }
                    min_distances[num] = min_dist;
                }

                if(choice == 2)
                    System.out.printf("%dª letra decifrada em %f segundos\n", i + 1, (System.currentTimeMillis() - time)/1000.0);

                double max = 0;
                char letter = ' ';
                if(choice == 3)
                    System.out.printf("\nSimilaridades para a %dª letra\n", i + 1);
                for (int num = 0; num < min_distances.length; num++) {
                    char simb = (char) ((int) 'A' + num);
                    if ((1 - min_distances[num]) > max) {
                        max = (1 - min_distances[num]);
                        letter = simb;
                    }

                    if(choice == 3)
                        if(1 - min_distances[num] >= 0)
                            System.out.println(simb + " -> " + (1 - min_distances[num]));
                }
                r.append(letter);
            }

            System.out.println("\n\n\nMensagem decifrada: " + r.toString());

            if(choice == 4){
                System.out.printf("\n\n\nMensagem esperada: ");
                written_message = input.next();

                //count how many letters are equal in two messages
                int number_equal = 0;
                for(int num = 0; num < r.length(); num++)
                    if(r.charAt(num) == Character.toUpperCase(written_message.charAt(num)))
                        number_equal++;
                System.out.println("Média de letras \"acertadas\": " + (double)number_equal/r.length());
            }

            //show number of each letter inserted on counting bloom filter
            if(choice == 5){
                System.out.println("\n\n\n\"Quantidade\" de cada letra armazenada no ficheiro:");
                String values[] = values_cbf.stream().toArray(String[]::new);
                for(int num = 0; num < values.length; num++)
                    System.out.println(values[num] + " -> " + b.count(values[num]));
            }

            noLoop();
        } else {

            drawAllArea();
            if (mousePressed && mouseButton == RIGHT)
                clear();
            if (mousePressed && mouseButton == LEFT) {
                //drawing
                mouseDragge();
                index = mouseI();
                once = true;
            } else if (once) {
                //notDrawing
                once = false;
                if (index != -1)
                    allAreas.get(index).addPixel(temp);
                else if (temp.size() != 0) {
                    Area a = new_area(smallestX, smallestY, biggestX - smallestX, biggestY - smallestY);
                    a.addPixel(temp);
                    allAreas.add(a);
                }
                temp.clear();
                lastY = lastX = -1;
                smallestX = smallestY = biggestX = biggestY = -1;
            }


        }
    }

    /*
     *  menu() is called when program starts
     */
    public static void menu(){
        cycle : while(true){
            System.out.printf("MENU\n" +
                                "1 -> Apresentar apenas a mensagem decifrada\n" +
                                "2 -> Apresentar os tempos de leitura do ficheiro e de cálculo das similaridades de cada letra\n" +
                                "3 -> Apresentar a similaridade de cada letra em relação á correspondente escrita\n" +
                                "4 -> Apresentar média de letras \"acertadas\"\n" +
                                "5 -> Apresentar a \"quantidade\" de cada letra armazenada no ficheiro usando um counting bloom filter\n" +
                                "6 -> Usar um contador estocástico para \"controlar\" o número de vezes que cada letra já foi considerada\n" +
                                "7 -> Sair\n> ");

            try{
                choice = input.nextInt();
                switch(choice){
                    case 1 :
                    case 2 :
                    case 3 :
                    case 4 :
                    case 6 :
                        break cycle;
                    case 5 :
                        System.out.printf("\nTamanho do counting bloom filter:\n> ");
                        size_cbf = input.nextInt();
                        System.out.printf("\nNúmero de hash functions a serem usadas no counting bloom filter:\n> ");
                        num_hash_cbf = input.nextInt();
                        break cycle;
                    case 7 :
                        System.exit(0);
                    default:
                        System.out.println("Opção inválida!\nTente novamente.\n\n");

                }
            }catch (java.util.InputMismatchException e){
                System.out.println("Formato inválido!\nO único formato aceite é inteiro\nTente novamente.\n\n");
                input.next();   //because of the buffer
            }
        }
    }

    public void mouseDragge() {
        if (biggestX == -1 && biggestY == -1 && smallestY == -1 && smallestX == -1) {
            biggestY = smallestY = mouseY;
            biggestX = smallestX = mouseX;
        }
        SetDimensions();
        strokeWeight(2);
        stroke(255);
        if (lastX > 0 && lastY > 0)
            line(mouseX, mouseY, lastX, lastY);
        else
            point(mouseX, mouseY);

        lastX = mouseX;
        lastY = mouseY;
        temp.add(new Point(lastX, lastY));

    }


    public void keyTyped() {
        if ((int) key == 10) {
            for (int i = 0; i < allAreas.size(); i++) {
                data1_xy.add(i, allAreas.get(i).getPixels());
            }
            count_read_data++;
        }
    }

    public Area new_area(int x, int y, int w, int h) {
        int k;
        int v2 = 0, v1 = 0;
        if (w > h) {
            k = w;
            v1 = w - h;
            if (h < 20)
                v1 = 0;
        } else {
            k = h;
            v2 = h - w;
            if (w < 20)
                v2 = 0;
        }
        int p = (int)(k/ change_padding);
        return new Area(x  - v2 / 2 - p / 2, y  - p / 2 - v1 / 2, k + p, k + p, this);
    }

    public void drawAllArea() {

        for (int i = 0; i < allAreas.size(); i++) {
            noFill();
            stroke(255);
            rect(allAreas.get(i).getX(), allAreas.get(i).getY(), allAreas.get(i).getW(), allAreas.get(i).getH());
        }

    }

    public void clear() {
        background(0);
        lastX = -1;
        lastY = -1;
        Area a = new Area(-1, -1, -1, -1, this);
        a.resetID();
        allAreas.clear();
    }

    public void SetDimensions() {
        if (biggestX < mouseX)
            biggestX = mouseX;
        if (biggestY < mouseY)
            biggestY = mouseY;
        if (smallestX > mouseX)
            smallestX = mouseX;
        if (smallestY > mouseY)
            smallestY = mouseY;
    }


    public int mouseI() {
        for (int i = 0; i < allAreas.size(); i++) {
            if (allAreas.get(i).mouseIn(mouseX, mouseY))
                return i;
        }
        return -1;
    }

}