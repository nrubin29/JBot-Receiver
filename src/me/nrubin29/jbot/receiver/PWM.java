package me.nrubin29.jbot.receiver;

/**
 * Represents a device which operates based on pulse width modulations.
 * @author Noah Rubin
 * @see Motor
 */
public class PWM extends Device {

	private double percent;
	
	protected PWM(int id) {
		super(id);
	}
	
	protected double getPercent() {
		return percent;
	}
	
	protected void setPercent(double percent) {
		this.percent = Math.max(0, Math.min(1, percent));
	}
}