package me.nrubin29.jbot.receiver;

import java.util.Arrays;
import java.util.List;

public abstract class Robot {

	private Optional<Motor> left, right;
	
	protected void preInit() {
		this.left = new Optional<Motor>();
		this.right = new Optional<Motor>();
	}
	
	public abstract void init();
	
	protected void postInit() {
		final long period = 1800000; // 1500000; // 2000000; // 2500000;
		final List<Optional<Motor>> motors = Arrays.asList(left, right);
		
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					for (Optional<Motor> m : motors) {
						if (m.isPresent()) {
							Motor motor = m.get();
							
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
	
	public void setMotor(String side, Motor motor) {
		if (side.equals("left")) {
			left = new Optional<Motor>(motor);
		}
		
		else if (side.equals("right")) {
			right = new Optional<Motor>(motor);
		}
		
		else {
			throw new IllegalArgumentException("Side must be one of [left, right].");
		}
	}
	
	protected Optional<Motor> getMotor(String side) {
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
}