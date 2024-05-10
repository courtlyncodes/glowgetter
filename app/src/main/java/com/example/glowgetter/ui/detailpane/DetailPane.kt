package com.example.glowgetter.ui.detailpane

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.glowgetter.ui.homepane.HomeScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.glowgetter.Product
import com.example.glowgetter.R
import com.example.glowgetter.ui.FavoritesUiState
import com.example.glowgetter.ui.viewmodels.GlowGetterViewModel



@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeAndCategoryScreen(
    onFirstCardClick: () -> Unit,
    onSecondCardClick: () -> Unit,
    onThirdCardClick: () -> Unit,
    onFourthCardClick: () -> Unit,
    onFirstFaceCardClick: () -> Unit,
    onSecondFaceCardClick: () -> Unit,
    onThirdFaceCardClick: () -> Unit,
    onFourthFaceCardClick: () -> Unit,
    onFifthFaceCardClick: () -> Unit,
    onFirstLipsCardClick: () -> Unit,
    onSecondLipsCardClick: () -> Unit,
    onThirdLipsCardClick: () -> Unit,
    onProductClick: (Product) -> Unit,
    onFavoritesClick: (Product) -> Unit,
    favoritesUiState: FavoritesUiState,
    modifier: Modifier = Modifier,
    viewModel: GlowGetterViewModel = viewModel(factory = GlowGetterViewModel.Factory)
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<Nothing>()
    var currentDestination by rememberSaveable { mutableStateOf(DetailList.EYES) }
    val glowGetterViewModel: GlowGetterViewModel = viewModel(factory = GlowGetterViewModel.Factory)
    val uiState by viewModel.favoritesUiState.collectAsState()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    ListDetailPaneScaffold(directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            HomeScreen(
                onEyesClick = {
                currentDestination = DetailList.EYES
                viewModel.updateProductCategory("eyes")
                navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
            },
                onFaceClick = {
                currentDestination = DetailList.FACE
                viewModel.updateProductCategory("face")
                navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
            },
                onLipsClick = {
                currentDestination = DetailList.LIPS
                viewModel.updateProductCategory("lips")
                navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
            },
                onProductClick = onProductClick,
                onFavoritesClick = onFavoritesClick,
                favoritesUiState = favoritesUiState,

            )
        },
        detailPane = {
            when (currentDestination) {
                DetailList.EYES -> EyesCategoryDetailPane(
                    onFirstCardClick = onFirstCardClick,
                    onSecondCardClick = onSecondCardClick,
                    onThirdCardClick = onThirdCardClick,
                    onFourthCardClick = onFourthCardClick
                )

                DetailList.FACE -> FaceCategoryDetailPane(
                    onFirstFaceCardClick = onFirstFaceCardClick,
                    onSecondFaceCardClick = onSecondFaceCardClick,
                    onThirdFaceCardClick = onThirdFaceCardClick,
                    onFourthFaceCardClick = onFourthFaceCardClick,
                    onFifthFaceCardClick = onFifthFaceCardClick,
                )

                DetailList.LIPS -> LipsCategoryDetailPane(
                    onFirstLipsClick = onFirstLipsCardClick,
                    onSecondLipsClick = onSecondLipsCardClick,
                    onThirdLipsClick = onThirdLipsCardClick
                )
            }
            Log.wtf("face", viewModel.productQuery)
        })
}

enum class DetailList {
    EYES, FACE, LIPS
}

