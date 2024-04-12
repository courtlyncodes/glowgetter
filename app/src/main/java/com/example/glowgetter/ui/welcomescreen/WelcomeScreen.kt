package com.example.glowgetter.ui.welcomescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.glowgetter.R

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
) {
    val text by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
    ){
        Text(text = stringResource(id = R.string.app_name))
        Image(
            painter = painterResource(id = R.mipmap.welcome_screen_graphic),
            contentDescription = null
        )
       Text(text = stringResource(R.string.tagline))
       OutlinedTextField(
           value = text,
           onValueChange = onValueChange,
           singleLine = true
       )
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}