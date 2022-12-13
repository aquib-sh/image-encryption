package image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageReader {
    public BufferedImage read(String imgPath) throws IOException {
        BufferedImage img = null;
        File f = null;

        try {
            f = new File(imgPath);
            if (! f.exists()) {
                System.out.printf("[!] %s does not exist", imgPath);
                System.exit(1);
            }
            img = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println("[!] Unable to read " + imgPath);
            System.out.printf("[!] Error Occured: %s\n%s", e.toString(), e.getStackTrace().toString());
        }
        return img;
    }
}
