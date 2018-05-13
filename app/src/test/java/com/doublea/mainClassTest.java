package com.doublea;

import org.junit.Test;

import static org.junit.Assert.*;

public class mainClassTest {

    @Test
    public void main() {
    }

    @Test
    public void reduce() {
        ArrayList<ArrayList<Rational>> matrix = new ArrayList<>();
        ArrayList<Rational> row1 = new ArrayList<>();
        ArrayList<Rational> row2 = new ArrayList<>();
        ArrayList<Rational> row3 = new ArrayList<>();
        row1.addElement(new Rational(1));row1.addElement(new Rational(2));row1.addElement(new Rational(3));row1.addElement(new Rational(9));
        row2.addElement(new Rational(3));row2.addElement(new Rational(0));row2.addElement(new Rational(-1));row2.addElement(new Rational(3));
        row3.addElement(new Rational(2));row3.addElement(new Rational(-1));row3.addElement(new Rational(1));row3.addElement(new Rational(8));
        matrix.addElement(row2);
        matrix.addElement(row3);
        matrix.addElement(row1);
        mainClass.reduce(matrix);
        mainClass.print(matrix);
    }

    @Test
    public void indexOfFirstNoneZero() {
    }

    @Test
    public void matrixIsReduced() {
    }

    @Test
    public void multAddAndStore() {
    }

    @Test
    public void divideRow() {
    }

    @Test
    public void swap() {
    }

    @Test
    public void print() {
    }

    @Test
    public void readItIn() {
    }

    @Test
    public void newMatrixRow() {
    }

    @Test
    public void checkForValidity() {
    }

    @Test
    public void isRational() {
    }
}