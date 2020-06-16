package produktionscode;

import java.util.Iterator;

public class ArrayIterator<T extends Object> implements Iterator<T>{

	private T[] array;
	private int pointer;
	
	ArrayIterator (T[] array){
		this.array = array;
	}
	
	@Override
	public boolean hasNext() {
		return pointer < array.length -1;
	}

	@Override
	public T next() {
		return array[pointer++];
	}
	
	
	
	
}
