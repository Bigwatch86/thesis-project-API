<h1>Проект по тестированию API сайта restful-booker.</h1>

<a target="_blank" href="http://restful-booker.herokuapp.com/">Ссылка на сайт</a>

<h2>:bookmark_tabs: Содержание</h2>

:eight_spoked_asterisk:	 Реализованные проверки

:eight_spoked_asterisk:	 Стек технологий

:eight_spoked_asterisk:	 Запуск тестов в Jenkins

:eight_spoked_asterisk:	 Отчет о результатах прохождения тестов в Allure TestOps

:eight_spoked_asterisk:	 Уведомления в Telegram с использованием бота

<h2>:eyes:	 Реализованные проверки</h2>

:black_medium_square: Получение авторизационного токена

:black_medium_square: Получение списка всех бронирований

:black_medium_square: Создание, изменение и удаление бронирования

<h2>:books:	 Стек технологий</h2>


<p>
<img title="IntelliJ IDEA" src="images/logo/Intelij_IDEA.svg" height="48" width="48">
<img title="GitHub" src="images/logo/GitHub.svg" height="48" width="48">
<img title="Java" src="images/logo/Java.svg" height="48" width="48">
<img title="Gradle" src="images/logo/Gradle.svg" height="48" width="48">
<img title="JUnit5" src="images/logo/JUnit5.svg" height="48" width="48">
<img title="Rest assured" src="images/logo/Rest-Assured.png" height="48" width="48">
<img title="Telegram" src="images/logo/Telegram.svg" height="48" width="48">
<img title="Jenkins" src="images/logo/Jenkins.svg" height="48" width="48"> 
<img title="Allure TestOps" src="images/logo/Allure_TestOps.png" height="48" width="48">
<img title="Jira" src="images/logo/Jira.png" height="48" width="48"> 
</p>


<h2>:arrow_forward:	 Запуск тестов в Jenkins</h2>
<p>Для запуска тестов используется инструмент CI/CD под названием <b>Jenkins</b></p>

<a target="_blank" href="https://jenkins.autotests.cloud/job/010-Bigwatch-thesis-project-API/">Ссылка на задачу для запуска тестов</a>

<p><i>Параметры сборки выбираются при запуске задачи:<b></i>
</br>
</br>

<p><code>COMMENT</code> – комментарий для сообщения в Телеграм.</p>
<p><code>ENVIRONMENT</code> - среда, на которой прогоняются автотесты.</p>

<h3>:large_blue_diamond:	 Процесс запуска происходит следующим образом:</h3>

<p><i>На странице сборки выбираем <b>"Собрать с параметрами"</b>: </i>
</br>
</br>
<img title="Запуск сборки с параметрами" src="images/screenshots/Jenkins_1.jpg">
</p>

<p><i>Выбираем необходимые параметры запуска и нажимаем <b>"Собрать"</b>: </i>
</br>
</br>
<img title="Выбор параметров" src="images/screenshots/Jenkins_2.jpg">
</p>

<p><i>Результаты работы сборки можно посмотреть, нажав на значок "Allure TestOps"</b>: </i>
</br>
</br>
<img title="Просмотр результатов" src="images/screenshots/Jenkins_3.jpg">
</p>

<h2>:bar_chart:	 Отчет о результатах прохождения тестов в Allure TestOps</h2>

<h4>:small_orange_diamond:	 Страница со списком всех пройденных проверок:</h4>

<p align="center">
<img title="Test results" src="images/screenshots/Allure_TestOps_1.jpg">
</p>

<h4>:small_orange_diamond:	 Страница со всеми запусками:</h4>

<p align="center">
<img title="Allure Launches" src="images/screenshots/Allure_TestOps_2.jpg">
</p>

<h4>:small_orange_diamond:	 Дефект, описанный в результате запуска тестов:</h4>

<p align="center">
<img title="Allure Defects" src="images/screenshots/Allure_TestOps_3.jpg">
</p>

<h4>:small_orange_diamond:	 Страница с общими результатами тестирования в графическом виде:</h4>

<p align="center">
<img title="Allure Dashboards" src="images/screenshots/Allure_TestOps_4.jpg">
</p>


<h2>:mailbox_with_mail:	 Уведомления в Telegram с использованием бота</h2>
<p><i>После окончания сборки в Телеграм отправляется уведомление со ссылкой на отчёт. </i>
</br>
</br>
<img title="Telegram notification" src="images/screenshots/Telegram_message.jpg">
</p>
