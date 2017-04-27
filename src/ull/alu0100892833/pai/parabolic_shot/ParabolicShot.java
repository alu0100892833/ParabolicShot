package ull.alu0100892833.pai.parabolic_shot;

/**
 * Modelo abstracto para representar un tiro parabólico. Almacena los datos iniciales del lanzamiento.
 * Los distintos métodos de esta clase permiten obtener todos los datos de la trayectoria que traza el objeto lanzado.
 * Representa un tiro parabólico sin rozamiento. 
 * @author Óscar Darias Plasencia
 * @since 27-4-2017
 */
public class ParabolicShot {
	public static final double GRAVITY_ACCELERATION = -9.81;
	private static final double HALF = 0.5;
	
	private double outputSpeed, launchAngle;
	private int initialHeight;


	/**
	 * Constructor por parámetros, especificando todos los valores de inicio.
	 * @param outputSpeed Velocidad con la que se dispara el proyectil.
	 * @param launchAngle Ángulo de lanzamiento.
	 * @param initialHeight Altura inicial desde la que se dispara el proyectil.
	 */
	public ParabolicShot(double outputSpeed, double launchAngle, int initialHeight) {
		this.outputSpeed = outputSpeed;
		this.launchAngle = launchAngle;
		this.initialHeight = initialHeight; 
	}
	
	/**
	 * Método que permite modificar todos los parámetros del tiro parabólico.
	 * @param outputSpeed Velocidad con la que se dispara el proyectil.
	 * @param launchAngle Ángulo de lanzamiento.
	 * @param initialHeight Altura inicial desde la que se dispara el proyectil.
	 */
	public void changeValues(double outputSpeed, double launchAngle, int initialHeight) {
		this.outputSpeed = outputSpeed;
		this.launchAngle = launchAngle;
		this.initialHeight = initialHeight; 
	}

	/**
	 * Retorna la velocidad de salida.
	 * @return
	 */
	public double getOutputSpeed() {
		return outputSpeed;
	}

	/**
	 * Modificar la velocidad de salida.
	 * @param outputSpeed
	 */
	public void setOutputSpeed(double outputSpeed) {
		this.outputSpeed = outputSpeed;
	}
 
	/**
	 * Retorna el ángulo inicial.
	 * @return
	 */
	public double getStartingAngle() {
		return launchAngle;
	}

	/**
	 * Modificar el ángulo inicial.
	 * @param startingAngle
	 */
	public void setStartingAngle(double startingAngle) {
		this.launchAngle = startingAngle;
	}

	/**
	 * Retorna la altura inicial.
	 * @return
	 */
	public int getInitialHeight() {
		return initialHeight;
	}

	/**
	 * Modificar la altura inicial.
	 * @param initialHeight
	 */
	public void setInitialHeight(int initialHeight) {
		this.initialHeight = initialHeight;
	}
	
	/**
	 * Obtener la componente X de la velocidad inicial.
	 * @return
	 */
	public double outputSpeedHorizontalComponent() {
		return getOutputSpeed() * Math.cos(launchAngle);
	}
	
	/**
	 * Obtener la componente Y de la velocidad inicial.
	 * @return
	 */
	public double outputSpeedVerticalComponent() {
		return getOutputSpeed() * Math.sin(launchAngle);
	}

	/**
	 * Obtener la posición en X tras un determinado tiempo.
	 * @param time Tiempo pasado.
	 * @return Componente X.
	 */
	public int getxAt(int time) {
		return (int) outputSpeedHorizontalComponent() * time;
	}
	
	/**
	 * Obtener la posición en Y tras un determinado tiempo.
	 * @param time Tiempo pasado.
	 * @return Componente Y.
	 */
	public int getyAt(int time) {
		return (int) (getInitialHeight() + outputSpeedVerticalComponent() * time - HALF * GRAVITY_ACCELERATION * Math.pow(time, 2));
	}
	
	/**
	 * Obtener el tiempo en el que el proyectil llega a su altura máxima.
	 * @return
	 */
	public int maximumHeightTime() {
		return (int) (outputSpeedVerticalComponent() / GRAVITY_ACCELERATION);
	}
	
	/**
	 * Obtener la altura máxima a la que llega el proyectil.
	 * @return
	 */
	public int maximumHeight() {
		return getyAt(maximumHeightTime());
	}
	
	/**
	 * Obtener el tiempo de vuelo del proyectil.
	 * Este es el tiempo que tarda en llegar al suelo.
	 * @return
	 */
	private int flightTime() {
		double numerator = outputSpeedVerticalComponent() + Math.sqrt(Math.pow(outputSpeedVerticalComponent(), 2) - 2 * GRAVITY_ACCELERATION * getInitialHeight());
		return (int) (numerator / GRAVITY_ACCELERATION);
	}
	
	/**
	 * Obtener la distancia máxima recorrida en X por el proyectil. 
	 * Es la distancia recorrida antes de caer.
	 * @return
	 */
	public int distance() {
		return getxAt(flightTime());
	}
}
























//END