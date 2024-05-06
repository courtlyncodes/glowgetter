package com.example.glowgetter.ui.productlist

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.glowgetter.Product

@Composable
fun DetailScreen(
    product: Product,
    modifier: Modifier = Modifier,
){
    val context = LocalContext.current
    LazyColumn() {
        item {
            Card(
                modifier = modifier
                    .padding(horizontal = 8.dp)
            ) {
                Column(
                    modifier = modifier
                        .padding(8.dp)
                ) {
                    Text(
                        product.name,
                        modifier = modifier.clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(product.productLink))
                            context.startActivity(intent)
                        })
                    product.brand?.let { Text(it) }
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(product.image)
                            .crossfade(true)
                            .build(),
                        contentDescription = product.productType,
                        contentScale = ContentScale.Crop,
                        modifier = modifier.aspectRatio(1f)
                    )
                    product.description?.let { Text(it) }

                }
            }
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview(){
    val product = Product(
        id = 1,
        name = "Test Product",
        brand = "Test Brand",
        productType = "Test Type",
        description = "Test Description",
        image = "image",
        category = "Test Category",
        productLink = "Test Link"
    )
    DetailScreen(product = product)
}
