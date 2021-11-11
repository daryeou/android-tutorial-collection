object BuildConfig {
    object KORBIT{
        object PROD : Field() {
            override val CHART_API_SERVER_BASE_URL = "\"https://d1cta0eou52ius.cloudfront.net/\""
        }
        abstract class Field {
            abstract val CHART_API_SERVER_BASE_URL: String
        }
    }
}