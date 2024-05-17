package com.example.glowgetter.ui.homepane

import android.util.Log
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.helper.widget.Carousel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.glowgetter.R
import com.example.glowgetter.data.ProductDataProvider
import com.example.glowgetter.data.model.CarouselImage
import com.example.glowgetter.data.model.Product
import com.example.glowgetter.ui.viewmodels.GlowGetterViewModel
import kotlinx.coroutines.delay


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    username: String,
    onEyesClick: () -> Unit,
    onFaceClick: () -> Unit,
    onLipsClick: () -> Unit,
    onProductClick: (Product) -> Unit,
    onFavoritesClick: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    val products = ProductDataProvider.products

    Column {
        LazyColumn {
            item {
                Column {
                    GlowGetterTopAppBar(text = username)
                }
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
                    Text(
                        text = stringResource(R.string.popular_products),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Normal,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        textAlign = TextAlign.Center
                    )
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
                            onFavoritesClick = onFavoritesClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProductItem(
    product: Product,
    onProductClick: (Product) -> Unit,
    onFavoritesClick: (Product) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GlowGetterViewModel = viewModel(factory = GlowGetterViewModel.Factory)
) {
    val favoritesList by viewModel.favoritesList.collectAsState()
    Card(
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        ),
        modifier = modifier
            .width(190.dp)
            .padding(4.dp)
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
                product.brand?.let {
                    Text(
                        it,
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
                Spacer(modifier = modifier.weight(1f))
                Icon(
                    if (favoritesList.favorites.contains(product)) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    tint = colorResource(id = R.color.red),
                    modifier = Modifier
                        .size(18.dp)
                        .clickable { onFavoritesClick(product) }
                )
            }
            Text(
                product.name,
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductCarousel(
    modifier: Modifier = Modifier,
    autoScrollEnabled: Boolean = true,
    autoScrollInterval: Int = 3000
) {
    // List of photos to loop through
    val photos = listOf(
        CarouselImage(R.mipmap.lipstick, stringResource(R.string.lipstick)),
        CarouselImage(R.mipmap.eyeshadow, stringResource(R.string.eyeshadow)),
        CarouselImage(R.mipmap.foundation, stringResource(R.string.foundation))
    )
    val pageCount = photos.size
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 100 })

// Infinitely loop through images every 2 seconds while app is live
    LaunchedEffect(autoScrollEnabled, autoScrollInterval) {
        if (autoScrollEnabled) {
            while (true) {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                delay(autoScrollInterval.toLong())
            }
        }
    }
    HorizontalPager(
        contentPadding = PaddingValues(horizontal = 8.dp),
        pageSpacing = 16.dp,
        state = pagerState,
        modifier = modifier.padding(bottom = 16.dp)
    ) { index ->
        val page = index % pageCount

        Image(
            painter = painterResource(id = photos[page].id),
            contentDescription = photos[page].name,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .aspectRatio(7f / 3f)
                .shadow(16.dp)
                .clip(RoundedCornerShape(4.dp))
        )
        Log.wtf("content des", photos[page].toString())
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
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .padding(8.dp)
    ) {
        Card(
            colors = CardColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.Transparent
            ),
            modifier = modifier
                .clickable { onEyesClick() }
                .width(125.dp)
        ) {
            Text(
                text = stringResource(R.string.eyes),
                style = MaterialTheme.typography.titleSmall,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .padding(8.dp)
            )
        }
        Spacer(modifier = modifier.weight(1f))
        Card(
            colors = CardColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.Transparent
            ),
            modifier = modifier
                .clickable { onFaceClick() }
                .width(125.dp)
        ) {
            Text(
                text = stringResource(R.string.face),
                style = MaterialTheme.typography.titleSmall,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .padding(8.dp)
            )
        }
        Spacer(modifier = modifier.weight(1f))
        Card(
            colors = CardColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.Transparent
            ),
            modifier = modifier
                .clickable { onLipsClick() }
                .width(125.dp)
        ) {
            Text(
                text = stringResource(R.string.lips),
                style = MaterialTheme.typography.titleSmall,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun GlowGetterTopAppBar(
    text: String,
    modifier: Modifier = Modifier,
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.light_gray))
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(R.mipmap.top_app_bar_logo),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = modifier
                    .size(88.dp)
            )
            Spacer(modifier = modifier.width(20.dp))
            Text(
                text = text,
                color = colorResource(id = R.color.dark_orange),
                style = MaterialTheme.typography.displayMedium
            )

        }
        HorizontalDivider(color = colorResource(R.color.gray)) // Adds a bottom border to the top app bar
    }
}
