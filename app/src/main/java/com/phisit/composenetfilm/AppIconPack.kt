package com.phisit.composenetfilm

import androidx.compose.ui.graphics.vector.ImageVector
import com.phisit.composenetfilm.appiconpack.Search
import kotlin.collections.List as ____KtList

public object AppIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val AppIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(Search)
    return __AllIcons!!
  }
