package com.ramanshsharma07.ecommerce.core.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.ramanshsharma07.ecommerce.core.domain.model.Product
import kmp_e_commerce.composeapp.generated.resources.Res
import kmp_e_commerce.composeapp.generated.resources.airpods
import kmp_e_commerce.composeapp.generated.resources.backpack
import kmp_e_commerce.composeapp.generated.resources.camera
import kmp_e_commerce.composeapp.generated.resources.headphones
import kmp_e_commerce.composeapp.generated.resources.hoodie
import kmp_e_commerce.composeapp.generated.resources.jacket
import kmp_e_commerce.composeapp.generated.resources.laptop
import kmp_e_commerce.composeapp.generated.resources.placeholder
import kmp_e_commerce.composeapp.generated.resources.shoes
import kmp_e_commerce.composeapp.generated.resources.smartwatch
import kmp_e_commerce.composeapp.generated.resources.tv
import kmp_e_commerce.composeapp.generated.resources.watch
import org.jetbrains.compose.resources.painterResource

/**
 * A shared, base Composable for displaying a product.
 * It handles the card, image, and favorite button.
 * The specific content (like name, price, add button) is passed in as a slot.
 */
@Composable
fun BaseProductCard(
    product: Product,
    modifier: Modifier = Modifier,
    onProductClick: (String) -> Unit,
    onFavoriteClick: (String) -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier.clickable { onProductClick(product.id) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
//                AsyncImage(
//                    model = product.imageUrl,
//                    contentDescription = product.name,
//                    modifier = Modifier.fillMaxSize(),
//                    contentScale = ContentScale.Crop
//                )
                Image(
                    painter = getPainterForProduct(product.imageUrl),
                    contentDescription = product.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Icon(
                    imageVector = if (product.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .clip(CircleShape)
                        .clickable { onFavoriteClick(product.id) }
                        .background(Color.Black.copy(alpha = 0.3f))
                        .padding(6.dp)
                )
            }
            this.content()
        }
    }
}

@Composable
fun getPainterForProduct(imageName: String): Painter {
    return when (imageName) {
        "watch" -> painterResource(Res.drawable.watch)
        "shoes" -> painterResource(Res.drawable.shoes)
        "airpods" -> painterResource(Res.drawable.airpods)
        "tv" -> painterResource(Res.drawable.tv)
        "hoodie" -> painterResource(Res.drawable.hoodie)
        "jacket" -> painterResource(Res.drawable.jacket)
        "camera" -> painterResource(Res.drawable.camera)
        "headphones" -> painterResource(Res.drawable.headphones)
        "smartwatch" -> painterResource(Res.drawable.smartwatch)
        "laptop" -> painterResource(Res.drawable.laptop)
        "backpack" -> painterResource(Res.drawable.backpack)
//        "jeans" -> painterResource(Res.drawable.jeans)
        else -> painterResource(Res.drawable.placeholder)
    }
}