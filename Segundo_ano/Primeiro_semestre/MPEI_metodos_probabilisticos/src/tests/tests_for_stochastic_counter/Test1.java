package tests.tests_for_stochastic_counter;

import classes.Counter;
import classes.CountingBloomFilter;
import tests.GenerateRandomStrings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Test1 {

    /****************
     *  parameters  *
     ****************/
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        /****************
         *  parameters  *
         ****************/
        String st;
        BufferedReader input_stream;
        String file = "src/tests/files/pg21209.txt";
        double prob = 0;


        try{
            System.out.printf("Insira a probabilidade (entre 0 e 1) de o contador estocástico ser incrementado: ");
            prob = input.nextDouble();

            if(prob < 0 || prob > 1){
                System.out.println("Erro!\nA probabilidade deverá ser um número entre 0 e 1!\n");
                System.exit(1);
            }
        }catch (java.util.InputMismatchException e){
            System.out.println("Formato inválido!\nO único formato aceite é double\n");
            System.exit(1);
        }


        /**********************************************
         * Read each word of the file and count it    *
         **********************************************/
        int real_counter = 0;
        Counter c = new Counter(prob);
        try {
            input_stream = new BufferedReader(new FileReader(file));
            while ((st = input_stream.readLine()) != null) {
                String after_split[] = st.split("\\P{Alpha}+");
                for (int word = 0; word < after_split.length; word++) {
                    real_counter++;
                    c.incrementCounter();
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao abrir ficheiros");
        }


        System.out.println("\n\nContagem dada pelo contador estocástico: " + (int)(c.getValue()/prob));
        System.out.println("Contagem real: " + real_counter);

        System.out.println("\n\n\nMais informações sobre o contador estocástico:");
        System.out.println("-> Média: " + c.getMedia());
        System.out.println("-> Variância: " + c.getVar());
    }

}
