package oy.tol.tra;

/**
 * An implementation of the QueueInterface.
 */
package oy.tol.tra;

import java.util.Arrays;

public class QueueImplementation<T> implements QueueInterface<T> {
	private T[] elements;
	private int head;
	private int tail;
	private int size;
	private static final int DEFAULT_CAPACITY = 10;

	@SuppressWarnings("unchecked")
	public QueueImplementation() {
		elements = (T[]) new Object[DEFAULT_CAPACITY];
		head = 0;
		tail = 0;
		size = 0;
	}

	@Override
	public int capacity() {
		return elements.length;
	}

	@Override
	public void enqueue(T element) {
		if (size == elements.length) {
			resize(elements.length * 2);
		}
		elements[tail] = element;
		tail = (tail + 1) % elements.length;
		size++;
	}

	@Override
	public T dequeue() {
		if (size == 0) {
			throw new IllegalStateException("Queue is empty");
		}
		T element = elements[head];
		elements[head] = null; // Avoid memory leak
		head = (head + 1) % elements.length;
		size--;
		if (size > 0 && size == elements.length / 4) {
			resize(elements.length / 2);
		}
		return element;
	}

	@Override
	public T element() {
		if (size == 0) {
			throw new IllegalStateException("Queue is empty");
		}
		return elements[head];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		elements = (T[]) new Object[DEFAULT_CAPACITY];
		head = 0;
		tail = 0;
		size = 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < size; i++) {
			sb.append(elements[(head + i) % elements.length]);
			if (i < size - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	private void resize(int newCapacity) {
		T[] newArray = (T[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newArray[i] = elements[(head + i) % elements.length];
		}
		elements = newArray;
		head = 0;
		tail = size;
	}
}
