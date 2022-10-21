package uz.gita.online_shopping.utils.extensions
// Created by Jamshid Isoqov an 10/12/2022


fun Throwable.getMessage() = this.message ?: "Unknown error"