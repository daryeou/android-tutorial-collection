package kr.feliz.tutorial_collection.lemonfox.widget.net

data class ApiResult<out T>(val status: Status, val responseData: T?, val error: ApiError?) {
    enum class Status {
        SUCCESS, ERROR
    }

    companion object {
        fun <T> success(responseData: T): ApiResult<T> {
            return ApiResult(Status.SUCCESS, responseData, null)
        }

        fun <T> error(error: ApiError?): ApiResult<T> {
            return ApiResult(Status.ERROR, null, error)
        }
    }
}