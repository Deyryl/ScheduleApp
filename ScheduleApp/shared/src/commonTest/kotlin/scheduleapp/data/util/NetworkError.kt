package util

enum class NetworkError : Error {
    REQUEST_TIMEOUT,
    UNAUTHORIZED,
    CONFLICT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    PAYLOAD_TOO_LARGE,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN;
}

fun NetworkError.message(): String = when (this) {
    NetworkError.REQUEST_TIMEOUT   -> "Время ожидания запроса истекло"
    NetworkError.UNAUTHORIZED      -> "Неверный логин или пароль"
    NetworkError.CONFLICT          -> "Пользователь уже существует"
    NetworkError.TOO_MANY_REQUESTS -> "Слишком много запросов. Повторите позже"
    NetworkError.PAYLOAD_TOO_LARGE -> "Отправленные данные слишком большие"
    NetworkError.SERVER_ERROR      -> "Ошибка сервера. Попробуйте позже"
    NetworkError.SERIALIZATION     -> "Ошибка обработки ответа от сервера"
    NetworkError.NO_INTERNET       -> "Отсутствует подключение к интернету"
    NetworkError.UNKNOWN           -> "Неизвестная ошибка"
}