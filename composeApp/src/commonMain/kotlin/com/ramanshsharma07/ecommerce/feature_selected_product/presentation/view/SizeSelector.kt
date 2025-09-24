package com.ramanshsharma07.ecommerce.feature_selected_product.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun SizeSelector(
    sizes: List<String>,
    selectedSize: String?,
    onSizeSelected: (String) -> Unit
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(sizes) { size ->
            val isSelected = size == selectedSize
            FilterChip(
                selected = isSelected,
                onClick = { onSizeSelected(size) },
                label = { Text(size) },
                shape = RoundedCornerShape(8.dp)
            )
        }
    }
}