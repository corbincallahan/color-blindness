public class ColorBlindness {
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

    // Returns true if a person with deuteranomaly might have trouble distinguishing between the given colors
    private boolean deuteranomaly(String color1, String color2) {
        int blue1 = getBlue(color1);

        int blue2 = getBlue(color2);

        return Math.abs(blue1 - blue2) < 20;
    }

    public static void main(String[] args) {
        ColorBlindness inst = new ColorBlindness();
        System.out.println(inst.deuteranomaly("FFFFFF", "FFFFFF"));
    }
}