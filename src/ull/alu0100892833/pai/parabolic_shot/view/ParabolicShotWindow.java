package ull.alu0100892833.pai.parabolic_shot.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class ParabolicShotWindow extends JFrame {
	private static final long serialVersionUID = -1729082629819255742L;
	private static final int WIDTH_PROPORTION = 2;
	private static final int HEIGHT_PROPORTION = 2;
	
	private ParabolicShotPanel mainPanel;
	
	public ParabolicShotWindow() {
		Dimension fullScreen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension size = new Dimension((int) fullScreen.getWidth() / WIDTH_PROPORTION, (int) fullScreen.getHeight() / HEIGHT_PROPORTION);
		mainPanel = new ParabolicShotPanel(size);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(mainPanel);
		setSize(size);
	}

}
