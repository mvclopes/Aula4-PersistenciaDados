package com.mvclopes.persistenciadados

import android.content.Context
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

        binding.btnSalvar.setOnClickListener {
            // Obtida instância do Shared Preference, determinando o modo como privado
            val greetingPreferences = this.getSharedPreferences(GREETING_KEY, Context.MODE_PRIVATE)
            val editor = greetingPreferences.edit()

            // Obtenção dos dados (Nome e Tratamento) dos componentes EditText e Spinner
            val name = binding.nomeInput.text.toString()
            val treatment = binding.tratamentoSpinner.selectedItem.toString()

            // Persistir os dados (Nome e tratamento) no Shared Preferences
            editor.putString(NAME_KEY, name)
            editor.putString(TREATMENT_KEY, treatment)
            editor.apply()

            // Mensagem visual para informar ao usuário que o dado foi salvo com sucesso
            Toast.makeText(this, "Dado salvo!", Toast.LENGTH_SHORT).show()
        }

        // Navegação para tela de Saudação via Intent explícita
        binding.btnExibirSaudacao.setOnClickListener {
            val intent = Intent(this, GreetingActivity::class.java)
            startActivity(intent)
        }
    }

    // Constantes com as chaves dos dados do Shared Preference
    companion object {
        const val GREETING_KEY = "saudacao"
        const val NAME_KEY = "nome"
        const val TREATMENT_KEY = "tratamento"
    }
}