package com.malic.muskerrest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@RestController
@RequestMapping(path = "/images")
public class FotoController {
    public static String KEY = "path";
    @Autowired
    ServletContext servletContext;

    @GetMapping("/get")
    public ResponseEntity<int[][][]> getImageBytes(HttpServletRequest request) throws IOException {
        String[] pathUrl = getPathUrl(request.getHeader(KEY), true);

        int[][][] colors = null;
        if (pathUrl[1] != null) {
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

        }

        return ResponseEntity.ok(colors);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveImage(HttpServletRequest request,
                          @RequestBody int [][][] colors) throws IOException {

        String[] pathUrl = getPathUrl(request.getHeader(KEY), true);
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

        return ResponseEntity.ok("saved");
    }

    public String getPath() throws IOException {
        Properties properties = new Properties();
        properties.load(FotoController.class.getClassLoader().getResourceAsStream("musker.properties"));

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
            if(add){
                requestUrl = requestUrl.replaceAll("\\s", "_");
                rutaUrl[1] = rutaUrl[0] + requestUrl;
            }
            else
                rutaUrl[1] = rutaUrl[0];
        } catch (Exception e) {
            System.out.println(rutaUrl[0] +" NO es correcto");
        }

        return rutaUrl;
    }

}
