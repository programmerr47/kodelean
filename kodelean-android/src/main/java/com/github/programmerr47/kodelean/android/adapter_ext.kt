package com.github.programmerr47.kodelean.android

import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.RecyclerView.ViewHolder
import com.github.programmerr47.kodelean.core.size

fun <T : ViewHolder> Adapter<T>.notifyItemRangeChanged(range: IntRange) = notifyItemRangeChanged(range.start, range.size)

fun <T : ViewHolder> Adapter<T>.notifyItemRangeChanged(range: IntRange, payload: Any) = notifyItemRangeChanged(range.start, range.size, payload)

fun <T : ViewHolder> Adapter<T>.notifyItemRangeInserted(range: IntRange) = notifyItemRangeInserted(range.start, range.size)

fun <T : ViewHolder> Adapter<T>.notifyItemRangeRemoved(range: IntRange) = notifyItemRangeRemoved(range.start, range.size)