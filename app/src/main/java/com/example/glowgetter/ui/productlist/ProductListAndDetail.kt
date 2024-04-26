package com.example.glowgetter.ui.productlist

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
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
    LazyVerticalGrid(columns = GridCells.Adaptive(157.dp)) {
        item {
            CategoryDetailCard(productType = "Eyebrow Pencils",
                painter = R.mipmap.eyebrow_pencil,
                firstSubCategory = null,
                secondSubCategory = null,
                thirdSubCategory = null,
                fourthSubCategory = null,
                onCardClick = { },
                onFirstClick = { },
                onSecondClick = { },
                onThirdClick = { },
                onFourthClick = { })
        }
        item {
            CategoryDetailCard(productType = "Mascara",
                painter = R.mipmap.mascara,
                firstSubCategory = null,
                secondSubCategory = null,
                thirdSubCategory = null,
                fourthSubCategory = null,
                onCardClick = { },
                onFirstClick = {},
                onSecondClick = {},
                onThirdClick = {},
                onFourthClick = {})
        }
        item {
            CategoryDetailCard(productType = "Eyeliner",
                painter = R.mipmap.eyeliner,
                firstSubCategory = "Liquid",
                secondSubCategory = "Pencil",
                thirdSubCategory = "Gel",
                fourthSubCategory = "Cream",
                onCardClick = { },
                onFirstClick = {},
                onSecondClick = {},
                onThirdClick = {},
                onFourthClick = {})
        }
        item {
            CategoryDetailCard(productType = "Eyeshadow",
                painter = R.mipmap.eyeshadow_product,
                firstSubCategory = "Palette",
                secondSubCategory = "Pencil",
                thirdSubCategory = "Cream",
                fourthSubCategory = null,
                onCardClick = { },
                onFirstClick = {},
                onSecondClick = {},
                onThirdClick = {},
                onFourthClick = {})
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
    LazyVerticalGrid(columns = GridCells.Adaptive(157.dp)) {

        item {
            CategoryDetailCard(
                productType = "Foundation",
                painter = R.mipmap.liquid_foundation,
                firstSubCategory = "Liquid",
                secondSubCategory = "BB & CC Cream",
                thirdSubCategory = "Mineral",
                fourthSubCategory = "Powder",
                onCardClick = onCardClick,
                onFirstClick = onFirstClick,
                onSecondClick = onSecondClick,
                onThirdClick = onThirdClick,
                onFourthClick = onFourthClick
            )
        }
        item {
            CategoryDetailCard(
                productType = "Blush",
                painter = R.mipmap.blush,
                firstSubCategory = "Powder",
                secondSubCategory = "Cream",
                thirdSubCategory = null,
                fourthSubCategory = null,
                onCardClick = onCardClick,
                onFirstClick = onFirstClick,
                onSecondClick = onSecondClick,
                onThirdClick = onThirdClick,
                onFourthClick = onFourthClick
            )
        }
        item {
            CategoryDetailCard(
                productType = "Bronzer",
                painter = R.mipmap.bronzer,
                firstSubCategory = null,
                secondSubCategory = null,
                thirdSubCategory = null,
                fourthSubCategory = null,
                onCardClick = onCardClick,
                onFirstClick = onFirstClick,
                onSecondClick = onSecondClick,
                onThirdClick = onThirdClick,
                onFourthClick = onFourthClick
            )
        }
        item {
            CategoryDetailCard(
                productType = "Concealer",
                painter = R.mipmap.concealer,
                firstSubCategory = null,
                secondSubCategory = null,
                thirdSubCategory = null,
                fourthSubCategory = null,
                onCardClick = onCardClick,
                onFirstClick = onFirstClick,
                onSecondClick = onSecondClick,
                onThirdClick = onThirdClick,
                onFourthClick = onFourthClick
            )
        }
        item {
            CategoryDetailCard(
                productType = "Contour",
                painter = R.mipmap.contour,
                firstSubCategory = null,
                secondSubCategory = null,
                thirdSubCategory = null,
                fourthSubCategory = null,
                onCardClick = onCardClick,
                onFirstClick = onFirstClick,
                onSecondClick = onSecondClick,
                onThirdClick = onThirdClick,
                onFourthClick = onFourthClick
            )
        }
        item {
            CategoryDetailCard(
                productType = "Highlighter",
                painter = R.mipmap.highlighter,
                firstSubCategory = null,
                secondSubCategory = null,
                thirdSubCategory = null,
                fourthSubCategory = null,
                onCardClick = onCardClick,
                onFirstClick = onFirstClick,
                onSecondClick = onSecondClick,
                onThirdClick = onThirdClick,
                onFourthClick = onFourthClick
            )
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
    LazyVerticalGrid(columns = GridCells.Adaptive(157.dp)) {
        item {
            CategoryDetailCard(
                productType = "Lip Gloss",
                painter = R.mipmap.lip_gloss,
                firstSubCategory = null,
                secondSubCategory = null,
                thirdSubCategory = null,
                fourthSubCategory = null,
                onCardClick = onCardClick,
                onFirstClick = onFirstClick,
                onSecondClick = onSecondClick,
                onThirdClick = onThirdClick,
                onFourthClick = onFourthClick
            )
        }
        item {
            CategoryDetailCard(
                productType = "Lip Liner",
                painter = R.mipmap.lipliner_pencil,
                firstSubCategory = null,
                secondSubCategory = null,
                thirdSubCategory = null,
                fourthSubCategory = null,
                onCardClick = onCardClick,
                onFirstClick = onFirstClick,
                onSecondClick = onSecondClick,
                onThirdClick = onThirdClick,
                onFourthClick = onFourthClick
            )
        }
        item {
            CategoryDetailCard(
                productType = "Lipstick",
                painter = R.mipmap.lipstick_product,
                firstSubCategory = null,
                secondSubCategory = null,
                thirdSubCategory = null,
                fourthSubCategory = null,
                onCardClick = onCardClick,
                onFirstClick = onFirstClick,
                onSecondClick = onSecondClick,
                onThirdClick = onThirdClick,
                onFourthClick = onFourthClick
            )
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
    modifier: Modifier = Modifier

) {
    Card(modifier = modifier.clickable { onCardClick() }) {
        Column {
            Text(text = productType)

            Image(
                painter = painterResource(id = painter),
                contentDescription = painter.toString(),
                modifier = modifier
            )
        }
        if (firstSubCategory != null) {
            Text(text = firstSubCategory, modifier = Modifier.clickable { onFirstClick() })
        }
        if (secondSubCategory != null) {
            Text(text = secondSubCategory, modifier = Modifier.clickable { onSecondClick() })
        }
        if (thirdSubCategory != null) {
            Text(text = thirdSubCategory, modifier = Modifier.clickable { onThirdClick() })
        }
        if (fourthSubCategory != null) {
            Text(text = fourthSubCategory, modifier = Modifier.clickable { onFourthClick() })
        }
    }


}

@Composable
fun DetailPane(
    modifier: Modifier = Modifier, uiState: ProductListUiState
) {
    when (uiState) {
        is ProductListUiState.Loading -> {
            // TODO
        }

        is ProductListUiState.Error -> {
            // TODO
        }

        is ProductListUiState.Success -> {
            MakeupListPane(productList = uiState.products)
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
        items(items = productList) { product ->
            ProductItem(product = product)
        }
    }
}

@Preview
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