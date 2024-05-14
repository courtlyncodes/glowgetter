package com.example.glowgetter.ui.productinfo

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.glowgetter.data.Product
import com.example.glowgetter.R
import com.example.glowgetter.ui.AppViewModelProvider
import com.example.glowgetter.ui.FavoritesUiState
import com.example.glowgetter.ui.viewmodels.GlowGetterViewModel

@Composable
fun DetailScreen(
    product: Product,
    onFavoritesClick: (Product) -> Unit,
    viewModel: GlowGetterViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val favoritesList by viewModel.favoritesList.collectAsState()

    LazyColumn(
        modifier = modifier
            .padding(8.dp)
    ) {
        item {
            Column(
                modifier = modifier
                    .padding(8.dp)
            ) {
                Text(
                    product.name,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = modifier
                        .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(product.productLink))
                        context.startActivity(intent)
                    }

                )
                product.brand?.let {
                    Text (
                        it,
                        style = MaterialTheme.typography.labelLarge,
                        color = colorResource(R.color.dark_gray),
                        modifier = modifier
                            .padding(top = 8.dp)
                    )
                }
            }
        }
        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                modifier = modifier
                    .padding(horizontal = 8.dp)
            ) {
                Column(
                    modifier = modifier
                        .padding(8.dp)
                ) {
                    Row {
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            if (favoritesList.favorites.contains(product)) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(18.dp)
                                .clickable { onFavoritesClick(product) }
                        )
                    }
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(product.image)
                            .crossfade(true)
                            .build(),
                        contentDescription = product.productType,
                        contentScale = ContentScale.Crop,
                        modifier = modifier.aspectRatio(1f)
                    )

                }
            }
        }
        item {
            product.description?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = modifier
                        .padding(8.dp)
                    )
            }
        }
    }
}