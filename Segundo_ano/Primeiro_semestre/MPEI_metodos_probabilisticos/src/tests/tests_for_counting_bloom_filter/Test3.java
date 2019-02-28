package tests.tests_for_counting_bloom_filter;

/*************************************
 *  Teste do exercicio 4 do guiao 6  *
 *************************************/

import classes.CountingBloomFilter;
import processing.core.PApplet;
import tests.GenerateRandomStrings;
import tests.makeChart;

public class Test3 extends PApplet {

    /****************
     *  parameters  *
     ****************/
    private static CountingBloomFilter b;
    private static makeChart.histogram chart;
    private static float values[];

    public static void main(String args[]) {

        /****************
         *  parameters  *
         ****************/
        int k_max = 15;
        values = new float[k_max];
        int n_words = 1000;
        int n_count_filter = 8 * n_words;


        for (int num = 1; num <= k_max; num++) {

            /************************************
             *  initialization of the object    *
             *  "CountingBloomFilter"(cbf)      *
             ************************************/
            b = new CountingBloomFilter(n_count_filter, num);


            /************************************************
             *  Insertion of 1000 words generated randomly  *
             *               in the cbf                     *
             ************************************************/
            for (int i = 0; i < n_words; i++)
                b.insert(GenerateRandomStrings.generate(40, 40));

            /****************************************************
             *  Save in a array the existence of 10000          *
             *          words generated randomly                *
             ****************************************************/
            int fp = 0;
            for (int i = 0; i < 10000; i++) {
                if (b.contains(GenerateRandomStrings.generate(40, 40)))
                    fp++;
            }

            values[num-1] = fp;

        }


        /***************************
         *  Add processing library *
         ***************************/
        PApplet.main("tests.tests_for_counting_bloom_filter.Test3");

    }

    public void settings() {
        size(1000, 500);
    }

    public void setup() {
        chart = new makeChart.histogram();
        chart.setup(this, values);
    }

    public void draw() {
        background(0);
        fill(120);
        chart.draw(this, 70, 70, width - 100, (height - 100));
    }

    public void mouseMoved() {
        chart.getChartData(this);
    }
}
