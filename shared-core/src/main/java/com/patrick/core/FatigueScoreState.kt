package com.patrick.core

data class FatigueScoreState(
    val score: Int,              // 0 ~ 100
    val level: FatigueLevel      // NORMAL / MEDIUM / HIGH / SEVERE
)
