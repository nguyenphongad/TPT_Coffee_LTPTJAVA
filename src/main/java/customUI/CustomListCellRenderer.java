package customUI;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class CustomListCellRenderer extends DefaultListCellRenderer {
    private Color selectionBackgroundColor;
    private Color backgroundColor;
    private Border border;

    public CustomListCellRenderer(Color selectionBackgroundColor, Color backgroundColor, Border border) {
        this.selectionBackgroundColor = selectionBackgroundColor;
        this.backgroundColor = backgroundColor;
        this.border = border;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (isSelected) {
            label.setBackground(selectionBackgroundColor);
        } else {
            label.setBackground(backgroundColor);
        }
        label.setBorder(border);
        return label;
    }
}
