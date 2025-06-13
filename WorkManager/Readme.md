### Алгоритм добавления WorkManager в ваш проект
1. Для начала вам нужно подключить необходимые, для работы WorkManager, библиотеки в build.gradle(Module:app) -
   ```implementation("androidx.work:work-runtime-ktx:2.10.1")```
   ```implementation("androidx.compose.runtime:runtime-livedata:1.8.2")```
2. Далее - нужно создать класс самого воркера, наследуемого от ```CoroutineWorker``` и переопределить функцию ```doWork()``` На этом примере - скачивание по uri какой то картинки.
