package com.example.glowgetter.ui.welcomescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Image(
            painter = painterResource(id = R.mipmap.welcome_screen_graphic),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = modifier.size(400.dp)
        )
       Text(
           text = stringResource(R.string.tagline),
           style = MaterialTheme.typography.titleLarge,
           modifier = modifier.padding(vertical = 8.dp)
       )
       Text(text = stringResource(R.string.enter_name))
       OutlinedTextField(
           value = text,
           onValueChange = onValueChange,
           singleLine = true
       )
    }
}