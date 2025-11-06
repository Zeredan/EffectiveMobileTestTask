package test.task.effectivemobile.database

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun listToString(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun stringToList(data: String): List<String> {
        return gson.fromJson(data, Array<String>::class.java).toList()
    }
}