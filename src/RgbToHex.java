public class RgbToHex {
    private String rgbToHex(int red, int green, int blue) {
        String redHex = Integer.toHexString(red);
        String greenHex = Integer.toHexString(green);
        String blueHex = Integer.toHexString(blue);

        redHex = redHex.toUpperCase();
        greenHex = greenHex.toUpperCase();
        blueHex = blueHex.toUpperCase();

        if(redHex.length() == 1) {
            redHex = "0" + redHex;
        }
        if(greenHex.length() == 1) {
            greenHex = "0" + greenHex;
        }
        if(blueHex.length() == 1) {
            blueHex = "0" + blueHex;
        }

        return redHex + greenHex + blueHex;
    }

    public static void main(String[] args) {
        RgbToHex inst = new RgbToHex();
        System.out.println(inst.rgbToHex(255, 64, 0));
    }
}
