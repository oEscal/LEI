package tests.tests_for_counting_bloom_filter;

/*************************************
 *  Teste do exercicio 2 do guiao 6  *
 *************************************/

import classes.CountingBloomFilter;
import tests.makeChart;

import processing.core.PApplet;

public class Test1 extends PApplet{

    /****************
     *  parameters  *
     ****************/
    private static CountingBloomFilter b;
    private static makeChart.makeBloomChart chart;

    public static void main(String[] args) {

        /****************
         *  parameters  *
         ****************/
        String[] country_list = {"Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Anguilla", "Antigua &amp; Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia &amp; Herzegovina", "Botswana", "Brazil", "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Cape Verde", "Cayman Islands", "Chad", "Chile", "China", "Colombia", "Congo", "Cook Islands", "Costa Rica", "Cote D Ivoire", "Croatia", "Cruise Ship", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Estonia", "Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji", "Finland", "France", "French Polynesia", "French West Indies", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guam", "Guatemala", "Guernsey", "Guinea", "Guinea Bissau", "Guyana", "Haiti", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Jamaica", "Japan", "Jersey", "Jordan", "Kazakhstan", "Kenya", "Kuwait", "Kyrgyz Republic", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Mauritania", "Mauritius", "Mexico", "Moldova", "Monaco", "Mongolia", "Montenegro", "Montserrat", "Morocco", "Mozambique", "Namibia", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Norway", "Oman", "Pakistan", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russia", "Rwanda", "Saint Pierre &amp; Miquelon", "Samoa", "San Marino", "Satellite", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "South Africa", "South Korea", "Spain", "Sri Lanka", "St Kitts &amp; Nevis", "St Lucia", "St Vincent", "St. Lucia", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor L'Este", "Togo", "Tonga", "Trinidad &amp; Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks &amp; Caicos", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "Uruguay", "Uzbekistan", "Venezuela", "Vietnam", "Virgin Islands (US)", "Yemen", "Zambia", "Zimbabwe"};
        int array_length = country_list.length;
        int n = 8*country_list.length/2;
        int i;
        int k = 3;

        /************************************
         *  initialization of the object    *
         *  "CountingBloomFilter"(cbf)      *
         ************************************/
        b = new CountingBloomFilter(n, k);


        /********************************************
         *  Insertion the first half of the array   *
         *      "country_list" in the cbf           *
         ********************************************/
        for (i = 0; i <= array_length / 2; i++)
            b.insert(country_list[i]);

        /***************************************
         *  Verify the existence of the second *
         *   half of the array in the cbf      *
         ***************************************/
        int fp = 0;
        for (; i < array_length; i++)
            if (b.contains(country_list[i]))
                fp++;

        /******************************************
         *  Printing the value of false positives *
         ******************************************/
        System.out.println("Número de falsos positivos --> " + fp);


        /***************************
         *  Add processing library *
         ***************************/
        PApplet.main("tests.tests_for_counting_bloom_filter.Test1");
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

    public void mouseMoved() {
        chart.getChartData(this);
    }
}
