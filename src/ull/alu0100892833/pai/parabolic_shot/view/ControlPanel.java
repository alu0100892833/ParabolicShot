package ull.alu0100892833.pai.parabolic_shot.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;


public class ControlPanel extends JPanel {
	private static final long serialVersionUID = 8086868588136313121L;
	private static final int N_BUTTONS = 3;
	private static final int N_SLIDERS = 3;
	private static final int N_CHECK_BOXES = 3;
	private static final int BUTTON_COLUMNS = 1;
	private static final int SLIDERS_COLUMNS = 1;
	private static final int CHECK_BOXES_COLUMNS = 1;
	private static final int MIN_INITIAL_SPEED = 10;
	private static final int MAX_INITIAL_SPEED = 50;
	public static final int INITIAL_SPEED = 25;
	private static final int MIN_INITIAL_ANGLE = 5;
	private static final int MAX_INITIAL_ANGLE = 88;
	public static final int INITIAL_ANGLE = 45;
	public static final int INITIAL_HEIGHT = 0;
	private static final int MIN_INITIAL_HEIGHT = 0;
	private static final int MAX_INITIAL_HEIGHT = 30;
	private static final int TEXT_GAP = 9;
	private static final int TEXT_LATERAL_GAP = 0;
	
	JButton throwButton, pauseButton, resetButton; 
	JSlider speedSlider, angleSlider, heightSlider;
	JCheckBox showSpeed, showTrajectory, showPositionVector;
	
	public ControlPanel() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		addButtons();
		addSliders();
		addCheckBoxes();
	}

	private void addButtons() {
		this.throwButton = new JButton("THROW");
		this.pauseButton = new JButton("PAUSE");
		this.resetButton = new JButton("RESET");
		
		JPanel buttonsPanel = new JPanel(new GridLayout(N_BUTTONS, BUTTON_COLUMNS));
		buttonsPanel.add(throwButton);
		buttonsPanel.add(pauseButton);
		buttonsPanel.add(resetButton);
		
		add(buttonsPanel);
	}
	
	private void addSliders() {
		this.speedSlider = new JSlider(JSlider.HORIZONTAL, MIN_INITIAL_SPEED, MAX_INITIAL_SPEED, INITIAL_SPEED);
		this.angleSlider = new JSlider(JSlider.HORIZONTAL, MIN_INITIAL_ANGLE, MAX_INITIAL_ANGLE, INITIAL_ANGLE);
		this.heightSlider = new JSlider(JSlider.HORIZONTAL, MIN_INITIAL_HEIGHT, MAX_INITIAL_HEIGHT, INITIAL_HEIGHT);
		
		JPanel slidersPanel = new JPanel(new GridLayout(N_SLIDERS, SLIDERS_COLUMNS));
		slidersPanel.add(speedSlider);
		slidersPanel.add(angleSlider);
		slidersPanel.add(heightSlider);
		
		JPanel infoPanel = new JPanel(new GridLayout(N_SLIDERS, SLIDERS_COLUMNS, TEXT_LATERAL_GAP, TEXT_GAP));
		infoPanel.add(new JLabel("SPEED"));
		infoPanel.add(new JLabel("ANGLE"));
		infoPanel.add(new JLabel("HEIGHT"));
		
		add(slidersPanel);
		add(infoPanel);
	}
	
	private void addCheckBoxes() {
		this.showSpeed = new JCheckBox("SHOW SPEED");
		this.showPositionVector = new JCheckBox("SHOW POSITION VECTOR");
		this.showTrajectory = new JCheckBox("SHOW TRAJECTORY");
		
		showTrajectory.setSelected(true);
		
		JPanel boxesPanel = new JPanel(new GridLayout(N_CHECK_BOXES, CHECK_BOXES_COLUMNS));
		boxesPanel.add(showSpeed);
		boxesPanel.add(showTrajectory);
		boxesPanel.add(showPositionVector);
		
		add(boxesPanel);
	}
	
	public void setListeners(ActionListener al, ChangeListener cl, ItemListener il) {
		addActionListener(al);
		addChangeListener(cl);
		addItemListener(il);
	}
	
	public void addActionListener(ActionListener al) {
		throwButton.addActionListener(al);
		pauseButton.addActionListener(al);
		resetButton.addActionListener(al);
	}
	
	private void addChangeListener(ChangeListener cl) {
		speedSlider.addChangeListener(cl);
		angleSlider.addChangeListener(cl);
		heightSlider.addChangeListener(cl);
	}
	
	private void addItemListener(ItemListener il) {
		showSpeed.addItemListener(il);
		showPositionVector.addItemListener(il);
		showTrajectory.addItemListener(il);
	}

	
	
	
	
	// GETTERS AND SETTERS
	
	
	public JButton getThrowButton() {
		return throwButton;
	}

	public void setThrowButton(JButton throwButton) {
		this.throwButton = throwButton;
	}

	public JButton getPauseButton() {
		return pauseButton;
	}

	public void setPauseButton(JButton pauseButton) {
		this.pauseButton = pauseButton;
	}

	public JButton getResetButton() {
		return resetButton;
	}

	public void setResetButton(JButton resetButton) {
		this.resetButton = resetButton;
	}

	public JSlider getSpeedSlider() {
		return speedSlider;
	}

	public void setSpeedSlider(JSlider speedSlider) {
		this.speedSlider = speedSlider;
	}

	public JSlider getAngleSlider() {
		return angleSlider;
	}

	public void setAngleSlider(JSlider angleSlider) {
		this.angleSlider = angleSlider;
	}

	public JSlider getHeightSlider() {
		return heightSlider;
	}

	public void setHeightSlider(JSlider heightSlider) {
		this.heightSlider = heightSlider;
	}

	public JCheckBox getShowSpeed() {
		return showSpeed;
	}

	public void setShowSpeed(JCheckBox showSpeed) {
		this.showSpeed = showSpeed;
	}

	public JCheckBox getShowTrajectory() {
		return showTrajectory;
	}

	public void setShowTrajectory(JCheckBox showTrajectory) {
		this.showTrajectory = showTrajectory;
	}

	public JCheckBox getShowPositionVector() {
		return showPositionVector;
	}

	public void setShowPositionVector(JCheckBox showPositionVector) {
		this.showPositionVector = showPositionVector;
	}
	
	
}
















//end
