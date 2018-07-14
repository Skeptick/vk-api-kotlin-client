VK API Kotlin Client
======================
Библиотека для работы с API vk.com на языке Kotlin
#### [Версия VK API](https://vk.com/dev/versions): 5.80
______________________
#### Использует:
  - [kotlinx.coroutines]
  - [kotlinx.serialization]



#### Статус покрытия методов API:
| Раздел          | Кол-во методов |                          |
| --------------- | :------------: | -                        |
| [Account]       |    18 из 19    | :heavy_check_mark:       |
| [AppWidgets]    |     0 из 8     | :heavy_multiplication_x: |
| [Apps]          |     0 из 7     | :heavy_multiplication_x: |
| [Auth]          |     0 из 3     | :heavy_multiplication_x: |
| [Board]         |     0 из 13    | :heavy_multiplication_x: |
| [Database]      |     0 из 11    | :heavy_multiplication_x: |
| [Docs]          |    11 из 11    | :heavy_check_mark:       |
| [Fave]          |    12 из 12    | :heavy_check_mark:       |
| [Friends]       |    18 из 18    | :heavy_check_mark:       |
| [Gifts]         |     1 из 1     | :heavy_check_mark:       |
| [Groups]        |    41 из 41    | :heavy_check_mark:       |
| [Likes]         |     0 из 4     | :heavy_multiplication_x: |
| [Market]        |     0 из 24    | :heavy_multiplication_x: |
| [Messages]      |    37 из 37    | :heavy_check_mark:       |
| [Newsfeed]      |     0 из 15    | :heavy_multiplication_x: |
| [Notes]         |     0 из 10    | :heavy_multiplication_x: |
| [Notifications] |     0 из 2     | :heavy_multiplication_x: |
| [Pages]         |     0 из 8     | :heavy_multiplication_x: |
| [Photos]        |     0 из 46    | :heavy_multiplication_x: |
| [Places]        |     0 из 6     | :heavy_multiplication_x: |
| [Polls]         |     0 из 6     | :heavy_multiplication_x: |
| [Search]        |     0 из 1     | :heavy_multiplication_x: |
| [Stats]         |     0 из 3     | :heavy_multiplication_x: |
| [Status]        |     0 из 2     | :heavy_multiplication_x: |
| [Storage]       |     0 из 3     | :heavy_multiplication_x: |
| [Stories]       |     0 из 13    | :heavy_multiplication_x: |
| [Streaming]     |     0 из 4     | :heavy_multiplication_x: |
| [Users]         |     0 из 7     | :heavy_multiplication_x: |
| [Utils]         |     0 из 7     | :heavy_multiplication_x: |
| [Video]         |     0 из 27    | :heavy_multiplication_x: |
| [Wall]          |     0 из 22    | :heavy_multiplication_x: |
| [Widgets]       |     0 из 2     | :heavy_multiplication_x: |

#### Использование
На данный момент библиотека не опубликована ни в каком репозитории java-библиотек, если рискнёте затащить к себе в проект, просто клонируйте git-репозиторий :)

#### HTTP-клиенты
На выбор имеется два HTTP-клиента:
 - [Async Http Client] (Java 1.8+)
 - [Fuel] (Java 1.7, подходит для Android)

Можете также реализовать `TransoprtClient` под свою любимую библиотеку.

Примеры использования
--------------------
#### Авторизация и инициализация клиента
```kotlin
val transportClient = NettyTransportClient()

val authData = OAuth.User.CodeFlow(
    clientId = 123456,
    clientSecret = "######",
    code = "######"
)

val authResult = transportClient.authorize(authData).await()

val accessToken = when (authResult) {
    is Result.Success -> authResult.value.accessToken
    is Result.Failure -> return
}

val apiClient = VkApiClient(accessToken, transportClient)

val api = VkApiUser(apiClient) // for user token
val api = VkApiCommunity(apiClient) // for community token
```
#### Использование методов
```kotlin
// Sending a message
val sendResult = api.messages.send(
    peerId = 1000000,
    message = "Hello!"
).awaitResult()

// Getting a list of friends
val friendsResult = api.friends.get(
    order = FriendsOrder.NAME,
    count = 10
).awaitResult()
```
#### Обработка результата
В качестве результата любого запроса возвращается ~~монада~~ объект Result. Подробнее о нём можно почитать в [ReadMe самой библиотеки](https://github.com/kittinunf/Result).
#### Загрузка файлов (на примере документа)
```kotlin
val file = File("file.txt")

// Note that get() can throw an exception!
val uploadServer = api.docs.getUploadServer()
    .awaitResult().get()

val uploadResponse = api.upload.document(
    uploadUrl = uploadServer.uploadUrl,
    file = file.name to file
).awaitResult().get()

val documents = api.docs.save(
    file = uploadResponse.file,
    title = "Some name.txt"
).awaitResult().get()
```
#### Использование метода `execute`
```kotlin
@Serializable
data class ExampleResponse(
    @SerialName("id") val id: Int,
    @SerialName("value") val value: String
)

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

val executeResult = api.execute(
    code = exampleCode,
    serializer = ExampleResponse.serializer().list
).awaitResult()
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

    Copyright 2018 Danil Yudov
    
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

[Async Http Client]: <https://github.com/AsyncHttpClient/async-http-client>
[Fuel]: <https://github.com/kittinunf/Fuel>

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
[Likes]: <https://vk.com/dev/likes>
[Market]: <https://vk.com/dev/market>
[Messages]: <https://vk.com/dev/messages>
[Newsfeed]: <https://vk.com/dev/newsfeed>
[Notes]: <https://vk.com/dev/notes>
[Notifications]: <https://vk.com/dev/notifications>
[Pages]: <https://vk.com/dev/pages>
[Photos]: <https://vk.com/dev/photos>
[Places]: <https://vk.com/dev/places>
[Polls]: <https://vk.com/dev/polls>
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