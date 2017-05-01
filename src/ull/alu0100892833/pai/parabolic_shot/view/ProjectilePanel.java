package ull.alu0100892833.pai.parabolic_shot.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import ull.alu0100892833.pai.parabolic_shot.ParabolicShot;

public class ProjectilePanel extends JPanel {
	private static final long serialVersionUID = 6509639337136291378L;
	private static final int FIRST_DATA = 0;
	private static final int NO_DATA = -1;
	private static final int GAP_PROPORTION = 15;
	private static final int STARTING_TIME = 0;
	private static final int TWO_SIDES = 2;
	private static final int SMALL_LINE_LENGTH = 5;
	private static final int POINT_RADIUS = 5;
	private static final int LINE_EXTRA_THICKNESS = 1;
	private static final int POINT_DIAMETER = 2 * POINT_RADIUS;
	private static final Color[] STROKE_COLORS = {Color.BLACK, Color.YELLOW, Color.GREEN, Color.RED, Color.BLUE};

	private ArrayList<ParabolicShot> data;
	private ArrayList<Color> colors;
	private int xValues, yValues, currentTime, currentData;
	private boolean drawingTrajectory, showingComplementaryData, showingPositionVector;
	
	public ProjectilePanel(ParabolicShot shot) {
		this.currentData = NO_DATA;
		this.data = new ArrayList<>();
		this.colors = new ArrayList<>();
		newData(shot);
		this.colors.add(getRandomColor());
		
		this.xValues = shot.distance();
		this.yValues = shot.maximumHeight();
		this.currentTime = STARTING_TIME;
		this.drawingTrajectory = true;
		this.showingComplementaryData = true; 
		this.showingPositionVector = false;
	}
	
	public ArrayList<ParabolicShot> getData() {
		return data;
	}

	public ParabolicShot getCurrentData() {
		if (currentData == NO_DATA)
			return data.get(FIRST_DATA);
		return data.get(currentData);
	}
	
	public void newData(ParabolicShot shot) {
		data.add(shot);
		colors.add(getRandomColor());
		if (currentData == NO_DATA)
			currentData = FIRST_DATA;
	}
	
	private Color getColorFor(int iterator) {
		return colors.get(iterator);
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

	public boolean isShowingPositionVector() {
		return showingPositionVector;
	}

	public void setShowingPositionVector(boolean showingPositionVector) {
		this.showingPositionVector = showingPositionVector;
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
		setxValues(getMaximumDistanceFromData());
	}
	
	public void estimateYValues() {
		setyValues(getMaximumHeightFromData());
	}

	@Override
	protected void paintComponent(Graphics g) {
		paintAxis(g);
		if (isShowingComplementaryData())
			paintInformation(g);
		if ((isDrawingTrajectory()) && (currentData != NO_DATA))
			paintTrajectory(g);
		paintReferencePoints(g);
	}
	
	private void paintAxis(Graphics graphics) {
		Point origin = getOrigin();
		graphics.setColor(Color.BLACK);
		
		// DRAW X-AXIS
		graphics.drawLine(origin.x, origin.y, realX(getMaximumDistanceFromData()), origin.y);
		graphics.drawLine(origin.x, origin.y + LINE_EXTRA_THICKNESS, 
				realX(getMaximumDistanceFromData()), origin.y + LINE_EXTRA_THICKNESS);
		graphics.drawLine(origin.x, origin.y - LINE_EXTRA_THICKNESS, 
				realX(getMaximumDistanceFromData()), origin.y - LINE_EXTRA_THICKNESS);
		// DRAW Y-AXIS
		graphics.drawLine(origin.x, origin.y, origin.x, realY(getMaximumHeightFromData()));
		graphics.drawLine(origin.x + LINE_EXTRA_THICKNESS, origin.y, 
				origin.x + LINE_EXTRA_THICKNESS, realY(getMaximumHeightFromData()));
		graphics.drawLine(origin.x - LINE_EXTRA_THICKNESS, origin.y, 
				origin.x - LINE_EXTRA_THICKNESS, realY(getMaximumHeightFromData()));
		
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
		for (int iterator = FIRST_DATA; iterator < getData().size(); iterator++) {
			graphics.setColor(getColorFor(iterator));
			int nValues = Math.min(getCurrentTime(), getData().get(iterator).distance());
			for (int time = STARTING_TIME; time < nValues; time++) {
				int x = realX(getData().get(iterator).getxAt(time));
				int y = realY(getData().get(iterator).getyAt(time));
				paintPoint(graphics, x, y);
			}
		}
	}
	
	private void paintPoint(Graphics graphics, int x, int y) {
		graphics.fillOval(x - POINT_RADIUS, y - POINT_RADIUS, POINT_DIAMETER, POINT_DIAMETER);
	}
	
	private void paintReferencePoints(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		for (ParabolicShot data : getData()) {
			graphics.fillOval(getOrigin().x - POINT_RADIUS, realY(data.getInitialHeight()) - POINT_RADIUS, 
					POINT_RADIUS * TWO_SIDES, POINT_RADIUS * TWO_SIDES);
			Point finalPoint = new Point(realX(data.distance()), realY(0));
			graphics.fillOval(finalPoint.x - POINT_RADIUS, getOrigin().y - POINT_RADIUS, 
					POINT_DIAMETER, POINT_DIAMETER);
		}
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
	
	private int getMaximumDistanceFromData() {
		int maximumDistance = (int) Double.NEGATIVE_INFINITY;
		for (ParabolicShot shot : getData()) {
			if (shot.distance() > maximumDistance)
				maximumDistance = shot.distance();
		}
		 
		return maximumDistance;
	}
	
	private int getMaximumHeightFromData() {
		int maximumHeight = (int) Double.NEGATIVE_INFINITY;
		for (ParabolicShot shot : getData()) {
			if (shot.maximumHeight() > maximumHeight)
				maximumHeight = shot.maximumHeight();
		}
		
		return maximumHeight;
	}
	
	public void reset() {
		resetTime();
		currentData = NO_DATA;
		getData().clear();
	}
}




















//END