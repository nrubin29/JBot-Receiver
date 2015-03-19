package me.nrubin29.jbot.receiver;

import java.util.ArrayList;

/**
 * Represents an observable value that can notify listeners when its value changes.
 * @author Noah Rubin
 * @param <T> The type of the observable value.
 */
public class Observable<T> {

	private String name;
	private T value;
	private ArrayList<Observer<T>> observers;
	
	protected Observable(String name) {
		this(name, null);
	}
	
	protected Observable(String name, T value) {
		this.name = name;
		this.value = value;
		this.observers = new ArrayList<Observer<T>>();
	}
	
	/**
	 * Returns the identifying name of the value.
	 * @return The identifying name of the value.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the value.
	 * @return The value.
	 */
	public T get() {
		return value;
	}
	
	/**
	 * Sets the value and notifies listeners.
	 * @param value The new value.
	 */
	public void set(T value) {
		this.value = value;
		
		for (Observer<T> observer : observers) {
			observer.update(value);
		}
	}
	
	/**
	 * Registers an observer to this value.
	 * @param observer The observer to register.
	 */
	public void observe(Observer<T> observer) {
		observers.add(observer);
	}
}