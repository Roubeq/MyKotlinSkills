package com.example.koin

import org.koin.core.qualifier.named
import org.koin.dsl.module

val scopeModule = module {
    scope<MainActivity> {
        scoped(qualifier =
        named("Hello")
        ){
            "hello from MainActivity"
        }
        scoped(qualifier =
        named("Bye")
        ){
            "Bye from MainActivity"
        }
    }
}