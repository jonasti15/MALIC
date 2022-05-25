package com.malic.musker.api;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
public class ImageMethods {
    public static final String BASE_PATH = "C:/Users/jon.astigarragaa/OneDrive - Mondragon Unibertsitatea/MU/3. Kurtsoa/2. sehilekoa/PBL/MALIC/WebSpring/musker/src/main/resources/static";

    public static void createImgWithBytes(String p) throws IOException {
        String path = BASE_PATH + p;

        HttpHeaders headers = new HttpHeaders();
        //headers.set("path", "C:/Users/jon.astigarragaa/Desktop/pruebaimg" + p);
        headers.set("path", path);

        int [][][] colors = RestController.RESTgetRequestHeaders("/images/get", headers, int[][][].class);

        int w = colors[1].length;
        int h = colors.length;

        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int red = (colors[y][x][0] << 16) & 0x00FF0000;
                int green = (colors[y][x][1] << 8) & 0x0000FF00;
                int blue = colors[y][x][2] & 0x000000FF;
                image.setRGB(x, y, 0xFF000000 | red | green | blue);
            }
        }

        File outputFile = new File(path);
        ImageIO.write(image, "png", outputFile);
    }

    public static void sendPhoto(String p) throws IOException {
        int[][][] colors = null;

        File f = new File(BASE_PATH + p);
        BufferedImage bimg = ImageIO.read(f);
        int w = bimg.getWidth();
        int h = bimg.getHeight();

        int[] dataBuffInt = bimg.getRGB(0, 0, w, h, null, 0, w);

        colors = new int[h][w][3];

        int i = 0;
        int j = 0;
        int k = 0;

        for (Integer in : dataBuffInt) {
            Color c = new Color(in);
            colors[k][j][0] = c.getRed();
            colors[k][j][1] = c.getGreen();
            colors[k][j][2] = c.getBlue();

            j++;

            if (j == w) {
                j = 0;
                k++;
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("path", p);

        RestController.RESTpostRequest("/images/save", headers, colors, Void.class);
    }

    public static boolean imgExists(String path){
        File file = new File(BASE_PATH + path);
        return file.exists();
    }

}
