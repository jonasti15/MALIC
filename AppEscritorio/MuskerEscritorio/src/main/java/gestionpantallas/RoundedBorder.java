package gestionpantallas;

import javax.swing.border.Border;
import java.awt.*;

public class RoundedBorder implements Border {
    private static final Color COLOROSCURO = new Color(118,185,71);
    private int radius;

    public RoundedBorder(int radius) {
        this.radius = radius;
    }


    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        c.setForeground(COLOROSCURO);
    }
}