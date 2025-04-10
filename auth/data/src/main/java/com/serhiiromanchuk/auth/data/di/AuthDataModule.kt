package com.serhiiromanchuk.auth.data.di

import com.serhiiromanchuk.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authDataModule = module {
    singleOf(::UserDataValidator)
}