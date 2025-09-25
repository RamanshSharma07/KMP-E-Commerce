package com.ramanshsharma07.ecommerce.feature_checkout.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.ramanshsharma07.ecommerce.feature_checkout.domain.model.PaymentMethod
import com.ramanshsharma07.ecommerce.feature_checkout.domain.model.PaymentMethodType

@Composable
fun PaymentMethodSelector(
    methods: List<PaymentMethod>,
    selectedMethod: PaymentMethodType,
    onMethodSelected: (PaymentMethodType) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text("Choose payment method", style = MaterialTheme.typography.titleMedium)
        methods.forEach { method ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onMethodSelected(method.type) },
//                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // In a real app, you would map method.icon to a real drawable
                // Icon(painterResource(id = getIconForPaymentMethod(method.icon)), ...)
                Spacer(Modifier.width(16.dp))
                if (method.type == selectedMethod) {
                    Text(method.name, modifier = Modifier.weight(1f))
                } else {
                    Text(method.name, modifier = Modifier.weight(1f).alpha(0.5f))
                }
                RadioButton(
                    selected = method.type == selectedMethod,
                    onClick = { onMethodSelected(method.type) }
                )
            }
        }
        TextButton(
            onClick = {
                /* Handle add new method */
            }
        ) {
            Icon(Icons.Default.Add, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text("Add new payment method")
        }
    }
}