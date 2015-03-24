package me.nrubin29.jbot.receiver;

/**
 * Represents a class that is capable of observing observable values.
 * @author Noah Rubin
 * @param <T> The type of the observable.
 */
public interface Observer<T> {

	/**
	 * Called when the value of the observable is updated.
	 * @param value The new value.
	 */
	public abstract void update(T value);
}