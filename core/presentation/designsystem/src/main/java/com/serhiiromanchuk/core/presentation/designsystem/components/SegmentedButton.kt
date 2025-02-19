package com.serhiiromanchuk.core.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.theme.AppColors
import com.serhiiromanchuk.core.presentation.designsystem.theme.SpendLessTheme

@Composable
fun SegmentedButton(
    options: List<String>,
    selectedIndex: Int,
    onOptionSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
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
                modifier = modifier
                    .weight(1f),

                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = option)
            }
        }
    }
}

@Composable
fun SegmentedButton(
    segmentOptions: List<SegmentOption>,
    selectedOption: SegmentOption,
    onOptionClick: (SegmentOption) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(AppColors.PrimaryContainerOpacity08)
            .padding(4.dp)
    ) {
        segmentOptions.forEach { option ->
            Button(
                onClick = { onOptionClick(option) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (option == selectedOption) AppColors.SurfContainerLowest else Color.Transparent,
                    contentColor = if (option == selectedOption) MaterialTheme.colorScheme.primary else AppColors.OnPrimaryFixed
                ),
                modifier = modifier.weight(1f),
                shape = RoundedCornerShape(12.dp)
            ) {
                option.label()
            }
        }
    }
}

interface SegmentOption {
    val label: @Composable () -> Unit
}

@Composable
fun OptionText(
    text: String,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leadingIcon != null) {
            leadingIcon()
            Spacer(modifier = Modifier.width(6.dp))
        }
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewSegmentedButton() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    SpendLessTheme {
        SegmentedButton(
            options = listOf("Label", "Label", "Label"),
            selectedIndex = selectedIndex,
            onOptionSelected = { selectedIndex = it }
        )
    }

}
