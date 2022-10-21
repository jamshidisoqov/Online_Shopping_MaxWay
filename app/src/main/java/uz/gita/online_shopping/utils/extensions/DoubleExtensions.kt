package uz.gita.online_shopping.utils.extensions

import java.math.BigDecimal

// Created by Jamshid Isoqov an 10/12/2022


fun Double.getFinanceType(): String = "$this sum"

fun BigDecimal.getFinanceType(): String = "$this sum"