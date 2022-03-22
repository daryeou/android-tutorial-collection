package kr.feliz.tutorial_collection.lemonfox.widget.utils

import java.util.*
import kotlin.math.min

fun String.toUpper(): String = this.toUpperCase(Locale.ROOT)
fun String.toLower(): String = this.toLowerCase(Locale.ROOT)

fun String.currencySymbol(type: SymbolCase): String {
    return when (type) {
        SymbolCase.UPPER -> this.toUpper()
        SymbolCase.LOWER -> this.toLower()
    }
}

fun String?.getMaskString(type: Int): String {
    if (isNullOrEmpty()) {
        return ""
    }

    var str = this
    var masking = ""
    val hiddenString = "*"

    when (type) {
        MaskingType.NAME -> {
            str.forEachIndexed { i, c ->
                masking += if (i == 0) c else hiddenString
            }
        }

        MaskingType.FULL_ADDRESS -> {
            val regexString = """((([가-힣]+(시|도)|))( |)[가-힣]+(시|군|구))( )""".toRegex()
            val match = regexString.find(this)
            val last = match?.range?.last ?: 0
            for (index in str.indices) {
                when {
                    index <= last || this[index] == ' ' -> masking += this[index]
                    else -> masking += hiddenString
                }
            }
        }

        MaskingType.ADDRESS -> {
            for (item in str.indices) {
                masking += hiddenString
            }
        }

        MaskingType.ACCOUNT -> {
            str.forEachIndexed { i, c ->
                masking += if (i < str.length - 5) c else hiddenString
            }
        }

        MaskingType.PHONE -> {
            str.forEachIndexed { i, c ->
                masking += if (i < 4) c else hiddenString
            }
        }

        MaskingType.HOME_PHONE -> {
            masking += str.substring(0, str.length - 4)
            for (i in 0 until 4) {
                masking += hiddenString
            }
        }

        MaskingType.ZIP_CODE -> {
            str.forEachIndexed { index, c ->
                masking += if (index in 0..1) c else hiddenString
            }
        }

        MaskingType.EMAIL -> {
            val splitIndex = str.indexOf("@")
            str.forEachIndexed { i, c ->
                masking += if (i < 2 || i >= splitIndex) c else hiddenString
            }
        }
    }

    return masking
}

fun getBaseAndCounter(currencyPair: String): Pair<String, String> {
    val splitString = currencyPair.split("_")
    val base = splitString[0]
    val counter = try {
        splitString[1]
    } catch (e: Exception) {
        ""
    }

    return base to counter
}

fun getCurrencyPair(base: String, counter: String): String {
    return "${base.toLowerCase(Locale.ROOT)}_${counter.toLowerCase(Locale.ROOT)}"
}

fun getFormattedPhoneNumber(before: String, after: String): String {
    if (after < before || after.length < 13) {
        return getFormattedString(before, after, 3, 6)
    } else {
        return getFormattedString(before, after, 3, 7)
    }
}

fun getFormattedDateOfBirth(before: String, after: String): String {
    return getFormattedString(before, after, 4, 6)
}

fun getFormattedIdCardNumber(before: String, after: String): String {
    return getFormattedString(before, after, 6, 14)
}

fun getFormattedDriveLicenseNumber(before: String, after: String): String {
    return getFormattedString(before, after, 2, 4, 10)
}

fun getFormattedString(before: String, after: String, firstSplitIndex: Int, secondSplitIndex: Int): String {
    val resultText: String
    if (before > after && before.endsWith("-")) {
        resultText = after.substring(0, after.length - 1)
    } else {
        val unFormattedText = after.replace("-", "")
        resultText = when {
            unFormattedText.length >= secondSplitIndex -> {
                val first = unFormattedText.substring(0, firstSplitIndex)
                val second = unFormattedText.substring(firstSplitIndex, secondSplitIndex)
                val third = unFormattedText.substring(secondSplitIndex, unFormattedText.length)
                "${first}-${second}-${third}"
            }
            unFormattedText.length >= firstSplitIndex -> {
                val first = unFormattedText.substring(0, firstSplitIndex)
                val second = unFormattedText.substring(firstSplitIndex, min(unFormattedText.length, secondSplitIndex))
                "${first}-${second}"
            }
            else -> {
                val first = unFormattedText.substring(0, min(unFormattedText.length, firstSplitIndex))
                first
            }
        }
    }
    return resultText
}

fun getFormattedString(before: String, after: String, firstSplitIndex: Int, secondSplitIndex: Int, thirdSplitIndex: Int): String {
    val resultText: String
    if (before > after && before.endsWith("-")) {
        resultText = after.substring(0, after.length - 1)
    } else {
        val unFormattedText = after.replace("-", "")
        resultText = when {

            unFormattedText.length >= thirdSplitIndex -> {
                val first = unFormattedText.substring(0, firstSplitIndex)
                val second = unFormattedText.substring(firstSplitIndex, secondSplitIndex)
                val third = unFormattedText.substring(secondSplitIndex, thirdSplitIndex)
                val fourth = unFormattedText.substring(thirdSplitIndex, unFormattedText.length)
                "${first}-${second}-${third}-${fourth}"
            }

            unFormattedText.length >= secondSplitIndex -> {
                val first = unFormattedText.substring(0, firstSplitIndex)
                val second = unFormattedText.substring(firstSplitIndex, secondSplitIndex)
                val third = unFormattedText.substring(secondSplitIndex, unFormattedText.length)
                "${first}-${second}-${third}"
            }
            unFormattedText.length >= firstSplitIndex -> {
                val first = unFormattedText.substring(0, firstSplitIndex)
                val second = unFormattedText.substring(firstSplitIndex, min(unFormattedText.length, secondSplitIndex))
                "${first}-${second}"
            }
            else -> {
                val first = unFormattedText.substring(0, min(unFormattedText.length, firstSplitIndex))
                first
            }
        }
    }
    return resultText
}
