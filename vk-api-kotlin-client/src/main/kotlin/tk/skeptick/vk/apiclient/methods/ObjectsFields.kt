package tk.skeptick.vk.apiclient.methods

//--- Fields ---//

interface ObjectField {
    val value: String
}

enum class UserOptionalField(override val value: String) : ObjectField {
    ABOUT("about"), // о себе
    ACTIVITIES("activities"), // поле "деятельность"
    BIRTH_DATE("bdate"), // дата рождения, строка D.M.YYYY или D.M
    BLACKLISTED("blacklisted"), // находится ли юзер в ЧС
    BLACKLISTED_BY_ME("blacklisted_by_me"), // нахожусь ли я у него в ЧС
    BOOKS("books"), // поле "любимые книги"
    CAN_POST("can_post"), // могу ли я оставлять записи у него на стене
    CAN_SEE_ALL_POSTS("can_see_all_posts"), // могу ли я видеть чужие записи у него на стене
    CAN_SEE_AUDIO("can_see_audio"), // могу ли я видеть его аудио
    CAN_SEND_FRIEND_REQUEST("can_send_friend_request"), // будет ли отправлено уведомление о заявке в др.
    CAN_WRITE_PRIVATE_MESSAGE("can_write_private_message"), // могу ли писать личные сообщения
    CAREER("career"), // информация о карьере
    CITY("city"), // информация о текущем городе жительства
    COMMON_COUNT("common_count"), // количество общих друзей
    CONNECTIONS("connections"), // подключенные соц. сервисы (skype, facebook, etc.)
    CONTACTS("contacts"), // информация о телефонных номерах
    COUNTERS("counters"), // кол-во различных объектов (видео, аудио, etc.) у пользователя
    COUNTRY("country"), // информация о текущей стране жительства
    CROP_PHOTO("crop_photo"), // вырезанная фотография пользователя
    DOMAIN("domain"), // короткий адрес страницы (или "id123")
    EXPORTS("exports"), // сервисы, в которые настроен экспорт (twitter, instagram, etc)
    FIRST_NAME_NOM("first_name_nom"), // имя в именительном падеже
    FIRST_NAME_GEN("first_name_gen"), // имя в родительном падеже
    FIRST_NAME_DAT("first_name_dat"), // имя в дательном падеже
    FIRST_NAME_ACC("first_name_acc"), // имя в винительном падеже
    FIRST_NAME_INS("first_name_ins"), // имя в творительном падеже
    FIRST_NAME_ABL("first_name_abl"), // имя в предложном падеже
    FOLLOWERS_COUNT("followers_count"), // кол-во подписчиков
    FRIEND_STATUS("friend_status"), // статус дружбы (не друг, подписчик, подписка, друг)
    GAMES("games"), // поле "любимые игры"
    HAS_MOBILE("has_mobile"), // известен ли мобильный номер пользователя
    HAS_PHOTO("has_photo"), // установлена ли фотография профиля
    HOME_TOWN("home_town"), // название родного города
    INTERESTS("interests"), // поле "интересы"
    IS_FAVORITE("is_favorite"), // находится ли пользователь в закладках
    IS_FRIEND("is_friend"), // является ли пользователь другом
    IS_HIDDEN_FROM_FEED("is_hidden_from_feed"), // скрыты ли новости пользователя из моей ленты
    LAST_NAME_NOM("last_name_nom"), // фамилия в именительном падеже
    LAST_NAME_GEN("last_name_gen"), // фамилия в родительном падеже
    LAST_NAME_DAT("last_name_dat"), // фамилия в дательном падеже
    LAST_NAME_ACC("last_name_acc"), // фамилия в винительном падеже
    LAST_NAME_INS("last_name_ins"), // фамилия в творительном падеже
    LAST_NAME_ABL("last_name_abl"), // фамилия в предложном падеже
    LAST_SEEN("last_seen"), // информация о последнем онлайне (когда и с какой платформы)
    LISTS("lists"), // идентификаторы списков друзей, в которых состоит пользователь
    MAIDEN_NAME("maiden_name"), // девичья фамилия
    MILITARY("military"), // информация о воинской службе
    MUSIC("music"), // поле "любимая музыка"
    NICKNAME("nickname"), // никнейм (отчество)
    OCCUPATION("occupation"), // информация о текущем роде деятельности (учеба, работа)
    ONLINE("online"), // онлайн ли пользователь
    PERSONAL("personal"), // поля из раздела "жизненная позиция"
    PHOTO_50("photo_50"), // квадратное фото с шириной 50 пикс.
    PHOTO_100("photo_100"), // квадратное фото с шириной 100 пикс.
    PHOTO_200_ORIG("photo_200_orig"), // оригинальное фото с шириной 200 пикс.
    PHOTO_200("photo_200"), // квадратное фото с шириной 200 пикс.
    PHOTO_400_ORIG("photo_400_orig"), // оригинальное фото с шириной 400 пикс.
    PHOTO_ID("photo_id"), // id главной фотографии в формате {user_id}_{photo_id}
    PHOTO_MAX("photo_max"), // квадратное фото с максимальной шириной
    PHOTO_MAX_ORIG("photo_max_orig"), // оригинальное фото с максимальной шириной
    QUOTES("quotes"), // поле "любимые цитаты"
    RELATIVES("relatives"), // список родственников
    RELATION("relation"), // семейное положение
    SCHOOLS("schools"), // информация о школах
    SCREEN_NAME("screen_name"), // короткое имя страницы пользователя
    SEX("sex"), // пол пользователя
    SITE("site"), // значение поля "сайт"
    STATUS("status"), // статус пользователя
    TIMEZONE("timezone"), // временная зона (только для текущего пользователя)
    TRENDING("trending"), // есть ли на странице пользователя "огонек"
    TV("tv"), // поле "любимые телешоу"
    UNIVERSITIES("universities"), // информация о вузах
    VERIFIED("verified") // верифицирована ли страница пользователя
}

