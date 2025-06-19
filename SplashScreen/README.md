# Splash Screen для Android приложения

## 📱 Что такое Splash Screen?

Splash Screen (экран-заставка) - это графический элемент, который отображается:
- При запуске приложения
- Во время инициализации ресурсов
- Для брендинга приложения
- Во время загрузки критически важных данных

Стандартный Splash Screen появился в Android 12 (API 31) и стал обязательным для всех приложений, начиная с Android 13.

## 🛠️ Как подключить Splash Screen

### 1. Добавьте зависимость
В `build.gradle` модуля app:
```
    gradle
dependencies {
    implementation 'androidx.core:core-splashscreen:1.0.1'
}
```
### 2. Создайте тему для Splash Screen, которая наследуется от Theme.SplashScreen
Добавьте в нее атрибуты: 
```
    <item name="windowSplashScreenBackground"></item>
    <item name="windowSplashScreenAnimatedIcon"></item>
    <item name="postSplashScreenTheme"></item>
```
### 3. В манифесте замените тему на эту
### 4. Вызовите в начале метода ```onCreate()``` или прямо перед ним - ```installSplashScreen()```
