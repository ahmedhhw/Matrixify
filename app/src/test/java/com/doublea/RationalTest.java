package com.doublea;

import org.junit.Test;

import static org.junit.Assert.*;

public class RationalTest {

    @Test
    public void decimalToRational() {
        Rational num1 = new Rational(2.5);
        assertEquals("5/2",num1.toString());
    }

    @Test
    public void simplify() {
    }

    @Test
    public void greatestCommonFactor() {
    }

    @Test
    public void stringToRational() {
        Rational num1 = new Rational("5/25");
        assertEquals("1/5",num1.toString());
    }

    @Test
    public void checkType() {
        Rational num1 = new Rational("5/515");
        assertEquals(-1,num1.checkType("2.5/15"));
        assertEquals(1,num1.checkType("25/15"));
        assertEquals(2,num1.checkType("2.5"));

    }

    @Test
    public void multiply() {
        Rational num1 = new Rational("17/3");
        Rational num2 = new Rational("5/14");
        Rational res = num1.multiply(num2);
        System.out.println(res);
    }

    @Test
    public void divide() {
        Rational num1 = new Rational("17/3");
        Rational num2 = new Rational("5/14");
        Rational res = num1.divide(num2);
        System.out.println(res);
    }

    @Test
    public void add() {
        Rational num1 = new Rational("17/3");
        Rational num2 = new Rational("5/14");
        Rational res = num1.add(num2);
        System.out.println(res);
    }

    @Test
    public void subtract() {
        Rational num1 = new Rational("17/3");
        Rational num2 = new Rational("5/14");
        Rational res = num1.subtract(num2);
        System.out.println(res);
    }
}