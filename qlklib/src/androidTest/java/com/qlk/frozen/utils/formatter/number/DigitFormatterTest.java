package com.qlk.frozen.utils.formatter.number;

import android.util.Log;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/12/14 11:25
 */
public class DigitFormatterTest {
    private DigitFormatter.WeightFormatter weightFormatter = new DigitFormatter.WeightFormatter();

    @Test
    public void isLawful() {
        assertTrue(weightFormatter.isLawful(""));
        assertTrue(weightFormatter.isLawful(null));
        assertTrue(weightFormatter.isLawful("1"));
        assertTrue(weightFormatter.isLawful("1.1"));
        assertTrue(weightFormatter.isLawful("1.11"));
        assertTrue(weightFormatter.isLawful("1.111"));
        assertFalse(weightFormatter.isLawful("1.1111"));
        assertFalse(weightFormatter.isLawful("."));
        assertTrue(weightFormatter.isLawful("1."));
        assertTrue(weightFormatter.isLawful(".1"));
        assertTrue(weightFormatter.isLawful("-1"));
        assertTrue(weightFormatter.isLawful("-1."));
        assertTrue(weightFormatter.isLawful("-1.1"));
        assertTrue(weightFormatter.isLawful("-1.11"));
        assertTrue(weightFormatter.isLawful("-1.111"));
        assertFalse(weightFormatter.isLawful("-1.1111"));
        assertFalse(weightFormatter.isLawful("a-1.1111"));
        assertFalse(weightFormatter.isLawful("-1.11s11"));
        assertTrue(weightFormatter.isLawful("-1.1e2"));
        assertTrue(weightFormatter.isLawful("-1.1e-2"));
        assertTrue(weightFormatter.isLawful("-1.1e3"));
        assertFalse(weightFormatter.isLawful("-1.1e-3"));
    }

    @Test
    public void format() {
        assertEquals("",weightFormatter.format(""));
        assertEquals("",weightFormatter.format("null"));
        Log.i("DigitFormatterTest", "format: "+weightFormatter.format("1"));
        assertEquals("1",weightFormatter.format("1"));
        assertEquals("1",weightFormatter.format("1."));
        assertEquals("",weightFormatter.format("."));
        assertEquals("0.1",weightFormatter.format(".1"));
        assertEquals("1.1",weightFormatter.format("1.1"));
        assertEquals("1.11",weightFormatter.format("1.11"));
        assertEquals("1.111",weightFormatter.format("1.111"));
        assertEquals("1100",weightFormatter.format("1.1e3"));
        assertEquals("0.001",weightFormatter.format("1.13e-3"));
        assertEquals("0.002",weightFormatter.format("1.5e-3"));
        assertEquals("1.111",weightFormatter.format("1.11111111"));
        assertEquals("1.116",weightFormatter.format("1.1156"));
        assertEquals("-1.116",weightFormatter.format("-1.1156"));
        assertEquals("-1.115",weightFormatter.format("-1.1154"));
        assertEquals("-1.111",weightFormatter.format("-1.1111"));
        assertEquals("1.1",weightFormatter.format("1.1a11"));
        assertEquals("1.111",weightFormatter.format("de1.111bbd"));
        assertEquals("1.112",weightFormatter.format("a1.1118bbd"));
        assertEquals("110",weightFormatter.format("aa1.1e2ccd"));
        assertEquals("1.1",weightFormatter.format("aa1.1f2ccd"));
    }

    @Test
    public void toNumber() {
    }

    @Test
    public void toDigit() {
    }

    @Test
    public void toDigit1() {
    }
}