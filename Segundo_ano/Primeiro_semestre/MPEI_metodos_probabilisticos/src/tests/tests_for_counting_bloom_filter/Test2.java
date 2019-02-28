package tests.tests_for_counting_bloom_filter;

/*************************************
 *  Teste do exercicio 3 do guiao 6  *
 *************************************/

import classes.CountingBloomFilter;
import processing.core.PApplet;
import tests.GenerateRandomStringsPortugues;
import tests.GenerateRandomStrings;
import tests.makeChart;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Test2 extends PApplet {

    /****************
     *  parameters  *
     ****************/
    private static CountingBloomFilter b;
    private static makeChart.makeBloomChart chart1;
    private static makeChart.makeBloomChart chart2;

    public static void main(String[] args) {

        /****************
         *  parameters  *
         ****************/
        int n_words = 1000;
        int n_count_filter = 8000;
        int k = 3;
        /************************************************************
         *  Array of files to be read to evaluate the probability   *
         *      of each letter present in the files                 *
         ************************************************************/
        String[] files = {"src/tests/files/pg21209.txt",
                "src/tests/files/pg26017.txt",
                "src/tests/files/pg40409.txt",
                "src/tests/files/pg20142.txt"};

        /****************
         *  parameters  *
         ****************/
        Map<Character, Float> occurrences = new TreeMap<>();
        int t;
        FileReader input_stream;

        /****************************************************
         * Save into a Map the occurrence of each letter    *
         ****************************************************/
        for (int i = 0; i < files.length; i++) {
            try {
                input_stream = new FileReader(files[i]);
                while ((t = input_stream.read()) != -1) {
                    if (t > 122)
                        continue;
                    if (!(Character.isLetter(t) || Character.isDigit(t)))
                        continue;
                    if (occurrences.containsKey((char) t)) {
                        occurrences.put((char) t, occurrences.get((char) t) + 1);
                        continue;
                    }
                    occurrences.put((char) t, (float) 0);
                }
            } catch (IOException e) {
                System.out.println("Erro ao abrir ficheiros");
            }

        }

        /****************************************************
         *  Transform the map above into a map with the     *
         *  accumulative probabilities of each letter       *
         ****************************************************/
        float sum = 0;
        for (Map.Entry<Character, Float> entry : occurrences.entrySet()) {
            sum += entry.getValue();
            occurrences.put(entry.getKey(), sum);
        }

        for (Map.Entry<Character, Float> entry : occurrences.entrySet())
            occurrences.put(entry.getKey(), occurrences.get(entry.getKey()) / sum);

        /************************************
         *  initialization of the object    *
         *  "CountingBloomFilter"(cbf)      *
         ************************************/
        b = new CountingBloomFilter(n_count_filter, k);

        /*********************************************
         *  Insertion of 1000 words generated with   *
         *      a given probability in the cbf       *
         *********************************************/
        for (int i = 0; i < n_words; i++)
            b.insert(GenerateRandomStringsPortugues.generate(40, 40, occurrences));

        /****************************************************
         *  Verify the existence of 10000 words generated   *
         *                  randomly                        *
         ****************************************************/
        int fp_1 = 0;
        for (int i = 0; i < 10000; i++)
            if (b.contains(GenerateRandomStrings.generate(40, 40)))
                fp_1++;

        /******************************************
         *  Printing the value of false positives *
         ******************************************/
        System.out.println("Número de falsos positivos-->" + fp_1);


        /***************************
         *  Add processing library *
         ***************************/
        PApplet.main("tests.tests_for_counting_bloom_filter.Test2");

    }
    public void settings() {
        size(1000, 500);
    }

    public void setup() {
        chart2 = new makeChart.makeBloomChart();
        chart2.setup(this, b);
    }

    public void draw() {
        background(0);
        fill(120);
        chart2.draw(this, 70, 150, width - 100, height-250);
    }

    public void mouseMoved() {
        chart2.getChartData(this);
    }

}