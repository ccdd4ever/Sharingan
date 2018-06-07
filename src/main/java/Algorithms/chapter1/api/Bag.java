package Algorithms.chapter1.api;

import java.util.Iterator;

/**
 * @author Robin
 * @ClassName: Bag
 * @DESCRIPTION:
 * @date: 2016/6/2.
 */
public class Bag<T> implements Iterable<T> {
	Bag(){

	}

	void add(){
	}

	boolean isEmpty(){
		return true;
	}

	int size(){
		return 1;
	}

	@Override
	public Iterator<T> iterator() {
		return null;
	}
}
