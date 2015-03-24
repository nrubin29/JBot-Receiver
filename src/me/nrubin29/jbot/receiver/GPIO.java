package me.nrubin29.jbot.receiver;

/**
 * Represents a GPIO (general purpose input and output) device that has a state of on or off.
 * @author Noah Rubin
 */
public class GPIO extends Device {

	private boolean state;
	
	/**
	 * Instantiates a GPIO for the given ID.
	 * @param id The ID of the GPIO.
	 */
	public GPIO(int id) {
		super(id);
	}
	
	/**
	 * Returns the state of the GPIO.
	 * @return The state of the GPIO.
	 */
	public boolean getState() {
		return state;
	}
	
	/**
	 * Sets the state of the GPIO.
	 * @param state The new state of the GPIO.
	 */
	public void setState(boolean state) {
		echo(state ? 1 : 0, "/sys/class/gpio/gpio" + getID() + "/value");
		this.state = state;
	}
	
	/**
	 * Toggles the state of the GPIO.
	 */
	public void toggleState() {
		setState(!state);
	}
}