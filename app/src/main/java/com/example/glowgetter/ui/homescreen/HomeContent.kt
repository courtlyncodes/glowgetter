package com.example.glowgetter.ui.homescreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemInfo
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.glowgetter.Product
import com.example.glowgetter.R
import com.example.glowgetter.data.ProductDataProvider
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ListDetailScreen(
    modifier: Modifier = Modifier
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<Nothing>()

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            HomeScreen()
        },
        detailPane = {
            MakeupListPane(productList = ProductDataProvider.products)
        }
    )
}


@Composable
fun ProductCategories(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Card(modifier = modifier.clickable {}) {
            Text(text = stringResource(R.string.eyes))
        }
        Card(modifier = modifier.clickable {}) {
            Text(text = stringResource(R.string.face))
        }
        Card(modifier = modifier.clickable {}) {
            Text(text = stringResource(R.string.lips))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductCarousel(
    autoScrollEnabled: Boolean = true,
    autoScrollInterval: Int = 3000,
    modifier: Modifier = Modifier,
) {
    val photos = listOf(
        R.mipmap.lipstick,
        R.mipmap.eyeshadow,
        R.mipmap.foundation
    )
    val pageCount = photos.size
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 250 })

// infinitely loop through images every 2 seconds while app is live
    LaunchedEffect(autoScrollEnabled, autoScrollInterval) {
        if (autoScrollEnabled) {
            while (true) {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                delay(autoScrollInterval.toLong())
            }
        }
    }
    HorizontalPager(
        contentPadding = PaddingValues(horizontal = 16.dp),
        pageSpacing = 16.dp,
        state = pagerState,
    ) { index ->
        val page = index % pageCount
        Image(
            painter = painterResource(photos[page]),
            contentDescription = photos[page].toString()
        )
    }
}

// possibly delete
@Composable
fun HomePaneMakeupProductsList(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    val productList = ProductDataProvider.products


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

//this should go in another file
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

//this should also go in the same file as the above composable/.....maybe
@Composable
fun ProductItem(
    product: Product,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.width(150.dp)) {
        Column() {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.image)
                    .crossfade(true)
                    .build(),
                contentDescription = product.productType,
            )
            Text(product.brand)
            Text(product.name)
            // add a lazy row of circles for product colors
            product.price?.let { Text(it) }
        }
    }
}

@Composable
fun GgTopAppBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit
) {
    val text by remember {
        mutableStateOf("")
    }

    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.mipmap.top_app_bar_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .heightIn(96.dp)
                .fillMaxWidth()
        )
        Column(modifier = modifier) {
            Text(text = stringResource(R.string.find))
            Text(text = stringResource(R.string.your_glow))
//            Spacer(modifier = modifier.padding(16.dp))
            BasicTextField(
                value = text,
                onValueChange = {
                    onSearch(it)
                },
                maxLines = 1,
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                modifier = Modifier
                    .shadow(5.dp, CircleShape)
                    .background(Color.White, CircleShape)
//                .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val products1 = ProductDataProvider.products.subList(0, 2)
    val products2 = ProductDataProvider.products.subList(2, 4)
    val products3 = ProductDataProvider.products.subList(4, 6)
    val products4 = ProductDataProvider.products.subList(6, 8)
    val products5 = ProductDataProvider.products.subList(8, 10)


    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        GgTopAppBar(onSearch = { "search me" })
        ProductCategories()
        ProductCarousel()
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            products1.forEach { product ->
                ProductItem(product = product)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            products2.forEach { product ->
                ProductItem(product = product)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            products3.forEach { product ->
                ProductItem(product = product)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            products4.forEach { product ->
                ProductItem(product = product)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            products5.forEach { product ->
                ProductItem(product = product)
            }
        }
    }
}


//    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
//        LazyColumn() {
//            item {
//                GgTopAppBar(onSearch = { "search me" })
//            }
//            item {
//                ProductCategories()
//            }
//            item {
//                ProductCarousel()
//            }
//            items(count = products.size) { product ->
//                ProductItem(product = products[product])
//            }
//        }
//        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
//            items(count = products.size) { product ->
//                ProductItem(product = products[product])
//            }
//        }
//    }


//@Preview
//@Composable
//fun ProductItemPreview() {
//    val product = Product(
//        id = 1,
//        brand = "brand",
//        name = "name",
//        price = "price",
//        image = "image",
//        productType = "productType",
//        description = "description",
//        rating = 5.0,
//        category = "category",
//        productColors = listOf("color", "color")
//    )
//    ProductItem(product)
//}

@Preview
@Composable
fun TopAppBarPreview() {
    GgTopAppBar(onSearch = { "search me" })
}

@Preview
@Composable
fun ProductCategoriesPreview() {
    ProductCategories()
}

@Preview
@Composable
fun CarouselPreview() {
    ProductCarousel()
}

@Preview
@Composable
fun MakeupListPreview() {
    MakeupListPane(productList = ProductDataProvider.products)
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
