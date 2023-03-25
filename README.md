<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
<div align="center">
<h2 align="center">Yandex Praktikum (QA Java) Diplom part 2 (API)</h2>
  <p align="center">
    Вторая часть дипломного проекта Яндекс Практикум по специальности "Автоматизатор тестирования на Java".
 <br /> Тестирование API. 
    <br />
    <a href="https://github.com/NickoT88/Diplom_2"><strong>Explore the docs</strong></a>
    <br />
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->

## About The Project

**Задание 2: API:** Тебе нужно протестировать ручки API для <a href="https://stellarburgers.nomoreparties.site">
stellarburgers</a>.
Пригодится <a href="https://code.s3.yandex.net/qa-automation-engineer/java/cheatsheets/paid-track/diplom/api-documentation.pdf">
api-documentation</a>.
В ней описаны все ручки сервиса. Тестировать нужно только те, которые указаны в задании. Всё остальное — просто для
контекста.

**Создание пользователя:**

- создать уникального пользователя;
- создать пользователя, который уже зарегистрирован;
- создать пользователя и не заполнить одно из обязательных полей.

**Логин пользователя:**

- логин под существующим пользователем,
- логин с неверным логином и паролем.

**Изменение данных пользователя:**

- с авторизацией,
- без авторизации,
  Для обеих ситуаций нужно проверить, что любое поле можно изменить. Для неавторизованного пользователя — ещё и то, что
  система вернёт ошибку.

**Создание заказа:**

- с авторизацией,
- без авторизации,
- с ингредиентами,
- без ингредиентов,
- с неверным хешем ингредиентов.

**Получение заказов конкретного пользователя:**

- авторизованный пользователь,
- неавторизованный пользователь.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

* <a href="https://www.java.com/ru/">Java 11</a>
* <a href="https://junit.org/junit4/">Junit 4</a>
* <a href="https://rest-assured.io/">REST Assured</a>
* <a href="https://github.com/allure-framework/">Allure Framework</a>
* <a href="https://mvnrepository.com/artifact/com.google.code.gson/gson">Gson</a>

<p align="right">(<a href="#readme-top">back to top</a>)</p>
<!-- CONTACT -->

## Contact

Николай Токарев - [@tokarevnickolay](https://t.me/tokarevnickolay) - Tibibo88@yandex.ru

Project Link: [https://github.com/NickoT88/Diplom_2](https://github.com/NickoT88/Diplom_2)

<p align="right">(<a href="#readme-top">back to top</a>)</p>
