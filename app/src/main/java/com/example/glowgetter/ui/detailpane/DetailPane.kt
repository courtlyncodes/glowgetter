package com.example.glowgetter.ui.detailpane

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.glowgetter.Product
import com.example.glowgetter.ui.homescreen.HomeScreen
import com.example.glowgetter.ui.homescreen.ProductItem
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.glowgetter.R
import com.example.glowgetter.ui.ProductListUiState
import com.example.glowgetter.ui.viewmodels.GlowGetterViewModel


@Composable
fun EyesCategoryDetailPane(
    onCardClick: () -> Unit,
    onFirstClick: () -> Unit,
    onSecondClick: () -> Unit,
    onThirdClick: () -> Unit,
    onFourthClick: () -> Unit,
    modifier: Modifier = Modifier
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
                    firstSubCategory = null,
                    secondSubCategory = null,
                    thirdSubCategory = null,
                    fourthSubCategory = null,
                    onCardClick = { },
                    onFirstClick = { },
                    onSecondClick = { },
                    onThirdClick = { },
                    onFourthClick = { },
                    description = stringResource(R.string.eyebrow_pencil_des)
                )
            }
            item {
                Spacer(modifier = Modifier.size(16.dp))
            }
            item {
                CategoryDetailCard(
                    productType = stringResource(R.string.mascara),
                    painter = R.mipmap.mascara,
                    firstSubCategory = null,
                    secondSubCategory = null,
                    thirdSubCategory = null,
                    fourthSubCategory = null,
                    onCardClick = { },
                    onFirstClick = {},
                    onSecondClick = {},
                    onThirdClick = {},
                    onFourthClick = {},
                    description = stringResource(R.string.mascara_des)
                )
            }
            item {
                Spacer(modifier = Modifier.size(16.dp))
            }
            item {
                CategoryDetailCard(
                    productType = stringResource(R.string.eyeliner),
                    painter = R.mipmap.eyeliner,
                    firstSubCategory = "Liquid",
                    secondSubCategory = "Pencil",
                    thirdSubCategory = "Gel",
                    fourthSubCategory = "Cream",
                    onCardClick = { },
                    onFirstClick = {},
                    onSecondClick = {},
                    onThirdClick = {},
                    onFourthClick = {},
                    description = stringResource(R.string.eyeliner_des)
                )
            }
            item {
                Spacer(modifier = Modifier.size(16.dp))
            }
            item {
                CategoryDetailCard(
                    productType = stringResource(R.string.eyeshadow),
                    painter = R.mipmap.eyeshadow_product,
                    firstSubCategory = "Palette",
                    secondSubCategory = "Pencil",
                    thirdSubCategory = "Cream",
                    fourthSubCategory = null,
                    onCardClick = { },
                    onFirstClick = {},
                    onSecondClick = {},
                    onThirdClick = {},
                    onFourthClick = {},
                    description = stringResource(R.string.eyeshadow_des)
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
    onCardClick: () -> Unit,
    onFirstClick: () -> Unit,
    onSecondClick: () -> Unit,
    onThirdClick: () -> Unit,
    onFourthClick: () -> Unit,
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
                    firstSubCategory = "Liquid",
                    secondSubCategory = "BB & CC Cream",
                    thirdSubCategory = "Mineral",
                    fourthSubCategory = "Powder",
                    onCardClick = onCardClick,
                    onFirstClick = onFirstClick,
                    onSecondClick = onSecondClick,
                    onThirdClick = onThirdClick,
                    onFourthClick = onFourthClick,
                    description = stringResource(R.string.foundation_des)
                )
            }
            item {
                CategoryDetailCard(
                    productType = stringResource(R.string.blush),
                    painter = R.mipmap.blush,
                    firstSubCategory = "Powder",
                    secondSubCategory = "Cream",
                    thirdSubCategory = null,
                    fourthSubCategory = null,
                    onCardClick = onCardClick,
                    onFirstClick = onFirstClick,
                    onSecondClick = onSecondClick,
                    onThirdClick = onThirdClick,
                    onFourthClick = onFourthClick,
                    description = stringResource(R.string.blush_des)
                )
            }
            item {
                CategoryDetailCard(
                    productType = stringResource(R.string.bronzer),
                    painter = R.mipmap.bronzer,
                    firstSubCategory = null,
                    secondSubCategory = null,
                    thirdSubCategory = null,
                    fourthSubCategory = null,
                    onCardClick = onCardClick,
                    onFirstClick = onFirstClick,
                    onSecondClick = onSecondClick,
                    onThirdClick = onThirdClick,
                    onFourthClick = onFourthClick,
                    description = stringResource(R.string.bronzer_des)
                )
            }
            item {
                CategoryDetailCard(
                    productType = stringResource(R.string.concealer),
                    painter = R.mipmap.concealer,
                    firstSubCategory = null,
                    secondSubCategory = null,
                    thirdSubCategory = null,
                    fourthSubCategory = null,
                    onCardClick = onCardClick,
                    onFirstClick = onFirstClick,
                    onSecondClick = onSecondClick,
                    onThirdClick = onThirdClick,
                    onFourthClick = onFourthClick,
                    description = stringResource(R.string.concealer_des)
                )
            }
            item {
                CategoryDetailCard(
                    productType = stringResource(R.string.contour),
                    painter = R.mipmap.contour,
                    firstSubCategory = null,
                    secondSubCategory = null,
                    thirdSubCategory = null,
                    fourthSubCategory = null,
                    onCardClick = onCardClick,
                    onFirstClick = onFirstClick,
                    onSecondClick = onSecondClick,
                    onThirdClick = onThirdClick,
                    onFourthClick = onFourthClick,
                    description = stringResource(R.string.contour_des)
                )
            }
            item {
                CategoryDetailCard(
                    productType = stringResource(R.string.highlighter),
                    painter = R.mipmap.highlighter,
                    firstSubCategory = null,
                    secondSubCategory = null,
                    thirdSubCategory = null,
                    fourthSubCategory = null,
                    onCardClick = onCardClick,
                    onFirstClick = onFirstClick,
                    onSecondClick = onSecondClick,
                    onThirdClick = onThirdClick,
                    onFourthClick = onFourthClick,
                    description = stringResource(R.string.highlighter_des)
                )
            }
        }
    }
}

