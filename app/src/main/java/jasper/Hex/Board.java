package jasper.Hex;

/**
 * Created by HarrC on 11/5/2017.
 * Array of Hex objects
 */

class Board {

    //Instantiations
    Hex[][] hexes; //Array of Hex objects
    private int dim; //Dimensions of Hex array

    //Constructor
    Board(int dimen) {dim = dimen;}

    //Initiate Hex array
    void initiateArray() {
        int colNum;
        hexes = new Hex[dim][dim];
        for (int r = 0; r < dim; r++) {
            colNum = dim - Math.abs(r - (dim / 2));
            for (int c = 0; c < colNum; c++)
                hexes[r][c] = new Hex();
        }

    }

}