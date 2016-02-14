package test;

import junit.framework.TestCase;
import multiformat.*;

/**
 * Created by Laurens on 14-2-2016.
 */
public class TestOctalBase extends TestCase {

    public TestOctalBase(String arg0){
        super(arg0);
    }

    public void testFormat(){
        Base base = new OctalBase();
        Format format = new FixedPointFormat();
        try{
            base.parse("8");
            fail("The number accepts digits of the wrong base");
        }catch (NumberBaseException e){

        }
    }

    public void testCalculations(){
        Calculator calculator = new Calculator();
        calculator.setBase(new OctalBase());
        try {
            calculator.addOperand("7");
            calculator.addOperand("1");
            calculator.add();
            assertEquals(calculator.getOperand(0), "10.0");
        } catch (FormatException e) {
            fail("FormatException");
            e.printStackTrace();
        } catch (NumberBaseException e) {
            fail("NumberBaseException");
            e.printStackTrace();
        }
    }
}
