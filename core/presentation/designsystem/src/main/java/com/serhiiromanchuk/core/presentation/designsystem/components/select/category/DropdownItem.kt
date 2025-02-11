package com.serhiiromanchuk.core.presentation.designsystem.components.select.category

import androidx.compose.runtime.Composable
/*
* Если будет нужен новый список для какой-то категории - просто создаешь enum класс по примеру
* других классов в этой папке и наследуешся от этого интерфейса
*/
interface DropdownItem {
    val title: String
    @Composable
    fun Icon() {}
}