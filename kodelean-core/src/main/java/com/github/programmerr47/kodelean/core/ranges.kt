package com.github.programmerr47.kodelean.core

val CharRange.size get() = endInclusive - start + 1
val IntRange.size get() = endInclusive - start + 1
val LongRange.size get() = endInclusive - start + 1