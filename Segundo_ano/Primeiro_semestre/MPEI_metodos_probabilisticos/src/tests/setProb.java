package tests;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
@SuppressWarnings("Duplicates")
public class setProb {

    public static TreeMap<Character,Float> setCumulativeProb(String files[]){
        TreeMap<Character,Float> cumulativeProb=new TreeMap<>();
        int t;
        FileReader input_stream;

        for (int i = 0; i < files.length; i++) {
            try {
                input_stream = new FileReader(files[i]);
                while ((t = input_stream.read()) != -1) {
                    if (t > 122)
                        continue;
                    if (!(Character.isLetter(t) || Character.isDigit(t)))
                        continue;
                    if (cumulativeProb.containsKey((char) t)) {
                        cumulativeProb.put((char) t, cumulativeProb.get((char) t) + 1);
                        continue;
                    }
                    cumulativeProb.put((char) t, (float) 0);
                }
            } catch (IOException e) {
                System.out.println("Erro ao abrir ficheiros");
            }

        }

        float sum = 0;
        for (Map.Entry<Character, Float> entry : cumulativeProb.entrySet()) {
            sum += entry.getValue();
            cumulativeProb.put(entry.getKey(), sum);
        }

        for (Map.Entry<Character, Float> entry : cumulativeProb.entrySet())
            cumulativeProb.put(entry.getKey(), cumulativeProb.get(entry.getKey()) / sum);

        return cumulativeProb;
    }


}
