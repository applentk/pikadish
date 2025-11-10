package com.applentk.pickadish.feature.ui.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.applentk.pickadish.R
import com.applentk.pickadish.feature.ui.theme.ColorScheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TextInputWithTextScreen(
    placeholder: String,
    texts: List<String>,
    onInputChange: (String) -> Unit,
    onClickEachText: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }

    val sortedTexts = texts
        .filter { it.lowercase().startsWith(text.lowercase()) }
        .sorted()

    Column(modifier = modifier) {
        if (WindowInsets.isImeVisible && !text.isBlank()) {
            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ColorScheme.background)
                    .weight(1f)
                    .padding(all = 16.dp)
                    .padding(top = 24.dp)
            ) {
                items(items = sortedTexts, itemContent = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null // Disables the ripple effect
                            ) {
                                onClickEachText(it)
                            }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_search_24),
                            contentDescription = "search",
                            modifier = Modifier.size(36.dp)
                        )
                        Text(
                            text = it,
                            fontSize = 24.sp,
                            lineHeight = 28.sp,
                        )
                    }
                })
            }
        }

        TextField(
            value = text,
            onValueChange = { newText ->
                text = newText
                onInputChange(newText)
            },
            placeholder = {
                Text(
                    text = placeholder,
                    fontSize = 14.sp
                )
            },
            shape = RoundedCornerShape(999.dp),
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = Color.Gray,
                unfocusedPlaceholderColor = ColorScheme.primary,
                unfocusedContainerColor = ColorScheme.tertiary,
                unfocusedBorderColor = Color.Transparent,
                focusedPlaceholderColor = ColorScheme.primary,
                focusedContainerColor = ColorScheme.tertiary,
                focusedBorderColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .imePadding()
                .padding(horizontal = 20.dp, vertical = 14.dp)
                .let {
                    if (!WindowInsets.isImeVisible) {
                        it.padding(bottom = 16.dp)
                    }
                    else {
                        it
                    }
                }
        )
    }
}
