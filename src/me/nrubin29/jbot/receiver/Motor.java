package me.nrubin29.jbot.receiver;

/**
 * Represents a motor.
 * @author Noah Rubin
 */
public class Motor extends PWM {

	/**
	 * Instantiates a motor for the given ID.
	 * @param id The ID of the motor.
	 */
	public Motor(int id) {
		super(id);
		super.setDirection(Direction.OUT);
	}
	
	/**
	 * Returns a task for running the motor at the specified percent.
	 * @param percent The percent at which to run the motor. Range of [0...1] where 0.5 is neutral.
	 */
	public Task run(final double percent) {
		return new Task() {
			@Override
			public void run() {
				setPercent(percent);
				
			}
			
			@Override
			public void end() {
				setPercent(0.5);
			}
		};
	}
	
	/**
	 * The direction of a motor is always out and cannot be set.
	 */
	@Override
	public void setDirection(Direction direction) {
		throw new IllegalArgumentException("Motors can only be Direction.OUT");
	}
}