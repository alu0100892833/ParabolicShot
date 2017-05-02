package ull.alu0100892833.pai.parabolic_shot.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Timer;

import javax.swing.JPanel;

import ull.alu0100892833.pai.parabolic_shot.ParabolicShot;

public class ParabolicShotPanel extends JPanel {
	private static final long serialVersionUID = 6796017625760582345L;
	private static final int TIMER_DELAY = 350;
	
	private ProjectilePanel projectilePanel;
	private ControlPanel controlPanel;
	private Timer timer;
	
	public ParabolicShotPanel(Dimension size) {
		projectilePanel = new ProjectilePanel();
		controlPanel = new ControlPanel();
		
		setLayout(new BorderLayout());
		add(projectilePanel, BorderLayout.CENTER);
		add(controlPanel, BorderLayout.SOUTH);
		
		setSize(size);
		setListeners();
	}

	public ProjectilePanel getProjectilePanel() {
		return projectilePanel;
	}

	public void setProjectilePanel(ProjectilePanel projectilePanel) {
		this.projectilePanel = projectilePanel;
	}

	public ControlPanel getControlPanel() {
		return controlPanel;
	}

	public void setControlPanel(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
	}
	
	public void setListeners() {	
		ButtonsListener buttonsListener = new ButtonsListener();
		timer = new Timer(TIMER_DELAY, buttonsListener);
		controlPanel.setListeners(buttonsListener, new BoxesListener());
	}
	
	private void startTimer() {
		timer.start();
	}
	
	private void stopTimer() {
		timer.stop();
	}

	public Timer getTimer() {
		return timer;
	}
	
	
	
	/***
	 * 
	 * LISTENER CLASSES
	 *
	 ****/
	
	
	
	class ButtonsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == controlPanel.getThrowButton()) {
				ParabolicShot newShot = new ParabolicShot(controlPanel.getSpeedSlider().getValue(), 
						controlPanel.getAngleSlider().getValue(), 
						controlPanel.getHeightSlider().getValue());
				if (!newShot.equals(projectilePanel.getCurrentData()))
					projectilePanel.newData(newShot);
				projectilePanel.resetTime();
				startTimer();
			} else if (e.getSource() == controlPanel.getPauseButton()) {
				stopTimer();
			} else if (e.getSource() == controlPanel.getResetButton()) {
				stopTimer();
				projectilePanel.reset();
				revalidate();
				repaint();
			} else if (e.getSource() == getTimer()) {
				projectilePanel.timePasses();
				if (projectilePanel.enoughTime())
					stopTimer();
				revalidate();
				repaint();
			}
		}
	}
	
	class BoxesListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == controlPanel.getShowPositionVector()) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					projectilePanel.setShowingPositionVector(true);
				else
					projectilePanel.setShowingPositionVector(false);
			} else if (e.getSource() == controlPanel.getShowSpeed()) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					projectilePanel.setShowingComplementaryData(true);
				else
					projectilePanel.setShowingComplementaryData(false);
			} else if (e.getSource() == controlPanel.getShowTrajectory()) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					projectilePanel.setDrawingTrajectory(true);
				else
					projectilePanel.setDrawingTrajectory(false);
			}
		}
	}
	
}









//END
