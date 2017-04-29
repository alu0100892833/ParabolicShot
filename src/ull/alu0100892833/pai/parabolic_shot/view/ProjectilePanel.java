package ull.alu0100892833.pai.parabolic_shot.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import javax.swing.JPanel;

import ull.alu0100892833.pai.parabolic_shot.ParabolicShot;

public class ProjectilePanel extends JPanel {
	private static final long serialVersionUID = 6509639337136291378L;
	private static final int GAP_PROPORTION = 15;
	private static final int STARTING_TIME = 0;
	private static final int TWO_SIDES = 2;
	private static final int SMALL_LINE_LENGTH = 5;
	private static final int POINT_RADIUS = 5;
	private static final int LINE_EXTRA_THICKNESS = 1;
	private static final int POINT_DIAMETER = 2 * POINT_RADIUS;
	private static final Color[] STROKE_COLORS = {Color.BLACK, Color.YELLOW, Color.GREEN, Color.RED, Color.BLUE};

	private ParabolicShot data;
	private int xValues, yValues, currentTime;
	private boolean drawingTrajectory, showingComplementaryData;
	
	public ProjectilePanel(ParabolicShot shot) {
		this.data = shot;
		this.xValues = data.distance();
		this.yValues = data.maximumHeight();
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
	
	private int realX(int xValue) {
		int xRealPosition = getOrigin().x + horizontalValuesSeparation() * xValue;
		return xRealPosition;
	}
	
	private int realY(int yValue) {
		int yRealPosition = getOrigin().y - verticalValuesSeparation() * yValue;
		return yRealPosition;
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
		graphics.setColor(Color.BLACK);
		
		// DRAW X-AXIS
		graphics.drawLine(origin.x, origin.y, realX(data.distance()), origin.y);
		graphics.drawLine(origin.x, origin.y + LINE_EXTRA_THICKNESS, 
				realX(data.distance()), origin.y + LINE_EXTRA_THICKNESS);
		graphics.drawLine(origin.x, origin.y - LINE_EXTRA_THICKNESS, 
				realX(data.distance()), origin.y - LINE_EXTRA_THICKNESS);
		// DRAW Y-AXIS
		graphics.drawLine(origin.x, origin.y, origin.x, realY(data.maximumHeight()));
		graphics.drawLine(origin.x + LINE_EXTRA_THICKNESS, origin.y, 
				origin.x + LINE_EXTRA_THICKNESS, realY(data.maximumHeight()));
		graphics.drawLine(origin.x - LINE_EXTRA_THICKNESS, origin.y, 
				origin.x - LINE_EXTRA_THICKNESS, realY(data.maximumHeight()));
		
		// PAINT THE SMALL LINES SEPARATING VALUES FOR THE X-AXIS
		for (int xIterator = 0; xIterator <= getxValues(); xIterator++) {
			graphics.drawLine(realX(xIterator), origin.y, 
					realX(xIterator), origin.y - SMALL_LINE_LENGTH);
		}
		// PAINT THE SMALL LINES SEPARATING VALUES FOR THE Y-AXIS
		for (int yIterator = 0; yIterator <= getyValues(); yIterator++) {
			graphics.drawLine(origin.x, realY(yIterator), 
					origin.x + SMALL_LINE_LENGTH, realY(yIterator));
		}
	}
	
	private void paintInformation(Graphics graphics) {
		// TODO pintar toda la información
	}
	
	private void paintTrajectory(Graphics graphics) {
		graphics.setColor(getRandomColor());
		// TODO pintar la trayectoria
	}
	
	private void paintReferencePoints(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillOval(getOrigin().x - POINT_RADIUS, realY(data.getInitialHeight()) - POINT_RADIUS, 
				POINT_RADIUS * TWO_SIDES, POINT_RADIUS * TWO_SIDES);
		Point finalPoint = new Point(realX(data.distance()), realY(0));
		graphics.fillOval(finalPoint.x - POINT_RADIUS, getOrigin().y - POINT_RADIUS, 
				POINT_DIAMETER, POINT_DIAMETER);
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
		return getHeight() / GAP_PROPORTION;
	}
	
	private int horizontalGap() {
		return getWidth() / GAP_PROPORTION;
	}
	
	private int horizontalValuesSeparation() {
		int range = getWidth() - TWO_SIDES * horizontalGap();
		return range / getxValues();
	}
	
	private int verticalValuesSeparation() {
		int range = getHeight() - TWO_SIDES * verticalGap();
		return range / getyValues();
	}
}




















//END