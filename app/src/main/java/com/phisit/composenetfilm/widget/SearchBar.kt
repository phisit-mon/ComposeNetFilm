package com.phisit.composenetfilm.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phisit.composenetfilm.AppIconPack
import com.phisit.composenetfilm.appiconpack.Search
import com.phisit.composenetfilm.ui.theme.ComposeNetFilmTheme

@Composable
fun SearchBar(
    value: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    enable: Boolean = true,
    requestFocus: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Search,
    keyboardAction: KeyboardActions = KeyboardActions.Default,
    onFocusChanged: (Boolean) -> Unit = {},
    onValueChanged: (String) -> Unit = {}
) {
    val focusRequester = remember {
        FocusRequester()
    }

    SideEffect {
        if (requestFocus) {
            focusRequester.requestFocus()
        }
    }

    TextField(
        value = value,
        modifier = modifier
            .focusRequester(focusRequester)
            .onFocusChanged { focusState ->
                onFocusChanged(focusState.isFocused)
            },
        singleLine = true,
        enabled = enable,
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.labelMedium
            )
        },
        onValueChange = onValueChanged,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(8.dp),
        leadingIcon = {
            Image(imageVector = AppIconPack.Search, contentDescription = "Search")
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        keyboardActions = keyboardAction,
    )
}

@Preview(showBackground = false)
@Composable
fun GreetingPreview() {
    ComposeNetFilmTheme {
        SearchBar(value = "", placeholder = "Search for movies")
    }
}