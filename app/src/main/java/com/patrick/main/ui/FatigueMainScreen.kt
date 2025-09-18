package com.patrick.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.camera.view.PreviewView
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FatigueMainScreen(
    fatigueLevel: com.patrick.core.FatigueLevel,
    calibrationProgress: Int,
    isCalibrating: Boolean,
    showFatigueDialog: Boolean,
    previewView: PreviewView,
    fatigueScore: Int,
    fatigueScoreLevel: com.patrick.core.FatigueLevel,
    onUserAcknowledged: () -> Unit = {},
    onUserRequestedRest: () -> Unit = {}
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItem by remember { mutableStateOf(0) }

    val statusText = when {
        isCalibrating -> "正在校正中..."
        fatigueLevel == com.patrick.core.FatigueLevel.NORMAL -> "偵測中"
        fatigueLevel == com.patrick.core.FatigueLevel.HIGH -> "警告：請注意安全"
        fatigueLevel == com.patrick.core.FatigueLevel.SEVERE -> "請盡快找地方休息"
        else -> "DrowsyGuard"
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { /* Drawer 保持不變 */ }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = statusText,
                            style = MaterialTheme.typography.titleMedium,
                            color = when {
                                isCalibrating -> MaterialTheme.colorScheme.primary
                                fatigueLevel == com.patrick.core.FatigueLevel.NORMAL -> MaterialTheme.colorScheme.onSurface
                                fatigueLevel == com.patrick.core.FatigueLevel.HIGH -> Color(0xFFFF9800)
                                fatigueLevel == com.patrick.core.FatigueLevel.SEVERE -> Color(0xFFF44336)
                                else -> MaterialTheme.colorScheme.onSurface
                            }
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Text("☰", style = MaterialTheme.typography.titleLarge)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.95f)
                    )
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                AndroidView(factory = { previewView }, modifier = Modifier.fillMaxSize())

                Column(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp)
                        .background(
                            MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(8.dp)
                ) {
                    Text("Score: $fatigueScore", style = MaterialTheme.typography.bodyLarge)
                    Text(
                        "Level: ${fatigueScoreLevel.name}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = when (fatigueScoreLevel) {
                            com.patrick.core.FatigueLevel.NORMAL -> Color(0xFF4CAF50)
                            com.patrick.core.FatigueLevel.MEDIUM -> Color(0xFFFFC107)
                            com.patrick.core.FatigueLevel.HIGH -> Color(0xFFFF9800)
                            com.patrick.core.FatigueLevel.SEVERE -> Color(0xFFF44336)
                        }
                    )
                }
            }
        }
    }
}
