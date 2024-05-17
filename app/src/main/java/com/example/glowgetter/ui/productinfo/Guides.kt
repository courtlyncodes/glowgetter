package com.example.glowgetter.ui.productinfo

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.glowgetter.R
import com.example.glowgetter.ui.homepane.GlowGetterTopAppBar

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun Guides(
    onBackClick: () -> Unit
){
    val navigator = rememberListDetailPaneScaffoldNavigator<Nothing>()
    var currentDestination by rememberSaveable { mutableStateOf(DetailPane.BRUSHES) }

    BackHandler {
        onBackClick()
    }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
                   GuideList(
                       onBrushClick = {
                           currentDestination = DetailPane.BRUSHES
                           navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
                                      },
                       onEyeshadowClick = {
                           currentDestination = DetailPane.EYESHADOW
                           navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
                                          },
                       onLipsClick = {
                           currentDestination = DetailPane.LIPS
                           navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
                                     },
                       onCHClick = {
                           currentDestination = DetailPane.CONTOUR_HIGHLIGHT
                           navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
                                   },
                       onFoundationClick = {
                           currentDestination = DetailPane.FOUNDATION
                           navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
                       }
                   )
        },
        detailPane = {
            when (currentDestination) {
                DetailPane.BRUSHES -> BrushesGuide()
                DetailPane.EYESHADOW -> EyeshadowGuide()
                DetailPane.LIPS -> LipsGuide()
                DetailPane.CONTOUR_HIGHLIGHT -> ContourHighlightGuide()
                DetailPane.FOUNDATION -> FoundationGuide()
                }
            }
    )
}

@Composable
fun GuideList(
    onBrushClick: () -> Unit,
    onEyeshadowClick: () -> Unit,
    onLipsClick: () -> Unit,
    onCHClick: () -> Unit,
    onFoundationClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        GlowGetterTopAppBar(stringResource(R.string.guides_top_app_bar))
        Text(
            text = stringResource(R.string.guides_intro),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
        )

    GuideButtons(
        onBrushClick = onBrushClick,
        onEyeshadowClick = onEyeshadowClick,
        onLipsClick = onLipsClick,
        onCHClick = onCHClick,
        onFoundationClick = onFoundationClick,
        modifier = Modifier
            .padding(20.dp)
    )
    }
}

@Composable
fun GuideButtons(
    onBrushClick: () -> Unit,
    onEyeshadowClick: () -> Unit,
    onLipsClick: () -> Unit,
    onCHClick: () -> Unit,
    onFoundationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        GuideButton(stringResource(R.string.brushes), onBrushClick)
        GuideButton(stringResource(R.string.eyeshadow), onEyeshadowClick)
        GuideButton(stringResource(R.string.lips), onLipsClick)
        GuideButton(stringResource(R.string.contouring_and_highlighters), onCHClick)
        GuideButton(stringResource(R.string.foundation), onFoundationClick)
    }
}

@Composable
fun GuideButton(
    text: String,
    onClick: () -> Unit,
) {
    Card(
        onClick = onClick,
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        ),
        modifier = Modifier
            .width(300.dp)
    ) {
        Text(
            text,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
                .padding(8.dp)
        )
    }
}

@Composable
fun BrushesGuide(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        GlowGetterTopAppBar(stringResource(R.string.brushes))
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = stringResource(R.string.guides_brushes),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(8.dp)
                )
            Image(
                painter = painterResource(id = R.mipmap.brushes),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = modifier
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(4.dp))
                )
        }
    }
}

@Composable
fun EyeshadowGuide(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        item {
            GlowGetterTopAppBar(stringResource(R.string.eyeshadow))
        }
        item {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = stringResource(R.string.guides_eyeshadow1),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(8.dp)
                )
                Image(
                    painter = painterResource(R.mipmap.eye_application),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(400.dp)
                )
                Text(
                    text = stringResource(R.string.guides_eyeshadow2),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun LipsGuide(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        item {
            GlowGetterTopAppBar(stringResource(R.string.lips))
        }
        item {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = stringResource(R.string.guides_lips1),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(8.dp)
                )
                Image(
                    painter = painterResource(id = R.mipmap.lips_application),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(400.dp)
                )
                Text(
                    text = stringResource(R.string.guides_lips2),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

    }
}
@Composable
fun ContourHighlightGuide(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        item {
            GlowGetterTopAppBar(stringResource(R.string.contouring_and_highlighters))
        }
        item {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = stringResource(R.string.guides_contouring_highlighting1),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(8.dp)
                )
                Image(
                    painter = painterResource(id = R.mipmap.countour_highlight),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(400.dp)
                )
                Text(
                    text = stringResource(R.string.guides_contouring_highlighting2),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

    }
}

@Composable
fun FoundationGuide(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        item {
            GlowGetterTopAppBar(stringResource(R.string.foundation))
        }
        item {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = stringResource(R.string.guides_foundation1),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(8.dp)
                )
                Image(
                    painter = painterResource(id = R.mipmap.foundation_application),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.size(500.dp)
                )
                Text(
                    text = stringResource(R.string.guides_foundation2),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

    }
}

enum class DetailPane {
    BRUSHES,
    EYESHADOW,
    LIPS,
    CONTOUR_HIGHLIGHT,
    FOUNDATION
}