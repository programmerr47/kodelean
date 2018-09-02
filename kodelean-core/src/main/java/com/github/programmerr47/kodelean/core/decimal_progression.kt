package com.github.programmerr47.kodelean.core

import java.math.BigDecimal
import java.math.BigDecimal.ZERO
import java.util.NoSuchElementException

infix fun ClosedRange<BigDecimal>.step(step: BigDecimal) =
        BigDecimalProgression(start, endInclusive, step)

infix fun BigDecimalProgression.step(step: BigDecimal) =
        BigDecimalProgression(first, last, if (this.step > BigDecimal.ZERO) step else -step)

class BigDecimalProgression(start: BigDecimal, endInclusive: BigDecimal, val step: BigDecimal)
    : Iterable<BigDecimal> {

    val first: BigDecimal = start
    val last: BigDecimal = getProgressionLastElement(start, endInclusive, step)

    init {
        if (step == BigDecimal.ZERO) throw IllegalArgumentException("Step must be non-zero")
    }

    override fun iterator(): Iterator<BigDecimal> = BigDecimalProgressionIterator(first, last, step)

    fun isEmpty() = if (step > BigDecimal.ZERO) first > last else first < last

    override fun equals(other: Any?) =
            other is BigDecimalProgression && (isEmpty() && other.isEmpty() ||
                    first == other.first && last == other.last && step == other.step)

    override fun hashCode() = if (isEmpty()) -1 else hashCode(first, last, step)

    override fun toString() = if (step > BigDecimal.ZERO) "$first..$last step $step" else "$first downTo $last step ${-step}"

    private fun getProgressionLastElement(start: BigDecimal, end: BigDecimal, step: BigDecimal) = when {
        step != ZERO -> start + BigDecimal(((end - start) / step).toInt()) * step
        else -> throw IllegalArgumentException("Step is zero.")
    }
}

class BigDecimalProgressionIterator(
        first: BigDecimal,
        private val last: BigDecimal,
        private val step: BigDecimal) : Iterator<BigDecimal> {
    private val stepSignum = step.signum()
    private var hasNext = if (stepSignum >= 0) last >= first else first >= last
    private var next = if (hasNext) first else last

    init {
        if (step == BigDecimal.ZERO) throw IllegalArgumentException("Step must be non-zero")
    }

    override fun hasNext() = hasNext

    override fun next(): BigDecimal {
        val value = next
        if (value == last || value.compareTo(last) == stepSignum) {
            if (!hasNext) throw NoSuchElementException()
            hasNext = false
        } else {
            next += step
        }
        return value
    }
}