public class ColorBlindness {
    // true = works for color blindness
    private boolean protanopia = true;
    private boolean deuteranopia = true;
    private boolean tritanopia = true;

    private int getRed(String hex) {
        String sub = hex.substring(0, 2);
        return Integer.parseInt(sub, 16);
    }

    private int getGreen(String hex) {
        String sub = hex.substring(2, 4);
        return Integer.parseInt(sub, 16);
    }

    private int getBlue(String hex) {
        String sub = hex.substring(4);
        return Integer.parseInt(sub, 16);
    }

    private boolean idDeuteranopia(String color){
        int green = getGreen(color);
        return green == 0;
    }

    private boolean idProtanopia(String color){
        int red = getRed(color);
        return red == 0;
    }
    // need to figure out the yellow hex values
    private boolean idTritanopia(String color){
        return false;
    }

    private void identifyPalette(String palette) {
        int start = 0;
        for (int i = 6; i <= palette.length(); i += 7) {

            String color = palette.substring(start, i);

            if (protanopia)
                protanopia = idProtanopia(color);

            if (deuteranopia)
                deuteranopia = idDeuteranopia(color);

            if (tritanopia)
                tritanopia = idTritanopia(color);

            start = i + 1;
        }
    }

    private void printResults(){
        String res = "This palette ";
        if(protanopia && deuteranopia && tritanopia){
            res += "works for all types of color blindness.";
        }
        else{
            res += "does not work for ";
            int comma = 0;
            if (!protanopia){
                res += "Protanopia";
                comma++;
            }
            if (!deuteranopia && comma < 1 ){
                res += "Deuteranopia";
                comma++;
            }
            else if (!deuteranopia && comma > 0){
                res += ", Deuteranopia";
                comma++;
            }
            if (!tritanopia && comma < 1 ){
                res += "Tritanopia";
                comma++;
            }
            else if (!tritanopia && comma > 0){
                res += ", Tritanopia";
                comma++;
            }
        }
        System.out.println(res); // could also return it, not sure which is best
    }

    public static void main(String[] args) {
        ColorBlindness inst = new ColorBlindness();
        inst.identifyPalette("0FFFF0 1FFFF1 2FFFF2 3FFFF3");
        inst.printResults();
    }
}