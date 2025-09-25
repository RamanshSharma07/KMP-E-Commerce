package com.ramanshsharma07.ecommerce.feature_settings.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramanshsharma07.ecommerce.feature_settings.presentation.viewmodel.SettingsEvent
import com.ramanshsharma07.ecommerce.feature_settings.presentation.viewmodel.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit
) {
    val viewModel: SettingsViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else if (state.userProfile != null && state.appSettings != null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Account Section
                    Text("Account", style = MaterialTheme.typography.titleMedium)
                    SettingAccountItem(profile = state.userProfile!!)

                    // Settings Section
                    Text("Settings", style = MaterialTheme.typography.titleMedium)
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        SettingMenuItem(icon = Icons.Default.Notifications, text = "Notification") {
                            Switch(
                                checked = state.appSettings!!.notificationsEnabled,
                                onCheckedChange = { isChecked ->
                                    viewModel.onEvent(SettingsEvent.OnNotificationToggled(isChecked))
                                }
                            )
                        }
                        SettingMenuItem(
                            icon = Icons.Default.Language,
                            text = "Language"
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    state.appSettings!!.selectedLanguage,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                        SettingMenuItem(icon = Icons.Default.Shield, text = "Privacy")
                        SettingMenuItem(icon = Icons.Default.HeadsetMic, text = "Help Center")
                        SettingMenuItem(icon = Icons.Default.Info, text = "About us")
                    }
                }
            }
        }
    }
}