package me.nrubin29.jbot.receiver;

/**
 * Represents a group of tasks that should run together for a set amount of time.
 * @author Noah Rubin
 * @see Task
 */
public class TaskGroup {

	private int seconds;
	private Task[] tasks;
	
	/**
	 * Instantiates a task group with the given duration and tasks.
	 * @param seconds The amount of time in seconds to run the tasks.
	 * @param tasks The tasks to be run.
	 */
	public TaskGroup(int seconds, Task... tasks) {
		this.seconds = seconds;
		this.tasks = tasks;
	}
	
	/**
	 * Runs the tasks for the given duration, then ends the tasks.
	 */
	public void run() {
		for (Task t : tasks) {
			t.run();
		}
		
		long start = System.nanoTime();
	    long end;
	    
	    do {
	        end = System.nanoTime();
	    } while(start + ((long) seconds) * 1000000000 >= end);
		
	    for (Task t : tasks) {
			t.end();
		}
	}
}