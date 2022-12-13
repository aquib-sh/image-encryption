package image.algorithms;

import image.utils.RGBOperator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;

public class VigenereCipher {
    private BufferedImage mainImage,equalizedKeyImage;
    private int mainImageWidth, mainImageHeight, keyImageWidth, keyImageHeight;
    RGBOperator imgOp;

    public VigenereCipher(BufferedImage mainImage, BufferedImage keyImage) {
       this.mainImage = mainImage;
       this.mainImageWidth = mainImage.getWidth();
       this.mainImageHeight = mainImage.getHeight();
       this.equalizedKeyImage = this.equalizeImage(keyImage);
       this.imgOp = new RGBOperator(mainImage);
    }

    // Returns a clone of BufferedImage object
    private BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    // Makes keyImage's dimensions equal to the dimensions of mainImage
    // If the dimensions are greater than main then trim,
    // If the dimensions are smaller than use a circular way to add new bytes from the same image
    private BufferedImage equalizeImage(BufferedImage keyImage) {
        BufferedImage changedImage = this.deepCopy(mainImage);
        keyImageWidth = keyImage.getWidth();
        keyImageHeight = keyImage.getHeight();

        int kX, kY;

        for (int x = 0; x < this.mainImageWidth; x++) {
            kX = x % keyImageWidth;
            for (int y = 0; y < this.mainImageHeight; y++) {
                kY = y % keyImageHeight;
                changedImage.setRGB(x, y, keyImage.getRGB(kX, kY));
            }
        }
        return changedImage;
    }

    public BufferedImage encrypt() {
        BufferedImage encryptedImage = this.mainImage;
        int shiftedRed, shiftedGreen, shiftedBlue;

        for (int x = 0; x < this.mainImageWidth; x++) {
            for (int y = 0; y < this.mainImageHeight; y++) {
                Color mpxlColor = new Color(this.mainImage.getRGB(x, y));           // main image pixel
                Color kpxlColor = new Color(this.equalizedKeyImage.getRGB(x, y));   // key image pixel

                shiftedRed = (mpxlColor.getRed() + kpxlColor.getRed()) % 255;
                shiftedGreen = (mpxlColor.getGreen() + kpxlColor.getGreen()) % 255;
                shiftedBlue = (mpxlColor.getBlue() + kpxlColor.getBlue()) % 255;

                Color shiftedPixel = new Color(shiftedRed, shiftedGreen, shiftedBlue);

                encryptedImage.setRGB(x, y, shiftedPixel.getRGB());
            }
        }
        return encryptedImage;
    }
}