enum class CommunityOptionalField(override val value: String) : ObjectField {
    ACTIVITY("activity"), // состояние публичной страницы (откр./закр., дата начала для событий)
    AGE_LIMITS("age_limits"), // возрастное ограничение
    BAN_INFO("ban_info"), // информация о занесении в черный список сообщества
    CAN_CREATE_TOPIC("can_create_topic"), // может ли текущий пользователь создать новое обсуждение в группе
    CAN_MESSAGE("can_message"), // может ли текущий пользователь написать сообщение сообществу
    CAN_POST("can_post"), // может ли текущий пользователь оставлять записи на стене сообщества
    CAN_SEE_ALL_POSTS("can_see_all_posts"), // разрешено ли видеть чужие записи на стене группы
    CAN_UPLOAD_DOC("can_upload_doc"), // может ли текущий пользователь загружать документы в группу
    CAN_UPLOAD_VIDEO("can_upload_video"), // может ли текущий пользователь загружать видеозаписи в группу
    CITY("city"), // город, указанный в информации о сообществе
    CONTACTS("contacts"), // информация из блока контактов публичной страницы
    COUNTERS("counters"), // счётчики сообщества
    COUNTRY("country"), // страна, указанная в информации о сообществе
    COVER("cover"), // обложка сообщества
    CROP_PHOTO("crop_photo"), // данные о точках, по которым вырезаны профильная и миниатюрная фотографии сообщества
    DESCRIPTION("description"), // текст описания сообщества
    FIXED_POST("fixed_post"), // идентификатор закрепленной записи
    HAS_PHOTO("has_photo"), // установлена ли у сообщества главная фотография
    IS_FAVORITE("is_favorite"), // находится ли сообщество в закладках у текущего пользователя
    IS_HIDDEN_FROM_FEED("is_hidden_from_feed"), // скрыто ли сообщество из ленты новостей текущего пользователя
    IS_MESSAGES_BLOCKED("is_messages_blocked"), // заблокированы ли сообщения от этого сообщества у текущего пользователя
    LINKS("links"), // информация из блока ссылок сообщества
    MAIN_ALBUM_ID("main_album_id"), // идентификатор основного фотоальбома
    MAIN_SECTION("main_section"), // информация о главной секции
    MARKET("market"), // информация о магазине
    MEMBER_STATUS("member_status"), // статус участника текущего пользователя
    PLACE("place"), // место, указанное в информации о сообществе
    PUBLIC_DATE_LABEL("public_date_label"), // возвращается для публичных страниц. Текст описания для поля start_date
    SITE("site"), // адрес сайта из поля «веб-сайт» в описании сообщества
    START_DATE("start_date"), // время начала для встреч, дата основания для публичных страниц
    FINISH_DATE("finish_date"), // время окончания встречи
    STATUS("status"), // статус сообщества
    TRENDING("trending"), // информация о том, есть ли у сообщества «огонёк»
    VERIFIED("verified"), // верифицировано ли сообщество
    WIKI_PAGE("wiki_page") // название главной вики-страницы
}

