package com.github.programmerr47.kodelean.reflect

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ReflectionTest {

    @Test
    fun checkJavaPublicField() {
        val instance = PublicPropertiesClass()
        val objectField = instance.getField("intProperty")
        assertNotNull(objectField)
        assertEquals(54, objectField!!.get())
    }

    @Test(expected = IllegalAccessException::class)
    fun checkKotlinPublicProperty() {
        val instance = PublicPropertiesClass()
        val objectField = instance.getField("longProperty")
        assertNotNull(objectField)
        objectField!!.get()
    }

    @Test
    fun checkKotlinPublicPropertyWithForcing() {
        val instance = PublicPropertiesClass()
        val objectField = instance.getField("longProperty")
        assertNotNull(objectField)
        assertEquals(45, objectField!!.forceGet())
    }

    @Test
    fun checkJavaPublicPropertySetting() {
        val instance = PublicPropertiesClass()
        val objectField = instance.getField("intProperty")
        assertNotNull(objectField)
        objectField!!.set(66)
        assertEquals(66, instance.intProperty)
    }

    @Test(expected = IllegalAccessException::class)
    fun checkKotlinPublicPropertySetting() {
        val instance = PublicPropertiesClass()
        val objectField = instance.getField("longProperty")
        assertNotNull(objectField)
        objectField!!.set(66)
    }

    @Test
    fun checkKotlinPublicPropertySettingWithForcing() {
        val instance = PublicPropertiesClass()
        val objectField = instance.getField("longProperty")
        assertNotNull(objectField)
        objectField!!.forceSet(66)
        assertEquals(66, instance.longProperty)
    }

    @Test(expected = IllegalAccessException::class)
    fun checkSuperClassKotlinPrivatePropertySetting() {
        val instance = PublicPropertiesClass()
        val objectField = instance.getField("strProperty")
        assertNotNull(objectField)
        objectField!!.set("tttest")
    }

    @Test
    fun checkSuperClassKotlinPrivatePropertySettingWithForcing() {
        val instance = PublicPropertiesClass()
        val objectField = instance.getField("strProperty")
        assertNotNull(objectField)
        objectField!!.forceSet("tttest")
        assertEquals("tttest", instance.strProperty)
    }
}

private class PublicPropertiesClass : PrivatePropertiesClass() {
    @JvmField var intProperty: Int = 54
    var longProperty: Int = 45
}

private open class PrivatePropertiesClass {
    var strProperty: String = "test"
        private set
}