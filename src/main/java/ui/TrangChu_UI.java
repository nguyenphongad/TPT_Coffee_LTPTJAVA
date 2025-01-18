package ui;

import javax.swing.JPanel;

import customUI.ImageScaler;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class TrangChu_UI extends JPanel{
	public TrangChu_UI() {
		
		setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageScaler("/image/bgr_trangchu.PNG", 1180, 800).getScaledImageIcon());
		add(lblNewLabel, BorderLayout.CENTER);
		
		
	}
}
