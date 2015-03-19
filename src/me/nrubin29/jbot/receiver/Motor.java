package me.nrubin29.jbot.receiver;

public class Motor extends PWM {

	public Motor(int id) {
		super(id);
		super.setDirection(Direction.OUT);
	}
	
	@Override
	public void setDirection(Direction direction) {
		
	}
}