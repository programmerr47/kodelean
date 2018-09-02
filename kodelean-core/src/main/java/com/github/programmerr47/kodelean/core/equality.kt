package com.github.programmerr47.kodelean.core

fun <T> equal(a: T, b: T, vararg selectors: (T) -> Any?) = selectors.all { equal(a, b, it) }
fun <T> equal(a: T, b: T, selectors: List<(T) -> Any?>) = selectors.all { equal(a, b, it) }

inline fun <T> equal(a: T, b: T, selector: (T) -> Any?) = selector(a) == selector(b)

fun <T> equalTo(a: T?, b: T?, c: T?) = satisfy(a, b, c) { a, b -> a == b}
fun <T> notEqualTo(a: T?, b: T?, c: T?) = satisfy(a, b, c) { a, b -> a != b}

inline fun <T> satisfy(a: T, b: T, c: T, cond: (T, T) -> Boolean) = cond(a, b) && cond(b, c)