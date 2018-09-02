package com.github.programmerr47.kodelean.core

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class EqualWithVarargTest(var a: TestClass, var b: TestClass, var selector: Array<(TestClass) -> Any?>, var expected: Boolean) {
    companion object {
        @JvmStatic
        @Parameters
        fun data(): Collection<Array<out Any>> {
            val shareObj = ClassWithoutEquals(43, "Test")
            return listOf(
                    arrayOf(TestClass(0, 1.0, "", shareObj), TestClass(0, 1.0, "", shareObj), arrayOf<(TestClass) -> Any?>(), true),
                    arrayOf(TestClass(0, 1.0, "", ClassWithoutEquals(3, "Test2")), TestClass(0, 1.0, "", shareObj), arrayOf<(TestClass) -> Any?>(), true),
                    arrayOf(TestClass(0, 2.3, "Misha", ClassWithoutEquals(3, "Test2")), TestClass(34, 2.3, "Kolya", ClassWithoutEquals(3, "Test2")), arrayOf<(TestClass) -> Any?>(TestClass::f2), true),
                    arrayOf(TestClass(0, 2.3, "Misha", ClassWithoutEquals(3, "Test2")), TestClass(34, 2.3, "Kolya", ClassWithoutEquals(3, "Test2")), arrayOf<(TestClass) -> Any?>(TestClass::f2, TestClass::f4), false),
                    arrayOf(TestClass(0, 2.3, "Misha", shareObj), TestClass(34, 2.3, "Kolya", shareObj), arrayOf<(TestClass) -> Any?>(TestClass::f2, TestClass::f4), true),
                    arrayOf(TestClass(0, 2.3, "Misha", shareObj), TestClass(0, 2.3, "Kolya", shareObj), arrayOf<(TestClass) -> Any?>(TestClass::f3), false)
            )
        }
    }


    @Test
    fun checkEqual() {
        assertEquals(expected, equal(a, b, *selector))
    }

    data class TestClass(val f1: Int, val f2: Double, val f3: String, val f4: ClassWithoutEquals)
    class ClassWithoutEquals(val f1: Int, val f2: String)
}