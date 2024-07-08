package com.phisit.composenetfilm.`ีutils`

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color

fun Modifier.clickableWithRippleEffect(onClick: () -> Unit): Modifier = composed {
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = rememberRipple(color = Color.DarkGray),
        onClick = onClick
    )
}

fun Modifier.debounceClickable(debounceTime: Long = 1500L, onClick: () -> Unit): Modifier =
    composed {
        var latestClickedTimeStamp = 0L
        this.clickableWithRippleEffect(
            onClick = {
                if (System.currentTimeMillis() - latestClickedTimeStamp > debounceTime) {
                    latestClickedTimeStamp = System.currentTimeMillis()
                    onClick()
                }
            }
        )
    }