package com.patrick.core

import kotlin.math.max
import kotlin.math.min

class FatigueScoreEngine @JvmOverloads constructor(
    private val maxScore: Int = 100,          // 上限
    private val decayPerSecond: Float = 0.4f, // 每秒衰減
    private val wBlink: Int = 2,              // 眨眼 +?
    private val wLongEyeClosurePerSec: Int = 8, // 長閉眼每秒 +?
    private val wYawnPerSec: Int = 10,        // 哈欠每秒 +?
    private val wHighBlinkBurst: Int = 12     // 1 分鐘高頻眨眼 +?
) {
    private var score = 0f
    private var lastUpdateMs = System.currentTimeMillis()

    fun reset() {
        score = 0f
        lastUpdateMs = System.currentTimeMillis()
    }

    private fun applyDecay(nowMs: Long) {
        val dtSec: Float = max(0.0, (nowMs - lastUpdateMs).toDouble()).toFloat() / 1000f
        score = max(0.0, (score - decayPerSecond * dtSec).toDouble()).toFloat()
        lastUpdateMs = nowMs
    }

    fun onBlink() {
        val now = System.currentTimeMillis()
        applyDecay(now)
        score = min(maxScore.toDouble(), (score + wBlink).toDouble()).toFloat()
    }

    fun applyEyeClosure(durationMs: Long) {
        val now = System.currentTimeMillis()
        applyDecay(now)
        val secs = max(0.1, (durationMs / 1000f).toDouble()).toFloat()
        score = min(
            maxScore.toDouble(),
            (score + wLongEyeClosurePerSec * secs).toDouble()
        ).toFloat()
    }

    fun applyYawn(durationMs: Long) {
        val now = System.currentTimeMillis()
        applyDecay(now)
        val secs = max(0.5, (durationMs / 1000f).toDouble()).toFloat()
        score = min(maxScore.toDouble(), (score + wYawnPerSec * secs).toDouble()).toFloat()
    }

    fun applyHighBlinkBurst() {
        val now = System.currentTimeMillis()
        applyDecay(now)
        score = min(maxScore.toDouble(), (score + wHighBlinkBurst).toDouble()).toFloat()
    }

    fun current(): FatigueScoreState {
        val now = System.currentTimeMillis()
        applyDecay(now)
        return FatigueScoreState(Math.round(score), mapLevel(score))
    }

    private fun mapLevel(s: Float): FatigueLevel {
        return when {
            s >= 80f -> FatigueLevel.SEVERE
            s >= 60f -> FatigueLevel.HIGH
            s >= 30f -> FatigueLevel.MEDIUM
            else -> FatigueLevel.NORMAL
        }
    }
}
