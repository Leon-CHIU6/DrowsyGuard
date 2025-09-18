package com.patrick.main.ui

// UI 狀態模型
sealed class FatigueUiState {
    object Calibrating : FatigueUiState()     // 校正中
    object Normal : FatigueUiState()          // 正常
    object ModerateAlert : FatigueUiState()   // 輕度疲勞 (MEDIUM)
    object HighAlert : FatigueUiState()       // 高度疲勞 (HIGH)
    object SevereDialog : FatigueUiState()    // 嚴重疲勞 (SEVERE)
    object RestReminder : FatigueUiState()    // 提醒休息
}

// 動作事件
sealed class FatigueAction {
    object Acknowledge : FatigueAction()
    object RequestRest : FatigueAction()
    object Dismiss : FatigueAction()
}
