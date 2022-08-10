package com.mvclopes.persistenciadados

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mvclopes.persistenciadados.MainActivity.Companion.NAME_KEY
import com.mvclopes.persistenciadados.MainActivity.Companion.TREATMENT_KEY
import com.mvclopes.persistenciadados.databinding.ActivityGreetingBinding

class GreetingActivity : AppCompatActivity() {

    private val binding: ActivityGreetingBinding by lazy { ActivityGreetingBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Obtida instância do Shared Preference, determinando o modo como privado
        val greetingPreferences = this.getSharedPreferences("saudacao", Context.MODE_PRIVATE)

        // Recuperação dos dados (Nome e Tratamento) do Shared Preferences, especificando valor default
        val name = greetingPreferences.getString(NAME_KEY, "")
        val treatment = greetingPreferences.getString(TREATMENT_KEY, "")

        // Condicional que determina conteúdo a ser exibido ao usuário
        // Caso o valor lido do arquivo não possua tratamento, será exibido somente o nome gravado
        if (treatment.equals("Sem tratamento")) {
            binding.nomes.text = name
        } else {
            binding.nomes.text = "$treatment $name"
        }
    }
}