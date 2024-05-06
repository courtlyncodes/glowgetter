package com.example.glowgetter.ui.productlist

import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.Glide
import com.example.glowgetter.Product
import com.example.glowgetter.R
import com.example.glowgetter.ui.ProductListUiState
import com.example.glowgetter.ui.ProductUiState
import com.example.glowgetter.ui.homepane.GgTopAppBar
import com.example.glowgetter.ui.homepane.ProductItem
import com.example.glowgetter.ui.viewmodels.GlowGetterViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun ProductScreen(
    onProductClick: (Product) -> Unit,
    onFavoritesClick: (Product) -> Unit,
    favoritesUiState: ProductUiState,
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
            MakeupListPane(
                productList = uiState.products,
                onProductClick = onProductClick,
                onFavoritesClick = onFavoritesClick,
                favoritesUiState = favoritesUiState
            )
        }
    }
}
@Composable
fun EyesLipsProductListScreen(
//    searchQuery: String,
    videoId: String,
    productName: String,
    lifecycleOwner: LifecycleOwner,
    onProductClick: (Product) -> Unit,
    onFavoritesClick: (Product) -> Unit,
    favoritesUiState: ProductUiState,
    modifier: Modifier = Modifier,
    uiState: ProductListUiState
) {
    Column {
        GgTopAppBar()
        ProductVideoPlayer(videoId, lifecycleOwner)
        Text(text = productName)
        ProductScreen(
            uiState = uiState,
            onProductClick = onProductClick,
            onFavoritesClick = onFavoritesClick,
            favoritesUiState = favoritesUiState
        )
    }
}

@Composable
fun FaceProductListScreen(
//    searchQuery: String,
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
    favoritesUiState: ProductUiState,
    uiState: ProductListUiState,
    modifier: Modifier = Modifier,
    viewModel: GlowGetterViewModel
) {

    Column {
        GgTopAppBar ()
        if(viewModel.videoId == "c__JPlF5Q7o") {
            LazyVerticalGrid(columns = GridCells.Adaptive(157.dp)) {
                item {
                    Button(onClick = onFirstFoundationClick ) {
                        Text(text = stringResource(R.string.liquid_foundation))
                    }
                }
                item {
                    Button(onClick = onSecondFoundationClick) {
                        Text(text = stringResource(R.string.bb_cc_cream))
                    }
                }
                item {
                    Button(onClick = onThirdFoundationClick) {
                        Text(text = stringResource(R.string.mineral_foundation))
                    }
                }
                item {
                    Button(onClick = onFourthFoundationClick) {
                        Text(text = stringResource(R.string.powder_foundation))
                    }
                }
            }
        }
        if(viewModel.videoId == "1LBnsgHNAkE") {
            Row {
                    Button(onClick = onFirstBlushClick) {
                        Text(text = stringResource(R.string.powder_blush))
                    }
                    Button(onClick = onSecondBlushClick) {
                        Text(text = stringResource(R.string.cream_blush))
                    }
                }
            }
        ProductVideoPlayer(videoId, lifecycleOwner)
        Text(text = productName)
        ProductScreen(
            uiState = uiState, onProductClick = onProductClick, onFavoritesClick = onFavoritesClick,
            favoritesUiState = favoritesUiState
        )
    }
}


@Composable
fun ProductVideoPlayer(
    videoId: String,
    lifecycleOwner: LifecycleOwner,
    modifier: Modifier = Modifier
    ) {

    AndroidView(factory = { context ->
       YouTubePlayerView(context).apply {
           lifecycleOwner.lifecycle.addObserver(this)

           addYouTubePlayerListener(object: AbstractYouTubePlayerListener() {
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
fun MakeupListPane(
    productList: List<Product>,
    onProductClick: (Product) -> Unit,
    onFavoritesClick: (Product) -> Unit,
    favoritesUiState: ProductUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(157.dp),
        state = rememberLazyGridState(),
        contentPadding = contentPadding,
    ) {
        items(items = productList) { product ->
            ProductItem(
                product = product,
                onProductClick = onProductClick,
                onFavoritesClick = onFavoritesClick,
                favoritesUiState = favoritesUiState
            )
        }
            }
    }



@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    // MutableState is used to track Gif URLs
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
            AndroidView(factory = { view } )
        }
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier
) {
    Text(text = stringResource(R.string.error))
}

