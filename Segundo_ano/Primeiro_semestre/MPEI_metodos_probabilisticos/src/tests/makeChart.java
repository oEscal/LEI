package tests;

import classes.CountingBloomFilter;
import org.gicentre.utils.stat.BarChart;
import org.gicentre.utils.stat.XYChart;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.TreeMap;

@SuppressWarnings("Duplicates")
public class makeChart {
    public static class makeBloomChart {

        private BarChart chart;
        private String chart_info[];
        private int tid;

        private static int id;

        public makeBloomChart() {
            id++;
            this.tid = id;
        }

        /*
         *   setup() function must be used on setup() function of main class
         *
         *   Arguments:
         *      PApplet pa -> PApplet parent
         *      CountingBloomFilter b -> counting bloom filter to be represented
         */
        public void setup(PApplet pa, CountingBloomFilter b) {
            int jumps = 100;
            chart_info = new String[2];
            chart_info[0] = "";
            chart_info[1] = "";

            chart = new BarChart(pa);
            String data_x[] = new String[b.getSize()];
            float data_y[] = new float[b.getSize()];

            if (b.getSize() > 2000)
                jumps = 1000;
            if (b.getSize() > 20000)
                jumps = 10000;
            if (b.getSize() > 200000)
                jumps = 100000;
            if (b.getSize() > 2000000)
                jumps = 1000000;


            for (int num = 0; num < data_x.length; num++) {
                data_y[num] = (float) b.getData()[num];
                data_x[num] = " ";
                if ((num + 1) % jumps == 0)
                    data_x[num] = Integer.toString(num + 1);
            }

            chart.setData(data_y);
            chart.showValueAxis(true);
            chart.showCategoryAxis(true);
            chart.setBarLabels(data_x);
            chart.setBarPadding(2);
            chart.setBarColour(pa.color(255));
        }

        /*
         *   draw() function must be used on draw() function of main class
         *
         *   Arguments:
         *      PApplet pa -> PApplet parent
         *      int x -> x coordinate where graph must be drawn
         *      int y -> y coordinate where graph must be drawn
         *      int s_x -> width of graph
         *      int s_y -> height of graph
         */
        public void draw(PApplet pa, int x, int y, int s_x, int s_y) {
            String id_str = Integer.toString(this.tid);
            if (id == 1)
                id_str = "";

            pa.textSize(30);
            pa.text("Counting Bloom Filter " + id_str, x + 10, y - 30);

            pa.textSize(20);
            pa.text(chart_info[0] + "  " + chart_info[1], x + 10, y);
            chart.draw(x, y, s_x, s_y);
        }

        /*
         *   getChartData() function must be used on mouseMoved() function of main class
         *
         *   Arguments:
         *      PApplet pa -> PApplet parent
         */
        public void getChartData(PApplet pa) {
            PVector pos = new PVector();
            pos.x = pa.mouseX;
            pos.y = pa.mouseY;
            if (chart.getScreenToData(pos) != null) {
                chart_info[0] = Float.toString(Math.round(chart.getScreenToData(pos).x));
                chart_info[1] = Float.toString(Math.round(chart.getScreenToData(pos).y));
            } else {
                chart_info[0] = "";
                chart_info[1] = "";
            }
        }
    }

    public static class makeRunningTimePlot{
        private XYChart plot;
        private String chart_info[];

        /*
         *   setup() function must be used on setup() function of main class
         *
         *   Arguments:
         *      PApplet pa -> PApplet parent
         *      TreeMap<Integer, Double> time -> Map where keys are the number of hash functions and values are the running times form them
         */
        public void setup(PApplet pa, TreeMap<Integer, Double> time) {
            chart_info = new String[2];
            chart_info[0] = "";
            chart_info[1] = "";
            float data_y[] = new float[time.size()];
            float data_x[] = new float[time.size()];

            plot = new XYChart(pa);
            for(int num = 0; num < time.values().toArray().length; num++){
                data_x[num] = (Integer)time.keySet().toArray()[num];
                data_y[num] = ((Double)time.values().toArray()[num]).floatValue()/(float) 1000;
            }

            plot.setData(data_x, data_y);
            plot.showXAxis(true);
            plot.showYAxis(true);
            plot.setXAxisLabel("Number of hash functions");
            plot.setLineWidth(1);
            plot.setLineColour(pa.color(255));
            plot.setPointColour(pa.color(255));
        }

        /*
         *   draw() function must be used on draw() function of main class
         *
         *   Arguments:
         *      PApplet pa -> PApplet parent
         *      int x -> x coordinate where graph must be drawn
         *      int y -> y coordinate where graph must be drawn
         *      int s_x -> width of graph
         *      int s_y -> height of graph
         */
        public void draw(PApplet pa, int x, int y, int s_x, int s_y) {
            pa.textSize(30);
            pa.text("Running time in seconds", x + 10, y - 30);

            pa.textSize(20);
            pa.text(chart_info[0] + "  " + chart_info[1], x + 10, y);
            plot.draw(x, y, s_x, s_y);
        }

        /*
         *   getChartData() function must be used on mouseMoved() function of main class
         *
         *   Arguments:
         *      PApplet pa -> PApplet parent
         */
        public void getChartData(PApplet pa) {
            PVector pos = new PVector();
            pos.x = pa.mouseX;
            pos.y = pa.mouseY;
            if (plot.getScreenToData(pos) != null) {
                chart_info[0] = String.format("%d", (int)Math.round(plot.getScreenToData(pos).x));
                chart_info[1] = String.format("%.3f", plot.getScreenToData(pos).y);
            } else {
                chart_info[0] = "";
                chart_info[1] = "";
            }
        }
    }

    public static class histogram {

        private BarChart chart;
        private String chart_info[];

        public void setup(PApplet pa,float values[]){

            chart = new BarChart(pa);
            chart_info = new String[2];
            chart_info[0] = "";
            chart_info[1] = "";

            String data_x[] = new String[values.length];

            for (int num = 1; num <= values.length; num++)
                data_x[num - 1] = Integer.toString(num);

            chart.setData(values);
            chart.setBarLabels(data_x);
            chart.showValueAxis(true);
            chart.showCategoryAxis(true);
            chart.setBarPadding(1);
            chart.setBarColour(pa.color(255));

        }

        public void draw(PApplet pa, int x, int y, int s_x, int s_y) {
            pa.textSize(30);
            pa.text("k hash function histogram" , x + 10, y - 30); //ver o title!!!!

            pa.textSize(20);
            pa.text(chart_info[0] + "  " + chart_info[1], x + 10, y);
            chart.draw(x, y, s_x, s_y);
        }

        public void getChartData(PApplet pa) {
            PVector pos = new PVector();
            pos.x = pa.mouseX;
            pos.y = pa.mouseY;
            if (chart.getScreenToData(pos) != null) {
                chart_info[0] = Float.toString(Math.round(chart.getScreenToData(pos).x));
                chart_info[1] = Float.toString(Math.round(chart.getScreenToData(pos).y));
            } else {
                chart_info[0] = "";
                chart_info[1] = "";
            }
        }

    }

}