enum class NameCase(internal val value: String) {
    NOM("nom"), // именительный
    GEN("gen"), // родительный
    DAT("dat"), // дательный
    ACC("acc"), // винительный
    INS("ins"), // творительный
    ABL("abl"), // предложный
}

enum class AccountInfoField(internal val value: String) {
    COUNTRY("country"),
    HTTPS_REQUIRED("https_required"),
    OWN_POSTS_DEFAULT("own_posts_default"),
    NO_WALL_REPLIES("no_wall_replies"),
    INTRO("intro"),
    LANG("lang")
}

//--- Filters ---//

enum class CounterFilter(internal val value: String) {
    FRIENDS("friends"), // новые заявки в друзья
    FRIENDS_SUGGESTIONS("friends_suggestions"), // предлагаемые друзья
    MESSAGES("messages"), // новые сообщения
    PHOTOS("photos"), // новые отметки на фотографиях
    VIDEOS("videos"), // новые отметки на видеозаписях
    GIFTS("gifts"), // подарки
    EVENTS("events"), // события
    GROUPS("groups"), // сообщества
    NOTIFICATIONS("notifications"), // ответы
    SDK("sdk"), // запросы в мобильных играх
    APP_REQUESTS("app_requests") // уведомления от приложений
}

enum class ConversationFilter(internal val value: String) {
    ALL("all"),
    UNREAD("unread"),
    IMPORTANT("important"),
    UNANSWERED("unanswered")
}

enum class GroupsFilter(internal val value: String) {
    ADMIN("admin"),
    EDITOR("editor"),
    MODER("moder"),
    GROUPS("groups"),
    PUBLICS("publics"),
    EVENTS("events")
}

enum class LikesFilter(internal val value: String) {
    LIKES("likes"), // все лайкнувшие
    COPIES("copies") // только поделившиеся
}

//--- Order & Sort ---//

enum class FriendsOrder(internal val value: String) {
    HINTS("hints"), // по рейтингу
    RANDOM("random"), // случайны порядок
    MOBILE("mobile"), // сверху друзья с мобильными приложениями (?)
    NAME("name") // сортировка по имени
}

enum class CommunityMembersSort(internal val value: String) {
    ID_ASC("id_asc"), // по ID в порядке возрастания
    ID_DESC("id_desc"), // по ID в порядке убывания
    TIME_ASC("time_asc"), // по времени вступления в порядке возрастания
    TIME_DESC("time_desc") // по времени вступления в порядке убывания
}

