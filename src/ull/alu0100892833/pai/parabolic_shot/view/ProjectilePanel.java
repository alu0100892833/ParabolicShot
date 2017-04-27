package ull.alu0100892833.pai.parabolic_shot.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import javax.swing.JPanel;

import com.sun.management.VMOption.Origin;

import ull.alu0100892833.pai.parabolic_shot.ParabolicShot;

public class ProjectilePanel extends JPanel {
	private static final long serialVersionUID = 6509639337136291378L;
	private static final int DEFAULT_X_VALUES = 500;
	private static final int DEFAULT_Y_VALUES = 250;
	private static final int GAP_PROPORTION = 8;
	private static final int STARTING_TIME = 0;
	private static final int TWO_SIDES = 2;
	private static final int SMALL_LINE_LENGTH = 10;
	private static final int POINT_RADIUS = 2;
	private static final Color[] STROKE_COLORS = {Color.BLACK, Color.YELLOW, Color.GREEN, Color.RED, Color.BLUE};

	private ParabolicShot data;
	private int xValues, yValues, currentTime;
	private boolean drawingTrajectory, showingComplementaryData;
	
	public ProjectilePanel(ParabolicShot shot) {
		this.data = shot;
		this.xValues = DEFAULT_X_VALUES;
		this.yValues = DEFAULT_Y_VALUES;
		this.currentTime = STARTING_TIME;
		this.drawingTrajectory = true;
		this.showingComplementaryData = true; 
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
	
	public int getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}

	public boolean isDrawingTrajectory() {
		return drawingTrajectory;
	}

	public void setDrawingTrajectory(boolean drawingTrajectory) {
		this.drawingTrajectory = drawingTrajectory;
	}

	public boolean isShowingComplementaryData() {
		return showingComplementaryData;
	}

	public void setShowingComplementaryData(boolean showingComplementaryData) {
		this.showingComplementaryData = showingComplementaryData;
	}

	public void timePasses() {
		setCurrentTime(getCurrentTime() + 1);
	}
	
	public void resetTime() {
		setCurrentTime(STARTING_TIME);
	}
	
	public void estimateXValues() {
		setxValues(data.distance());
	}
	
	public void estimateYValues() {
		setyValues(data.maximumHeight());
	}

	@Override
	protected void paintComponent(Graphics g) {
		paintAxis(g);
		if (isShowingComplementaryData())
			paintInformation(g);
		if (isDrawingTrajectory())
			paintTrajectory(g);
		paintReferencePoints(g);
	}
	
	private void paintAxis(Graphics graphics) {
		Point origin = getOrigin();
		int xValuesGap = (getWidth() - horizontalGap() * TWO_SIDES) / getxValues();
		int yValuesGap = (getHeight() - verticalGap() * TWO_SIDES) / getyValues();
		graphics.setColor(Color.BLACK);
		
		// DRAW X-AXIS
		graphics.drawLine(origin.x, origin.y, origin.x + getxValues(), origin.y);
		// DRAW Y-AXIS
		graphics.drawLine(origin.x, origin.y, origin.x, origin.y - getyValues());
		
		// PAINT THE SMALL LINES SEPARATING VALUES FOR THE X-AXIS
		for (int xIterator = 0; xIterator <= getxValues(); xIterator++) {
			graphics.drawLine(origin.x + xIterator * xValuesGap, origin.y, 
					origin.x + xIterator * xValuesGap, origin.y - SMALL_LINE_LENGTH);
		}
		// PAINT THE SMALL LINES SEPARATING VALUES FOR THE Y-AXIS
		for (int yIterator = 0; yIterator <= getyValues(); yIterator++) {
			graphics.drawLine(origin.x, origin.y - yIterator * yValuesGap, 
					origin.x + SMALL_LINE_LENGTH, origin.y - yIterator * yValuesGap);
		}
	}
	
	private void paintInformation(Graphics graphics) {
		
	}
	
	private void paintTrajectory(Graphics graphics) {
		graphics.setColor(getRandomColor());
	}
	
	private void paintReferencePoints(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillOval(getOrigin().x - POINT_RADIUS, data.getInitialHeight() - POINT_RADIUS, 
				POINT_RADIUS * TWO_SIDES, POINT_RADIUS * TWO_SIDES);
		// TODO pintar el círculo de destino
	}
	
	private Color getRandomColor() {
		Random rand = new Random();
		return STROKE_COLORS[rand.nextInt(STROKE_COLORS.length)];
	}
	
	private Point getOrigin() {
		int xCoordinate = horizontalGap();
		int yCoordinate = getHeight() - verticalGap();
		return new Point(xCoordinate, yCoordinate);
	}
	
	private int verticalGap() {
		return getHeight() - getHeight() / GAP_PROPORTION;
	}
	
	private int horizontalGap() {
		return getWidth() - getWidth() / GAP_PROPORTION;
	}
	
	
}




















//END