package com.example.glowgetter.ui.homepane

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.glowgetter.Product
import com.example.glowgetter.R
import com.example.glowgetter.data.ProductDataProvider
import com.example.glowgetter.ui.FavoritesUiState
import com.example.glowgetter.ui.productinfo.GlowGetterTopAppBar
import kotlinx.coroutines.delay


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    onEyesClick: () -> Unit,
    onFaceClick: () -> Unit,
    onLipsClick: () -> Unit,
    onProductClick: (Product) -> Unit,
    onFavoritesClick: (Product) -> Unit,
    favoritesUiState: FavoritesUiState,
    modifier: Modifier = Modifier
) {
    val products = ProductDataProvider.products

    Column() {
        LazyColumn() {
            item {
                GlowGetterTopAppBar(text = "Hey, your name!")
            }
            item {
                ProductCategories(
                    onEyesClick,
                    onFaceClick,
                    onLipsClick
                )
            }
            item {
                Column {
                    ProductCarousel()
                    Text(text = stringResource(R.string.popular_products))
                }
            }
            item {
                FlowRow(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth(),
                    maxItemsInEachRow = 4
                ) {
                    products.forEach { product ->
                        ProductItem(
                            product = product,
                            onProductClick = onProductClick,
                            onFavoritesClick = onFavoritesClick,
                            favoritesUiState = favoritesUiState
                        )
                    }
                }
            }
        }
    }
}
//    Scaffold(
//        topBar = {
//            GgTopAppBarHomeScreen()
//        }
//    ) {
//        Column(
//            modifier = Modifier
//                .padding(it)
//                .verticalScroll(rememberScrollState())
//        ) {
//            ProductCategories(
//                onEyesClick,
//                onFaceClick,
//                onLipsClick
//            )
//            ProductCarousel()
//            Text(text = stringResource(R.string.popular_products))
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly
//                ) {
//                    products1.forEach { product ->
//                        ProductItem(
//                            product = product,
//                            onProductClick = onProductClick,
//                            onFavoritesClick = onFavoritesClick,
//                            favoritesUiState = favoritesUiState
//                        )
//                    }
//                }
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 16.dp),
//                horizontalArrangement = Arrangement.SpaceEvenly
//                ) {
//                    products2.forEach { product ->
//                        ProductItem(
//                            product = product,
//                            onProductClick = onProductClick,
//                            onFavoritesClick = onFavoritesClick,
//                            favoritesUiState = favoritesUiState
//                        )
//                    }
//                }
//
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 16.dp),
//                    horizontalArrangement = Arrangement.SpaceEvenly
//                ) {
//                    products3.forEach { product ->
//                        ProductItem(
//                            product = product,
//                            onProductClick = onProductClick,
//                            onFavoritesClick = onFavoritesClick,
//                            favoritesUiState = favoritesUiState
//                        )
//                    }
//                }
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 16.dp),
//                    horizontalArrangement = Arrangement.SpaceEvenly
//                ) {
//                    products4.forEach { product ->
//                        ProductItem(
//                            product = product,
//                            onProductClick = onProductClick,
//                            onFavoritesClick = onFavoritesClick,
//                            favoritesUiState = favoritesUiState
//                            )
//                    }
//                }
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 16.dp, bottom = 16.dp),
//                    horizontalArrangement = Arrangement.SpaceEvenly
//                ) {
//                    products5.forEach { product ->
//                        ProductItem(
//                            product = product,
//                            onProductClick = onProductClick,
//                            onFavoritesClick = onFavoritesClick,
//                            favoritesUiState = favoritesUiState
//                        )
//                    }
//                }
//
//        }
//    }
//}

@Composable
fun ProductItem(
    product: Product,
    onProductClick: (Product) -> Unit,
    onFavoritesClick: (Product) -> Unit,
    favoritesUiState: FavoritesUiState,
    modifier: Modifier = Modifier
) {
        Card(
            colors = CardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.Transparent
            ),
            modifier = modifier
                .width(190.dp)
                .padding(8.dp)
                .clickable { onProductClick(product) }
        ) {
            Column(
                modifier = modifier
                    .padding(8.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(product.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = product.productType,
                    contentScale = ContentScale.Crop,
                    modifier = modifier.aspectRatio(1f)
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    product.brand?.let { Text(it) }
                    Spacer(modifier = modifier.weight(1f))
                    Icon(
                        if (favoritesUiState.favorites.contains(product)) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.surfaceTint,
                        modifier = Modifier
                            .size(18.dp)
                            .clickable { onFavoritesClick(product) }
                    )
                }
                Text(product.name)
            }
        }
    }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductCarousel(
    autoScrollEnabled: Boolean = true,
    autoScrollInterval: Int = 3000,
    modifier: Modifier = Modifier,
) {
    val photos = listOf(
        R.mipmap.lipstick,
        R.mipmap.eyeshadow,
        R.mipmap.foundation
    )
    val pageCount = photos.size
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 250 })

// infinitely loop through images every 2 seconds while app is live
    LaunchedEffect(autoScrollEnabled, autoScrollInterval) {
        if (autoScrollEnabled) {
            while (true) {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                delay(autoScrollInterval.toLong())
            }
        }
    }
    HorizontalPager(
        contentPadding = PaddingValues(horizontal = 16.dp),
        pageSpacing = 16.dp,
        state = pagerState,
        modifier = modifier.padding(bottom = 16.dp)
    ) { index ->
        val page = index % pageCount
        Image(
            painter = painterResource(photos[page]),
            contentDescription = photos[page].toString(),
            contentScale = ContentScale.Crop,
            modifier = modifier
                .aspectRatio(7f/3f)
        )
    }
}

@Composable
fun ProductCategories(
    onEyesClick: () -> Unit,
    onFaceClick: () -> Unit,
    onLipsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(12.dp)
    ) {
        Card(
            modifier = modifier
                .clickable { onEyesClick() }
        ) {
            Text(
                text = stringResource(R.string.eyes),
                style = MaterialTheme.typography.bodyLarge,
                modifier = modifier
                    .padding(40.dp)
            )
        }
        Spacer(modifier = modifier.weight(1f))
        Card(
            modifier = modifier
                .clickable { onFaceClick() }
        ) {
            Text(
                text = stringResource(R.string.face),
                style = MaterialTheme.typography.bodyLarge,
                modifier = modifier
                    .padding(40.dp)
            )
        }
        Spacer(modifier = modifier.weight(1f))
        Card(
            modifier = modifier
                .clickable { onLipsClick() }
        ) {
            Text(
                text = stringResource(R.string.lips),
                style = MaterialTheme.typography.bodyLarge,
                modifier = modifier
                    .padding(40.dp)
            )
        }
    }
}
//
//@Composable
//fun GgTopAppBarHomeScreen(
//    modifier: Modifier = Modifier,
//) {
//
//   Row(
//       verticalAlignment = Alignment.CenterVertically,
//       modifier = modifier
//           .fillMaxWidth()
//           .background(color = Color(0xFFF6F6F5))
//   ) {
//        Image(
//            painter = painterResource(R.mipmap.top_app_bar_logo),
//            contentDescription = null,
//            contentScale = ContentScale.FillBounds,
//            modifier = modifier.size(100.dp)
//        )
//       Spacer(modifier = modifier.width(20.dp))
//       Text(text = "Hey, Your Name!", modifier = modifier)
//        }
//}