@Composable
fun EyesCategoryDetailPane(
    onFirstCardClick: () -> Unit,
    onSecondCardClick: () -> Unit,
    onThirdCardClick: () -> Unit,
    onFourthCardClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GlowGetterViewModel = viewModel(factory = GlowGetterViewModel.Factory),

) {
    Scaffold(
        topBar = {
            DetailPaneTopAppBar(
                productType = stringResource(R.string.eyes)
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                CategoryDetailCard(
                    productType = stringResource(R.string.eyebrow_pencils),
                    painter = R.mipmap.eyebrow_pencil,
                    description = stringResource(R.string.eyebrow_pencil_des),
                    modifier = modifier.clickable { onFirstCardClick() }
                )
            }
            item {
                Spacer(modifier = Modifier.size(16.dp))
            }
            item {
                CategoryDetailCard(
                    productType = stringResource(R.string.mascara),
                    painter = R.mipmap.mascara,
                    description = stringResource(R.string.mascara_des),
                    modifier = modifier.clickable { onSecondCardClick() }
                )
            }
            item {
                Spacer(modifier = Modifier.size(16.dp))
            }
            item {
                CategoryDetailCard(
                    productType = stringResource(R.string.eyeliner),
                    painter = R.mipmap.eyeliner,
                    description = stringResource(R.string.eyeliner_des),
                    modifier = modifier.clickable { onThirdCardClick() }
                )
            }
            item {
                Spacer(modifier = Modifier.size(16.dp))
            }
            item {
                CategoryDetailCard(
                    productType = stringResource(R.string.eyeshadow),
                    painter = R.mipmap.eyeshadow_product,
                    description = stringResource(R.string.eyeshadow_des),
                    modifier = modifier.clickable { onFourthCardClick() }
                )
            }
            item {
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
fun FaceCategoryDetailPane(
    onFirstFaceCardClick: () -> Unit,
    onSecondFaceCardClick: () -> Unit,
    onThirdFaceCardClick: () -> Unit,
    onFourthFaceCardClick: () -> Unit,
    onFifthFaceCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            DetailPaneTopAppBar(
                productType = stringResource(R.string.face)
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                CategoryDetailCard(
                    productType = stringResource(R.string.foundation),
                    painter = R.mipmap.liquid_foundation,
                    description = stringResource(R.string.foundation_des),
                    modifier = modifier.clickable { onFirstFaceCardClick ()}


                )
            }
            item {
                Spacer(modifier = Modifier.size(16.dp))
            }
            item {
                CategoryDetailCard(
                    productType = stringResource(R.string.blush),
                    painter = R.mipmap.blush,
                    description = stringResource(R.string.blush_des),
                    modifier = modifier.clickable { onSecondFaceCardClick ()}
                )
            }
            item {
                Spacer(modifier = Modifier.size(16.dp))
            }
            item {
                CategoryDetailCard(
                    productType = stringResource(R.string.concealer),
                    painter = R.mipmap.concealer,
                    description = stringResource(R.string.concealer_des),
                    modifier = modifier.clickable { onThirdFaceCardClick() }
                )
            }
            item {
                Spacer(modifier = Modifier.size(16.dp))
            }
            item {
                CategoryDetailCard(
                    productType = stringResource(R.string.contour),
                    painter = R.mipmap.contour,
                    description = stringResource(R.string.contour_des),
                    modifier = modifier.clickable { onFourthFaceCardClick ()}
                )
            }
            item {
                Spacer(modifier = Modifier.size(16.dp))
            }
            item {
                CategoryDetailCard(
                    productType = stringResource(R.string.highlighter),
                    painter = R.mipmap.highlighter,
                    description = stringResource(R.string.highlighter_des),
                    modifier = modifier.clickable { onFifthFaceCardClick ()}
                )
            }
        }
    }
}

@Composable
fun LipsCategoryDetailPane(
    onFirstLipsClick: () -> Unit,
    onSecondLipsClick: () -> Unit,
    onThirdLipsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            DetailPaneTopAppBar(
                productType = stringResource(R.string.lips)
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                CategoryDetailCard(
                    productType = stringResource(R.string.lip_gloss),
                    painter = R.mipmap.lip_gloss,
                    description = stringResource(R.string.lip_gloss_des),
                    modifier = modifier.clickable { onFirstLipsClick() }
                )
            }
            item {
                Spacer(modifier = Modifier.size(16.dp))
            }
            item {
                CategoryDetailCard(
                    productType = stringResource(R.string.lip_liner),
                    painter = R.mipmap.lipliner_pencil,
                    description = stringResource(R.string.lip_liner_des),
                    modifier = modifier.clickable { onSecondLipsClick() }
                )
            }
            item {
                Spacer(modifier = Modifier.size(16.dp))
            }
            item {
                CategoryDetailCard(
                    productType = stringResource(R.string.lipstick),
                    painter = R.mipmap.lipstick_product,
                    description = stringResource(R.string.lipstick_des),
                    modifier = modifier.clickable { onThirdLipsClick() }
                )
            }
        }
    }
}

@Composable
fun CategoryDetailCard(
    painter: Int,
    productType: String,
    description: String,
    modifier: Modifier = Modifier

) {
    Card(
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledContainerColor = MaterialTheme.colorScheme.inversePrimary,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary,
        )
    ) {
        Column(
            modifier = modifier
                .padding(16.dp)
        ) {
            Text(
                text = productType,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Row(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = painter),
                        contentDescription = painter.toString(),
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .size(150.dp)
                            .aspectRatio(8f / 7f)
                    )
                }
                Text(
                    text = description,
                    modifier = modifier
                        .padding(start = 16.dp, top = 12.dp)
                        .weight(1f)
                )
            }

        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPaneTopAppBar(
    productType: String
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = productType,
                style = MaterialTheme.typography.titleLarge
            )
        }
    )
}
