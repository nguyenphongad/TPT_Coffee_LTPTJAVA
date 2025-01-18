package customUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;
import javax.swing.border.AbstractBorder;

public class RoundedBorder extends AbstractBorder {
	private Color color;
    private int gap;
    private int thickness;

    public RoundedBorder(Color color, int gap, int thickness) {
        this.color = color;
        this.gap = gap;
        this.thickness = thickness;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(thickness));
        g2d.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, gap, gap));
        g2d.dispose();
    }
}