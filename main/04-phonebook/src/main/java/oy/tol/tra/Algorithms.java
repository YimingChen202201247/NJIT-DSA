package oy.tol.tra;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

/**
 * A simple array of student grades to be used in testing
 * misbehaving algorithm for reversing the array.
 */

public interface Dictionary<K, V> {
	void put(K key, V value);
	V get(K key);
	void remove(K key);
	boolean containsKey(K key);
	int size();
	boolean isEmpty();
}

public class HashTable<K, V> implements Dictionary<K, V> {
	private static final int INITIAL_CAPACITY = 16;
	private LinkedList<Entry<K, V>>[] table;
	private int size;

	public HashTable() {
		table = new LinkedList[INITIAL_CAPACITY];
		for (int i = 0; i < table.length; i++) {
			table[i] = new LinkedList<>();
		}
		size = 0;
	}

	private int hash(K key) {
		return Math.abs(key.hashCode()) % table.length;
	}

	@Override
	public void put(K key, V value) {
		int index = hash(key);
		LinkedList<Entry<K, V>> bucket = table[index];
		for (Entry<K, V> entry : bucket) {
			if (entry.key.equals(key)) {
				entry.value = value;
				return;
			}
		}
		bucket.add(new Entry<>(key, value));
		size++;
		if (size > table.length * 0.75) {
			resize();
		}
	}

	@Override
	public V get(K key) {
		int index = hash(key);
		LinkedList<Entry<K, V>> bucket = table[index];
		for (Entry<K, V> entry : bucket) {
			if (entry.key.equals(key)) {
				return entry.value;
			}
		}
		return null;
	}

	@Override
	public void remove(K key) {
		int index = hash(key);
		LinkedList<Entry<K, V>> bucket = table[index];
		for (Entry<K, V> entry : bucket) {
			if (entry.key.equals(key)) {
				bucket.remove(entry);
				size--;
				return;
			}
		}
	}

	@Override
	public boolean containsKey(K key) {
		return get(key) != null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	private void resize() {
		LinkedList<Entry<K, V>>[] oldTable = table;
		table = new LinkedList[oldTable.length * 2];
		for (int i = 0; i < table.length; i++) {
			table[i] = new LinkedList<>();
		}
		size = 0;
		for (LinkedList<Entry<K, V>> bucket : oldTable) {
			for (Entry<K, V> entry : bucket) {
				put(entry.key, entry.value);
			}
		}
	}

	private static class Entry<K, V> {
		K key;
		V value;

		Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
}

public class BST<K extends Comparable<K>, V> implements Dictionary<K, V> {
	private Node<K, V> root;
	private int size;

	private static class Node<K, V> {
		K key;
		V value;
		Node<K, V> left, right;

		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	@Override
	public void put(K key, V value) {
		root = put(root, key, value);
	}

	private Node<K, V> put(Node<K, V> node, K key, V value) {
		if (node == null) {
			size++;
			return new Node<>(key, value);
		}
		int cmp = key.compareTo(node.key);
		if (cmp < 0) {
			node.left = put(node.left, key, value);
		} else if (cmp > 0) {
			node.right = put(node.right, key, value);
		} else {
			node.value = value;
		}
		return node;
	}

	@Override
	public V get(K key) {
		Node<K, V> node = get(root, key);
		return (node != null) ? node.value : null;
	}

	private Node<K, V> get(Node<K, V> node, K key) {
		if (node == null) return null;
		int cmp = key.compareTo(node.key);
		if (cmp < 0) return get(node.left, key);
		else if (cmp > 0) return get(node.right, key);
		else return node;
	}

	@Override
	public void remove(K key) {
		root = remove(root, key);
	}

	private Node<K, V> remove(Node<K, V> node, K key) {
		if (node == null) return null;
		int cmp = key.compareTo(node.key);
		if (cmp < 0) {
			node.left = remove(node.left, key);
		} else if (cmp > 0) {
			node.right = remove(node.right, key);
		} else {
			if (node.left == null) return node.right;
			if (node.right == null) return node.left;
			Node<K, V> temp = node;
			node = min(temp.right);
			node.right = deleteMin(temp.right);
			node.left = temp.left;
			size--;
		}
		return node;
	}

	private Node<K, V> min(Node<K, V> node) {
		while (node.left != null) node = node.left;
		return node;
	}

	private Node<K, V> deleteMin(Node<K, V> node) {
		if (node.left == null) return node.right;
		node.left = deleteMin(node.left);
		return node;
	}

	@Override
	public boolean containsKey(K key) {
		return get(key) != null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
}

public class PhoneBook<K, V> {
	private Dictionary<K, V> dictionary;

	public PhoneBook(Dictionary<K, V> dictionary) {
		this.dictionary = dictionary;
	}

	public void addEntry(K key, V value) {
		dictionary.put(key, value);
	}

	public V getEntry(K key) {
		return dictionary.get(key);
	}

	public void removeEntry(K key) {
		dictionary.remove(key);
	}

	public boolean containsEntry(K key) {
		return dictionary.containsKey(key);
	}

	public int getSize() {
		return dictionary.size();
	}

	public boolean isEmpty() {
		return dictionary.isEmpty();
	}
}
