package com.ramanshsharma07.ecommerce.feature_home.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramanshsharma07.ecommerce.core.domain.model.Product
import com.ramanshsharma07.ecommerce.core.presentation.BaseProductCard
import com.ramanshsharma07.ecommerce.core.presentation.purple

@Composable
fun ProductCard(
    product: Product,
    onNavigateToDetails: (String) -> Unit,
    onFavoriteClick: (productId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    BaseProductCard(
        product = product,
        modifier = modifier
            .width(180.dp),
        onProductClick = {
            onNavigateToDetails(product.id)
        },
        onFavoriteClick = {
            onFavoriteClick(product.id)
        }
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = product.name, style = MaterialTheme.typography.titleMedium, maxLines = 1)
            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = purple
            )
        }
    }
}