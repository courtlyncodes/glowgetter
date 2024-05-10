package com.example.glowgetter.ui.productinfo

import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.Glide
import com.example.glowgetter.Product
import com.example.glowgetter.R
import com.example.glowgetter.ui.ProductListUiState
import com.example.glowgetter.ui.FavoritesUiState
import com.example.glowgetter.ui.homepane.ProductItem
import com.example.glowgetter.ui.viewmodels.GlowGetterViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun ProductScreen(
    onProductClick: (Product) -> Unit,
    onFavoritesClick: (Product) -> Unit,
    favoritesUiState: FavoritesUiState,
    modifier: Modifier = Modifier,
    uiState: ProductListUiState
) {
    when (uiState) {
        is ProductListUiState.Loading -> {
            LoadingScreen()
        }

        is ProductListUiState.Error -> {
            ErrorScreen()
        }

        is ProductListUiState.Success -> {
            uiState.products.forEach { product ->
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EyesLipsProductListScreen(
    videoId: String,
    productName: String,
    lifecycleOwner: LifecycleOwner,
    onProductClick: (Product) -> Unit,
    onFavoritesClick: (Product) -> Unit,
    favoritesUiState: FavoritesUiState,
    modifier: Modifier = Modifier,
    uiState: ProductListUiState
) {
    Column {
        LazyColumn() {
            item {
                GlowGetterTopAppBar(if(videoId === "bmygzxaV7Hc" || videoId === "WuNTgwaVwZI") stringResource(R.string.lips) else stringResource(R.string.eyes))
            }
            item {
                Column {
                    ProductVideoPlayer(videoId, lifecycleOwner)
                    Text(text = productName)
                }
            }
            items(count = 1) {
                FlowRow(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth(),
                    maxItemsInEachRow = 4
                ) {
                    ProductScreen(
                        uiState = uiState,
                        onProductClick = onProductClick,
                        onFavoritesClick = onFavoritesClick,
                        favoritesUiState = favoritesUiState
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FaceProductListScreen(
    videoId: String,
    lifecycleOwner: LifecycleOwner,
    productName: String,
    onFirstFoundationClick: () -> Unit,
    onSecondFoundationClick: () -> Unit,
    onThirdFoundationClick: () -> Unit,
    onFourthFoundationClick: () -> Unit,
    onFirstBlushClick: () -> Unit,
    onSecondBlushClick: () -> Unit,
    onProductClick: (Product) -> Unit,
    onFavoritesClick: (Product) -> Unit,
    favoritesUiState: FavoritesUiState,
    uiState: ProductListUiState,
    modifier: Modifier = Modifier,
    viewModel: GlowGetterViewModel
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn() {
            item {
                GlowGetterTopAppBar(text = stringResource(R.string.face))
            }
            if (viewModel.videoId == "c__JPlF5Q7o") {
                items(1) {
                    FlowRow(maxItemsInEachRow = 2) {
                        Button(onClick = onFirstFoundationClick) {
                            Text(text = stringResource(R.string.liquid_foundation))
                        }
                        Button(onClick = onSecondFoundationClick) {
                            Text(text = stringResource(R.string.bb_cc_cream))
                        }
                        Button(onClick = onThirdFoundationClick) {
                            Text(text = stringResource(R.string.mineral_foundation))
                        }
                        Button(onClick = onFourthFoundationClick) {
                            Text(text = stringResource(R.string.powder_foundation))
                        }
                    }
                }
            }
            if (viewModel.videoId == "1LBnsgHNAkE") {
                items(1) {
                    FlowRow(maxItemsInEachRow = 2) {
                        Button(onClick = onFirstBlushClick) {
                            Text(text = stringResource(R.string.powder_blush))
                        }
                        Button(onClick = onSecondBlushClick) {
                            Text(text = stringResource(R.string.cream_blush))
                        }
                    }
                }
            }
            item() {
                Column(modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()) {
                    ProductVideoPlayer(videoId, lifecycleOwner)
                    Text(text = productName)
                }
            }
            items(count = 1) {
                FlowRow(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth(),
                    maxItemsInEachRow = 4
                ) {
                    ProductScreen(
                        favoritesUiState = favoritesUiState,
                        onProductClick = onProductClick,
                        onFavoritesClick = onFavoritesClick,
                        uiState = uiState
                    )
                }
            }
        }
    }
}

@Composable
fun ProductVideoPlayer(
    videoId: String,
    lifecycleOwner: LifecycleOwner,
    modifier: Modifier = Modifier
) {
    AndroidView(
        factory = { context ->
            YouTubePlayerView(context).apply {
                lifecycleOwner.lifecycle.addObserver(this)

                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                })
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp))
    )
}

@Composable
fun GlowGetterTopAppBar(
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color(0xFFF6F6F5))
    ) {
        Image(
            painter = painterResource(R.mipmap.top_app_bar_logo),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = modifier.size(100.dp)
        )
        Spacer(modifier = modifier.width(20.dp))
        Text(text = text)
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    val gifUrl = "https://raw.githubusercontent.com/courtlyncodes/glowgetter/main/loadinggif.gif"
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAFC)),

        ) {
        val view = remember { ImageView(context) }

        DisposableEffect(context) {
            Glide.with(context)
                .asGif()
                .load(gifUrl)
                .into(view)
            onDispose {
                Glide.with(context).clear(view)
            }
        }
        AndroidView(factory = { view })
    }
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier
) {
    Text(text = stringResource(R.string.error), modifier = Modifier
        .padding(16.dp)
        .fillMaxSize())
}