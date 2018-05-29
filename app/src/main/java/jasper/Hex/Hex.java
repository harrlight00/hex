package jasper.Hex;

/**
 * Created by HarrC on 11/5/2017.
 * Class defining both playerHexes and gameHexes
 */

class Hex {

    //Instantiations
    private int color; //The color of the Hex due to player choices

    //Default constructor
    Hex(){color = 0;}

    //Returns color value
    int getColor() {return color;}

    //Resets color value
    void reset() {color=0;}

    //Update color by 1 (playerHex method)
    void upColor(){color = (color+1)%4;}

    //Implemented when a neighboring player color changes (gameHex method)
    int changeColor(Hex[][] hexes, int r, int c){
        int color1 = 0,  color2 = -1;
        int dim = hexes.length;
        if(r % 2 == 0) {
            color1 = hexes[r][c-1].getColor();
            color2 = hexes[r][c+1].getColor();}
        else if(c % 2 == 0){
            color1 = hexes[r-1][c].getColor();
            color2 = hexes[r+1][c].getColor();}
        else if(r < dim/2) {
            color1 = hexes[r-1][c-1].getColor();
            color2 = hexes[r+1][c+1].getColor();}
        else if(r > dim/2) {
            color1 = hexes[r-1][c+1].getColor();
            color2 = hexes[r+1][c-1].getColor();}
        color = color1+color2;
        //fix error if color is purple or colors are the same
        if(color1==color2){color = color1;}
        else if(color==3&&(color1!=0&&color2!=0)){color = 6;}
        return color;
    }

}