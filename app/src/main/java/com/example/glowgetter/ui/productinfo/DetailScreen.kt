package com.example.glowgetter.ui.productinfo

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.glowgetter.R
import com.example.glowgetter.data.model.Product
import com.example.glowgetter.ui.AppViewModelProvider
import com.example.glowgetter.ui.viewmodels.GlowGetterViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailScreen(
    product: Product,
    modifier: Modifier = Modifier,
    onFavoritesClick: (Product) -> Unit,
    viewModel: GlowGetterViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val favoritesList by viewModel.favoritesList.collectAsState()

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 36.dp)
    ) {

        item {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                ProductCard(onFavoritesClick = onFavoritesClick, product = product)
                ProductInformation(product = product)

            }
        }
    }
}

@Composable
fun ProductInformation(
    product: Product,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .padding(8.dp)
    ) {
        Text(
            product.name,
            style = MaterialTheme.typography.titleMedium,
            textDecoration = TextDecoration.Underline,
            modifier = modifier
                .clickable {
                    val intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse(product.productLink))
                    context.startActivity(intent)
                }

        )
        product.brand?.let {
            Text(
                it,
                style = MaterialTheme.typography.labelLarge,
                color = colorResource(R.color.dark_gray),
                modifier = modifier
                    .padding(top = 8.dp)
            )
        }
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

@Composable
fun ProductCard(
    product: Product,
    onFavoritesClick: (Product) -> Unit,
    viewModel: GlowGetterViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val favoritesList by viewModel.favoritesList.collectAsState()

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier
            .padding(8.dp)
            .width(250.dp)
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
