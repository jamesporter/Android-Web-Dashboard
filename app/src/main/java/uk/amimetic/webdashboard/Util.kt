package uk.amimetic.webdashboard

import android.content.SharedPreferences

inline fun SharedPreferences.edit(func: SharedPreferences.Editor.() -> Unit){
    val editor = edit()
    editor.func()
    editor.apply()
}

fun SharedPreferences.Editor.set(pair: Pair<String, String>) = putString(pair.first, pair.second)
