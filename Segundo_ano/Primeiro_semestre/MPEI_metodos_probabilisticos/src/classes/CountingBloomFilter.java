package classes;

public class CountingBloomFilter{

    /****************
     *  parameters  *
     ****************/

    int data[];
    int size;
    int k;


    /*****************
     *  constructor  *
     *****************/

    public CountingBloomFilter(int n, int k){
        this.size = n;
        this.data = new int[n];
        this.k = k;
    }


    /*************
     *  methods  *
     *************/

    private static int applyHashFunction(int key){
        return HashFunctions.knuthHash((long)key);
    }

    public void insert(String str) {
        int pos = 0;
        for(int i = 0; i < this.k; i++) {
            pos = hf[i%hf.length].fun(str, this.size);
            for(int num = 1; num < Math.ceil((i + 0.1)/hf.length); num++)
                pos = applyHashFunction(pos);
            this.data[pos] += 1;
        }
    }

    public int count(String str){
        int pos;
        int m = Integer.MAX_VALUE;
        for(int i = 0; i < this.k; i++) {
            pos = hf[i%hf.length].fun(str, this.size);
            for(int num = 1; num < Math.ceil((i + 0.1)/hf.length); num++)
                pos = applyHashFunction(pos);
            if(this.data[pos] < m)
                m = this.data[pos];
        }
        return m;
    }

    public boolean contains(String str){
        if(count(str) == 0)
            return false;
        return true;
    }

    public void delete(String str){
        int pos;
        for(int i = 0; i < this.k; i++) {
            pos = hf[i%hf.length].fun(str, this.size);
            for(int num = 1; num < Math.ceil((i + 0.1)/hf.length); num++)
                pos = applyHashFunction(pos);
            if(this.data[pos] != 0)
                this.data[pos]--;
        }
    }

    interface HashFunction{
        int fun(String str, int size);
    }
    private HashFunction hf[] = new HashFunction[] {
            new HashFunction() {
                public int fun(String str, int size) {
                    return HashFunctions.DJB31MA(str, 317, size);
                }
            },
            new HashFunction(){
                public int fun(String str, int size){
                    return HashFunctions.string2hash(str, "djb2", size);
                }
            },
            new HashFunction(){
                public int fun(String str, int size){
                    return HashFunctions.string2hash(str, "sdbm", size);
                }
            },
            new HashFunction(){
                public int fun(String str, int size){
                    return HashFunctions.hashString(str, size);
                }
            },
            new HashFunction(){
                public int fun(String str, int size){
                    return HashFunctions.hashcode(str, size);
                }
            }
    };

    /*************************
     *  getters and setters  *
     *************************/
    public int[] getData() {
        return this.data;
    }

    public int getSize() {
        return this.size;
    }

    public int getK() {
        return this.k;
    }

}