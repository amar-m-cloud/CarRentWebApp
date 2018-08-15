# Car rent Java EE web app

## Содержание

1. Подготовка необходимых инструментов
 + [Установка MySQL](#mysql)
 + [Установка среды Java](#jdk)
 + [Установка контейнера Tomcat](#tomcat)
 + [Подготовка директории развертывания](#tom_dir)
 + [Установка системы контроля версий Git](#git)
 + [Установка системы maven](#maven)
2. Установка, сборка, подготовка к запуску
 + [Скачивание приложения](#clone)
 + [Развертывание дампа базы данных](#import)
 + [Создание пользователя базы данных](#setup_db)
 + [Настройка проекта](#set_param)
 + [Сборка приложения](#compile)
3. Развертывание приложения
 + [Установка в контейнер Tomcat](#tomcat_deploy)
 + [Запуск Tomcat](#start_tomcat)
 + [Открытие приложения в браузере](#browse)



Учебный проект веб-приложение Аренда машин.

##	Подготовка необходимых инструментов
### <a name="mysql"></a>	Установка MySQL

Для корректной работы приложения требуется MySQL версии не ниже 5.7

[Дистрибутив СУБД MySQL](https://dev.mysql.com/downloads/mysql/)

[Инструкция по установке СУБД MySQL](https://dev.mysql.com/doc/refman/8.0/en/installing.html)

### <a name="jdk"></a>	Установка среды Java

Для корректной работы приложения требуется установить Java Development Kit версии 8 (1.8)

[Страница для скачивания JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

[Инструкция по установке JDK](https://docs.oracle.com/javase/8/docs/technotes/guides/install/index.html)

### <a name="tomcat"></a>	Установка контейнера Tomcat

Приложение протестировано на работоспособность с контейнером Tomcat версии 8.5
Скачайте и установите Tomcat 8.5

[Страница для скачивания Tomcat 8.5](https://tomcat.apache.org/download-70.cgi)

[Инструкция по установке Tomcat](https://tomcat.apache.org/tomcat-8.5-doc/setup.html)

### <a name="tom_dir"></a>    Подготовка директории развертывания

Удалите или переместите содержимое директории tomcat/webapps/ROOT.

### <a name="git"></a>	Установка системы контроля версий Git

Установка Git опциональна, и производится для удобства скачивания приложения из репозитория git-hub.

[Страница для скачивания Git](https://git-scm.com/downloads)

### <a name="maven"></a> Установка системы maven

Установка системы автоматической сборки и управления пакетами maven версии 3.3 или выше необходима для сборки, тестирования и размещения приложения.

[Страница для скачивания maven](https://maven.apache.org/download.cgi)

[Инструкция по установке maven](https://maven.apache.org/install.html)


## Установка, сборка, подготовка к запуску
### <a name="clone"></a>	Скачивание приложения

Для скачивания приложения из репозитория Git-hub введите в командной строке:
	
	git clone https://github.com/MaximChshelokov/CarRentWebApp.git

	
### <a name="import"></a>	Развертывание дампа базы данных

Для развертывания БД введите в командной строке:
	
	mysql -u root -p < путь к проекту/db_dump.sql
	
Затем введите пароль root пользователя MySQL. База данных (схема) будет создана автоматически.

Для развертывания тестовой БД (которая необходима для успешной сборки проекта) выполните в командной строке:
	
	mysql -u root -p < путь к проекту/test_db_dump.sql
	
Затем введите пароль root пользователя MySQL. База данных (схема) будет создана автоматически.

### <a name="setup_db"></a>	Создание пользователя базы данных

Приложение изначально настроено на работу с базой данных под учётной записью car_rent_app (и паролем Un3L41NoewVA).
Для создание необходимого пользователя:
Запустите терминал mysql, и выполните команду:
	
	CREATE USER 'car_rent_app'@'localhost' IDENTIFIED BY 'Un3L41NoewVA';
	
Если вы хотите использовать другие имя пользователя и пароль, необходимо внести их в [файл настроек базы данных](#set_param).

Чтобы предоставить доступ данному пользователю к базе данных (схеме) приложения, выполните команду:
    
    GRANT SELECT, INSERT, UPDATE, DELETE ON * . * TO 'car_rent_app'@'localhost';
    
### Установка часового пояса MySQL для пользователей Windows

При использовании СУБД MySQL под ОС Windows при подключении к БД может возникнуть ошибка, заключающаяся в невозможности автоматической установки часового пояса. Чтобы избежать данной ошибки запустите терминал mysql, и выполните команду:
    
    SET GLOBAL time_zone = 'ВАШ ЧАСОВОЙ ПОЯС';
    
Где вместо слов ВАШ ЧАСОВОЙ ПОЯС укажите свой часовой пояс в формате +7:00

### <a name="set_param"></a>	Настройка проекта

В случае, если вы хотите использовать произвольные имя и пароль пользователя базы данных, измените строки в файле src/main/resources/db_params.properties
	
	LOGIN=имя пользователя БД
	PASSWORD=пароль пользователя БД
	
Так же при необходимости, можно настроить другие параметры соединения с БД:
	
	URL=jdbc:mysql://localhost/имя базы?autoReconnect=true&useSSL=false&characterEncoding=utf-8
	POOL_SIZE=размер пула подключений к БД
	
### <a name="compile"></a>  Сборка приложения

Для сборки приложения введите в командной строке:
	
	mvn clean compile
	
## Развертывание приложения
### <a name="tomcat_deploy"></a> Установка в контейнер Tomcat

Скопируйте содержимое директории путь к проекту/target/car_rent-1.0-SNAPSHOT/ в директорию tomcat/webapps/ROOT
Примечание: директория target создается в пункте [Сборка приложения](#compile)

### <a name="start_tomcat"></a>  Запуск Tomcat

Для запуска контейнера Tomcat перейдите в директорию tomcat/bin и в командной строке выполните:
    
    startup
    
### <a name="browse"></a>   Открытие приложения в браузере

Чтобы открыть страницу приложения, в браузере в адресной строке введите localhost:8080 и нажмите "перейти".
Для того, чтобы войти под администратором, используйте логин admin@mail.com и пароль admin1.
Чтобы войти под пользователем, логин client@mail.com и пароль client.

