package com.serhiiromanchuk.auth.presentation.screens.pin_prompt.components

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.auth.presentation.R
import com.serhiiromanchuk.core.presentation.designsystem.LogoutIcon
import com.serhiiromanchuk.core.presentation.designsystem.components.IconButtonDemo
import com.serhiiromanchuk.core.presentation.designsystem.theme.AppColors

@Composable
internal fun LogOutButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.padding(top = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = AppColors.ErrorOpacity08,
            contentColor = MaterialTheme.colorScheme.error
        )
    ) {
        Icon(
            imageVector = LogoutIcon,
            contentDescription = stringResource(R.string.log_out)
        )
    }
}
