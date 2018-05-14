package com.doublea.matrixify.Utils;
import java.util.Iterator;

/**
	 *  An arraylist class
	 * @author Ahmed Ali
	 *
	 * @param <T> Type of data of arraylist
	 */
	public class ArrayList<T> implements Iterable<T>{
		@Override
		public String toString() {
			String temp = "";
			for (T element: this) temp+= element.toString() + " ";
			return temp;
		}
		private T[] elements;
		private int numElements;

		protected ArrayList() {
			elements = (T[]) (new Object[100]);
			numElements = 0;
		}

		public int numElements() {
			// return the number of elements
			// O(1)
			return numElements;
		}

		/**
		 * 
		 * @param index Index of the element to be returned
		 * @return Returns a element at the index passed in
		 * @throws Runtime exception for when element is outside of valid range
		 */
		public T getElement(int index) {
			// return element at index
			// throw RuntimeException for invalid index
			// O(1)
			if (index < 0 || index > numElements)
				throw new RuntimeException("Index invalid");
			return elements[index];
		}

		/**
		 * Replaces element at index by c
		 * @param index Index of the element to be replaced
		 * @param c Element to be placed at the given index
		 * @throws Runtime exception for index outside of valid range
		 */
		public void setElement(int index, T c) {
			// change the element at index to be c
			// throw RuntimeException for invalid index
			// O(1)
			if (index < 0 || index > numElements)
				throw new RuntimeException("Index invalid");
			elements[index] = c;
		}

		/**
		 * Adds new element to the hand
		 * @param c Element to be added
		 */
		public void addElement(T c) {
			// add element c at the end
			// O(N)
			if (numElements == elements.length - 1)
				elements = expand(elements);
			elements[numElements] = c;
			numElements++;
		}

		/**
		 * Expands type T[] array passed in by double its size
		 * 
		 * @param elements Element array to be expanded
		 * @return Returns elements array with double its original size (But same
		 *         content)
		 */
		private T[] expand(T[] elements) {
			T[] elements2 = (T[]) new Object[(elements.length) * 2];
			for (int i = 0; i < elements.length; i++)
				elements2[i] = elements[i];
			elements = elements2;
			return elements;
		}

		/**
		 * Gets index of element passed in
		 * @param c element to get the index of
		 * @return Returns index of element passed in, returns -1 if element not found
		 */
		public int indexOf(T c) {
			// find the index of a given element c,
			// returns -1 if not found
			// O(N)
			for (int i = 0; i < numElements; i++) {
				if (elements[i].equals(c))
					return i;
			}
			return -1;
		}

		/**
		 * Removes element from Hand
		 * 
		 * @param index index of element to be removed
		 * @return Returns the element that was removed
		 * @throws Runtime exception for element outside of valid range
		 */
		public T removeElement(int index) {
			// remove the element at index,
			// throw RuntimeException for invalid index
			// O(N)
			if (index < 0 || index > numElements)
				throw new RuntimeException("Index invalid");
			T temp = elements[index];

			// Override
			for (int i = index; i < numElements; i++) {
				elements[i] = elements[i + 1];
			}
			numElements--;
			return temp;
		}

		/**
		 * Removes element passed in from hand
		 * 
		 * @param c Element to be removed
		 * @return Returns true if element was removed correctly, otherwise returns false
		 */
		public boolean removeElement(T c) {
			// remove element c,
			// returns false if no such element
			// O(N)
			for (int i = 0; i < numElements; i++) {
				if (elements[i].equals(c)) {
					removeElement(i);
					return true;
				}
			}
			return false;
		}

		private class iter implements Iterator<T> {
			int index = 0;
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return index < numElements;
			}

			@Override
			public T next() {
				// TODO Auto-generated method stub
				return elements[index++];
			}
			
		}
		@Override
		public Iterator<T> iterator() {
			// TODO Auto-generated method stub
			return new iter();
		}
		
}
