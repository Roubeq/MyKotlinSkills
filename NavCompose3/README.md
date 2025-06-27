# NavigationCompose3 🚀

Этот релиз основан на **Jetpack Navigation 3 (Nav3)** — новой, Compose-native библиотеке от Google, анонсированной 20 мая 2025 на Android Developers Blog. В отличие от предыдущих версий, Nav3 обеспечивает:

- **Прямое управление BackStack** через `SnapshotStateList<T>`, что полностью интегрируется с Compose‑стейтом.
- **Адаптивные макеты** (Scenes), которые позволяют одновременно отображать несколько экранов, полезно для планшетов и складных устройств.
- **Модульность и расширяемость**: можно легко подключать кастомные анимации, layout‑стратегии и feature‑модули.
- **Встроенные transition‑анимации**, предиктивный back и API для кастомных переходов.
- **Сохранение состояния** NavKey через `Serializable` и `rememberNavBackStack` — навигацию переживет поворот экрана и даже смерть процесса.

## 📦 Установка

Добавьте зависимости:

```kotlin
val nav3Version = "1.0.0-alpha01"
implementation("androidx.navigation3:navigation3-runtime:$nav3Version")
implementation("androidx.navigation3:navigation3-ui:$nav3Version")
```
### Примечания
- Вместо ```NavHost``` теперь ```NavDisplay```, и используется он как компонуемая функция.
- Библиотека пока что в альфе, и не всё что есть сейчас работает корректно.
- Можно прямо из ```viewModel``` проводить какие то действия над вашим backstack экранов(можно сказать список).
