package com.tavieto.textsections.textsection

sealed class SectionType(val tag: Tag) {
    data class Link(val callback: () -> Unit) : SectionType(tag = Tag.LINK)
    object Bold : SectionType(tag = Tag.BOLD)
    object Default : SectionType(tag = Tag.DEFAULT)
}
