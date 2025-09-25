package com.ramanshsharma07.ecommerce.feature_profile.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramanshsharma07.ecommerce.core.presentation.getPainterForProduct
import com.ramanshsharma07.ecommerce.feature_profile.presentation.viewmodel.ProfileEvent
import com.ramanshsharma07.ecommerce.feature_profile.presentation.viewmodel.ProfileViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileScreen() {
    val viewModel: ProfileViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else if (state.userProfile != null) {
                val profile = state.userProfile!!
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    Image(
                        painter = getPainterForProduct(profile.profilePictureUrl),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(profile.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Text(profile.email, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(modifier = Modifier.height(32.dp))

                    // Menu Items
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        ProfileMenuItem(icon = Icons.Default.PersonOutline, text = "Profile", onClick = {})
                        ProfileMenuItem(icon = Icons.Default.Settings, text = "Setting", onClick = {})
                        ProfileMenuItem(icon = Icons.Default.MailOutline, text = "Contact", onClick = {})
                        ProfileMenuItem(icon = Icons.Default.Share, text = "Share App", onClick = {})
                        ProfileMenuItem(icon = Icons.AutoMirrored.Filled.HelpOutline, text = "Help", onClick = {})
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = { viewModel.onEvent(ProfileEvent.SignOutClicked) }) {
                        Text("Sign Out", color = MaterialTheme.colorScheme.error)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}