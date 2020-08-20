public class ColorBlindness {
    // true = works for color blindness
    private boolean protanopia = true;
    private boolean deuteranopia = true;
    private boolean tritanopia = true;

    // Extract the red component
    private int getRed(String hex) {
        String sub = hex.substring(0, 2);
        return Integer.parseInt(sub, 16);
    }

    // Extract the green component
    private int getGreen(String hex) {
        String sub = hex.substring(2, 4);
        return Integer.parseInt(sub, 16);
    }

    // Extract the blue component
    private int getBlue(String hex) {
        String sub = hex.substring(4);
        return Integer.parseInt(sub, 16);
    }

    // Returns true if the given color is unaffected by Deuteranopia
    private boolean idDeuteranopia(String color){
        int green = getGreen(color);

        // Would it be better to use some threshhold here rather than checking == 0?
        // Could use green < 64, although this is still a simplification
        return green == 0;
    }

    // Returns true if the given color is unaffected by Protanopia
    private boolean idProtanopia(String color){
        int red = getRed(color);
        return red == 0;
    }

    // need to figure out the yellow hex values
    // Returns true if the given color is unaffected by Tritanopia
    private boolean idTritanopia(String color){
        int blue = getBlue(color);
        int red = getRed(color);
        int green = getGreen(color);

        if(red <256 && red >230){
            if(green < 256 && green >220){
                    return false;
            }
        }
        return true;
    }

    // Takes a series of colors and prints how the palette works for different types of colorblindness
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

            // Should we include this? Could leave this out to simplify things.
            // Could simplify output to "This palette does not work for Protanopia Deuteranopia Tritanopia"
            boolean comma = false;

            if (!protanopia){
                res += "Protanopia";
                comma = true;
            }

            if (!deuteranopia){
                if(comma){
                    res += "Deuteranopia";
                }
                else{
                    res += ", Deuteranopia";
                }
                comma = true;
            }

            if (!tritanopia){
                if(comma){
                    res += "Tritanopia";
                }
                else{
                    res += ", Tritanopia";
                }
            }
        }
        System.out.println(res); // could also return it, not sure which is best
    }

    // If we want to add more, could also include a method to convert from three integers (0 - 255) into a hex string
    public static void main(String[] args) {
        ColorBlindness inst = new ColorBlindness();
        inst.identifyPalette("0FFFF0 1FFFF1 2FFFF2 3FFFF3");
        inst.printResults();
    }
}