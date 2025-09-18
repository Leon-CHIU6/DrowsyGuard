package com.patrick.core

/**
 * 应用常量定义
 */
object Constants {

    // 应用信息
    const val APP_NAME = "疲勞Bye"
    const val APP_VERSION = "1.0.0"

    // 疲劳检测相关常量
    object FatigueDetection {
        const val DEFAULT_EAR_THRESHOLD = 1.25f
        const val MIN_EAR_THRESHOLD = 0.15f
        const val MAX_EAR_THRESHOLD = 0.35f

        const val DEFAULT_MAR_THRESHOLD = 0.7f
        const val MIN_MAR_THRESHOLD = 0.5f
        const val MAX_MAR_THRESHOLD = 0.9f

        const val EYE_CLOSURE_DURATION_THRESHOLD = 1500L
        const val YAWN_DURATION_THRESHOLD = 1000L
        const val BLINK_INTERVAL_MIN = 200L
        const val BLINK_FREQUENCY_THRESHOLD = 20

        const val FATIGUE_EVENT_THRESHOLD = 3
        const val SEVERE_FATIGUE_MULTIPLIER = 2
    }

    // 警报相关常量
    object Alert {
        const val ALERT_DURATION_MS = 3000L
        const val SOUND_ALERT_DURATION_MS = 2000L
        const val VIBRATION_DURATION_MS = 500L
        const val EMERGENCY_ALERT_DURATION_MS = 5000L
    }

    // 摄像头相关常量
    object Camera {
        const val DEFAULT_CAMERA_FACING = "front"
        const val CAMERA_ASPECT_RATIO = "4:3"
        const val CAMERA_PREVIEW_WIDTH = 640
        const val CAMERA_PREVIEW_HEIGHT = 480
    }

    // 文件相关常量
    object Files {
        const val WARNING_SOUND_FILE = "warning.wav"
        const val EMERGENCY_SOUND_FILE = "emergency.wav"
        const val FACE_LANDMARKER_MODEL = "face_landmarker.task"
    }

    const val FACE_LANDMARKER_MODEL_PATH = "face_landmarker.task"

    // 字体相关常量
    object Fonts {
        const val NOTO_SANS_CJK = "fonts/NotoSansCJK-Regular.ttc"
        const val NOTO_SANS_CJK_BOLD = "fonts/NotoSansCJK-Bold.ttc"
        const val NOTO_SANS_CJK_TC = "fonts/NotoSansCJKtc-Regular.otf"
    }

    // 颜色常量 ✅ 正確放這裡
    object Colors {
        const val FATIGUE_NORMAL = "#4CAF50"   // 綠
        const val FATIGUE_MEDIUM = "#FFC107"   // 黃
        const val FATIGUE_HIGH = "#FF9800"     // 橘
        const val FATIGUE_SEVERE = "#F44336"   // 紅
        const val WARNING_BACKGROUND = "#FFF59D" // ⚠️ 警告背景 (淡黃)
    }

    // 本地化字符串键
    object StringKeys {
        const val FATIGUE_DETECTED = "fatigue_detected"
        const val MODERATE_FATIGUE_WARNING = "moderate_fatigue_warning"
        const val SEVERE_FATIGUE_WARNING = "severe_fatigue_warning"
        const val EYE_CLOSURE_DETECTED = "eye_closure_detected"
        const val YAWN_DETECTED = "yawn_detected"
        const val HIGH_BLINK_FREQUENCY = "high_blink_frequency"
        const val PLEASE_REST = "please_rest"
        const val STOP_DRIVING = "stop_driving"
    }

    // 偏好设置键
    object Preferences {
        const val PREF_NAME = "drowsyguard_preferences"
        const val KEY_EAR_THRESHOLD = "ear_threshold"
        const val KEY_MAR_THRESHOLD = "mar_threshold"
        const val KEY_FATIGUE_EVENT_THRESHOLD = "fatigue_event_threshold"
        const val KEY_SOUND_ENABLED = "sound_enabled"
        const val KEY_VIBRATION_ENABLED = "vibration_enabled"
        const val KEY_ALERT_ENABLED = "alert_enabled"
    }

    // 数据库相关常量
    object Database {
        const val DATABASE_NAME = "drowsyguard.db"
        const val DATABASE_VERSION = 1
        const val TABLE_FATIGUE_EVENTS = "fatigue_events"
        const val TABLE_USER_SETTINGS = "user_settings"
        const val COLUMN_ID = "id"
        const val COLUMN_TIMESTAMP = "timestamp"
        const val COLUMN_EVENT_TYPE = "event_type"
        const val COLUMN_FATIGUE_LEVEL = "fatigue_level"
        const val COLUMN_DURATION = "duration"
        const val COLUMN_DESCRIPTION = "description"
    }

    // 权限相关常量
    object Permissions {
        const val CAMERA_PERMISSION = "android.permission.CAMERA"
        const val VIBRATE_PERMISSION = "android.permission.VIBRATE"
        const val WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE"
        const val READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE"
    }

    // 错误码
    object ErrorCodes {
        const val CAMERA_INITIALIZATION_FAILED = 1001
        const val FACE_DETECTION_FAILED = 1002
        const val FATIGUE_DETECTION_FAILED = 1003
        const val ALERT_SYSTEM_FAILED = 1004
        const val PERMISSION_DENIED = 1005
        const val MODEL_LOADING_FAILED = 1006
    }
}
