package day1

import ext.hasSubstringAt

fun getSumOfCalibrationValues(lines: List<String>, readStringsAsDigits: Boolean): Int {
    return lines.sumOf { getCalibrationValue(it, readStringsAsDigits) }
}

fun getCalibrationValue(line: String, readStringsAsDigits: Boolean): Int {
    val digits = getDigits(line, readStringsAsDigits).map { it.toString() }
    return (digits.first + digits.last).toInt()
}

fun getDigits(line: String, readStringsAsDigits: Boolean): List<Int> {
    val stringToDigitMap = if (readStringsAsDigits) extendedStringToDigitMap else digitStringToDigitMap
    return line
        .mapIndexed { index, _ -> stringToDigitMap
            .filter { line.hasSubstringAt(index, it.key) }
            .map { it.value }
            .firstOrNull()
        }
        .filterNotNull()
}