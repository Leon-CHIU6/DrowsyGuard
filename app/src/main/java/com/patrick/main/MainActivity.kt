package com.patrick.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.camera.view.PreviewView
import com.patrick.camera.CameraViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import com.patrick.main.ui.FatigueMainScreen
import com.patrick.core.FatigueLevel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CameraScreen()
        }
    }
}

@Composable
fun CameraScreen() {
    val context = LocalContext.current.applicationContext
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraViewModel: CameraViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return CameraViewModel(context as android.app.Application) as T
            }
        }
    )

    // 原本的狀態
    val fatigueLevel by cameraViewModel.fatigueLevel.collectAsState()
    val calibrationProgress by cameraViewModel.calibrationProgress.collectAsState()
    val isCalibrating by cameraViewModel.isCalibrating.collectAsState()
    val showFatigueDialog by cameraViewModel.showFatigueDialog.collectAsState()

    // ✅ 新增：分數狀態
    val fatigueScore by cameraViewModel.fatigueScore.collectAsState()
    val fatigueScoreLevel by cameraViewModel.fatigueScoreLevel.collectAsState()

    val previewView = remember { PreviewView(context) }

    LaunchedEffect(previewView, lifecycleOwner) {
        cameraViewModel.initializeCamera(previewView, lifecycleOwner)
    }

    FatigueMainScreen(
        fatigueLevel = fatigueLevel,
        calibrationProgress = calibrationProgress,
        isCalibrating = isCalibrating,
        showFatigueDialog = showFatigueDialog,
        previewView = previewView,
        fatigueScore = fatigueScore,                  // ✅ 傳入
        fatigueScoreLevel = fatigueScoreLevel,        // ✅ 傳入
        onUserAcknowledged = { cameraViewModel.onUserAcknowledged() },
        onUserRequestedRest = { cameraViewModel.onUserRequestedRest() }
    )
}
