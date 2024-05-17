package com.example.glowgetter.ui.productinfo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.glowgetter.data.Product
import com.example.glowgetter.R
import com.example.glowgetter.ui.ProductListUiState
import com.example.glowgetter.ui.homepane.GlowGetterTopAppBar
import com.example.glowgetter.ui.homepane.ProductItem
import com.example.glowgetter.ui.viewmodels.GlowGetterViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.launch

@Composable
fun ProductScreen(
    onProductClick: (Product) -> Unit,
    onFavoritesClick: (Product) -> Unit,
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
                    onFavoritesClick = onFavoritesClick
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
    modifier: Modifier = Modifier,
    uiState: ProductListUiState
) {
    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Column {
        LazyColumn(
            state = scrollState,
            modifier = Modifier.weight(1f)
        ) {
            item {
                GlowGetterTopAppBar(if(videoId === "bmygzxaV7Hc" || videoId === "WuNTgwaVwZI") stringResource(R.string.lips) else stringResource(R.string.eyes))
            }
            item {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    ProductVideoPlayer(videoId, lifecycleOwner)
                    Text(
                        text = productName,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
            items(count = 1) {
                FlowRow(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth(),
                    maxItemsInEachRow = 4
                ) {
                    ProductScreen(
                        uiState = uiState,
                        onProductClick = onProductClick,
                        onFavoritesClick = onFavoritesClick
                    )
                }
            }
        }
    }
    AnimatedVisibility(visible = !scrollState.isScrollingUp(), enter = fadeIn(), exit = fadeOut()) {
        GoToTop {
            scope.launch {
                scrollState.scrollToItem(0)
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
    uiState: ProductListUiState,
    modifier: Modifier = Modifier,
    viewModel: GlowGetterViewModel
) {
    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = scrollState,
            modifier = Modifier.weight(1f)
        ) {
            item {
                GlowGetterTopAppBar(text = stringResource(R.string.face))
            }
            if (viewModel.videoId == "c__JPlF5Q7o") {
                items(1) {
                    FlowRow(
                        horizontalArrangement = Arrangement.Center,
                        maxItemsInEachRow = 2,
                        modifier = modifier
                            .fillMaxWidth()
                    ) {
                        Button(
                            onClick = onFirstFoundationClick,
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text(text = stringResource(R.string.liquid_foundation))
                        }
                        Button(
                            onClick = onSecondFoundationClick,
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text(text = stringResource(R.string.bb_cc_cream))
                        }
                        Button(
                            onClick = onThirdFoundationClick,
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text(text = stringResource(R.string.mineral_foundation))
                        }
                        Button(
                            onClick = onFourthFoundationClick,
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text(text = stringResource(R.string.powder_foundation))
                        }
                    }
                }
            }
            if (viewModel.videoId == "1LBnsgHNAkE") {
                items(1) {
                    FlowRow(
                        horizontalArrangement = Arrangement.Center,
                        maxItemsInEachRow = 2,
                        modifier = modifier
                            .fillMaxWidth()
                    ) {
                        Button(
                            onClick = onFirstBlushClick,
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text(text = stringResource(R.string.powder_blush))
                        }
                        Button(
                            onClick = onSecondBlushClick,
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text(text = stringResource(R.string.cream_blush))
                        }
                    }
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    ProductVideoPlayer(videoId, lifecycleOwner)
                    Text(
                        text = productName,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        textAlign = TextAlign.Center
                        )
                }
            }
            items(count = 1) {
                FlowRow(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth(),
                    maxItemsInEachRow = 4
                ) {
                    ProductScreen(
                        onProductClick = onProductClick,
                        onFavoritesClick = onFavoritesClick,
                        uiState = uiState
                    )
                }
            }
        }
    }
    AnimatedVisibility(visible = !scrollState.isScrollingUp(), enter = fadeIn(), exit = fadeOut()) {
        GoToTop {
            scope.launch {
                scrollState.scrollToItem(0)
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
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading_gif))

        LottieAnimation(
            composition = composition,
            iterations = 4,
            modifier = modifier
                .padding(4.dp)
        )
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(R.string.error),
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()

    )
}