package me.arutyomu.act.util;

public class CustomQueue<E> implements DataStructure<E>{
	
	private final MyList<E> delegatee = new MyLinkedList<>();
	
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
		return delegatee.size() == 0;
	}
}
