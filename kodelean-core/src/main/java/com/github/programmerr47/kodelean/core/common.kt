package com.github.programmerr47.kodelean.core

fun hashCode(vararg fields: Any?) =
        fields.map { it?.hashCode() ?: 0 }.reduce { acc, field -> acc * 31 + field}