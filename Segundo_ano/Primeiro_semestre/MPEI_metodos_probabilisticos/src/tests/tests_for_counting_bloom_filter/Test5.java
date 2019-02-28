package tests.tests_for_counting_bloom_filter;

/*************************************
 *  Teste do exercicio 6 do guiao 6  *
 *************************************/

import classes.CountingBloomFilter;
import processing.core.PApplet;
import tests.makeChart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Test5 extends PApplet {

    /****************
     *  parameters  *
     ****************/
    private static CountingBloomFilter b;
    private static makeChart.makeBloomChart chart;
    private static int choice;
    private static String word;
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args){

        /****************
         *  parameters  *
         ****************/
        File file;
        BufferedReader br;
        String st;
        String[] after_split;
        ArrayList<String> words = new ArrayList<>();
        int n;
        int k = 3;

        /********************
         *   Display menu   *
         ************ ******/
        menu();

        file = new File("src/tests/files/pg40409.txt");

        /***********************************************************
         * Save into a ArrayList all words present in the  file    *
         ***********************************************************/
        try{
            br = new BufferedReader(new FileReader(file));

            while ((st = br.readLine()) != null) {
                after_split = st.split("\\P{Alpha}+");
                for (int num = 0; num < after_split.length; num++) {
                    words.add(after_split[num]);
                }
            }
        }catch (IOException e) {
            System.out.println("Erro ao abrir ficheiros");
        }

        n = 8*words.size();

        /************************************
         *  initialization of the object    *
         *  "CountingBloomFilter"(cbf)      *
         ************************************/
        b = new CountingBloomFilter(n, k);

        /*************************************************************
         *  Insertion of all words present in the file in the cbf    *
         *************************************************************/

        for(int num = 0; num < words.size(); num++)
            b.insert(words.get(num));


        TreeSet<String> words2=new TreeSet<>();
        words2.addAll(words);
        ArrayList<String> words3=new ArrayList<>();
        words3.addAll(words2);
        if(choice==1){
            /****************************************************
             *  Give ocurrences of all words present in file    *
             ****************************************************/
            for(int i=0;i<words2.size();i++)
                System.out.println(words3.get(i)+"-->"+b.count(words3.get(i)));
        }
        else{
            /**************************************************
             *  Give ocurrence of one word ,written by user    *
             *************************************************/
            System.out.println(word+"-->"+b.count(word));
        }


        /***************************
         *  Add processing library *
         ***************************/
        PApplet.main("tests.tests_for_counting_bloom_filter.Test5");
    }
    public static void menu() {
        cycle : while(true) {

            System.out.printf("MENU\n" +
                    "1 -> Apresentar as ocorrencias de todas as palavras presentes no ficheiro\n" +
                    "2 -> Apresentar a ocorrencia de uma palavra\n" +
                    "3 -> Sair\n> ");
            try{
                choice = input.nextInt();
                switch(choice){
                    case 1 :
                        break cycle;
                    case 2 :
                        input.nextLine();
                        System.out.print("Insira uma palavra\n> ");
                        word=input.nextLine();
                        break cycle;
                    case 3 :
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
    public void settings() {
        size(1000, 500);
    }

    public void setup() {
        chart = new makeChart.makeBloomChart();
        chart.setup(this, b);
    }

    public void draw() {
        background(0);
        fill(120);
        chart.draw(this, 70, 70, width - 100, height - 100);
    }
}