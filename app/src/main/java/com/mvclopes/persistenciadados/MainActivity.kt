package com.mvclopes.persistenciadados

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mvclopes.persistenciadados.databinding.ActivityMainBinding
import java.io.FileNotFoundException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSalvar.setOnClickListener {
            // Obtenção dos dados: título de tratamento e o nome escrito no EditText
            val data = binding.tratamentoSpinner.selectedItem.toString() + " " + binding.nomeInput.text.toString()
            writeFile(FILENAME_KEY, data)
            // Exibição de mensagem informando que o dado foi gravado
            Toast.makeText(this, "Dado salvo!", Toast.LENGTH_SHORT).show()
        }

        binding.btnExibirSaudacao.setOnClickListener {
            // Navegação para tela de Saudação via Intent explícita
            val intent = Intent(this, GreetingActivity::class.java)
            startActivity(intent)
        }
    }

    // Método para escrever dados em um arquivo especificando nome do arquivo
    private fun writeFile(filename: String, data: String) {
        try {
            val fs = openFileOutput(filename, Context.MODE_PRIVATE)
            fs.write(data.toByteArray())
            fs.close()
        }
        // Tratamento das exceções que podem acontecer ao realizar operação de escrita de arquivo
        catch (e: FileNotFoundException) { Log.e("TAG_", "File Not Found", e) }
        catch (e: IOException) { Log.e("TAG_", "IO Exception", e) }
    }

    // Constantes com as chaves dos dados do Shared Preference
    companion object {
        const val FILENAME_KEY = "saudacao"
        const val DELIMITER_KEY = ":"
    }
}