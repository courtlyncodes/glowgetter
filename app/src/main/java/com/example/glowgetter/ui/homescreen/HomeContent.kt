package com.example.glowgetter.ui.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.helper.widget.Carousel
import com.example.glowgetter.Product
import com.example.glowgetter.R
import coil.compose.AsyncImage
import coil.request.ImageRequest

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

@Composable
fun ProductItem(
    product: Product,
    modifier: Modifier = Modifier
){
    Card(){
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
            // add a lazy row of circles
            Text(product.price)
        }
    }
}

@Composable
fun TopAppBar(
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
            Text(text = "Find")
            Text(text = "Your Glow" )
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
    TopAppBar(onSearch = { "search me"})
}


