# MyKotlinSkills

Коллекция небольших **Android-проектов на Kotlin**, в каждом из которых я разбираю отдельный инструмент или концепцию Android-разработки. Каждый модуль — самостоятельное Gradle-приложение с собственным `README`, где кратко изложена теория и практика по теме.

## Модули

| Модуль | Тема |
|---|---|
| **NavCompose3** | Навигация в Jetpack Compose (Navigation Compose) |
| **DaggerHilt** | Внедрение зависимостей через Hilt (Dagger) |
| **Koin** | Внедрение зависимостей через Koin |
| **WorkManager** | Отложенные и фоновые задачи через WorkManager |
| **ForegroundService** | Foreground-сервис с постоянным уведомлением |
| **AlarmManagerAndBroadcastReceiver** | Планирование задач через AlarmManager + BroadcastReceiver |
| **IntentsAndIntentFilters** | Явные и неявные Intent, intent-фильтры |
| **Snackbars** | Snackbar в Jetpack Compose (Material 3) |
| **SplashScreen** | Экран-заставка через Splash Screen API |

## Стек
- **Kotlin**
- **Jetpack Compose** (Material 3)
- **Gradle (Kotlin DSL)** с version catalog (`libs.versions.toml`)
- DI: **Hilt**, **Koin**
- Фоновая работа: **WorkManager**, **Foreground Service**, **AlarmManager**

## Как запустить
Каждый модуль открывается как отдельный проект в **Android Studio**:
```
File → Open → MyKotlinSkills/<Модуль>
```
Затем **Run** на эмуляторе или устройстве (min SDK см. в `app/build.gradle.kts`).

## Зачем это
Репозиторий — мой практический конспект по Android: вместо теории «в стол» каждая тема доведена до рабочего примера. Наработки отсюда переиспользую в собственных приложениях.
