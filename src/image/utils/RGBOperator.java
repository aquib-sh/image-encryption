package image.utils;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.image.DirectColorModel;
import java.util.HashMap;

public class RGBOperator {
    private BufferedImage operatingImage;
    public RGBOperator(BufferedImage img) {
       operatingImage = img;
    }
    public void incrementAtPoint(int x, int y, HashMap<String, Integer> values) {
        Color pxlColor, shiftedPxlColor;

        pxlColor = new Color(operatingImage.getRGB(x, y));
        int shiftedRedValue = pxlColor.getRed() + values.get("Red");
        int shiftedGreenValue = pxlColor.getGreen() + values.get("Green");
        int shiftedBlueValue = pxlColor.getBlue() + values.get("Blue");

        shiftedPxlColor = new Color(shiftedRedValue, shiftedGreenValue, shiftedBlueValue);

        operatingImage.setRGB(x, y, shiftedPxlColor.getRGB());
    }

    public static boolean areEqual(BufferedImage img1, BufferedImage img2) {
        if (img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight()) return false;

        for (int i = 0; i < img1.getWidth(); i++) {
            for (int j = 0; j < img1.getHeight(); j++) {
                if (img1.getRGB(i, j) != img2.getRGB(i, j)) return false;
            }
        }
        return true;
    }
    public BufferedImage getImage() {
        return operatingImage;
    }
}
