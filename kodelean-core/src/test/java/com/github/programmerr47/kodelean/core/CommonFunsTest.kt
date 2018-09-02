package com.github.programmerr47.kodelean.core

import org.junit.Test
import java.util.*
import kotlin.test.assertEquals


class CommonFunsTest {

    @Test
    fun checkHashCode() {
        Objects.hash()
        assertEquals(993, TestHashCodeEntity().hashCode())
    }
}

private class TestHashCodeEntity {
    private val hashCodeHolder1: HashCodeHolder? = null
    private val hashCodeHolder2: HashCodeHolder? = null
    private val hashCodeHolder3 = HashCodeHolder()

    override fun hashCode() = hashCode(hashCodeHolder1, hashCodeHolder2, hashCodeHolder3)
}

private class HashCodeHolder {
    override fun hashCode() = 1
}