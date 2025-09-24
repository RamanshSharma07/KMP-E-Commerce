package com.ramanshsharma07.ecommerce.feature_products.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ramanshsharma07.ecommerce.core.domain.model.Product
import com.ramanshsharma07.ecommerce.core.presentation.BaseProductCard
import com.ramanshsharma07.ecommerce.core.presentation.purple

@Composable
fun ProductGridItem(
    product: Product,
    onNavigateToDetails: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    BaseProductCard(
        product = product,
        modifier = modifier
            .padding(bottom = 8.dp)
        ,
        onProductClick = {
            onNavigateToDetails(product.id)
        },
        onFavoriteClick = { /* Handle favorite click */ }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp, horizontal = 10.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1
                )
                Text(
                    text = "$${product.price}",
                    fontWeight = FontWeight.Bold,
                    color = purple
                )
            }
            IconButton(
                onClick = { /* Handle Add to Cart */ },
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
                    .size(32.dp)
                    .background(purple)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add to cart",
                    tint = Color.White,
//                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}