package com.doublea.matrixify.Utils;


public class MatrixOperations {
    public ArrayList<ArrayList<Rational>> matrix;

    public void initiateMatrix() {
        matrix = new ArrayList<>();
    }

    /**
     *
     */
    public void reduce() {
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
                        swap(col, row);
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
     *
     * @param otherRow row to be checked
     * @return index
     */
    private int indexOfFirstNoneZero(ArrayList<Rational> otherRow) {
        for (int i = 0; i < otherRow.numElements(); i++) {
            if (otherRow.getElement(i).getValue() != 0) return i;
        }
        return -1;
    }

    public boolean matrixIsReduced() {
        int valuesInCollumn = 0;
        int rowsize = matrix.getElement(0).numElements();
        for (int col = 0; col < rowsize; col++) {
            valuesInCollumn = 0;
            for (int row = 0; row < matrix.numElements(); row++) {
                if (matrix.getElement(row).getElement(col).getValue() != 0) valuesInCollumn++;
            }
            if (valuesInCollumn > 1) return false;
        }
        return true;
    }

    /**
     * Takes elements from temp1 multiplies them by val, adds them to temp 2 and stores them in temp 2
     *
     * @param temp1 first row
     * @param temp2 second row
     * @param val   value
     */
    private void multAddAndStore(ArrayList<Rational> temp1, ArrayList<Rational> temp2, Rational val) {
        for (int i = 0; i < temp2.numElements(); i++) {
            temp2.setElement(i, temp1.getElement(i).multiply(val).add(temp2.getElement(i)));
        }
    }

    private void divideRow(ArrayList<Rational> temp, Rational val) {
        for (int i = 0; i < temp.numElements(); i++) {
            temp.setElement(i, temp.getElement(i).divide(val));
        }
    }

    private void swap(int index1, int index2) {
        ArrayList<Rational> temp = matrix.getElement(index1);
        matrix.setElement(index1, matrix.getElement(index2));
        matrix.setElement(index2, temp);
    }

    public String print() {
        String temp = "";
        for (ArrayList<Rational> row : matrix) {
            for (Rational dbl : row) {
                temp += dbl + " ";
            }
            temp+= "\n";
        }
        return temp;
    }

    public void newMatrixRow(String[] arrInput) {
        ArrayList<Rational> row = new ArrayList<>();
        for (String dbl : arrInput) {
            row.addElement(new Rational(dbl));
        }
        matrix.addElement(row);
    }

    public boolean checkForValidity(String[] arrInput) {
        for (String s : arrInput) {
            if (!isRational(s)) return false;
        }
        return true;
    }

    private boolean isRational(String dbl) {
        int check = Rational.checkType(dbl);
        if (check != -1) return true;
        return false;
    }
}