package jasper.Hex;

/**
 * Created by HarrC on 11/25/2017.
 * Counts how many levels won
 */

class Counter {

    //Instantiations
    private int count; //win counter

    //Constructor
    Counter(int c){
        count = c;
    }

    //Adds one to win counter
    void upCount(){
        count++;
    }

    //Returns win counter
    int getCount(){
        return count;
    }

}
