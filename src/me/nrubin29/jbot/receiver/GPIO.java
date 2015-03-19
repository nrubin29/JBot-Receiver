package me.nrubin29.jbot.receiver;

public class GPIO extends Device {

	private boolean state;
	
	private GPIO(int id) {
		super(id);
		
		echo(getID(), "/sys/class/gpio/export");
	}
	
	public boolean getState() {
		return state;
	}
	
	public void setState(boolean state) {
		echo(state ? 1 : 0, "/sys/class/gpio/gpio" + getID() + "/value");
		this.state = state;
	}
	
	public void toggleState() {
		setState(!state);
	}
	
	@Override
	public void setDirection(Direction direction) {
		super.setDirection(direction);
		echo(direction, "/sys/class/gpio/gpio" + getID() + "/direction");
	}
}