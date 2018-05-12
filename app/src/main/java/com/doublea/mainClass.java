package com.doublea;


import java.util.Scanner;
public class mainClass {
	private static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		ArrayList<ArrayList<Double>> matrix = new ArrayList<>();
		readItIn(matrix);
		//print(matrix);
		reduce(matrix);
	}
	private static void reduce(ArrayList<ArrayList<Double>> matrix) {

	}
	private static void print(ArrayList<ArrayList<Double>> matrix) {
		for (ArrayList<Double> row: matrix) {
			for (Double dbl: row) {
				System.out.print(dbl + " ");
			}
			System.out.println();
		}
	}
	private static void readItIn(ArrayList<ArrayList<Double>> matrix) {
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
	private static void newMatrixRow(ArrayList<ArrayList<Double>> matrix, String[] arrInput) {
		ArrayList<Double> row = new ArrayList<>();
		for (String dbl: arrInput) {
			row.addElement(new Double(dbl));
		}
		matrix.addElement(row);
	}
	private static boolean checkForValidity(String[] arrInput) {
		for (String s: arrInput) {
			if (!isDouble(s)) return false;
		}
		return true;
	}
	private static boolean isDouble(String dbl) {
		int countDots = 0;
		boolean found;
		for (int i = 0; i < dbl.length(); i++) {
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
