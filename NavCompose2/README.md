# NavCompose2

Проект-пример реализации навигации в Android-приложении с использованием **Jetpack Compose Navigation**.

## Особенности
*   Использование **Jetpack Compose** для построения UI.
*   Реализация **Bottom Navigation** (нижней навигации).
*   Разделение экранов с помощью `sealed class Screen`.
*   Управление графом навигации через `NavHost`.
*   Использование расширенного набора иконок `material-icons-extended`.

## Структура проекта
*   `MainActivity.kt` — точка входа, содержит `Scaffold` с `BottomBar`.
*   `NavGraph.kt` — описание маршрутов (`NavHost`) и экранов.
*   `Screen.kt` — описание маршрутов экранов.
*   `NavItem.kt` — описание элементов нижнего меню (заголовки, иконки, маршруты).

## Стек технологий
*   **Kotlin**
*   **Jetpack Compose**
*   **Compose Navigation**
*   **Material 3**

## Как запустить
1. Откройте проект в Android Studio.
2. Дождитесь завершения Gradle Sync.
3. Запустите приложение на эмуляторе или физическом устройстве (требуется API 24+).
