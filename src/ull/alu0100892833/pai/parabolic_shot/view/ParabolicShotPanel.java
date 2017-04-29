package ull.alu0100892833.pai.parabolic_shot.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ull.alu0100892833.pai.parabolic_shot.ParabolicShot;

public class ParabolicShotPanel extends JPanel {
	private static final long serialVersionUID = 6796017625760582345L;
	
	private ProjectilePanel projectilePanel;
	private ControlPanel controlPanel;
	
	public ParabolicShotPanel(Dimension size) {
		projectilePanel = new ProjectilePanel(new ParabolicShot(ControlPanel.INITIAL_SPEED, ControlPanel.INITIAL_ANGLE, ControlPanel.INITIAL_HEIGHT));
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
		controlPanel.setListeners(new ButtonsListener(), new SlidersListener(), new BoxesListener());
	}

	
	
	
	/***
	 * 
	 * LISTENER CLASSES
	 *
	 ****/
	
	
	
	class ButtonsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class SlidersListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class BoxesListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
}









//END
