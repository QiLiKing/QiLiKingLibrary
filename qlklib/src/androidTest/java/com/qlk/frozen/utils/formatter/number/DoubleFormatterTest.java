package com.qlk.frozen.utils.formatter.number;

import android.test.InstrumentationTestCase;
import android.util.Log;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/12/14 11:26
 */
public class DoubleFormatterTest {
    private final DoubleFormatter formatter = new DoubleFormatter();

    @Test
    public void isLawful() {
        assertTrue(formatter.isLawful(""));
        assertTrue(formatter.isLawful(null));
        assertTrue(formatter.isLawful("01"));
        assertTrue(formatter.isLawful("1.1"));
        assertTrue(formatter.isLawful("1."));
        assertTrue(formatter.isLawful("1.1e10"));
        assertTrue(formatter.isLawful("-1.1"));
        assertTrue(formatter.isLawful("-1.1e3"));
        assertTrue(formatter.isLawful("-1.1e10"));
        assertFalse(formatter.isLawful("."));
        assertFalse(formatter.isLawful("-1.1e1011"));
        assertFalse(formatter.isLawful("-1.1e1011e"));
        assertFalse(formatter.isLawful("-s1.1e1011e"));
        assertFalse(formatter.isLawful("as"));
        assertFalse(formatter.isLawful("a1.01s"));

    }

    @Test
    public void format() {
        assertEquals("", formatter.format(null));
        assertEquals("", formatter.format(""));
        assertEquals("1", formatter.format("1"));
        assertEquals("1.1", formatter.format("1.1"));
        assertEquals("1", formatter.format("1."));
        assertEquals("0.1", formatter.format(".1"));
        assertEquals("1", formatter.format("1.0"));
        assertEquals("-1", formatter.format("-1.0"));
        assertEquals("-0.1", formatter.format("-.1"));
        assertEquals("-1", formatter.format("adf-1.0dfdff1.23df"));
        assertEquals("-0.1", formatter.format("adf-.1dfdff1.23df"));
        assertEquals("-1", formatter.format("adf-1.dfdff1.23df"));
        assertEquals("1", formatter.format("adf+1.dfdff1.23df"));
        assertEquals("1", formatter.format("adf1.dfdff1.23df"));
        assertEquals("1.35", formatter.format("adf1.35dfdff1.23df"));
        assertEquals("1350", formatter.format("adf1.35e3dfdff1.23df"));
        assertEquals("1.35", formatter.format("adf1.35edfdff1.23df"));
        assertEquals("1.35", formatter.format("adf1.35e0dfdff1.23df"));
        assertEquals("13.5", formatter.format("adf1.35e01dfdff1.23df"));
    }

    @Test
    public void toNumber() {
        assertEquals(0, formatter.toNumber(null), 0);   //第三个参数表示误差范围
        assertEquals(0, formatter.toNumber(""), 0);
        assertEquals(-1, formatter.toNumber("-1."), 0);
        assertEquals(0, formatter.toNumber("sdff"), 0);
        assertEquals(1.1, formatter.toNumber("1.1"), 0);
        assertEquals(-1.1, formatter.toNumber("-1.1"), 0);
        assertEquals(-1.1, formatter.toNumber("d-1.1dfdsf1.34dfe"), 0);
        assertEquals(-0.1, formatter.toNumber("d-.1dfdsf1.34dfe"), 0);
        assertEquals(0.1, formatter.toNumber("d.1dfdsf1.34dfe"), 0);
        assertEquals(0.1, formatter.toNumber("d.1.dfdsf1.34dfe"), 0);
        assertEquals(-0.1, formatter.toNumber("-.1"), 0);
        assertEquals(-1.0, formatter.toNumber("f-1.ad1q"), 0);
        assertEquals(-1.1, formatter.toNumber("f-1.1q"), 0);
        assertEquals(Double.MAX_VALUE, formatter.toNumber(Double.MAX_VALUE + ""), 0);
    }
}