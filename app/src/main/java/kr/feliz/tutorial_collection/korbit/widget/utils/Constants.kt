package kr.feliz.tutorial_collection.korbit.widget.utils

object Constants {

}

enum class ChartInterval(val value: String) {
    INTERVAL_FIVE_MIN("5m"),
    INTERVAL_TEM_MIN("10m"),
    INTERVAL_ONE_HOUR("1h"),
    INTERVAL_ONE_DAY("1d");

    fun toInt(): Int {
        return when (this) {
            INTERVAL_FIVE_MIN -> 5
            INTERVAL_TEM_MIN -> 10
            INTERVAL_ONE_HOUR -> 1
            INTERVAL_ONE_DAY -> 1
        }
    }

    companion object {
        fun get(interval: String): ChartInterval? {
            return values().find { it.value == interval }
        }
    }
}

enum class SymbolCase {
    UPPER, LOWER
}

class MaskingType {
    companion object {
        const val NAME = 0
        const val PHONE = 1
        const val ADDRESS = 2
        const val ACCOUNT = 3
        const val HOME_PHONE = 4
        const val FULL_ADDRESS = 5
        const val ZIP_CODE = 6
        const val EMAIL = 7
    }
}

enum class ChartPeriod(val value: Long) {
    PERIOD_ONE_DAY(86400),
    PERIOD_ONE_WEEK(604800),
    PERIOD_ONE_MONTH(2592000),
    PERIOD_THREE_MONTH(7776000),
    PERIOD_101_HOUR(363600),
    PERIOD_101_DAY(8726400),
    PERIOD_ONE_YEAR(31536000),
    NONE(-1);

    companion object {
        fun getDefault() = PERIOD_ONE_DAY

        fun get(period: Long): ChartPeriod? {
            return values().find { it.value == period }
        }
    }
}