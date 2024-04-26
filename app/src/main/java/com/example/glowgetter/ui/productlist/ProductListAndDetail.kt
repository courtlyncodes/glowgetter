package com.example.glowgetter.ui.productlist

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.glowgetter.Product
import com.example.glowgetter.ui.homescreen.HomeScreen
import com.example.glowgetter.ui.homescreen.ProductItem
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.glowgetter.R
import com.example.glowgetter.ui.ProductListUiState
import com.example.glowgetter.ui.viewmodels.GlowGetterViewModel

@Composable
fun EyesCategoryDetailPane(modifier: Modifier = Modifier) {
    LazyVerticalGrid(columns = GridCells.Adaptive(157.dp)) {
        item {
            CategoryDetailCard(
                productType = "Eyebrow Pencils",
                painter = R.mipmap.eyebrow_pencil,
                firstProductType = null,
                secondProductType = null,
                thirdProductType = null,
                fourthProductType = null,
                fifthProductType = null
            )
        }
        item {
            CategoryDetailCard(
                productType = "Mascara",
                painter = R.mipmap.mascara,
                firstProductType = null,
                secondProductType = null,
                thirdProductType = null,
                fourthProductType = null,
                fifthProductType = null
            )
        }
        item {
            CategoryDetailCard(
                productType = "Eyeliner",
                painter = R.mipmap.eyeliner,
                firstProductType = "Liquid",
                secondProductType = "Pencil",
                thirdProductType = "Gel",
                fourthProductType = "Cream",
                fifthProductType = null
            )
        }
        item {
            CategoryDetailCard(
                productType = "Eyeshadow",
                painter = R.mipmap.eyeshadow_product,
                firstProductType = "Palette",
                secondProductType = "Pencil",
                thirdProductType = "Cream",
                fourthProductType = null,
                fifthProductType = null
            )
        }

    }
}

//@Composable
//fun FaceCategoryDetailPane(modifier: Modifier = Modifier){
//    LazyVerticalGrid(columns = GridCells.Adaptive(157.dp)) {
//        item
//    }
//}
//
//@Composable
//fun LipsCategoryDetailPane(modifier: Modifier = Modifier){
//    LazyVerticalGrid(columns = GridCells.Adaptive(157.dp)) {
//        item
//    }
//}
@Composable
fun CategoryDetailCard(
    painter: Int,
    productType: String,
    firstProductType: String?,
    secondProductType: String?,
    thirdProductType: String?,
    fourthProductType: String?,
    fifthProductType: String?,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column {
            Text(text = productType)
            Image(painter = painterResource(id = painter), contentDescription = painter.toString())
            if (firstProductType != null) {
                Text(text = firstProductType)
            }
            if (secondProductType != null) {
                Text(text = secondProductType)
            }
            if (thirdProductType != null) {
                Text(text = thirdProductType)
            }
            if (fourthProductType != null) {
                Text(text = fourthProductType)
            }
            if (fifthProductType != null) {
                Text(text = fifthProductType)
            }
        }
    }
}

@Composable
fun DetailPane(
    modifier: Modifier = Modifier,
    uiState: ProductListUiState
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

@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ListDetailScreen(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GlowGetterViewModel = viewModel(factory = GlowGetterViewModel.Factory)
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<Nothing>()
    val glowGetterViewModel: GlowGetterViewModel = viewModel(factory = GlowGetterViewModel.Factory)
    val uiState by viewModel.productUiState.collectAsState()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            HomeScreen(
                onEyesClick = {
                    navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
                },
                onFaceClick =
                onButtonClick,
                onLipsClick =
                onButtonClick

            )
        },
        detailPane = {
            DetailPane(uiState = viewModel.productListUiState)
        }
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
        items(items = productList) { product ->
            ProductItem(product = product)
        }
    }
}

@Preview
@Composable
fun EyesCategoryDetailPanePreview() {
    EyesCategoryDetailPane()
}