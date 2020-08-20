import java.util.Scanner;

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

    // Returns true if the given color is unaffected by Tritanopia
    private boolean idTritanopia(String color){
        int blue = getBlue(color);
        int red = getRed(color);
        int green = getGreen(color);
        if(blue > 0) {
            if (red < 256 && red > 230) {
                if (green < 256 && green > 220) {
                    return false;
                }
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

    private String printResults(){
        String res = "This palette ";
        if(protanopia && deuteranopia && tritanopia){
            res += "works for all types of color blindness.";
        }
        else{
            res += "does not work for ";

            if (!protanopia){
                res += "Protanopia ";
            }

            if (!deuteranopia){
                res += "Deuteranopia "; // not sure if we should do something special with the spacing
            }

            if (!tritanopia){
                res += "Tritanopia";
            }
        }
        return res;
    }

    // If we want to add more, could also include a method to convert from three integers (0 - 255) into a hex string
    public static void main(String[] args) {
        ColorBlindness inst = new ColorBlindness();
        Scanner input = new Scanner(System.in);
        String palette = input.nextLine();
        inst.identifyPalette(palette);
        System.out.println(inst.printResults());
    }
}