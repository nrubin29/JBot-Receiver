package me.nrubin29.jbot.receiver;

public class PWM extends Device {

	private double percent;
	
	public PWM(int id) {
		super(id);
		
		echo(getID(), "/sys/class/gpio/export");
	}
	
	public double getPercent() {
		return percent;
	}
	
	public void setPercent(double percent) {
		this.percent = percent; // (percent / 100);
	}
	
	@Override
	public void setDirection(Direction direction) {
		super.setDirection(direction);
		echo(direction, "/sys/class/gpio/gpio" + getID() + "/direction");
	}
}