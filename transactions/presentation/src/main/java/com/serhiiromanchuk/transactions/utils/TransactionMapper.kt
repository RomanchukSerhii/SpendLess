package com.serhiiromanchuk.transactions.utils

import com.serhiiromanchuk.transactions.common_components.ExpenseCategory
import com.serhiiromanchuk.core.domain.entity.Expense
import com.serhiiromanchuk.core.domain.entity.RepeatType
import com.serhiiromanchuk.transactions.common_components.RepeatingCategory

fun Expense.toUi(): ExpenseCategory {
    return when (this) {
        Expense.HOME -> ExpenseCategory.HOME
        Expense.FOOD -> ExpenseCategory.FOOD
        Expense.ENTERTAINMENT -> ExpenseCategory.ENTERTAINMENT
        Expense.CLOTHING -> ExpenseCategory.CLOTHING
        Expense.HEALTH -> ExpenseCategory.HEALTH
        Expense.PERSONAL_CARE -> ExpenseCategory.PERSONAL_CARE
        Expense.TRANSPORTATION -> ExpenseCategory.TRANSPORTATION
        Expense.EDUCATION -> ExpenseCategory.EDUCATION
        Expense.SAVING -> ExpenseCategory.SAVING
        Expense.OTHER -> ExpenseCategory.OTHER
    }
}

fun ExpenseCategory.toDomain(): Expense {
    return when (this) {
        ExpenseCategory.HOME -> Expense.HOME
        ExpenseCategory.FOOD -> Expense.FOOD
        ExpenseCategory.ENTERTAINMENT -> Expense.ENTERTAINMENT
        ExpenseCategory.CLOTHING -> Expense.CLOTHING
        ExpenseCategory.HEALTH -> Expense.HEALTH
        ExpenseCategory.PERSONAL_CARE -> Expense.PERSONAL_CARE
        ExpenseCategory.TRANSPORTATION -> Expense.TRANSPORTATION
        ExpenseCategory.EDUCATION -> Expense.EDUCATION
        ExpenseCategory.SAVING -> Expense.SAVING
        ExpenseCategory.OTHER -> Expense.OTHER
    }
}

fun RepeatType.toUi(): RepeatingCategory {
    return when (this) {
        RepeatType.NOT_REPEAT -> RepeatingCategory.NOT_REPEAT
        RepeatType.DAILY -> RepeatingCategory.DAILY
        RepeatType.WEEKLY -> RepeatingCategory.WEEKLY
        RepeatType.MONTHLY -> RepeatingCategory.MONTHLY
        RepeatType.YEARLY -> RepeatingCategory.YEARLY
    }
}

fun RepeatingCategory.toDomain(): RepeatType{
    return when (this) {
        RepeatingCategory.NOT_REPEAT -> RepeatType.NOT_REPEAT
        RepeatingCategory.DAILY -> RepeatType.DAILY
        RepeatingCategory.WEEKLY -> RepeatType.WEEKLY
        RepeatingCategory.MONTHLY -> RepeatType.MONTHLY
        RepeatingCategory.YEARLY -> RepeatType.YEARLY
    }
}