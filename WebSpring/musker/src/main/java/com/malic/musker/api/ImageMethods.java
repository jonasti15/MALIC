package com.malic.musker.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

@Service
public class ImageMethods {
    @Autowired
    ServletContext servletContext;


    public void createImgWithBytes(String p) throws IOException {
        String[] pathUrl = getPathUrl(p, true);

        HttpHeaders headers = new HttpHeaders();
        headers.set("path", p);

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

        File outputFile = new File(pathUrl[1]);
        String name = outputFile.getName();
        String extension = name.substring(name.lastIndexOf(".") + 1);
        ImageIO.write(image, extension, outputFile);
    }

    public void sendPhoto(String p) throws IOException {
        int[][][] colors = null;
        String[] pathUrl = getPathUrl(p, true);
        File f = new File(pathUrl[1]);
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

    public boolean imgExists(String path){
        String[] pathUrl = getPathUrl(path, true);
        File file = new File(pathUrl[1]);
        return file.exists();
    }

    public String getPath() throws IOException {
        Properties properties = new Properties();
        properties.load(ImageMethods.class.getClassLoader().getResourceAsStream("musker.properties"));

        String location = properties.getProperty("IMG");
        String path = location.substring(1, location.length() -1);
        String result = null;

        switch (properties.getProperty("REMOTE")) {
            case "1":
                result = servletContext.getRealPath(path);
                break;
            default:
                result = path;
                break;
        }
        return result;
    }

    public String[] getPathUrl(String requestUrl, boolean add) {
        String[] rutaUrl = new String[2];
        try {
            rutaUrl[0] = getPath();
            if(add)
                rutaUrl[1] = rutaUrl[0] + requestUrl;
            else
                rutaUrl[1] = rutaUrl[0];
        } catch (Exception e) {
            System.out.println(rutaUrl[0] +" NO es correcto");
        }

        return rutaUrl;
    }

}
