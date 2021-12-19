package com.pnevalle.itunessearch.common

import java.util.*

/**
 * Get the display text for track price
 *
 * @param currency the currency code
 * @param price the amount price
 */
fun getPriceDisplay(currency: String, price: Double): String =
    "${Currency.getInstance(currency).symbol} $price"