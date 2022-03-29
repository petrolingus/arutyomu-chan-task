package me.arutyomu.act.util;

public interface DataStructure<T> {
	
	T pop();
	
	void push(T t);
	
	boolean isEmpty();
}
