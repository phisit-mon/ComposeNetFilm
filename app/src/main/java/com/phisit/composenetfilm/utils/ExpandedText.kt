package com.phisit.composenetfilm.utils

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview


val CALLAPSED_MAXIMUM_TEXT_LINE = 3

@Composable
fun ExpandedText(
    text: String,
    modifier: Modifier = Modifier,
    showMoreText: String = "... Show More",
    showLessText: String = " Show less",
    textAlignment: TextAlign? = null,
    style: TextStyle = LocalTextStyle.current,
    showMoreStyle: SpanStyle = SpanStyle(fontWeight = FontWeight.Bold),
    showLessStyle: SpanStyle = showMoreStyle,
    collapsedMaxLine: Int = CALLAPSED_MAXIMUM_TEXT_LINE,
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var isClickable by remember {
        mutableStateOf(false)
    }
    var lastCharIndex by remember {
        mutableStateOf(0)
    }

    Box(
        modifier = Modifier
            .clickable(isClickable) {
                isExpanded = !isExpanded
            }
            .then(modifier)
    ) {
        Text(
            text = buildAnnotatedString {
                if (isClickable) {
                    if (isExpanded) {
                        append(text)
                        withStyle(style = showLessStyle) {
                            append(showLessText)
                        }
                    } else {
                        val adjustText = text.substring(startIndex = 0, endIndex = lastCharIndex)
                            .dropLast(showMoreText.length)
                            .dropLastWhile { Character.isWhitespace(it) || it == '.' }
                        append(adjustText)
                        withStyle(style = showMoreStyle) {
                            append(showMoreText)
                        }
                    }
                } else {
                    append(text)
                }
            },
            maxLines = if (isExpanded) Int.MAX_VALUE else collapsedMaxLine,
            textAlign = textAlignment,
            modifier = modifier
                .fillMaxWidth()
                .animateContentSize(),
            style = style,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { textLayoutResult ->
                if (!isExpanded && textLayoutResult.hasVisualOverflow) {
                    isClickable = true
                    lastCharIndex = textLayoutResult.getLineEnd(collapsedMaxLine - 1, true)
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpandedTextPreview() {
    ExpandedText(
        text = "01234567890",
        collapsedMaxLine = 2
    )
}