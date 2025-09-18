package com.patrick.core

interface FatigueUiCallback {
    fun onBlink()
    fun onFatigueAlert(message: String)

    // 校正相關回調
    fun onCalibrationStarted() {}
    fun onCalibrationProgress(progress: Int, currentEar: Float) {}
    fun onCalibrationCompleted(
        newThreshold: Float,
        minEar: Float,
        maxEar: Float,
        avgEar: Float
    ) {}

    // 疲勞對話框回調
    fun onUserAcknowledged() {}     // 使用者點擊「我已清醒」
    fun onUserRequestedRest() {}    // 使用者點擊「我會找地方休息」
    fun onModerateFatigue() {}      // 疲勞警告顯示

    // ✅ 新增：分數更新回調（預設空實作）
    fun onScoreUpdated(score: Int, level: FatigueLevel) {}
}
