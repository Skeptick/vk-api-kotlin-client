VK API Kotlin Client
======================
[![Kotlin 1.3.70](https://img.shields.io/badge/Kotlin-1.3.70-blue.svg?style=flat)](http://kotlinlang.org)
[![Bintray Download](https://img.shields.io/bintray/v/skeptick/maven/vk-api-kotlin-client?label=Bintray)](https://bintray.com/skeptick/maven/vk-api-kotlin-client/_latestVersion)
[![VK API](https://img.shields.io/badge/VK%20API-5.103-blue.svg?style=flat&logo=vk&logoColor=white)](https://vk.com/dev/versions)



#### Использует:
  - [kotlinx.coroutines]
  - [kotlinx.serialization]
  - [ktor-client]



#### Статус покрытия методов API:
| Раздел          | Кол-во методов |                          |
| --------------- | :------------: | -                        |
| [Account]       |    18 из 19    | :heavy_check_mark:       |
| [AppWidgets]    |     0 из 8     | :heavy_multiplication_x: |
| [Apps]          |     0 из 8     | :heavy_multiplication_x: |
| [Auth]          |     0 из 2     | :heavy_multiplication_x: |
| [Board]         |     0 из 13    | :heavy_multiplication_x: |
| [Database]      |     0 из 10    | :heavy_multiplication_x: |
| [Docs]          |    11 из 11    | :heavy_check_mark:       |
| [Fave]          |    23 из 23    | :heavy_check_mark:       |
| [Friends]       |    18 из 18    | :heavy_check_mark:       |
| [Gifts]         |     1 из 1     | :heavy_check_mark:       |
| [Groups]        |    46 из 46    | :heavy_check_mark:       |
| [LeadForms]     |     0 из 7     | :heavy_multiplication_x: |
| [Likes]         |     4 из 4     | :heavy_check_mark:       |
| [Market]        |     0 из 24    | :heavy_multiplication_x: |
| [Messages]      |    39 из 39    | :heavy_check_mark:       |
| [Newsfeed]      |     0 из 16    | :heavy_multiplication_x: |
| [Notes]         |     0 из 10    | :heavy_multiplication_x: |
| [Notifications] |     0 из 3     | :heavy_multiplication_x: |
| [Pages]         |     0 из 8     | :heavy_multiplication_x: |
| [Photos]        |    46 из 46    | :heavy_check_mark:       |
| [Polls]         |     0 из 9     | :heavy_multiplication_x: |
| [PrettyCards]   |     0 из 6     | :heavy_multiplication_x: |
| [Search]        |     0 из 1     | :heavy_multiplication_x: |
| [Stats]         |     0 из 3     | :heavy_multiplication_x: |
| [Status]        |     0 из 2     | :heavy_multiplication_x: |
| [Storage]       |     0 из 3     | :heavy_multiplication_x: |
| [Stories]       |     0 из 13    | :heavy_multiplication_x: |
| [Streaming]     |     0 из 5     | :heavy_multiplication_x: |
| [Users]         |     7 из 6     | :heavy_check_mark:       |
| [Utils]         |     7 из 7     | :heavy_check_mark:       |
| [Video]         |    24 из 24    | :heavy_check_mark:       |
| [Wall]          |    23 из 23    | :heavy_check_mark:       |
| [Widgets]       |     0 из 2     | :heavy_multiplication_x: |

#### Использование
Подключите репозиторий:
```groovy
repositories {
    maven { url "https://dl.bintray.com/skeptick/maven/" }
}
```
Если включена Gradle Metadata (в Gradle 6+ включена по умолчанию):
```groovy
dependencies {
    implementation "tk.skeptick:vk-api-kotlin-client:0.2.2"
}
```
В ином случае явно укажите необходимую платформу:
```groovy
dependencies {
    implementation "tk.skeptick:vk-api-kotlin-client-jvm:0.2.2"
    implementation "tk.skeptick:vk-api-kotlin-client-js:0.2.2"
    implementation "tk.skeptick:vk-api-kotlin-client-linuxx64:0.2.2"
    implementation "tk.skeptick:vk-api-kotlin-client-mingwx64:0.2.2"
    implementation "tk.skeptick:vk-api-kotlin-client-macosx64:0.2.2"
    implementation "tk.skeptick:vk-api-kotlin-client-iosarm32:0.2.2"
    implementation "tk.skeptick:vk-api-kotlin-client-iosarm64:0.2.2"
    implementation "tk.skeptick:vk-api-kotlin-client-iosx64:0.2.2"
    implementation "tk.skeptick:vk-api-kotlin-client-tvosarm64:0.2.2"
    implementation "tk.skeptick:vk-api-kotlin-client-tvosx64:0.2.2"
}
```

#### Зачем и чтобы что
Библиотека пишется руками (:see_no_evil:), так как документация у VK не редко серьезно хромает. Преследуется цель сделать использование библиотеки максимально комфортным в плоскости одной из главных фич языка - nullable-типов, а также задействовать другие его особенности: корутины, мультиплатформенный HTTP-клиент (ktor) и мультиплатформенную сериализацию, опциональные именованные параметры функций заместо билдеров и т.д.

В продакшне используйте на свой страх и риск. Лично я использую (в основном для ботов), и имеющиеся методы польностью покрывают мои нужды. Если вам нужен какой-то иной раздел API, не реализованный в библиотеке, можете реквестировать.

Библиотека доступна для большинства платформ (весь список расположен выше), в том числе для Android и iOS.

Примеры использования
--------------------
#### Подготовка
Первым делом подключите подходящий вам [HTTP-клиент](https://github.com/ktorio/ktor/tree/master/ktor-client), например CIO:
```
implementation "io.ktor:ktor-client-cio:1.3.2"
```
Для Android используейте `ktor-client-okhttp` или `ktor-client-android`, для server-side рекомендую `ktor-client-apache`. Подробнее см. в [документации](https://ktor.io/clients/http-client/engines.html) и в [репозиториях](https://github.com/ktorio/ktor/tree/master/ktor-client) ktor.

#### Инициализация клиента
```kotlin
val apiClient = VkApiClient("your_token", HttpClient(CIO))
val api = VkApiUser(apiClient) // для токена пользователя
val api = VkApiCommunity(apiClient) // для токена сообщества
```
#### Использование методов
```kotlin
// отправка сообщения
val sendResult = api.messages.send(peerId = 1000000, message = "Hello!").execute()

// получение первых десяти друзей в алфавитном порядке
val friendsResult = api.friends.get(order = FriendsOrder.NAME, count = 10).execute()
```
#### Обработка результата
В качестве результата любого запроса возвращается ~~монада~~ объект [VkResult](vk-api-kotlin-client/src/main/kotlin/tk/skeptick/vk/apiclient/VkResult.kt). Подробнее ознакомиться с концепцией и примерами использования можно в [ReadMe библиотеки](https://github.com/kittinunf/Result), копипастом с которой он является (увы, сама библиотека Kotlin/Multiplatform не поддерживает).
#### Загрузка файлов (на примере документа)
```kotlin
val file = File("file.txt")
val fileContent = FileContent(file.name, file.readBytes())
val documents = api.docs.getUploadServer().execute()
    .flatMap { api.upload.document(it.uploadUrl, fileContent).execute() }
    .flatMap { api.docs.save(it.file, "Title.txt").execute() }
```
#### Использование метода `execute`
Опишите модель ответа (или используйте имеющиеся):
```kotlin
@Serializable
data class ExampleResponse(
    @SerialName("id") val id: Int,
    @SerialName("value") val value: String
)
```
Код для выполнения на сервере:
```kotlin
val exampleCode = """
    var response = [];
    var i = 0;

    while (i < 10) {
        response.push({
            "id": i,
            "value": "something"
        });

        i = i + 1;
    }

    return response;
""".trimIndent()
```
Вызовите метод:
```kotlin
val executeResult = api.execute(
    code = exampleCode,
    serializer = ExampleResponse.serializer().list
).execute()
```

Дополнительно
--------------------
#### Работа с битовыми масками
```kotlin
val scope = AccessPermissionsUser().apply { 
    friends = true
    photos = true
    messages = true
}.mask
```
#### DSL-builder клавиатуры для сообщений сообществ
```kotlin
@Serializable
data class ExamplePayload(@SerialName("key") val key: String)

keyboard { 
    buttonsRow { 
        positiveButton("OK")
    }
    buttonsRow { 
        defaultButton("Button with payload") {
            payload = MessagePayload.from(ExamplePayload("value"))
        }
        defaultButton("Simple button")
    }
    buttonsRow { 
        negativeButton("Cancel")
    }
}
```
#### Конвертация идентификаторов
```kotlin
1.chatIdToPeerId // == 2000000001
1.communityIdToPeerId // == -1

2000000001.peerIdToChatId // == 1
-1.peerIdToCommunityId // == 1

2000000001.isChatPeerId // == true
-1.isCommunityPeerId // == true
1.isUserPeerId // == true
```

License
=======

    Copyright 2020 Danil Yudov
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
       
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[kotlinx.coroutines]: <https://github.com/Kotlin/kotlinx.coroutines>
[kotlinx.serialization]: <https://github.com/Kotlin/kotlinx.serialization>
[ktor-client]: <https://github.com/ktorio/ktor/tree/master/ktor-client>

[Account]: <https://vk.com/dev/account>
[AppWidgets]: <https://vk.com/dev/appWidgets>
[Apps]: <https://vk.com/dev/apps>
[Auth]: <https://vk.com/dev/auth>
[Board]: <https://vk.com/dev/board>
[Database]: <https://vk.com/dev/database>
[Docs]: <https://vk.com/dev/docs>
[Fave]: <https://vk.com/dev/fave>
[Friends]: <https://vk.com/dev/friends>
[Gifts]: <https://vk.com/dev/gifts>
[Groups]: <https://vk.com/dev/groups>
[LeadForms]: <https://vk.com/dev/leadForms>
[Likes]: <https://vk.com/dev/likes>
[Market]: <https://vk.com/dev/market>
[Messages]: <https://vk.com/dev/messages>
[Newsfeed]: <https://vk.com/dev/newsfeed>
[Notes]: <https://vk.com/dev/notes>
[Notifications]: <https://vk.com/dev/notifications>
[Pages]: <https://vk.com/dev/pages>
[Photos]: <https://vk.com/dev/photos>
[Polls]: <https://vk.com/dev/polls>
[PrettyCards]: <https://vk.com/dev/prettyCards>
[Search]: <https://vk.com/dev/search>
[Stats]: <https://vk.com/dev/stats>
[Status]: <https://vk.com/dev/status>
[Storage]: <https://vk.com/dev/storage>
[Stories]: <https://vk.com/dev/stories>
[Streaming]: <https://vk.com/dev/streaming>
[Users]: <https://vk.com/dev/users>
[Utils]: <https://vk.com/dev/utils>
[Video]: <https://vk.com/dev/video>
[Wall]: <https://vk.com/dev/wall>
[Widgets]: <https://vk.com/dev/widgets>