enum class CommunitySearchOrder(internal val value: Int) {
    DEFAULT(0), // по умолчанию (как в полной версии сайта)
    GROWTH_SPEED(1), // по скорости роста
    DAY_ATTENDANCE_TO_MEMBERS_NUMBER(2), // по отношению дневной посещаемости к количеству пользователей
    LIKES_NUMBER_TO_MEMBERS_NUMBER(3), // по отношению количества лайков к количеству пользователей
    COMMENTS_NUMBER_TO_MEMBERS_NUMBER(4), // по отношению количества комментариев к количеству пользователей
    BOARDS_ENTRIES_NUMBER_TO_MEMBERS_NUMBER(5) // по отношению количества записей в обсуждениях к количеству пользователей
}

enum class UserSearchSort(internal val value: Int) {
    DATE_REGISTERED(1),
    RATING(0)
}

//--- Types & other Enums ---//

enum class MarketCurrency(internal val value: Int) {
    RUB(643),
    UAH(980),
    KZT(398),
    EURO(978),
    USD(840)
}

enum class GroupType(internal val value: String) {
    EVENT("event"),
    GROUP("group"),
    PUBLIC("public")
}

enum class PublicSubtype(internal val value: Int) {
    PLACE_OR_SMALL_BUSINESS(1),
    COMPANY_OR_WEBSITE(2),
    FAMOUS_PERSON(3),
    PRODUCT_OR_WORK_OF_ART(4)
}

enum class CommunityManagerRole(internal val value: String) {
    MODERATOR("moderator"),
    EDITOR("editor"),
    ADMINISTRATOR("administrator")
}

enum class GroupSubject(internal val value: Int) {
    AUTO_AND_MOTO(1),
    ACTIVITY_HOLIDAYS(2),
    BUSINESS(3),
    PETS(4),
    HEALTH(5),
    DATING_AND_COMMUNICATION(6),
    GAMES(7),
    IT(8),
    CINEMA(9),
    BEAUTY_AND_FASHION(10),
    COOKING(11),
    ART_AND_CULTURE(12),
    LITERATURE(13),
    MOBILE_SERVICES_AND_INTERNET(14),
    MUSIC(15),
    SCIENCE_AND_TECHNOLOGY(16),
    REAL_ESTATE(17),
    NEWS_AND_MEDIA(18),
    SECURITY(19),
    EDUCATION(20),
    HOME_AND_RENOVATIONS(21),
    POLITICS(22),
    FOOD(23),
    INDUSTRY(24),
    TRAVEL(25),
    WORK(26),
    ENTERTAINMENT(27),
    RELIGION(28),
    FAMILY(29),
    SPORTS(30),
    INSURANCE(31),
    TELEVISION(32),
    GOODS_AND_SERVICES(33),
    HOBBIES(34),
    FINANCE(35),
    PHOTO(36),
    ESOTERICS(37),
    ELECTRONICS_AND_APPLIANCES(38),
    EROTIC(39),
    HUMOR(40),
    SOCIETY_AND_HUMANITIES(41),
    DESIGN_AND_GRAPHICS(42)
}

enum class NearbyRadius(internal val value: Int) {
    M_300(1),
    M_2400(2),
    KM_18(3),
    KM_150(4)
}

enum class ReportComplaintType(internal val value: String) {
    PORN("porn"),
    SPAM("spam"),
    INSULT("insult"),
    ADVERTISEMENT("advertisment")
}

enum class UsersListType(internal val value: String) {
    FRIENDS("friends"),
    SUBSCRIPTIONS("subscriptions")
}

enum class LikeType(internal val value: String) {
    POST("post"),
    COMMENT("comment"),
    PHOTO("photo"),
    AUDIO("audio"),
    VIDEO("video"),
    NOTE("note"),
    MARKET("market"),
    PHOTO_COMMENT("photo_comment"),
    VIDEO_COMMENT("video_comment"),
    TOPIC_COMMENT("topic_comment"),
    MARKET_COMMENT("market_comment"),
    SITE_PAGE("sitepage")
}

enum class ShortLinkStatsInterval(internal val value: String) {
    HOUR("hour"),
    DAY("day"),
    WEEK("week"),
    MONTH("month"),
    FOREVER("forever")
}