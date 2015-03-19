package me.nrubin29.jbot.receiver;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

/**
 * Represents a device that is manipulated through virtual files on a Linux-based system.
 * @author Noah Rubin
 */
public abstract class Device {
	
	private static HashMap<String, FileWriter> writers = new HashMap<String, FileWriter>();

	private int id;
	private Direction direction;
	
	protected Device(int id) {
		this.id = id;
	}
	
	/**
	 * Returns the ID of this device.
	 * @return The ID of this device.
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Returns the Direction of this device.
	 * @return The Direction of this device.
	 */
	public Direction getDirection() {
		return direction;
	}
	
	/**
	 * Sets the direction of this device.
	 * @param direction The direction of this device.
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	protected static void echo(Object value, String file) {
		try {
			if (!writers.containsKey(file)) {
				writers.put(file, new FileWriter(new File(file)));
			}
			
			FileWriter writer = writers.get(file);
			writer.write(String.valueOf(value));
			writer.flush();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}