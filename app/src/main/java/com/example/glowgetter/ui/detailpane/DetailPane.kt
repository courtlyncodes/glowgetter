package com.example.glowgetter.ui.detailpane

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.glowgetter.data.Product
import com.example.glowgetter.R
import com.example.glowgetter.ui.homepane.HomeScreen
import com.example.glowgetter.ui.viewmodels.FavoritesUiState
import com.example.glowgetter.ui.viewmodels.GlowGetterViewModel


@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun HomeAndCategoryScreen(
    username: String,
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

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    ListDetailPaneScaffold(directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            HomeScreen(
                username = username,
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
                onFavoritesClick = onFavoritesClick
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
    paddingValues: PaddingValues = PaddingValues(8.dp),
    modifier: Modifier = Modifier

) {
    Scaffold(
        topBar = {
            Column {
                DetailPaneTopAppBar(
                    productType = stringResource(R.string.eyes)
                )
            }
            HorizontalDivider(color = colorResource(R.color.gray))
        }
    ) {
        LazyColumn(modifier = Modifier.padding(paddingValues).padding(it)) {
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
    paddingValues: PaddingValues = PaddingValues(8.dp),
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            Column {
                DetailPaneTopAppBar(
                    productType = stringResource(R.string.face)
                )
                HorizontalDivider(color = colorResource(R.color.gray))
            }

        }
    ) {

        LazyColumn(modifier = Modifier.padding(paddingValues).padding(it)) {
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
    paddingValues: PaddingValues = PaddingValues(8.dp),
    modifier: Modifier = Modifier
) {
    Scaffold(

        topBar = {
            Column {
                DetailPaneTopAppBar(
                    productType = stringResource(R.string.lips)
                )
                HorizontalDivider(color = colorResource(R.color.gray))
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(paddingValues).padding(it)) {
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
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        )
    ) {
        Column(
            modifier = modifier
                .padding(16.dp)
        ) {
            Text(
                text = productType,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                    Image(
                        painter = painterResource(id = painter),
                        contentDescription = painter.toString(),
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .size(150.dp)
                            .aspectRatio(8f / 7f)
                    )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = modifier
                        .padding(start = 16.dp)
                        .weight(1f)
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPaneTopAppBar(
    productType: String,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = productType,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier

            )
        },
        colors = TopAppBarColors(
            containerColor = colorResource(id = R.color.light_gray),
            titleContentColor = colorResource(id = R.color.black),
            navigationIconContentColor = colorResource(id = R.color.black),
            actionIconContentColor = colorResource(id = R.color.black),
            scrolledContainerColor = colorResource(id = R.color.light_gray),
        ),
        modifier = modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.light_gray))
            .padding(18.dp)
    )
    HorizontalDivider(color = colorResource(R.color.gray))
}
