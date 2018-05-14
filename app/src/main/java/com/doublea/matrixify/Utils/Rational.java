package com.doublea.matrixify.Utils;

public class Rational {
    public double getTop() {
        return top;
    }
    public double getValue(){
        return top / (double)bottom;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    private double top; //This is double so that it can store irrational numbers
    private int bottom;
    private Rational(){
        top = 0;
        bottom = 0;
    }
    protected Rational(String num)  {
        int type = checkType(num);
        if (type == -1) System.out.println("Invalid rational number");
        else if (type == 1) stringToRational(num);
        else decimalToRational(Double.parseDouble(num));
    }

    protected void decimalToRational(double v) {
        //Making decimal raitonal
        bottom = 1;
        top = v;
        while ((top - (int)top) != 0){
            top *= 10;
            bottom *= 10;
        }

        //simplifying
        simplify();
    }

    /**
     * Uses the greatest common factor method to simplify the rational number
     */
    protected void simplify() {
        int gfc = greatestCommonFactor();
        top /= gfc;
        bottom /= gfc;
        if (bottom == -1){
            top *= -1;
            bottom = 1;
        }
    }

    /**
     * @TODO Improve effeciency
     * Finds the greatest common factor between top and bottom
     * @return greatest common factor
     */
    protected int greatestCommonFactor() {
        if (top > bottom){
            for (int i = Math.abs(bottom); i > 0; i--){
                if (top % i == 0 && bottom % i == 0) return i;
            }
        }else if (bottom > top){
            for (int i = Math.abs((int)top); i > 0; i--){
                if (bottom % i == 0 && top % i == 0) return i;
            }
        }
        return bottom;
    }

    /**
     * Takes in a String and converts it to a rational Object
     * @param num String to be converted
     */
    protected void stringToRational(String num) {
        String[] arr = num.split("/");
        top = Double.parseDouble(arr[0]);
        bottom = Integer.parseInt(arr[1]);

        //Simplify
        simplify();
    }

    protected Rational(double num){
        decimalToRational(num);
    }

    /**
     * -1 invalid rational number
     * 1 number inputted in as rational number
     * 2 number inputted in as a decimal
     * @param num String to be checked
     * @return Described above
     */
    public static int checkType(String num){
        int countDots = 0;
        int countDivides = 0;
        int i = 0;
        if (num.charAt(0) == '-' || num.charAt(0) == '+') i = 1;
        boolean found;
        for (; i < num.length(); i++) {
            found = false;
            if(num.charAt(i) == '.') {
                countDots++;
                continue;
            }else if (num.charAt(i) == '/'){
                countDivides++;
                continue;
            }
            for (char c = '0'; c <= '9'; c++) {
                if (num.charAt(i) == c)
                    found = true;
            }
            if (!found)
                return -1;
        }
        if (countDots == 0 && countDivides == 1) return 1;
        else if (countDivides == 0 && countDots < 2) return 2;
        else return -1;
    }

    /**
     * Multiplies rational with another rational number
     * @param other number to be multiplied with
     * @return Result rational number
     */
    public Rational multiply(Rational other){
        Rational temp = new Rational();
        temp.top = this.top * other.top;
        temp.bottom = this.bottom * other.bottom;
        temp.simplify();
        return temp;
    }

    /**
     * Divides rational by another rational number
     * @param other number to be divided by
     * @return Result rational number
     */
    public Rational divide(Rational other) {
        Rational temp = new Rational();
        temp.top = this.top * other.bottom;
        temp.bottom = (int) (this.bottom * other.top);
        temp.simplify();
        return temp;
    }

    /**
     * Adds rational to rational
     * @param other number to be added
     * @return Result rational
     */
    public Rational add(Rational other) {
        Rational temp = new Rational();
        temp.bottom = this.bottom * other.bottom; // finding a common denominator

        // Adds
        temp.top = this.top * other.bottom + other.top * this.bottom;
        temp.simplify();

        return temp;
    }
    /**
     * Subtracts other from this
     * @param other number to be subtracted
     * @return Result rational
     */
    public Rational subtract(Rational other) {
        Rational temp = new Rational();
        temp.bottom = this.bottom * other.bottom; // finding a common denominator

        // Adds
        temp.top = this.top * other.bottom - other.top * this.bottom;
        temp.simplify();

        return temp;
    }

    @Override
    public String toString(){
        return (bottom != 1 && top != 0)?(int)top + "/" + bottom: (int)top + "";
    }
}
