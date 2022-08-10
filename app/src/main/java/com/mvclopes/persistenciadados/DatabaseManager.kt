package com.mvclopes.persistenciadados

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.mvclopes.persistenciadados.MainActivity.Companion.COLUMN_ID
import com.mvclopes.persistenciadados.MainActivity.Companion.COLUMN_NAME
import com.mvclopes.persistenciadados.MainActivity.Companion.COLUMN_TREATMENT
import com.mvclopes.persistenciadados.MainActivity.Companion.TABLE_NAME

// Classe auxiliar para realizar operações CRUD no banco de dados
class DatabaseManager(context: Context, name: String?) : SQLiteOpenHelper(context, name, null, 1) {

    override fun onCreate(p0: SQLiteDatabase?) {
        // Query SQL para criação de tabela no banco de dados, especificando seu schema
        p0?.execSQL("CREATE TABLE SAUDACAO(\n" +
                "\tID_SAUDACAO INT NOT NULL,\n " +
                "\tNOME VARCHAR(20),\n " +
                "\tTRATAMENTO VARCHAR(20),\n " +
                "\t PRIMARY KEY (ID_SAUDACAO)\n" +
                "\t);")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        // Query SQL para remoção de determinada tabela do banco de dados
        p0?.execSQL("DROP TABLE IF EXISTS SAUDACAO")
        p0?.execSQL("CREATE TABLE SAUDACAO(\n" +
                "\tID_SAUDACAO INT NOT NULL,\n " +
                "\tNOME VARCHAR(20),\n " +
                "\tTRATAMENTO VARCHAR(20),\n " +
                "\t PRIMARY KEY (ID_SAUDACAO)\n" +
                "\t);")
    }

    // Método para inserção de novos dados no banco de dados
    fun insertGreeting(id: Int, name: String, treatment: String) {
        val db = this.writableDatabase
        val cv = ContentValues()
        // Montagem do objeto ContentValues que serão posteriormente salvo no banco de dados
        cv.put(COLUMN_ID, id)
        cv.put(COLUMN_NAME, name)
        cv.put(COLUMN_TREATMENT, treatment)
        db.insert(TABLE_NAME, "ID_SAUDACAO", cv)
    }

    // Método para obtenção de dados do banco de dados
    fun listGreetings(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("select nome, tratamento from saudacao", null)
    }

    // Método para remoção de determinado dado do banco de dados, especificando seu ID
    fun removeGreeting() {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "ID_SAUDACAO = 1", null)
    }

}