package config

import android.content.Context

class prefs(val context:Context) {
    val db_prefs="DB_USER"
    val user_name="USER"
    val user_lastname="LAST NAME"
    val user_phone= "CELLPHONE"
    val user_mail="MAIL"
    fun guardar(user:String,last_name:String,cellphone:String,email:String){
        context.getSharedPreferences(db_prefs,Context.MODE_PRIVATE)
            .edit()
            .putString(user_name,user)
            .putString(user_lastname,last_name)
            .putString(user_phone,cellphone)
            .putString(user_mail,email).apply()
    }
    fun guardarUser(user: String) {
        context.getSharedPreferences(db_prefs, Context.MODE_PRIVATE)
            .edit()
            .putString(user_name, user)
            .apply()
    }
    fun GetUser(): String? {
        var config = context.getSharedPreferences(db_prefs, Context.MODE_PRIVATE)
        return config.getString(user_name, null)
    }
    fun guardarlast_name(last_name: String) {
        context.getSharedPreferences(db_prefs, Context.MODE_PRIVATE)
            .edit()
            .putString(user_lastname,last_name)
            .apply()
    }
    fun Getlast_name(): String? {
        var config = context.getSharedPreferences(db_prefs, Context.MODE_PRIVATE)
        return config.getString(user_lastname, null)
    }
    fun guardarphone(cellphone: String) {
        context.getSharedPreferences(db_prefs, Context.MODE_PRIVATE)
            .edit()
            .putString(user_phone,cellphone)
            .apply()
    }
    fun Getphone(): String? {
        var config = context.getSharedPreferences(db_prefs, Context.MODE_PRIVATE)
        return config.getString(user_phone, null)
    }
    fun guardarmail(email: String) {
        context.getSharedPreferences(db_prefs, Context.MODE_PRIVATE)
            .edit()
            .putString(user_mail,email)
            .apply()
    }
    fun Getemail(): String? {
        var config = context.getSharedPreferences(db_prefs, Context.MODE_PRIVATE)
        return config.getString(user_mail, null)
    }


}