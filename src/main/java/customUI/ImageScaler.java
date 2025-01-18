package customUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ImageScaler {
    private String filePath;
    private int width;
    private int height;

    public ImageScaler(String filePath, int width, int height) {
        this.filePath = filePath;
        this.width = width;
        this.height = height;
    }

    public ImageIcon getScaledImageIcon() {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(filePath));
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }
    public ImageIcon getScaledImageAvatar() {
	    File file = new File(filePath);
    	if(file.exists()) {
            ImageIcon imageIcon = new ImageIcon(filePath);
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(newimg);
    	}else {
    		filePath = "";
    		System.out.println("loi anh");
    		return getScaledImageIcon();
    	}
    }
}