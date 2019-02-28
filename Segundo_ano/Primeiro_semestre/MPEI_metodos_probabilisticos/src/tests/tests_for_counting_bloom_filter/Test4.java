package tests.tests_for_counting_bloom_filter;

/*************************************
 *  Teste do exercicio 5 do guiao 6  *
 *************************************/

import classes.CountingBloomFilter;
import processing.core.PApplet;
import tests.makeChart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Test4 extends PApplet{

    /****************
     *  parameters  *
     ****************/
    private static CountingBloomFilter b;
    private static makeChart.makeBloomChart chart;

    public static void main(String[] args) {

        /****************
         *  parameters  *
         ****************/
        int n;
        int k = 3;
        ArrayList<String> words = new ArrayList<>();
        File file;
        BufferedReader br;
        String st;
        String[] after_split;
        String[] files = {"src/tests/files/pg20142.txt",
                            "src/tests/files/pg40409.txt"};

        file = new File(files[0]);


        /****************************************************************
         * Save into a ArrayList all words present in the first file    *
         ****************************************************************/
        try{
            br = new BufferedReader(new FileReader(file));

            while ((st = br.readLine()) != null) {
                after_split = st.split("\\P{Alpha}+");
                for (int word = 0; word < after_split.length; word++) {
                    words.add(after_split[word]);
                }
            }
        }catch(IOException e) {
            System.out.println("Erro ao abrir ficheiros");
        }
        /****************
         *  parameters  *
         ****************/
        n = 8*words.size();

        /************************************
         *  initialization of the object    *
         *  "CountingBloomFilter"(cbf)      *
         ************************************/
        b = new CountingBloomFilter(n, k);


        /*******************************************************************
         *  Insertion of all words present in the first file in the cbf    *
         *******************************************************************/
        for(int num = 0; num < words.size(); num++)
            b.insert(words.get(num));


        file = new File(files[1]);

        /*****************************************************************
         * Save into a ArrayList all words present in the second file    *
         *****************************************************************/
        int different_words = 0;
        try{
            br = new BufferedReader(new FileReader(file));

            while ((st = br.readLine()) != null) {
                after_split = st.split("\\s");
                for (int word = 0; word < after_split.length; word++) {
                    if (!b.contains(after_split[word]))
                        different_words++;
                }
            }
        }catch(IOException e) {
            System.out.println("Erro ao abrir ficheiros");
        }

        /************************************************
         *  Printing  the number of words that are in   *
         *  the first file and are not in the second,by *
         *      checking their existence in the cbf     *
         ************************************************/
        System.out.println("Número de palavras de " + files[1] +
                            " que não se encontram em " + files[0] +
                            " --> " + different_words);


        /***************************
         *  Add processing library *
         ***************************/
        PApplet.main("tests.tests_for_counting_bloom_filter.Test4");
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
        noLoop();
    }
}
