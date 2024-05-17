package com.example.glowgetter.test

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.matcher.ViewMatchers.hasContentDescription
import com.example.glowgetter.ui.homepane.HomeScreen
import com.example.glowgetter.ui.homepane.ProductCarousel
import com.example.glowgetter.ui.theme.GlowGetterTheme
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
    @Test
    fun homePanePopularProducts_displaysProductDetails(){
        composeTestRule.setContent {
            GlowGetterTheme {
                HomeScreen(username = "", {}, {}, {}, {}, {})
            }
        }
        val productNode = composeTestRule.onNodeWithText("Glow Reviver Lip Oil")
        productNode.performClick() // Simulate click
        productNode.assertTextContains("e.l.f.")

//        // 2. Verify description after click (assuming separate node)
        val productDetailsNode = composeTestRule.onNodeWithText(text = "Nourish and hydrate your lips while enhancing your pout's natural glow with e.l.f. Cosmetics' addicting Glow Reviver Lip Oil. The non-sticky formula imbues your pout with a sheer tint of color and glass-like shine while boosting your lips' natural hue.")
            productDetailsNode.assertIsDisplayed()
    }
}