package project.lonelywheeler.util.date

import java.text.SimpleDateFormat
import java.util.*


object MyDateUtils {
    fun createDate(millis: Long) : String{
        val date = Date(millis)
        val format = SimpleDateFormat("dd.MM.yyyy.")
        return format.format(date)
    }
}