package classes;

public class Counter{

    /****************
     *  parameters  *
     ****************/

    private int value;
    private double prob;
    private int counter;


    /*****************
     *  constructor  *
     *****************/

    public Counter(double prob){
        if(prob < 0 || prob > 1)
            throw new IllegalArgumentException("Erro!\nProbabilidade inválida");

        this.value = 0;
        this.prob = prob;
        this.counter = 0;
    }

    /*************
     *  methods  *
     *************/

    /*
     *  increment counter's value
     */
    public void incrementCounter(){
        this.counter++;
        if(Math.random() < this.prob)
            this.value++;
    }

    /*
     *  get counter's variance
     */
    public double getVar(){//PERGUNTAR AO STOR
        return this.counter*Math.pow(this.prob, 2);
    }

    /*
     *  get counter's expected value
     */
    public double getMedia() {//PERGUNTAR AO STOR
        return this.counter*this.prob;
    }

    /*
     *  get counter's actual value
     */
    public int getValue(){
        return this.value;
    }

    /*
     *  get probability of increment the counter
     */
    public double getProb(){
        return this.prob;
    }

    /*
     *  reinitialize counter
     */
    public void clearCounter(){
        this.value = 0;
        this.counter = 0;
    }

}