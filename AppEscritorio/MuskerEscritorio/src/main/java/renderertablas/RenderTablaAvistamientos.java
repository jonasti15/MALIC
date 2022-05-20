package renderertablas;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class RenderTablaAvistamientos extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
                                                   int column) {

        JLabel componente = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        table.setRowHeight(30);
        switch (column) {
            case 0:
                componente.setHorizontalAlignment(CENTER);
                componente.setFont(new Font("Serif", Font.PLAIN, 20));
            case 1:
                componente.setHorizontalAlignment(CENTER);
                componente.setFont(new Font("Serif", Font.PLAIN, 20));
            case 2:
                componente.setHorizontalAlignment(CENTER);
                componente.setFont(new Font("Serif", Font.PLAIN, 20));
            case 3:
                componente.setHorizontalAlignment(CENTER);
                componente.setFont(new Font("Serif", Font.PLAIN, 20));
            case 4:
                componente.setHorizontalAlignment(CENTER);
                componente.setFont(new Font("Serif", Font.PLAIN, 20));
            case 5:
                componente.setHorizontalAlignment(CENTER);
                componente.setFont(new Font("Serif", Font.PLAIN, 20));
        }

        return componente;
    }
}
