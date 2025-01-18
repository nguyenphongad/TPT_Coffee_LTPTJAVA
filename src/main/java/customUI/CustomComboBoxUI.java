package customUI;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;

public class CustomComboBoxUI extends BasicComboBoxUI {
	private ImageIcon icon;
    private Color backgroundColor;
    private Border border;

    public CustomComboBoxUI(ImageIcon icon, Color backgroundColor, Border border) {
        this.icon = icon;
        this.backgroundColor = backgroundColor;
        this.border = border;
    }

    @Override
    protected JButton createArrowButton() {
        JButton button = new JButton();
        // Đặt biểu tượng cho nút
        button.setIcon(icon);
        // Đặt màu nền cho nút
        button.setBackground(backgroundColor);
        // Đặt viền cho nút
        button.setBorder(border);
        return button;
    }

    @Override
    protected ComboPopup createPopup() {
        BasicComboPopup popup = new BasicComboPopup(comboBox);
        // Đặt màu nền cho popup
        popup.setBackground(backgroundColor);
        // Đặt viền cho popup
        popup.setBorder(border);
        return popup;
    }
}
