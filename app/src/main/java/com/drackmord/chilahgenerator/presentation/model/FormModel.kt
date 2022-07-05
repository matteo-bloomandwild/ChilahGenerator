package com.drackmord.chilahgenerator.presentation.model

data class FormModel(
    val clientName: String = "",
    val weight: String = "",
    val height: String = "",
    val fatPercent: String = "",
    val visceralFat: String = "",
    val posture: Int = 0,

    val feetCaveIn: LeftRight = LeftRight(),
    val squatKneeCaveIn: LeftRight = LeftRight(),
    val butwink: Boolean = false,
    val hyperextension: Boolean = false,
    val forwardLean: Boolean = false,

    val stepUpKneeCaveIn: LeftRight = LeftRight(),
    val hipShift: LeftRight = LeftRight(),

    val hipHinge: Boolean = false,
    val wallSlide: Boolean = false,

    val pushShrugged: LeftRight = LeftRight(),
    val pushRetraction: Boolean = false,
    val pushHyperextension: Boolean = false,

    val pullShrugged: LeftRight = LeftRight(),
    val pullRetraction: Boolean = false,
    val pullHyperextension: Boolean = false,
)
