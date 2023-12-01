package day1

import ext.hasSubstringAt

fun getSumOfCalibrationValues(lines: List<String>, readStringsAsDigits: Boolean): Int {
    return lines.sumOf { getCalibrationValue(it, readStringsAsDigits) }
}

fun getCalibrationValue(line: String, readStringsAsDigits: Boolean): Int {
    val stringToDigitMap = if (readStringsAsDigits) extendedStringToDigitMap else digitStringToDigitMap
    return (line.firstDigitFromFront(stringToDigitMap) + line.firstDigitFromBack(stringToDigitMap)).toInt()
}

fun String.firstDigitFromFront(stringToDigitMap: Map<String, Int>): String {
    for (i in indices) {
        val matchingDigit = stringToDigitMap
            .filter { stringToDigit -> this.hasSubstringAt(i, stringToDigit.key) }
            .map { it.value }
            .firstOrNull()
        if (matchingDigit != null) {
            return matchingDigit.toString()
        }
    }
    throw IllegalArgumentException("String '${this}' contains no digits")
}

fun String.firstDigitFromBack(stringToDigitMap: Map<String, Int>) =
    reversed().firstDigitFromFront(stringToDigitMap.mapKeys { it.key.reversed() })