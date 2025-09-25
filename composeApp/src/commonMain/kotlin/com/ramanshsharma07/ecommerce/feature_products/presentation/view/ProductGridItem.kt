package com.ramanshsharma07.ecommerce.feature_products.presentation.view

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ramanshsharma07.ecommerce.core.domain.model.Product
import com.ramanshsharma07.ecommerce.core.presentation.BaseProductCard
import com.ramanshsharma07.ecommerce.core.presentation.purple
import kotlinx.coroutines.delay

@Composable
fun ProductGridItem(
    product: Product,
    onNavigateToDetails: (String) -> Unit,
    onAddToCartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var justAdded by remember { mutableStateOf(false) }


    if (justAdded) {
        LaunchedEffect(justAdded) {
            delay(1500L)
            justAdded = false
        }
    }


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
                onClick = {
                    if (!justAdded) {
                        onAddToCartClick()
                        justAdded = true
                    }
                },
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
                    .size(32.dp)
                    .background(purple)
            ) {
                AnimatedContent(
                    targetState = justAdded,
                    transitionSpec = {
                        (slideInVertically { height -> height } + fadeIn())
                            .togetherWith(slideOutVertically { height -> -height } + fadeOut())
                    },
                    label = "Add to Cart Icon Animation"
                ) {
                    if (justAdded) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = "Added to cart",
                            tint = Color.White
                        )
                    } else {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Add to cart",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}