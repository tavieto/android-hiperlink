package com.tavieto.textsections.textsection

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@Composable
fun TextSection(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: Color = Color.Black,
    disabledColor: Color = Color.LightGray,
    style: TextStyle = TextStyle.Default,
    sections: List<Section> = emptyList()
) {

    val stringAnnotated by remember(text) {
        mutableStateOf(
            buildAnnotatedString {
                var sectionIndex = 0
                text.toTextParts().forEach {
                    val section = sections.getOrNull(it.sectionIndex) ?: Section(
                        type = SectionType.Default,
                        color = color,
                        style = style
                    )

                    val textColor = if (enabled) section.color else disabledColor

                    if (it.tagged) {
                        pushStringAnnotation(
                            tag = section.type.tag.id,
                            annotation = section.type.tag.annotation
                        )

                        withStyle(
                            style = section.style.copy(
                                color = textColor
                            ).toSpanStyle()
                        ) {
                            append(it.text)
                        }

                        sectionIndex++
                    } else {
                        pushStringAnnotation(
                            tag = "tag_default",
                            annotation = "annotation_default"
                        )

                        withStyle(
                            style = style.copy(
                                color = color
                            ).toSpanStyle()
                        ) {
                            append(it.text)
                        }
                    }
                    pop()
                }
            }
        )
    }

    ClickableText(
        modifier = modifier,
        text = stringAnnotated,
        onClick = { offSet ->
            sections.forEach {
                val sectionType = it.type
                if (sectionType is SectionType.Link) {
                    stringAnnotated.getStringAnnotations(
                        tag = sectionType.tag.id,
                        start = offSet,
                        end = offSet
                    ).forEach { _ ->
                        if (enabled) sectionType.callback()
                    }
                }
            }
        }
    )
}

private fun String.toTextParts(): List<TextPart> {
    val textSlices = this.split("{#", "#}").trim()
    val parts = mutableListOf<TextPart>()
    textSlices.forEach {
        if (it.contains("/")) {
            val slashIndex = it.indexOf("/")
            val preSlash = it.substring(
                startIndex = 0,
                endIndex = slashIndex
            )
            val text = it.substring(
                startIndex = slashIndex + 1,
                endIndex = it.length
            )

            val sectionIndexOrNull = preSlash.toIntOrNull()

            parts.add(
                TextPart(
                    text = text,
                    tagged = sectionIndexOrNull != null,
                    sectionIndex = sectionIndexOrNull ?: -1
                )
            )
        } else {
            parts.add(
                TextPart(
                    text = it,
                    tagged = false,
                    sectionIndex = -1
                )
            )
        }
    }
    return parts
}

private fun List<String>.trim(): List<String> = if (last().isBlank()) dropLast(1) else this

