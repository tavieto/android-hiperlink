package com.tavieto.textsections.textsection

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

data class Section(
    val type: SectionType,
    val color: Color,
    val style: TextStyle
)
