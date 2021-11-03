package com.example.jetpackcomposetest.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = White,
    primaryVariant = Black_23262C,
    secondary = Green_00FFD1,
    surface = White,
    error = Red_FF6231,
    background = White,
    onPrimary = Black_23262C,
    onBackground = Black_23262C,
    onSurface = Black_23262C,
    onSecondary = Black_23262C,
    onError = Black_0F1011
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun JetPackComposeTestTheme(content: @Composable() () -> Unit) {
    val colors = LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}