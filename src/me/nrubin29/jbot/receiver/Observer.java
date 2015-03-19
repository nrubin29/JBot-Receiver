package me.nrubin29.jbot.receiver;

public interface Observer<T> {

	public abstract void update(T value);
}