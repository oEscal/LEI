package classes;

public class HashFunctions{

    /*************************************
     *  Function adapted from TP slides  *
     *************************************/

    static public int DJB31MA(String str, int seed, int size){
        int hash = seed;
        for(int num = 0; num < str.length(); num++)
            hash = (31*hash + (int)str.charAt(num))%size;
        return hash;
    }


    /*******************************************************************************
     *  Adapted from function writted by D.Kroon University of Twente (June 2010)  *
     *  Website(implemented in c) --> http://www.cse.yorku.ca/~oz/hash.html        *
     *******************************************************************************/

    static public  int  string2hash(String str, String type, int size){
        long hash=0;
        switch(type ) {
            case "djb2":
                hash=5381;
                for(int i=0;i<str.length();i++){
                    hash=Math.floorMod(hash*33+(int)str.charAt(i),(long)Math.pow(2, 32)-1);
                }
                break;
            case "sdbm":
                for(int i=0;i<str.length();i++){
                    hash=Math.floorMod(hash*65599+(int)str.charAt(i),(long)Math.pow(2, 32)-1);
                }
                break;
            default:
                throw new IllegalArgumentException("Errro!\nTipo desconhecido");
        }
        return (int)(hash%(long)size);
    }


    /**************************************************************
     *  Function adapted from pdf hashStringP2_2016_2017 of PL05  *
     **************************************************************/

    static public int hashString(String str, int size){
        int len = str.length();
        long hash = 0;
        char[] buffer = str.toCharArray();

        int c = 0;
        for(int i = 0; i < len; i++){
            c = buffer[i] + 33;
            hash = ((hash<<33) + (hash>>28) + c);
        }

        hash = hash % size;
        return (int) (hash >= 0 ? hash : hash + size);
    }


    /******************************************************************************************************
     *  Function adapted from https://docs.oracle.com/javase/6/docs/api/java/lang/String.html#hashCode()  *
     ******************************************************************************************************/

    static public int hashcode(String str, int size){
        int n = str.length();
        int hash = 0;
        for(int num = 0; num < str.length(); num++)
            hash += ((int)str.charAt(num)*Math.pow(31, n - num - 1))%size;
        return hash%size;
    }


    /**********************************************************
     *  Function based on Knuth's multiplicative hash method  *
     **********************************************************/

    public static int knuthHash(long key){
        key *= 2654435761l;
        return (int)(key >> 32);
    }

    public static int universalHash(int key, int a, int b){
        return (a*key + b)%7187;
    }

    public static int universalHashString(String k, int a, int b, int r[]) {
        long key = 5381;
        for (int i = 0; i < k.length(); i++) {
            key += (r[i] + k.charAt(i));
        }
        return (int)((a * key + b)% 961748941);
    }
}