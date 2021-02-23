package demo.nut5g.demo;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class test {
    public static void main(String[] args) throws Exception {
        File file = new File("E:\\result.png");
        BufferedImage read = ImageIO.read(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(read, "jpg", baos);
        byte[] bytes = baos.toByteArray();
        System.out.println(bytes);
    }
}
