package com.example.musiclistapplication.utils

enum class ItemTypeEnum {
    TEXT,
    AUDIO,
    LINK;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}