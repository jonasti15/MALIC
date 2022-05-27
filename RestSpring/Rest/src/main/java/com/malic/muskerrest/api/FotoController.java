package com.malic.muskerrest.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping(path = "/images")
public class FotoController {
    public final String BASE_PATH = "C:/Users/jon.astigarragaa/OneDrive - Mondragon Unibertsitatea/MU/3. Kurtsoa/2. sehilekoa/PBL/MALIC/RestSpring/Rest/src/main/resources/static";

    public static String KEY = "path";

    @GetMapping("/get")
    public ResponseEntity<int[][][]> getImageBytes(HttpServletRequest request) throws IOException {
        String path = BASE_PATH + request.getHeader(KEY);

        int[][][] colors = null;
        if (path != null) {
            File f = new File(path);
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
        String path = BASE_PATH + request.getHeader(KEY);
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
        String name = outputFile.getName();
        String extension = name.substring(name.lastIndexOf(".") + 1);
        ImageIO.write(image, extension, outputFile);

        return ResponseEntity.ok("saved");
    }

}
