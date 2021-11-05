package kr.feliz.tutorial_collection.sence.singleton

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MySQLOpenHelperSingleton private constructor(context: Context): SQLiteOpenHelper(context, "MyDB", null, 1) {
    val TAG: String = "MySQLOpenHelperSingleton"

    companion object {
        // 자기 자신 변수 선언
        @Volatile private var instance: MySQLOpenHelperSingleton? = null

        // 자기 자신 가져오기
        fun getInstance(context: Context): MySQLOpenHelperSingleton =
            instance ?: synchronized(this){
                MySQLOpenHelperSingleton(context).also {
                    instance = it
                }
            }
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}