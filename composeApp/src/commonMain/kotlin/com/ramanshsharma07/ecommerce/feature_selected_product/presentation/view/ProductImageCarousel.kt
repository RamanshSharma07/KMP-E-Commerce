package com.ramanshsharma07.ecommerce.feature_selected_product.presentation.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kmp_e_commerce.composeapp.generated.resources.Res.drawable
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductImageCarousel(
    imageUrls: List<String>,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { imageUrls.size })
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f) // Makes the image container a square
            .background(Color.LightGray) // Placeholder background
    )  {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Image(
                painter = getPainterForProduct(imageUrls[page]),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        if (imageUrls.size > 1) {
            Row(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(8.dp)
                    )
                }
            }
        }

        IconButton(
            onClick = onNavigateBack,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
                .clip(CircleShape)
                .background(Color.Black.copy(alpha = 0.3f))
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = Color.White)
        }

        // Favorite Button
        IconButton(
            onClick = onFavoriteClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .clip(CircleShape)
                .background(Color.Black.copy(alpha = 0.3f))
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Favorite",
                tint = Color.White
            )
        }
    }
}

@Composable
private fun getPainterForProduct(imageName: String): Painter {
    return when (imageName) {
        "watch" -> painterResource(drawable.watch)
        "shoes" -> painterResource(drawable.shoes)
        "airpods" -> painterResource(drawable.airpods)
        "tv" -> painterResource(drawable.tv)
        "hoodie" -> painterResource(drawable.hoodie)
        "jacket" -> painterResource(drawable.jacket)
        "camera" -> painterResource(drawable.camera)
        "headphones" -> painterResource(drawable.headphones)
        "smartwatch" -> painterResource(drawable.smartwatch)
        "laptop" -> painterResource(drawable.laptop)
        "backpack" -> {
            painterResource(drawable.backpack)
        }
//        "jeans" -> painterResource(drawable.jeans)
        else -> painterResource(drawable.placeholder)
    }
}