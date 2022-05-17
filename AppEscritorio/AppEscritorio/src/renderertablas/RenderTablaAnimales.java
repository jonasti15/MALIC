package renderertablas;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class RenderTablaAnimales extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
                                                   int column) {

        JLabel componente = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        switch (column) {
            case 0:
                componente.setHorizontalAlignment(CENTER);
            case 1:
                componente.setHorizontalAlignment(CENTER);
            case 2:
                componente.setHorizontalAlignment(CENTER);
            case 3:
                componente.setHorizontalAlignment(CENTER);
        }

        return componente;
    }
}
