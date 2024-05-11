package com.example.glowgetter.ui.productinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.glowgetter.R
import com.example.glowgetter.ui.homepane.GlowGetterTopAppBar

@Composable
fun Guides() {
    LazyColumn {
        item {
            Column {
                GlowGetterTopAppBar("Makeup Guides and Cheat Sheets")
                Text(text = "Feeling lost in a land of brushes and powders? Don't worry, these makeup guides and cheat sheets are your magic decoder ring for the world of makeup! ✨")
            }
        }
        item {
            Column {
                Text(text = "Brushes")
                Text(text = "Brushes got you feeling overwhelmed? Don't fret, makeup newbie! Real Techniques and EcoTools offer amazing quality at wallet-friendly prices. Want to level up your brush game? Morphe and e.l.f. have a vast selection for all your blending, buffing, and bronzing needs. Now go forth and conquer the makeup world, one fluffy brush at a time!")
                Image(painter = painterResource(id = R.mipmap.brushes), contentDescription = null)
            }
        }
        item {
            Column {
                Text(text = "Eyeshadow")
                Text(
                    text = "Feeling lost in the land of eyeshadow, eyeliner, and brows? Don't sweat it, makeup newbie! Drugstore brands like Wet n Wild, Maybelline, and e.l.f. offer fantastic (and affordable!) options. We're talking pigmented eyeshadow palettes in every shade imaginable, smooth eyeliners for precise lines, and brow pencils or gels to tame those arches. Time to ditch the makeup struggle and create stunning eye looks that won't break the bank!\n If you want to step up your makeup game, dive into the world of higher-end brands!  Sephora's got your back with cult-favorites like Urban Decay's Naked palettes (a must-have for any eyeshadow enthusiast) and Fenty Beauty's Fly Pencil eyeliner for effortless definition.  Ulta boasts Too Faced's Born This Way Super Brow for natural-looking brows and Tarte's Tartelette in Bloom Clay Eyeshadow Palette, packed with gorgeous, blendable shades.  Get ready to experience the luxurious quality and pigment payoff these brands offer, and watch your eye looks transform!"
                )
                Image(
                    painter = painterResource(R.mipmap.eye_application),
                    contentDescription = null
                )
            }
        }
        item {
            Column {
                Text(text = "Lips")
                Text(text = "Lip goals without breaking the bank? We got you! Drugstore darlings like NYX Professional Makeup offer a fantastic selection of lip products.  NYX Slide On Lip Pencils line your lips with creamy color that stays put, while their Butter Gloss delivers a high-shine finish with a hint of color. Craving a liquid lipstick that won't dry out your lips? Milani's Matte Liquid Lipsticks come in a vast array of shades and offer impressive staying power at a budget-friendly price.  For a universally flattering lip balm with a hint of tint, check out e.l.f. Cosmetics' Tinted Lip Balm - it's perfect for everyday wear!  Get ready to rock luscious lips without sacrificing your savings!")
                Image(
                    painter = painterResource(id = R.mipmap.lips_application),
                    contentDescription = null
                )
            }
        }
        item {
            Column {
                Text(text = "Contouring and Highlighters")
                Text(
                    text = "Wanna sculpt and glow like a celeb, but without the celeb budget? No problem!\n" +
                            "\n" +
                            "Drugstore Duos:  Wet n Wild's MegaGlo Contour Palette offers buildable shades for sculpting and highlighting, while Milani's Baked Multitasking Makeup Stick lets you contour and highlight on the go!\n" +
                            "\n" +
                            "Mid-Range Marvels:  For a step up, e.l.f.'s Putty Primer is a fantastic base for your contour and highlight, while Maybelline's Face Studio Master Contour Palette provides a range of blendable shades for a natural-looking definition.\n" +
                            "\n" +
                            "High-End Hues:  Ready to splurge?  Sephora's Fenty Beauty Match Stix Skinstick lets you customize your contour and highlight shades for a flawless finish, while NARS' Radiant Creamy Concealer is a cult-favorite for brightening under eyes and highlighting cheekbones."
                )
                Image(
                    painter = painterResource(id = R.mipmap.countour_highlight),
                    contentDescription = null
                )
            }
        }
        item {
            Column {
                Text(text = "Foundation")
                Text(
                    text = "Face feeling bare? Foundation to the rescue!\n" +
                            "\n" +
                            "Drugstore Deliverance: Maybelline's Fit Me Matte + Poreless Foundation offers lightweight, buildable coverage for oily and normal skin, while L'Oreal's Infallible Pro-Matte Foundation tackles shine and provides full coverage for those who need it most.\n" +
                            "\n" +
                            "Mid-Range Must-Haves:  For a dewy, radiant finish, e.l.f.'s Flawless Finish Foundation is a steal, while NYX Professional Makeup's Born This Way Foundation offers medium, buildable coverage that feels like a second skin.\n" +
                            "\n" +
                            "High-End Holy Grails:  Sephora beckons with Fenty Beauty's Pro Filt'r Soft Matte Longwear Foundation, a favorite for its long-lasting, weightless matte finish in a massive shade range.  Ulta boasts Lancôme's Teint Idole Ultra Long Wear Foundation, offering full coverage and a flawless finish that lasts all day.\n" +
                            "\n" +
                            "Find your perfect foundation match, no matter your skin type or budget, and get ready to rock a flawless base!"
                )
                Image(
                    painter = painterResource(id = R.mipmap.foundation_application),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun GuidesPreview() {
    Guides()
}