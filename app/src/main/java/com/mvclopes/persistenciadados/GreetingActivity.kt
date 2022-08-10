package com.mvclopes.persistenciadados

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mvclopes.persistenciadados.MainActivity.Companion.DELIMITER_KEY
import com.mvclopes.persistenciadados.MainActivity.Companion.FILENAME_KEY
import com.mvclopes.persistenciadados.databinding.ActivityGreetingBinding
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.charset.Charset
import java.util.*

class GreetingActivity : AppCompatActivity() {

    private val binding: ActivityGreetingBinding by lazy { ActivityGreetingBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Obtenção dos dados utilizando método de leitura de arquivo
        val data = readFile(FILENAME_KEY)
        // StringTokenizer -> Classe utilizada para "quebrar" textos de acordo com determinado delimitador
        val tokenizer = StringTokenizer(data, DELIMITER_KEY)
        val name = if (tokenizer.hasMoreTokens()) tokenizer.nextToken() else "Sem nome"
        val treatment = if (tokenizer.hasMoreTokens()) tokenizer.nextToken() else "Sem tratamento"

        // Condicional que determina conteúdo a ser exibido ao usuário
        // Caso o valor lido do arquivo não possua tratamento, será exibido somente o nome gravado
        if (treatment.equals("Sem tratamento")) {
            binding.nomes.text = name
        } else {
            binding.nomes.text = "$treatment $name"
        }

    }

    // Método para ler arquivo e retornar string com seus dados
    private fun readFile(filename: String): String {
        return try {
            val fi = openFileInput(filename)
            val data = fi.readBytes()
            fi.close()
            data.toString(Charset.defaultCharset())
        } catch (e: FileNotFoundException) {
            "File Not Found"
        } catch (e: IOException) {
            "IO Exception"
        }
    }
}