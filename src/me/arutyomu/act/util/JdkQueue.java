package me.arutyomu.act.util;

import java.util.LinkedList;
import java.util.List;

public class JdkQueue<E> implements DataStructure<E>{
	
	private final List<E> delegatee = new LinkedList<>();
	
	@Override
	public E pop() {
		return delegatee.remove(0);
	}
	
	@Override
	public void push(E e) {
		delegatee.add(e);
	}
	
	@Override
	public boolean isEmpty() {
		return delegatee.isEmpty();
	}
}
