package me.nrubin29.jbot.receiver;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a robot. Superclass for all projects.
 * @author Noah Rubin
 */
public abstract class Robot {

	private Motor left, right;
	private boolean inputEnabled;
	
	protected void preInit() {
		this.inputEnabled = true;
	}
	
	/**
	 * Called after the robot has been pre-initialized but before it has been post-initialized. Open all devices here.
	 */
	public abstract void init();
	
	protected void postInit() {
		final long period = 2000000; // This should be a bit more.
		final List<Motor> motors = Arrays.asList(left, right);
		
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					for (Motor motor : motors) {
						/*
						Set it on.
						 */
						Device.echo(1, "/sys/class/gpio/gpio" + motor.getID() + "/value");
						
						sleep((long) (period * motor.getPercent()));
							
						/*
						Set it off.
						*/
						Device.echo(0, "/sys/class/gpio/gpio" + motor.getID() + "/value");
						
						sleep((long) (period - (period * motor.getPercent())));
					}
				}
			}
				
			private void sleep(long nanos) {
			    long start = System.nanoTime();
			    long end = 0;
			    
			    do {
			        end = System.nanoTime();
			    } while(start + nanos >= end);
			}
		}).start();
	}
	
	/**
	 * Called after post-initialization. Place autonomous code here.
	 */
	public void run() {
		
	}
	
	/**
	 * Sets a motor.
	 * @param side The side of the motor. Must be one of [left, right]
	 * @param motor The motor to use.
	 */
	public void setMotor(String side, Motor motor) {
		if (side.equals("left")) {
			left = motor;
		}
		
		else if (side.equals("right")) {
			right = motor;
		}
		
		else {
			throw new IllegalArgumentException("Side must be one of [left, right].");
		}
	}
	
	public Motor getMotor(String side) {
		if (side.equals("left")) {
			return left;
		}
		
		else if (side.equals("right")) {
			return right;
		}
		
		else {
			throw new IllegalArgumentException("Side must be one of [left, right].");
		}
	}
	
	/**
	 * Set the ability for the robot to handle input. 
	 * @param inputEnabled The new state.
	 */
	public void setInputEnabled(boolean inputEnabled) {
		this.inputEnabled = inputEnabled;
	}
	
	protected boolean isInputEnabled() {
		return inputEnabled;
	}
}