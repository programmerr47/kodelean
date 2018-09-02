package com.github.programmerr47.kodelean.core

import org.junit.Test
import java.math.BigDecimal
import java.math.BigDecimal.ONE
import java.math.BigDecimal.ZERO
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class BigDecimalProgressionIteratorTest {

    @Test
    fun checkUpProgression() {
        val bdIterator = BigDecimalProgressionIterator(ZERO, ONE, BigDecimal("0.3"))
        assertTrue(bdIterator.hasNext())
        assertEquals(ZERO, bdIterator.next())
        assertEquals(BigDecimal("0.3"), bdIterator.next())
        assertEquals(BigDecimal("0.6"), bdIterator.next())
        assertEquals(BigDecimal("0.9"), bdIterator.next())
        assertTrue(bdIterator.hasNext())
        assertEquals(BigDecimal("1.2"), bdIterator.next())
        assertFalse(bdIterator.hasNext())
    }

    @Test
    fun checkUpProgressionWithReversedRange() {
        val bdIterator = BigDecimalProgressionIterator(ZERO, ONE, BigDecimal("-0.3"))
        assertFalse(bdIterator.hasNext())
    }

    @Test(expected = NoSuchElementException::class)
    fun checkUpProgressionWithReversedRangeTakeNext() {
        BigDecimalProgressionIterator(ZERO, ONE, BigDecimal("-0.3")).next()
    }

    @Test
    fun checkDownProgression() {
        val bdIterator = BigDecimalProgressionIterator(ONE, ZERO, BigDecimal("-0.3"))
        assertTrue(bdIterator.hasNext())
        assertEquals(ONE, bdIterator.next())
        assertEquals(BigDecimal("0.7"), bdIterator.next())
        assertEquals(BigDecimal("0.4"), bdIterator.next())
        assertEquals(BigDecimal("0.1"), bdIterator.next())
        assertTrue(bdIterator.hasNext())
        assertEquals(BigDecimal("-0.2"), bdIterator.next())
        assertFalse(bdIterator.hasNext())
    }

    @Test
    fun checkDownProgressionWithReversedRange() {
        val bdIterator = BigDecimalProgressionIterator(ONE, ZERO, BigDecimal("0.3"))
        assertFalse(bdIterator.hasNext())
    }

    @Test(expected = NoSuchElementException::class)
    fun checkDownProgressionWithReversedRangeTakeNext() {
        BigDecimalProgressionIterator(ONE, ZERO, BigDecimal("0.3")).next()
    }

    @Test(expected = IllegalArgumentException::class)
    fun checkStepZero() {
        BigDecimalProgressionIterator(ZERO, ZERO, ZERO)
    }
}