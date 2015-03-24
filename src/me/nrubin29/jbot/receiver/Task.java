package me.nrubin29.jbot.receiver;

/**
 * Represents a task that a robot can run in autonomous mode.
 * @author Noah Rubin
 * @see TaskGroup
 */
public abstract class Task {

	/**
	 * Performs the task.
	 */
	public abstract void run();
	
	/**
	 * Called after the task has run for the requested amount of time.
	 */
	public void end() {
		
	}
}