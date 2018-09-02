package com.github.programmerr47.kodelean.core

import org.junit.Test
import java.math.BigDecimal
import java.math.BigDecimal.ONE
import java.math.BigDecimal.ZERO
import kotlin.test.assertEquals
import kotlin.test.assertFalse


class BigDecimalProgressionTest {

    @Test
    fun checkCorrectAdjustingLastValueInUpProgression() {
        (ONE..ZERO step BigDecimal(0.3))

        val progression = BigDecimalProgression(ZERO, ONE, BigDecimal("0.3"))
        assertEquals(BigDecimal("0.9"), progression.last)

        val iterator = progression.iterator()
        assertEquals(ZERO, iterator.next())
        assertEquals(BigDecimal("0.3"), iterator.next())
        assertEquals(BigDecimal("0.6"), iterator.next())
        assertEquals(BigDecimal("0.9"), iterator.next())
        assertFalse(iterator.hasNext())
    }

    @Test
    fun checkCorrectAdjustingLastValueInUpProgressionWithReversedStep() {
        val progression = BigDecimalProgression(ZERO, ONE, BigDecimal("-0.3"))
        assertEquals(BigDecimal("0.9"), progression.last)

        val iterator = progression.iterator()
        assertFalse(iterator.hasNext())
    }

    @Test
    fun checkCorrectAdjustingLastValueInDownProgression() {
        val progression = BigDecimalProgression(ONE, ZERO, BigDecimal("-0.3"))
        assertEquals(BigDecimal("0.1"), progression.last)

        val iterator = progression.iterator()
        assertEquals(ONE, iterator.next())
        assertEquals(BigDecimal("0.7"), iterator.next())
        assertEquals(BigDecimal("0.4"), iterator.next())
        assertEquals(BigDecimal("0.1"), iterator.next())
        assertFalse(iterator.hasNext())
    }

    @Test
    fun checkCorrectAdjustingLastValueInDownProgressionWithReversedStep() {
        val progression = BigDecimalProgression(ONE, ZERO, BigDecimal("0.3"))
        assertEquals(BigDecimal("0.1"), progression.last)

        val iterator = progression.iterator()
        assertFalse(iterator.hasNext())
    }

    @Test(expected = IllegalArgumentException::class)
    fun checkZeroStep() {
        BigDecimalProgression(ZERO, ZERO, ZERO)
    }
}