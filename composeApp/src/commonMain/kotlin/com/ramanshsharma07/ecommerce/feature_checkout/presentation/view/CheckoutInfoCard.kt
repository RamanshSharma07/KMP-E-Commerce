package com.ramanshsharma07.ecommerce.feature_checkout.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramanshsharma07.ecommerce.core.presentation.purple
import com.ramanshsharma07.ecommerce.feature_checkout.domain.model.DeliveryDetails

@Composable
fun CheckoutInfoCard(
    details: DeliveryDetails,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        InfoRow(
            icon = {
                Icon(
                    Icons.Default.LocationOn,
                    contentDescription = "Location",
                    tint = purple
                )
            },
            title = {
                Text(
                    details.address,
                    style = MaterialTheme.typography.titleMedium
                )
            },
            subtitle = {
                Text(
                    "Saepe eaque fugiat ea voluptatum veniam.",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        )
        InfoRow(
            icon = {
                Icon(
                    Icons.Default.Schedule,
                    contentDescription = "Time",
                    tint = purple
                )
            },
            title = {
                Text(
                    details.deliveryTime,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        )
    }
}

@Composable
private fun InfoRow(
    icon: @Composable () -> Unit,
    title: @Composable () -> Unit,
    subtitle: (@Composable () -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        icon()
        Column {
            title()
            subtitle?.invoke()
        }
    }
}