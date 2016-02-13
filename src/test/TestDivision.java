package test;

import junit.framework.TestCase;
import multiformat.Rational;

/**
 * Created by Laurens on 13-2-2016.
 */
public class TestDivision extends TestCase {


    public TestDivision(String arg0) {
        super(arg0);
    }

    public void testDivsionByZero(){
        try {
            Rational operandOne = new Rational();//This will be zero
            Rational operandTwo = new Rational(1,1);
            operandTwo.div(operandOne);
            fail("Dividing by zero did not throw an exception");
        } catch (ArithmeticException e) {
            //It is actually good if we and up here, so we don't print the stacktrace as this will indicate something went wrong.
        }
    }
}