@Composable
fun LipsCategoryDetailPane(
    onCardClick: () -> Unit,
    onFirstClick: () -> Unit,
    onSecondClick: () -> Unit,
    onThirdClick: () -> Unit,
    onFourthClick: () -> Unit,
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
                    firstSubCategory = null,
                    secondSubCategory = null,
                    thirdSubCategory = null,
                    fourthSubCategory = null,
                    onCardClick = onCardClick,
                    onFirstClick = onFirstClick,
                    onSecondClick = onSecondClick,
                    onThirdClick = onThirdClick,
                    onFourthClick = onFourthClick,
                    description = stringResource(R.string.lip_gloss_des)
                )
            }
            item {
                CategoryDetailCard(
                    productType = stringResource(R.string.lip_liner),
                    painter = R.mipmap.lipliner_pencil,
                    firstSubCategory = null,
                    secondSubCategory = null,
                    thirdSubCategory = null,
                    fourthSubCategory = null,
                    onCardClick = onCardClick,
                    onFirstClick = onFirstClick,
                    onSecondClick = onSecondClick,
                    onThirdClick = onThirdClick,
                    onFourthClick = onFourthClick,
                    description = stringResource(R.string.lip_liner_des)
                )
            }
            item {
                CategoryDetailCard(
                    productType = stringResource(R.string.lipstick),
                    painter = R.mipmap.lipstick_product,
                    firstSubCategory = null,
                    secondSubCategory = null,
                    thirdSubCategory = null,
                    fourthSubCategory = null,
                    onCardClick = onCardClick,
                    onFirstClick = onFirstClick,
                    onSecondClick = onSecondClick,
                    onThirdClick = onThirdClick,
                    onFourthClick = onFourthClick,
                    description = stringResource(R.string.lipstick_des)
                )
            }
        }
    }
}

@Composable
fun CategoryDetailCard(
    painter: Int,
    productType: String,
    onCardClick: () -> Unit,
    firstSubCategory: String?,
    secondSubCategory: String?,
    thirdSubCategory: String?,
    fourthSubCategory: String?,
    onFirstClick: () -> Unit,
    onSecondClick: () -> Unit,
    onThirdClick: () -> Unit,
    onFourthClick: () -> Unit,
    description: String,
    modifier: Modifier = Modifier

) {
    Card(
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledContainerColor = MaterialTheme.colorScheme.inversePrimary,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        modifier = modifier
            .clickable { onCardClick() }
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

                    if (firstSubCategory != null) {
                        Text(
                            text = firstSubCategory,
                            modifier = Modifier.clickable { onFirstClick() })
                    }
                    if (secondSubCategory != null) {
                        Text(
                            text = secondSubCategory,
                            modifier = Modifier.clickable { onSecondClick() })
                    }
                    if (thirdSubCategory != null) {
                        Text(
                            text = thirdSubCategory,
                            modifier = Modifier.clickable { onThirdClick() })
                    }
                    if (fourthSubCategory != null) {
                        Text(
                            text = fourthSubCategory,
                            modifier = Modifier.clickable { onFourthClick() })
                    }
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

enum class DetailList {
    EYES, FACE, LIPS
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ListDetailScreen(
    onCardClick: () -> Unit,
    onFirstClick: () -> Unit,
    onSecondClick: () -> Unit,
    onThirdClick: () -> Unit,
    onFourthClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GlowGetterViewModel = viewModel(factory = GlowGetterViewModel.Factory)
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<Nothing>()
    var currentDestination by rememberSaveable { mutableStateOf(DetailList.EYES) }
    val glowGetterViewModel: GlowGetterViewModel = viewModel(factory = GlowGetterViewModel.Factory)
    val uiState by viewModel.productUiState.collectAsState()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    ListDetailPaneScaffold(directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            HomeScreen(onEyesClick = {
                navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
            }, onFaceClick = {
                currentDestination = DetailList.FACE
                navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
            }, onLipsClick = {
                currentDestination = DetailList.LIPS
                navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
            })
        },
        detailPane = {
            when (currentDestination) {
                DetailList.EYES -> EyesCategoryDetailPane(
                    onCardClick, onFirstClick, onSecondClick, onThirdClick, onFourthClick
                )

                DetailList.FACE -> FaceCategoryDetailPane(
                    onCardClick, onFirstClick, onSecondClick, onThirdClick, onFourthClick
                )

                DetailList.LIPS -> LipsCategoryDetailPane(
                    onCardClick, onFirstClick, onSecondClick, onThirdClick, onFourthClick
                )
            }

        })
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


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun EyesCategoryDetailPanePreview(

) {
    EyesCategoryDetailPane({ }, { }, { }, { }, { })
}

@Preview
@Composable
fun LipsCategoryDetailPanePreview() {
    LipsCategoryDetailPane({ }, { }, { }, { }, { })
}

@Preview
@Composable
fun FaceCategoryDetailPanePreview() {
    FaceCategoryDetailPane({ }, { }, { }, { }, { })
}