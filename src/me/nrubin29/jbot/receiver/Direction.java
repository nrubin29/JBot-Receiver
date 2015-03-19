package me.nrubin29.jbot.receiver;

/**
 * Represents the direction of a device.
 * @author Noah Rubin
 */
public enum Direction {

	/**
	 * Denotes that the device receives input.
	 */
	IN,
	
	/**
	 * Denotes that output is sent to the device.
	 */
	OUT;
	
	/**
	 * Returns the name of the direction in lower case (in, out).
	 * @return The name of the direction.
	 */
	@Override
	public String toString() {
		return name().toLowerCase();
	}
}