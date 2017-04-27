package ull.alu0100892833.pai.parabolic_shot.view;

import javax.swing.JPanel;

import ull.alu0100892833.pai.parabolic_shot.ParabolicShot;

public class ProjectilePanel extends JPanel {
	private static final long serialVersionUID = 6509639337136291378L;
	private static final int DEFAULT_X_VALUES = 500;
	private static final int DEFAULT_Y_VALUES = 250;

	private ParabolicShot data;
	private int xValues, yValues;
	
	public ProjectilePanel(ParabolicShot shot) {
		this.data = shot;
		this.xValues = DEFAULT_X_VALUES;
		this.yValues = DEFAULT_Y_VALUES;
	}

	public ParabolicShot getData() {
		return data;
	}

	public void setData(ParabolicShot data) {
		this.data = data;
	}

	public int getxValues() {
		return xValues;
	}

	public void setxValues(int xValues) {
		this.xValues = xValues;
	}

	public int getyValues() {
		return yValues;
	}

	public void setyValues(int yValues) {
		this.yValues = yValues;
	}
	
	

}
