package com.serhiiromanchuk.core.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.theme.AppColors

@Composable
fun SegmentedButton(
    options: List<String>,
    selectedIndex: Int,
    onOptionSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(AppColors.PrimaryContainerOpacity08)
            .padding(4.dp)
    ) {
        options.forEachIndexed { index, option ->
            Button(
                onClick = { onOptionSelected(index) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (index == selectedIndex) AppColors.SurfContainerLowest else Color.Transparent,
                    contentColor = if (index == selectedIndex) MaterialTheme.colorScheme.primary else AppColors.OnPrimaryFixed
                ),
                modifier = Modifier
                    .weight(1f),

                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = option)
            }
        }
    }
}

@Preview
@Composable
fun PreviewSegmentedButton() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    SegmentedButton(
        options = listOf("Label", "Label", "Label"),
        selectedIndex = selectedIndex,
        onOptionSelected = { selectedIndex = it }
    )
}
