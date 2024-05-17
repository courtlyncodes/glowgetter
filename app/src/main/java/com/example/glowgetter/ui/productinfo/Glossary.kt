package com.example.glowgetter.ui.productinfo

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.glowgetter.R
import com.example.glowgetter.ui.homepane.GlowGetterTopAppBar
import kotlinx.coroutines.launch

@Composable
fun Glossary(
    onBackClick: () -> Unit
) {
    BackHandler {
        onBackClick()
    }
    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        state = scrollState,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        item {
            Column {
                GlowGetterTopAppBar(text = stringResource(R.string.glossary_top_app_bar))
                Text(
                    stringResource(R.string.glossary_intro),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }
        item {
            GlossaryButtons(scrollState)
        }
        item {
            FaceStuff()
        }
        item {
            EyeCandy()
        }
        item {
            LipService()
        }
        item {
            BrushBuddies()
        }
        item {
            FinishingTouches()
        }
        item {
            BonusWords()
        }
    }
    AnimatedVisibility(visible = !scrollState.isScrollingUp(), enter = fadeIn(), exit = fadeOut()) {
        GoToTop {
            scope.launch {
                scrollState.scrollToItem(0)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GlossaryButtons(
    scrollState: LazyListState,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .padding(8.dp)
    ) {
        GlossaryButton(stringResource(R.string.face_stuff)) {
            scope.launch { scrollState.animateScrollToItem(2) }
        }
        Spacer(modifier = modifier.weight(1f))
        GlossaryButton(stringResource(R.string.lip_service)) {
            scope.launch { scrollState.animateScrollToItem(4) }
        }
        Spacer(modifier = modifier.weight(1f))
        GlossaryButton(stringResource(R.string.finishing_touches)) {
            scope.launch { scrollState.animateScrollToItem(6) }
        }
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .padding(8.dp)
    ) {
        GlossaryButton(stringResource(R.string.eye_candy)) {
            scope.launch { scrollState.animateScrollToItem(3) }
        }
        Spacer(modifier = modifier.weight(1f))
        GlossaryButton(stringResource(R.string.brush_buddies)) {
            scope.launch { scrollState.animateScrollToItem(5) }
        }
        Spacer(modifier = modifier.weight(1f))
        GlossaryButton(stringResource(R.string.bonus_words)) {
            scope.launch { scrollState.animateScrollToItem(8) }
        }
    }
}

@Composable
fun GoToTop(goToTop: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            onClick = goToTop,
            content = {
                Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Scroll to Top")
            },
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)

        )
    }
}

@Composable
fun GlossaryButton(
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
            .width(125.dp)
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
fun FaceStuff(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(
            text = "${stringResource(R.string.face_stuff)} \uD83D\uDE18",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
        faceItems.forEach { item ->
            Text(
                text = "• ${stringResource(item)}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun EyeCandy(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(
            text = "${stringResource(R.string.eye_candy)} \uD83D\uDE0E",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
        eyeItems.forEach { item ->
            Text(
                text = "• ${stringResource(item)}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun LipService(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(
            text = "${stringResource(R.string.lip_service)} \uD83E\uDEE6",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
        lipsItems.forEach { item ->
            Text(
                text = "• ${stringResource(item)}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun BrushBuddies(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(
            text = "${stringResource(R.string.brush_buddies)} \uD83D\uDD8C\uFE0F",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
        brushItems.forEach { item ->
            Text(
                text = "• ${stringResource(item)}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun FinishingTouches(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(
            text = "${stringResource(R.string.finishing_touches)} \uD83E\uDE9E",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
        finishItems.forEach { item ->
            Text(
                text = "• ${stringResource(item)}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun BonusWords(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(
            start = 8.dp,
            top = 8.dp,
            end = 8.dp,
            bottom = 60.dp
        )
    ) {
        Text(
            text = "${stringResource(R.string.bonus_words)} \uD83D\uDC85",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
        bonusItems.forEach { item ->
            Text(
                text = "• ${stringResource(item)}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}

val faceItems = listOf(
    R.string.face_items_foundation,
    R.string.face_items_primer,
    R.string.face_items_concealer,
    R.string.face_items_powder,
    R.string.face_items_highlighter,
    R.string.face_items_bronzer,
    R.string.face_items_blush,
    R.string.face_items_contour
)

val eyeItems = listOf(
    R.string.eye_items_eyeshadow,
    R.string.eye_items_eyeliner,
    R.string.eye_items_mascara,
    R.string.eye_items_brows,
    R.string.eye_items_eyelash_curler
)

val lipsItems = listOf(
    R.string.lip_items_lipstick,
    R.string.lip_items_lip_gloss,
    R.string.lip_items_lipliner
)

val brushItems = listOf(
    R.string.brush_items_brushes,
    R.string.brush_items_sponge
)

val finishItems = listOf(
    R.string.finishing_items_matte,
    R.string.finishing_items_dewy,
    R.string.finishing_items_satin
)

val bonusItems = listOf(
    R.string.bonus_items_dupe,
    R.string.bonus_items_natural,
    R.string.bonus_items_full_coverage,
    R.string.bonus_items_setting_spray
)