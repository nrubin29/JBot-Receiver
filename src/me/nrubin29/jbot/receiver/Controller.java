package me.nrubin29.jbot.receiver;

import java.util.ArrayList;

/**
 * Represents a controller that is plugged into the broadcasting computer.
 * @author Noah Rubin
 */
public class Controller {
	
	private static ArrayList<Controller> controllers = new ArrayList<Controller>();
	
	/**
	 * Returns the controller for the given ID.
	 * @param id The ID of the controller.
	 * @return The controller.
	 */
	public static Controller get(int id) {
		for (Controller controller : controllers) {
			if (controller.getID() == id) {
				return controller;
			}
		}
		
		Controller controller = new Controller(id);
		controllers.add(controller);
		return controller;
	}
	
	private int id;
	private float xAxis, yAxis;
	
	private Controller(int id) {
		this.id = id;
		this.xAxis = 0.5f;
		this.yAxis = 0.5f;
	}
	
	/**
	 * Returns the ID of the controller.
	 * @return The ID of the controller.
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Returns the X axis value.
	 * @return The X axis value.
	 */
	public float getXAxis() {
		return xAxis;
	}
	
	protected void setXAxis(float xAxis) {
		this.xAxis = xAxis;
	}
	
	/**
	 * Returns the Y axis value.
	 * @return The Y axis value.
	 */
	public float getYAxis() {
		return yAxis;
	}
	
	protected void setYAxis(float yAxis) {
		this.yAxis = yAxis;
	}
}