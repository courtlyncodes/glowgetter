package com.example.glowgetter.test

import android.util.Log
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.hasContentDescription
import com.example.glowgetter.data.GlowGetterRepository
import com.example.glowgetter.data.ProductDataProvider
import com.example.glowgetter.data.favorites.FavoritesRepository
import com.example.glowgetter.ui.ProductListUiState
import com.example.glowgetter.ui.detailpane.EyesCategoryDetailPane
import com.example.glowgetter.ui.detailpane.FaceCategoryDetailPane
import com.example.glowgetter.ui.detailpane.LipsCategoryDetailPane
import com.example.glowgetter.ui.homepane.HomeScreen
import com.example.glowgetter.ui.homepane.ProductCarousel
import com.example.glowgetter.ui.productinfo.DetailScreen
import com.example.glowgetter.ui.productinfo.FaceProductListScreen
import com.example.glowgetter.ui.productinfo.Glossary
import com.example.glowgetter.ui.productinfo.Guides
import com.example.glowgetter.ui.theme.GlowGetterTheme
import com.example.glowgetter.ui.viewmodels.FavoritesUiState
import com.example.glowgetter.ui.viewmodels.GlowGetterViewModel
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test


class UiTests {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homePane_displaysCategoryButtons() {
        composeTestRule.setContent {
            GlowGetterTheme {
                HomeScreen(username = "", {}, {}, {}, {}, {})
            }
        }
        composeTestRule.onNodeWithText("Eyes").assertIsDisplayed()
        composeTestRule.onNodeWithText("Face").assertIsDisplayed()
        composeTestRule.onNodeWithText("Lips").assertIsDisplayed()
    }

    @Test
    fun homePane_displaysCarousel() {
        composeTestRule.setContent {
            GlowGetterTheme {
                HomeScreen(username = "", {}, {}, {}, {}, {})
            }
        }
        composeTestRule.onNode(hasContentDescription("Eyeshadow")).assertExists()
        composeTestRule.onNode(hasContentDescription("Foundation")).assertExists()
        composeTestRule.onNode(hasContentDescription("Lipstick")).assertExists()
    }

    @Test
    fun homePane_displaysPopularProducts() {
        composeTestRule.setContent {
            GlowGetterTheme {
                HomeScreen(username = "", {}, {}, {}, {}, {})
            }
        }
        composeTestRule.onNodeWithText("Glow Reviver Lip Oil").assertIsDisplayed()
    }

    @Test
    fun homePane_displaysUserName() {
        composeTestRule.setContent {
            GlowGetterTheme {
                HomeScreen(username = "test", {}, {}, {}, {}, {})
            }
        }
        composeTestRule.onNodeWithText("test").assertIsDisplayed()
    }

//    @Test
//    fun homePanePopularProducts_displaysProductDetails() {
//        composeTestRule.setContent {
//            GlowGetterTheme {
//                HomeScreen(username = "", {}, {}, {}, {}, {})
//            }
//        }
//
//        with(composeTestRule.onNodeWithText("Glow Reviver Lip Oil")) {
//            assertExists()
//            assertIsDisplayed()
//            performClick()
//            Log.wtf("clicked", "true")
//        }
//
//        // Wait for potential asynchronous operations (adjust timeout if needed)
//        composeTestRule.waitForIdle()
//
//        composeTestRule.onNodeWithText("e.l.f.").assertIsDisplayed()
//    }

    @Test
    fun productDetails_displaysProductDetails() {
        composeTestRule.setContent {
            GlowGetterTheme {
                DetailScreen(
                    onFavoritesClick = {},
                    product = ProductDataProvider.products[1]
                )
            }
        }
        composeTestRule.onNodeWithText("Rich & Foiled Rose to Fame Eyeshadow Palette")
            .assertIsDisplayed()
    }

    @Test
    fun faceCategoryScreen_displaysSubcategories() {
        composeTestRule.setContent {
            GlowGetterTheme {
                FaceCategoryDetailPane({}, {}, {}, {}, {})
            }
        }
        composeTestRule.onNodeWithText("Blush").assertIsDisplayed()
    }

    @Test
    fun eyeCategoryScreen_displaysSubcategories() {
        composeTestRule.setContent {
            GlowGetterTheme {
                EyesCategoryDetailPane({}, {}, {}, {})
            }
        }
        composeTestRule.onNodeWithText("Mascara").assertIsDisplayed()
    }

    @Test
    fun lipsCategoryScreen_displaysSubcategories() {
        composeTestRule.setContent {
            GlowGetterTheme {
                LipsCategoryDetailPane({}, {}, {})
            }
        }
        composeTestRule.onNodeWithText("Lipstick").assertIsDisplayed()
    }

    @Test
    fun faceProductListScreen_displaysButtons() {
        composeTestRule.setContent {
            GlowGetterTheme {
                FaceProductListScreen(
                    videoId = "c__JPlF5Q7o",
                    lifecycleOwner = LocalLifecycleOwner.current,
                    productName = ProductDataProvider.products[1].name,
                    {},
                    {},
                    {},
                    {},
                    {},
                    {},
                    {},
                    {},
                    uiState = ProductListUiState.Success(ProductDataProvider.products)
                )
            }
        }
        composeTestRule.onNodeWithText("Mineral Foundation").assertIsDisplayed()
        composeTestRule.onNodeWithText("BB & CC Cream").assertIsDisplayed()
    }

    @Test
    fun glossaryScreen_displaysButtons() {
        composeTestRule.setContent {
            GlowGetterTheme {
                Glossary(onBackClick = {})
            }
        }
        composeTestRule.onNodeWithText("Eye Candy").performClick()
    }
//
////    @Test
////    fun glossaryScreen_displaysGlossary() {
////        composeTestRule.setContent {
////            GlowGetterTheme {
////                Glossary(onBackClick = {})
////            }
////        }
////        composeTestRule.onNodeWithText(containsString( "Eye Candy")).assertIsDisplayed()
////    }
//
//    @Test
//    fun guidesScreen_displaysButtons() {
//        composeTestRule.setContent {
//            GlowGetterTheme {
//                Guides(onBackClick = {})
//            }
//        }
//        composeTestRule.onNode(hasClickAction()).performClick()
//    }
}