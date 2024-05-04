package com.example.glowgetter.ui.productlist

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.glowgetter.Product
import com.example.glowgetter.R
import com.example.glowgetter.ui.ProductListUiState
import com.example.glowgetter.ui.homepane.GgTopAppBar
import com.example.glowgetter.ui.homepane.ProductItem
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun ProductScreen(
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
            MakeupListPane(productList = uiState.products)
        }
    }
}
@Composable
fun EyesProductListScreen(
//    searchQuery: String,
    videoId: String,
    lifecycleOwner: LifecycleOwner,
    modifier: Modifier = Modifier,
    uiState: ProductListUiState
) {
    Column {
        GgTopAppBar {
        }
        ProductVideoPlayer(videoId, lifecycleOwner)
        ProductScreen(uiState)

    }
}

@Composable
fun FaceProductListScreen(
//    searchQuery: String,
    videoId: String,
    lifecycleOwner: LifecycleOwner,
//    productList: List<Product>,
    uiState: ProductListUiState,
    modifier: Modifier = Modifier,
) {
    Column {
        GgTopAppBar {
        }
        ProductVideoPlayer(videoId, lifecycleOwner)
        ProductScreen(uiState = uiState)
    }
}

@Composable
fun LipsProductListScreen(
//    searchQuery: String,
    videoId: String,
    lifecycleOwner: LifecycleOwner,
//    productList: List<Product>,
    modifier: Modifier = Modifier,
    uiState: ProductListUiState
) {
    Column {
        GgTopAppBar {

        }
        LazyRow {
            item {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = stringResource(R.string.lip_gloss))
                }
            }
            item {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = stringResource(R.string.lip_liner))
                }
            }
            item {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = stringResource(R.string.lipstick))
                }
            }
        }
        ProductVideoPlayer(videoId, lifecycleOwner)
        ProductScreen(uiState = uiState)

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
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(157.dp),
        state = rememberLazyGridState(),
        modifier = modifier.padding(horizontal = 4.dp),
        contentPadding = contentPadding,
    ) {
        item() {}
        items(items = productList) { product ->
            ProductItem(product = product)
        }
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading))

        LottieAnimation(
            composition = composition,
            iterations = 4
        )
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier
) {
    Text(text = stringResource(R.string.error))
}
//
//@Preview
//@Composable
//fun ProductScreenPreview() {
//        LipsProductListScreen(
//            searchQuery = "",
//            videoId = "",
//            lifecycleOwner = LocalLifecycleOwner.current,
//            productList = listOf()
//        )
//    }
