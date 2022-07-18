package com.tavieto.textsections.textsection

enum class Tag(
    val id: String,
    val annotation: String
) {
    LINK(id = "link", annotation = "link_annotation"),
    BOLD(id = "bold", annotation = "bold_annotation"),
    DEFAULT(id = "default", annotation = "default_annotation");

    companion object {
        fun getById(id: String): Tag = values().firstOrNull {
            id == it.id
        } ?: DEFAULT
    }
}
