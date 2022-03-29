package me.arutyomu.act.util;

public class CustomStack<E> implements DataStructure<E>{
	
	private final MyList<E> delegatee = new MyLinkedList<>();
	
	@Override
	public E pop() {
		return delegatee.remove(delegatee.size() - 1);
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
