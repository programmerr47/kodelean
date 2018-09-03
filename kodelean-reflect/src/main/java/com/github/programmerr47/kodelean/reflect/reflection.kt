package com.github.programmerr47.kodelean.reflect

import java.lang.reflect.AccessibleObject
import java.lang.reflect.Field
import java.lang.reflect.Method
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.isAccessible

fun Any.getField(name: String): ObjectField? {
    return javaClass
            .findThroughHierarchy {
                try {
                    it.getDeclaredField(name)
                } catch (ignored: NoSuchFieldException) {
                    null
                }
            }
            ?.let { ObjectField(this, it) }
}

fun Any.getMethod(name: String, vararg paramTypes: Class<*>): ObjectMethod? {
    return javaClass
            .findThroughHierarchy {
                try {
                    it.getDeclaredMethod(name, *paramTypes)
                } catch (ignored: NoSuchMethodException) {
                    null
                }
            }
            ?.let { ObjectMethod(this, it) }
}

inline fun <T> Class<*>.findThroughHierarchy(action: (Class<*>) -> T?): T? {
    forEachSuperclass { action(it)?.let { return it } }
    return null
}

inline fun Class<*>.forEachSuperclass(action: (Class<*>) -> Unit) {
    var clazz: Class<*>? = this
    do {
        clazz?.let {
            action(it)
            clazz = it.superclass
        }
    } while (clazz != null)
}

inline fun <T : AccessibleObject, R> T.withForceAccess(action: T.() -> R): R {
    val old = isAccessible
    isAccessible = true
    val result = action()
    isAccessible = old
    return result
}

fun <T> KProperty.Getter<T>.callWithForceAccess(vararg args: Any?): T {
    val isAccessible = property.isAccessible
    if (!isAccessible) property.isAccessible = true
    val isGetterAccessible = this.isAccessible
    if (!isGetterAccessible) this.isAccessible = true
    val result = call(*args)
    this.isAccessible = isGetterAccessible
    property.isAccessible = isAccessible
    return result
}

class ObjectField(val obj: Any, val field: Field) {
    fun get() = field.get(obj)
    fun set(value: Any) = field.set(obj, value)
    fun forceGet() = field.withForceAccess { get() }
    fun forceSet(value: Any) = field.withForceAccess { set(value) }
}

class ObjectMethod(val obj: Any, val method: Method) {
    fun invoke(vararg params: Any) = method.invoke(obj, *params)
    fun forceInvoke(vararg params: Any) = method.withForceAccess { invoke(*params) }
}