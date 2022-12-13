import image.ImageReader;
import image.ImageWriter;
import image.algorithms.VigenereCipher;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;


public class EncryptImage {
    public static void main(String args[]) throws IOException {
        final String exampleUsage = "java EncryptImage /path/image1.jpg /path/image2.jpg -O /path/output_image.jpg";
        if (args.length < 4) {
            System.out.println("[!] Insufficeint arguments");
            System.out.println("Example: " + exampleUsage);
        }
        else if (!args[args.length-2].equals("-O")){
           System.out.println("Please specifiy the output path using -O");
           System.out.println("Example: " + exampleUsage);
        }

        ImageReader reader = new ImageReader();
        BufferedImage mainImg = null;
        BufferedImage keyImg = null;
        BufferedImage encryptedImage = null;

        String outputPath = args[args.length-1];

        try {
            mainImg = reader.read(args[0]);
            keyImg = reader.read(args[1]);
        } catch (IOException e) {
            System.out.printf("[!] Error occured: %s\n", e.toString());
            System.exit(1);
        }
        String imageFormat = outputPath.split("\\.")[1];
        VigenereCipher cipher = new VigenereCipher(mainImg, keyImg);
        encryptedImage = cipher.encrypt();

        File f = new File(outputPath);
        ImageIO.write(encryptedImage, imageFormat, f);
        System.out.printf("[+] Output save to %s\n", outputPath);
    }
}
