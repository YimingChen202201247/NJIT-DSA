package oy.tol.tra;

/**
 * An implementation of the StackInterface.
 * <p>
 * TODO: Students, implement this so that the tests pass.
 * 
 * Note that you need to implement construtor(s) for your concrete StackImplementation, which
 * allocates the internal Object array for the Stack:
 * - a default constructor, calling the StackImplementation(int size) with value of 10.
 * - StackImplementation(int size), which allocates an array of Object's with size.
 *  -- remember to maintain the capacity and/or currentIndex when the stack is manipulated.
 */
public class StackImplementation<E> implements StackInterface<E> {

   private Object [] itemArray;
   private int capacity;
   private int currentIndex = -1;
   private static final int DEFAULT_STACK_SIZE = 10;

   /**
    * Allocates a stack with a default capacity.
    * @throws StackAllocationException
    */
   public class Stack<T> {
      private T[] elements;
      private int size;
      private static final int DEFAULT_CAPACITY = 10;

      @SuppressWarnings("unchecked")
      public Stack() {
         elements = (T[]) new Object[DEFAULT_CAPACITY];
         size = 0;
      }

      public int capacity() {
         return elements.length;
      }

      public void push(T value) {
         if (size == elements.length) {
            resize(elements.length * 2);
         }
         elements[size++] = value;
      }

      public T pop() {
         if (size == 0) {
            throw new IllegalStateException("Stack is empty");
         }
         T value = elements[--size];
         elements[size] = null; // Avoid memory leak
         if (size > 0 && size == elements.length / 4) {
            resize(elements.length / 2);
         }
         return value;
      }

      public T peek() {
         if (size == 0) {
            throw new IllegalStateException("Stack is empty");
         }
         return elements[size - 1];
      }

      public int size() {
         return size;
      }

      public boolean isEmpty() {
         return size == 0;
      }

      @SuppressWarnings("unchecked")
      public void clear() {
         elements = (T[]) new Object[DEFAULT_CAPACITY];
         size = 0;
      }

      @Override
      public String toString() {
         StringBuilder sb = new StringBuilder();
         sb.append("[");
         for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
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
            newArray[i] = elements[i];
         }
         elements = newArray;
      }
   }

