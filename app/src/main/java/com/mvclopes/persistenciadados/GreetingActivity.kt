package com.mvclopes.persistenciadados

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mvclopes.persistenciadados.MainActivity.Companion.COLUMN_NAME
import com.mvclopes.persistenciadados.MainActivity.Companion.COLUMN_TREATMENT
import com.mvclopes.persistenciadados.MainActivity.Companion.DATABASE_NAME
import com.mvclopes.persistenciadados.databinding.ActivityGreetingBinding

class GreetingActivity : AppCompatActivity() {

    private val binding: ActivityGreetingBinding by lazy { ActivityGreetingBinding.inflate(layoutInflater) }

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Obtendo instância do gestor de banco de dados SQLite
        val db = DatabaseManager(this, DATABASE_NAME)

        // Obtendo objeto cursor contendo dados gravados no banco de dados
        val cursor = db.listGreetings()
        var name = ""
        var treatment = ""

        // Laço de repetição para ler os dados do banco de dados percorrendo cursor
        if (cursor.count > 0) {
            cursor.moveToFirst()
            // Obtendo dados (Nome e Tratamento) especificando suas colunas no banco de dados
            name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
            treatment = cursor.getString(cursor.getColumnIndex(COLUMN_TREATMENT))
        }

        // Condicional que determina conteúdo a ser exibido ao usuário
        // Caso o valor lido do arquivo não possua tratamento, será exibido somente o nome gravado
        if (treatment.equals("Sem tratamento")) {
            binding.nomes.text = name
        } else {
            binding.nomes.text = "$treatment $name"
        }
    }
}