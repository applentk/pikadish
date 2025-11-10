package com.applentk.pickadish.feature.ui.composes

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.applentk.pickadish.feature.ui.theme.ColorScheme

@Composable
fun IconButton(
    onClick: () -> Unit,
    painter: Painter,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onClick,
        shape = CircleShape,
        containerColor = ColorScheme.background,
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
        modifier = modifier
            .border(2.dp, ColorScheme.secondary, CircleShape)
            .size(46.dp)
    ) {
        Icon(
            painter = painter,
            contentDescription = "Icon",
            tint = Color.Unspecified,
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp)
        )
    }
}