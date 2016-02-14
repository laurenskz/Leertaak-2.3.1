package test;

import static org.junit.Assert.*;

import multiformat.Rational;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit Testcase to test Rational. 
 * Note that this class uses 'annotations' (the @...). This is a Java 1.5 feature. 
 * @author gebruiker
 *
 */
public class TestRational {
	public static final float DELTA = 0.001f;
	Rational r;
	
	@Before
	public void setUp(){
		r = new Rational();
	}
	
	@Test
	public void testSimplify() {
		r.setNumerator(25);
		r.setDenominator(5);
		r.simplify();
		
		assertEquals(5.0, r.getNumerator(), DELTA);
		assertEquals(1.0, r.getDenominator(), DELTA);
		
		r.setNumerator(10);
		r.setDenominator(0.5);
		r.simplify();
		
		assertEquals(10.0, r.getNumerator(), DELTA);
		assertEquals(0.5, r.getDenominator(), DELTA);
	}
	
	@Test
	public void testCanonical() {
		r.setNumerator(12.5);
		r.setDenominator(1.0);
		r.canonical();
		
		assertEquals(125.0, r.getNumerator(), DELTA);
		assertEquals(10.0, r.getDenominator(), DELTA);

		r.setNumerator(12.5);
		r.setDenominator(0.01);
		r.canonical();
		
		assertEquals(125.0, r.getNumerator(), DELTA);
		assertEquals(0.1, r.getDenominator(), DELTA);
	}
	
	@Test
	public void testCanonicalAndSimplify() {
		r.setNumerator(12.5);
		r.setDenominator(1.0);
		r.canonical();
		r.simplify();
		
		assertEquals(25.0, r.getNumerator(), DELTA);
		assertEquals(2.0, r.getDenominator(), DELTA);
	}



}
