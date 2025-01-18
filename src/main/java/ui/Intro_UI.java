package ui;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import util.EntityManagerFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Intro_UI extends JFrame {
	private boolean isShowPageLogin = false;
	ImageIcon appIcon = new ImageIcon("res/image/icon_logo.PNG");

	public Intro_UI() {
		try {
			setSize(900, 450);
			setLocationRelativeTo(null);
			setUndecorated(true);
			setIconImage(appIcon.getImage());
			getContentPane().setLayout(new BorderLayout(0, 0));

			ImageIcon imageIntro = new ImageIcon("res/image/logo_VPTCoffee.PNG");
			getContentPane().setLayout(new BorderLayout(0, 0));
			JLabel lblImage = new JLabel(imageIntro);

			JPanel pnlImage = new JPanel();

			pnlImage.add(lblImage);
			getContentPane().add(pnlImage, BorderLayout.CENTER);

			JPanel pnlLoading = new JPanel();
			pnlLoading.setLayout(new BorderLayout(0, 0));
			getContentPane().add(pnlLoading, BorderLayout.SOUTH);

			// ham loading
			JProgressBar pgbLoadingBar = new JProgressBar();
			pgbLoadingBar.setStringPainted(true);
			pgbLoadingBar.setForeground(Color.decode("#B16E5C"));
			pgbLoadingBar.setBackground(Color.decode("#dbdbdb"));
			pgbLoadingBar.setBorder(null);

			Timer timer = new Timer(10, new ActionListener() {
				int progress = 0;

				@Override
				public void actionPerformed(ActionEvent e) {
					if (progress == 30)
						if (progress < 60) {
							progress++;
							pgbLoadingBar.setValue(progress);
						} else {
							((Timer) e.getSource()).stop();
						}
				}
			});

			timer.setInitialDelay(0);
			timer.start();

			Timer timer2 = new Timer(2000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					pgbLoadingBar.setValue(100);
				}
			});

			timer2.setRepeats(false);
			timer2.start();

			pnlLoading.add(pgbLoadingBar, BorderLayout.CENTER);

			OpenPageLogin();

			setVisible(true);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	// ham mo ui login
	private void OpenPageLogin() {

		Timer timeClosePage = new Timer(2200, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

				if (!isShowPageLogin) {
					Login_UI loginUI = new Login_UI();
					loginUI.setVisible(true);
					isShowPageLogin = true;
				}
			}
		});
		timeClosePage.start();

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new Intro_UI();
		});
	}
}
