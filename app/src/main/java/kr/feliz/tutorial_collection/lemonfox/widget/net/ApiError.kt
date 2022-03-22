package kr.feliz.tutorial_collection.lemonfox.widget.net

enum class ApiErrorCase {
    PARSE_ERROR, MAINTENANCE_ERROR, NETWORK_ERROR, UNKNOWN_ERROR, INVALID_TOKEN, CANCEL
}

data class ApiError(
    val errorType: ApiErrorCase,
    val errorMessage: String?,
    val errorCode: Int? = null
)