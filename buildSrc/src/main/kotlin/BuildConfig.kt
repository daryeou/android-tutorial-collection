object BuildConfig {
    object LEMONFOX{
        object PROD : Field() {
            override val CHART_API_SERVER_BASE_URL = "\"https://chart.lemonfox.net/\""
        }
        abstract class Field {
            abstract val CHART_API_SERVER_BASE_URL: String
        }
    }
}