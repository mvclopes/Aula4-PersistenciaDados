package com.mvclopes.persistenciadados

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mvclopes.persistenciadados.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Obtendo instância do gestor de banco de dados SQLite
        val db = DatabaseManager(this, DATABASE_NAME)

        binding.btnSalvar.setOnClickListener {
            // Remoção de determinado dado (ID=1) do banco de dados
            db.removeGreeting()

            // Obtenção dos dados (Nome e Tratamento) que serão inseridos no banco de dados
            val name = binding.nomeInput.text.toString()
            val treatment = binding.tratamentoSpinner.selectedItem.toString()

            // Inserindo novo dado ao banco de dados, especificando seu ID
            db.insertGreeting(1, name, treatment)

            // Exibição visual informando que o dado foi salvo com sucesso no banco de dados
            Toast.makeText(this, "Dado salvo!", Toast.LENGTH_SHORT).show()
        }

        // Navegação para tela de Saudação via Intent explícita
        binding.btnExibirSaudacao.setOnClickListener {
            val intent = Intent(this, GreetingActivity::class.java)
            startActivity(intent)
        }
    }

    // Constantes com as chaves referente ao Banco de Dados
    companion object {
        const val DATABASE_NAME = "saudacoes"
        const val TABLE_NAME = "SAUDACAO"
        const val COLUMN_ID = "ID_SAUDACAO"
        const val COLUMN_NAME = "NOME"
        const val COLUMN_TREATMENT = "TRATAMENTO"
    }
}