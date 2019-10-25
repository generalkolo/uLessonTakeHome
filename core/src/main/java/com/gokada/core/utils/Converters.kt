package com.gokada.core.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by edetebenezer on 2019-08-25
 **/
@SuppressLint("SimpleDateFormat")
object Converters {
    @JvmStatic
    fun toCustomDateString(initialDate: String): String {
        lateinit var returnedDate: String
        val date: Date
        try {
            date = initialDate.parseToGokadaDateWithFormat()
            returnedDate = date.dateToStringWithPattern("dd MMM yyyy")
        } catch (ex: ParseException) {
        }
        return returnedDate
    }

    @JvmStatic
    fun formatPhoneNumber(initialPhoneNumber: String): String {
        val stringBuilder = StringBuilder(initialPhoneNumber)
        stringBuilder.insert(3, " ")
        stringBuilder.insert(7, " ")
        stringBuilder.insert(0, "+234")
        return stringBuilder.toString()
    }

    @JvmStatic
    fun formatPhoneNumWithCountryCode(initialPhoneNumber: String): String {
        val stringBuilder = StringBuilder(initialPhoneNumber)
        stringBuilder.insert(7, " ")
        stringBuilder.insert(11, " ")
        return stringBuilder.toString()
    }

    @JvmStatic
    fun toExcuseDateString(initialDate: String): String {
        lateinit var returnedDate: String
        val date: Date
        try {
            date = initialDate.parseToGokadaDateWithFormat()
            returnedDate = date.dateToStringWithPattern("dd MMM")
        } catch (ex: ParseException) {
        }
        return returnedDate
    }

    @JvmStatic
    fun toCustomFullMonthDateString(initialDate: String): String {
        lateinit var returnedDate: String
        val date: Date
        try {
            date = initialDate.parseToGokadaDateWithFormat()
            returnedDate = date.dateToStringWithPattern("dd MMMM, yyyy")
        } catch (ex: ParseException) {
        }
        return returnedDate
    }

    @JvmStatic
    fun findDaysDifference(startDateValue: String, endDateValue: String): String {
        lateinit var returnedDate: String
        val startDate: Date
        val endDate: Date
        try {
            startDate = startDateValue.parseToGokadaDateWithFormat()
            endDate = endDateValue.parseToGokadaDateWithFormat()

            val startCalendar = startDate.dateToCalendar()!!.zeroTime()
            val endCalendar = endDate.dateToCalendar()!!.zeroTime()

            val diff =
                endCalendar.timeInMillis - startCalendar.timeInMillis
            returnedDate =
                if (TimeUnit.MILLISECONDS.toDays(diff).toInt() == 0) 1.toString() else TimeUnit.MILLISECONDS.toDays(
                    diff
                ).toString()
        } catch (ex: ParseException) {
        }
        return returnedDate
    }
}