package customUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

public class CustomSliderUI extends BasicSliderUI {

    private Color trackColor;
    private Color thumbColor;
    private int trackWidth;
    private int thumbDiameter;

    public CustomSliderUI(JSlider b, Color trackColor, int trackWidth, Color thumbColor, int thumbDiameter) {
        super(b);
        this.trackColor = trackColor;
        this.trackWidth = trackWidth;
        this.thumbColor = thumbColor;
        this.thumbDiameter = thumbDiameter;
    }

    @Override
    protected Dimension getThumbSize() {
        return new Dimension(thumbDiameter, thumbDiameter);
    }

    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(trackColor);
        g2d.fillRect(trackRect.x, trackRect.y + (trackRect.height - trackWidth) / 2, trackRect.width, trackWidth);
    }

    @Override
    public void paintThumb(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(thumbColor);
        g2d.fillOval(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height);
    }
}


