package io.blazeq.aspenny.models

data class Target(
    val name: String,
    val unitPrice: Double,
    val description: String,
    val image: String,
    val link: String,
    val favorite: Boolean,
    val type: String
) {
    // default constructor for FireBase
    constructor(): this(
        "unknown",
        0.0,
        "",
        "",
        "",
        false,
        "predefined")
}