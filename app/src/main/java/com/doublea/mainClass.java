package com.doublea;


import android.widget.ArrayAdapter;

import java.util.Scanner;
public class mainClass {
	public static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		ArrayList<ArrayList<Rational>> matrix = new ArrayList<>();
		readItIn(matrix);
		reduce(matrix);
		print(matrix);

	}
	/**
	 * 
	 * @param matrix 2d arraylist
	 */
	public static void reduce(ArrayList<ArrayList<Rational>> matrix) {
		int rowsize = matrix.getElement(0).numElements();
		ArrayList<Rational> current = null;
		ArrayList<Rational> otherRow = null;
		boolean swapped = false;
		for (int col = 0; col < matrix.numElements(); col++) {
			current = matrix.getElement(col);

			//Finding pivot entry
			if (current.getElement(col).getValue() == 0) {
				for (int row = 0; row < matrix.numElements(); row++) {
					otherRow = matrix.getElement(row);
					if (otherRow.getElement(col).getValue() != 0 && indexOfFirstNoneZero(otherRow) == col) {
						swapped = true;
						swap(matrix, col, row);
						current = matrix.getElement(col);
					}
				}
				if (!swapped) continue;
			}

			//Normalize pivot entry
			divideRow(current, current.getElement(col));

			//Using pivot entry to eliminate numbers in collumn
			for (int row = 0; row < matrix.numElements(); row++) {
				if (row == col) continue;
				otherRow = matrix.getElement(row);
				if (otherRow.getElement(col).getValue() != 0)
					multAddAndStore(current, otherRow, otherRow.getElement(col).multiply(new Rational(-1)));
			}
		}
	}
	/**
	 * Returns index of first none zero entry
	 * @param otherRow row to be checked
	 * @return index
	 */
	private static int indexOfFirstNoneZero(ArrayList<Rational> otherRow) {
		for (int i = 0; i < otherRow.numElements(); i++) {
			if (otherRow.getElement(i).getValue() != 0) return i;
		}
		return -1;
	}
	public boolean matrixIsReduced(ArrayList<ArrayList<Rational>> matrix){
		int valuesInCollumn = 0;
		int rowsize = matrix.getElement(0).numElements();
		for(int col = 0; col < rowsize; col++){
			valuesInCollumn = 0;
			for (int row = 0; row < matrix.numElements(); row++){
				if (matrix.getElement(row).getElement(col).getValue() != 0) valuesInCollumn++;
			}
			if (valuesInCollumn > 1) return false;
		}
		return true;
	}
	/**
	 * Takes elements from temp1 multiplies them by val, adds them to temp 2 and stores them in temp 2
	 * @param temp1 first row
	 * @param temp2 second row
	 * @param val value
	 */
	private static void multAddAndStore(ArrayList<Rational> temp1, ArrayList<Rational> temp2, Rational val) {
		for (int i = 0; i < temp2.numElements();i++){
			temp2.setElement(i, temp1.getElement(i).multiply(val).add(temp2.getElement(i)));
		}
	}

	private static void divideRow(ArrayList<Rational> temp, Rational val) {
		for (int i = 0; i < temp.numElements(); i ++){
			temp.setElement(i, temp.getElement(i).divide(val));
		}
	}

	private static void swap(ArrayList<ArrayList<Rational>> matrix, int index1, int index2) {
		ArrayList<Rational> temp = matrix.getElement(index1);
		matrix.setElement(index1,matrix.getElement(index2));
		matrix.setElement(index2,temp);
	}

	public static void print(ArrayList<ArrayList<Rational>> matrix) {
		for (ArrayList<Rational> row: matrix) {
			for (Rational dbl: row) {
				System.out.print(dbl + " ");
			}
			System.out.println();
		}
	}
	private static void readItIn(ArrayList<ArrayList<Rational>> matrix) {
		String input;
		String[] arrInput;
		do {
			System.out.println("Please enter a valid row (seperate entries with spaces)");
			input = in.nextLine();
			arrInput = input.split(" ");
		} while (!checkForValidity(arrInput));
		int size = arrInput.length; //Defines number of collumns
		newMatrixRow(matrix,arrInput); //Adds a new row
		System.out.println("First row entered successfully, now we'll read the \nremaining rows, type in f when done");
		while (true) {
			//Read
			System.out.println("Please enter a valid row (seperate entries with spaces)");
			input = in.nextLine();

			//check if done
			if (input.equals("f")) break;

			//Proceed to split
			arrInput = input.split(" ");

			//Check for validity
			if (arrInput.length != size){System.out.println("Row size mismatch, row not added"); continue;} //Checks if size is wrong
			if (!checkForValidity(arrInput)) { System.out.println("Intries invalid, row not added");continue;}

			//Create the new row
			newMatrixRow(matrix,arrInput);
		}
	}
	private static void newMatrixRow(ArrayList<ArrayList<Rational>> matrix, String[] arrInput) {
		ArrayList<Rational> row = new ArrayList<>();
		for (String dbl: arrInput) {
			row.addElement(new Rational(dbl));
		}
		matrix.addElement(row);
	}
	private static boolean checkForValidity(String[] arrInput) {
		for (String s: arrInput) {
			if (!isRational(s)) return false;
		}
		return true;
	}
	private static boolean isRational(String dbl) {
		int countDots = 0;
		int i = 0;
		if (dbl.charAt(0) == '-') i = 1;
		boolean found;
		for (; i < dbl.length(); i++) {
			found = false;
			if(dbl.charAt(i) == '.') {
				countDots++;
				continue;
			}
			for (char c = '0'; c <= '9'; c++) {
				if (dbl.charAt(i) == c)
					found = true;
			}
			if (!found)
				return false;
		}
		if (countDots > 1) return false;
		return true;
	}
}