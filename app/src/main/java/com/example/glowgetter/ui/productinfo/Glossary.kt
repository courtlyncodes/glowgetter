package com.example.glowgetter.ui.productinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.glowgetter.ui.homepane.GlowGetterTopAppBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Glossary(onCardClick: (GlossaryItem) -> Unit) {
    val rememberScrollState = rememberScrollState()

    val itemToSectionIndexMap = mapOf(
        GlossaryItem.FACE_STUFF to 0,
        GlossaryItem.EYE_CANDY to 1,
        GlossaryItem.LIP_SERVICE to 2,
        GlossaryItem.BRUSH_BUDDIES to 3,
        GlossaryItem.FINISH_TOUCHES to 4,
        GlossaryItem.BONUS_WORDS to 5
    )
//
//    var selectedItem by remember { mutableStateOf<GlossaryItem?>(null) }
//
//    val coroutineScope = rememberCoroutineScope()
//
//    LaunchedEffect(selectedItem) {
//        if (selectedItem != null) {
//            val scrollToId = itemToSectionIndexMap[selectedItem] ?: 0
//            coroutineScope.launch {
//                rememberScrollState.scrollTo(scrollToId)
//            }
//        }
//    }

    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        item{
            Column{
                GlowGetterTopAppBar(text = "Makeup Glossary for Beginners")
                Text(
                    "Feeling lost in a land of brushes and powders? Don't worry, this glossary is your magic decoder ring for the world of makeup!"
                )
            }
        }
        item {
            Column {
                FaceStuff()
                EyeCandy()
                LipService()
                BrushBuddies()
                FinishingTouches()
                BonusWords()

            }
        }

        }
    }

@Composable
fun GlossaryCard(item: GlossaryItem, sectionIndex: Int, onCardClick: () -> Unit) {
    Card(
        onClick = onCardClick,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(item.title)
    }
}

@Composable
fun FaceStuff() {
    Column {
        Text(text = "Face Stuff")
        faceItems.forEach { item ->
            Text(
                text = "• $item",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun EyeCandy() {
    Column {
        Text(text = "Eye Candy")
        eyeItems.forEach { item ->
            Text(
                text = "• $item",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun LipService() {
    Column {
        Text(text = "Lip Service")
        lipsItems.forEach { item ->
            Text(
                text = "• $item",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun BrushBuddies() {
    Column {
        Text(text = "Brush Buddies")
        brushItems.forEach { item ->
            Text(
                text = "• $item",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun FinishingTouches() {
    Column {
        Text(text = "Finishing Touches")
        finishItems.forEach { item ->
            Text(
                text = "• $item",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun BonusWords() {
    Column {
        Text(text = "Bonus Words")
        bonusItems.forEach { item ->
            Text(
                text = "• $item",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

val faceItems = listOf(
    "Foundation: Your skin's BFF, this evens out your tone and creates a flawless canvas (think of it as Instagram for your face, but in real life).",
    "Primer: Like double-sided tape for makeup, this helps everything stick and makes your masterpiece last longer.",
    "Concealer: Your secret weapon against dark circles, blemishes, and anything else trying to steal your glow.",
    "Powder: This sets your foundation like a boss, keeping you shine-free and camera-ready.",
    "Highlighter: Basically a tiny disco ball for your face, it catches light and makes your cheekbones, nose, and cupid's bow pop.",
    "Bronzer: Think sunkissed skin without the sunburn! This adds warmth and can even be used to fake some sculpting (hello, cheekbones!).",
    "Blush: A rosy kiss of color for your cheeks, because who doesn't love a healthy flush?",
    "Contour: Contouring uses darker shades to define your facial features and create a more sculpted look."
)

val eyeItems = listOf(
    "Eyeshadow: Like painting for your eyelids, this comes in a million colors and finishes to create endless looks.",
    "Eyeliner: This bad boy defines your eyes, making them look bigger and brighter. Think of it as the eyeliner flick that completes your superhero persona.",
    "Mascara: Lashes on fleek? This lengthens, thickens, and darkens your lashes for a dramatic (or natural) eye look.",
    "Brow Pomade/Pencil: Your brows are the frames for your face! This helps you fill them in and create the perfect shape.",
    "Eyelash Curler: This little tool gives your lashes a dramatic lift, making your eyes appear bigger and brighter."
)

val lipsItems = listOf (
    "Lipstick: The classic way to add a pop of color to your lips. It comes in every shade imaginable, so find your perfect pout!",
    "Lip Gloss: Think of it as chapstick with a dazzling personality. It adds shine and dimension to your lips, making them look plumper and juicier.",
    "Lip Liner: This defines the shape of your lips and helps prevent lipstick from bleeding. Think of it as a coloring book for your lips, keeping things neat and tidy."
)

val brushItems = listOf (
    "Makeup Brushes: Different brushes have different jobs! Some are for applying, some for blending, some for precise work. Think of them as your trusty paintbrushes for creating a masterpiece.",
    "Makeup Sponge: This bouncy little friend helps blend out liquid and cream products for a seamless, airbrushed finish."
)

val finishItems = listOf (
    "Matte: No shine, all business. Perfect for oily skin or a natural look.",
    "Dewy: Think fresh, radiant skin! Like you just walked off a beach vacation.",
    "Satin: The best of both worlds! Subtle sheen for a glow without disco ball vibes."
)

val bonusItems = listOf (
    "Dupe: Not your BFF's actual lipstick (don't be weird!), but a more affordable product that's practically its twin. Score!",
    "Natural Makeup: No clown makeup here! This look enhances your natural beauty, like a subtle filter for real life.",
    "Full Coverage: Need to hide the evidence of that late-night pizza party? This makeup completely covers imperfections for a flawless finish.",
    "Setting Spray: The superhero cape for your makeup! This mist helps it stay put all day, so you can conquer the world without worrying about smudging."
)
enum class GlossaryItem(val title: String) {
    FACE_STUFF("Face Stuff"),
    EYE_CANDY("Eye Candy"),
    LIP_SERVICE("Lip Service"),
    BRUSH_BUDDIES("Brush Buddies"),
    FINISH_TOUCHES("Finishing Touches"),
    BONUS_WORDS("Bonus Words")
}
@Preview
@Composable
fun GlossaryPreview() {
    Glossary(onCardClick = {})
}