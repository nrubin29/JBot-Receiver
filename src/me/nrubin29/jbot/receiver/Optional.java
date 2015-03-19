package me.nrubin29.jbot.receiver;

public class Optional<T> {

	private T value;
	
	public Optional() {
		this(null);
	}
	
	public Optional(T value) {
		this.value = value;
	}
	
	public T get() {
		return value;
	}
	
	public boolean isPresent() {
		return value != null;
	}
}