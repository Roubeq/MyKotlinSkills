# Внедрение зависимостей с Dagger Hilt в Android

## 📌 Внедрение зависимостей (Dependency Injection) в Android

### 🤔 Что такое DI?

**Dependency Injection (DI)** — паттерн, при котором объекты получают свои зависимости извне вместо самостоятельного создания.

## Что такое Dagger Hilt?

**Dagger Hilt** — это упрощенная обертка над **Dagger 2**, разработанная Google для:
- Упрощения настройки DI в Android-приложениях
- Сокращения шаблонного кода
- Стандартизации подхода к внедрению зависимостей

Основные возможности:
- Автоматическая генерация компонентов DI
- Встроенная интеграция с Android-компонентами
- Упрощенное управление областями видимости (scopes)

## Как подключить в проект

### 1. Добавьте зависимости в `build.gradle` (Module)

```gradle
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'kotlin-kapt'
    id 'dagger.hilt.android.plugin'
}

dependencies {
    implementation "com.google.dagger:hilt-android:2.48"
    kapt "com.google.dagger:hilt-compiler:2.48"
    
    // Для поддержки ViewModel
    implementation "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    kapt "androidx.hilt:hilt-compiler:1.0.0"
}
