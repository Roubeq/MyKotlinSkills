# Алгоритм добавления ```ForegroundService```
  1. Создать ваш класс, который будет наследоваться от ```Service()```
  2. Переопределить функцию ```onBind()``` и ```onStartCommand```
  3. В этом случае повесить постоянное уведомление.
## ForegroundService 
  ForegroundService - это особый тип сервиса в Android, который работает на переднем плане (foreground), уведомляя пользователя о своей работе через постоянное уведомление в статусной строке.
### Основные особенности ForegroundService
  Видимость для пользователя: Показывает постоянное уведомление
### Приоритет: Менее вероятно, что система его убьет при нехватке ресурсов
### Ограничения: Начиная с Android 8.0 (API 26), есть ограничения на работу сервисов в фоне
### Когда использовать
  1. Воспроизведение музыки
  2. Загрузка или выгрузка файлов
  3. Отслеживание местоположения
  4. Длительные операции, о которых должен знать пользователь
