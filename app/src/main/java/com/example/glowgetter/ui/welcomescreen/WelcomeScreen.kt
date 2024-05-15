package com.example.glowgetter.ui.welcomescreen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.glowgetter.R
import com.example.glowgetter.ui.AppViewModelProvider
import com.example.glowgetter.ui.viewmodels.GlowGetterViewModel

@Composable
fun WelcomeScreen(
    username: String,
    onWelcomeClick: () -> Unit,
    onContinueClick: () -> Unit,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
    ){
        item {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = modifier.padding(top = 40.dp)
            )
        }
       item {
           Text(
               text = stringResource(R.string.tagline),
               style = MaterialTheme.typography.titleLarge,
               modifier = modifier.padding(vertical = 8.dp)
           )
       }
        item{
            Image(
                painter = painterResource(id = R.mipmap.welcome_screen_graphic),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = modifier.size(400.dp)
            )
        }
       item{
           OutlinedTextField(
               value = username,
               label = { Text(text = stringResource(R.string.enter_name)) },
               onValueChange = onValueChange,
               singleLine = true
           )
       }
       item{
           Button(onClick = onWelcomeClick) {
               Text(text = stringResource(R.string.get_started))
           }
       }
        item{
            Spacer(modifier = modifier.size(16.dp))
        }
        item{
            Row() {
                Text(
                    text = stringResource(R.string.continue_without_account),
                    modifier = modifier.clickable { onContinueClick() }
                )
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = stringResource(R.string.continue_without_account))
            }
        }
    }
}
