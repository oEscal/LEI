package tests.tests_for_similarity;

import classes.Similarity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestShinglesLSH {

    public static void main(String args[]){
        /****************
         *  parameters  *
         ****************/
        ArrayList<ArrayList<String>> words = new ArrayList<>();
        File file;
        BufferedReader br;
        String st;
        String[] after_split;
        String[] files = {"src/tests/files/pg20142.txt",
                            "src/tests/files/pg21209.txt",
                            "src/tests/files/pg20142.txt"};


        long time = System.currentTimeMillis();

        /***********************************************************
         * Save into a ArrayList all words present in the files    *
         ***********************************************************/
        for(int num = 0; num < files.length; num++)
            try{
                file = new File(files[num]);
                words.add(new ArrayList<>());
                br = new BufferedReader(new FileReader(file));

                while ((st = br.readLine()) != null) {
                    after_split = st.split("");
                    for (int word = 0; word < after_split.length; word++) {
                        words.get(num).add(after_split[word]);
                    }
                }
            }catch(IOException e) {
                System.out.println("Erro ao abrir ficheiro " + files[num]);
            }

        System.out.printf("Ficheiros lidos em %d segundos\n\n", (System.currentTimeMillis() - time)/1000);
        Similarity s = Similarity.SimilarityString(words);

        for(int hash_num = 100; hash_num <= 1000; hash_num += 100){
            double results[][] = s.distanceInteger(0.4, 3, hash_num, 5, 10);
            System.out.println("\n\n\n\t\t\tLSH Distance for " +
                    hash_num + " hash functions:\n> Tempo de execução: " +
                    s.getTime_lsh_distance().get(hash_num)/1000 +
                    " s");
            System.out.println("> Resultados: ");

            for(int num = 0; num < results.length; num++)
                System.out.println("-> " + files[(int)results[num][0]] + " " +
                                            files[(int)results[num][1]] + " " +
                                            results[num][2]);
        }

    }
}
