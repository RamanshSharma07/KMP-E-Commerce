package com.ramanshsharma07.ecommerce.feature_selected_product.presentation.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramanshsharma07.ecommerce.core.presentation.purple

@Composable
fun ProductDetailsBottomBar(
    onBuyNowClick: () -> Unit,
    onAddToCartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = onAddToCartClick,
                modifier = Modifier.height(48.dp),
                border = BorderStroke(
                    width = 2.dp,
                    color = purple
                ),
                colors = ButtonDefaults.outlinedButtonColors().copy(
                    contentColor = purple
                )
            ) {
                Icon(
                    Icons.Outlined.ShoppingBag,
                    contentDescription = "Add to Cart"
                )
            }

            Button(
                onClick = onBuyNowClick,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = purple
                )
            ) {
                Text("Buy Now")
            }
        }
    }
}