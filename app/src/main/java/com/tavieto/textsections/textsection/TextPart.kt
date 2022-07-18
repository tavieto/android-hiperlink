package com.tavieto.textsections.textsection

data class TextPart(
    val text: String,
    val tagged: Boolean,
    val sectionIndex: Int
) {
    override fun toString(): String {
        return "TextPart(text=${this.text},tagged=${this.tagged},sectionIndex=${this.sectionIndex})"
    }
}
