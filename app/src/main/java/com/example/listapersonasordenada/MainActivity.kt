package com.example.listapersonasordenada

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listapersonasordenada.databinding.ActivityMainBinding
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val listaPersonas = mutableListOf<Persona>()

    companion object {
        const val TAG = "Hola"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        //le indico abajo que ventana quiero mostrar
        setContentView(binding.root)

        for(i in 0..9){
            val auxNota = String.format(Locale.US,"%.2f", Random.nextDouble(0.0, 10.01)).toFloat()
            val auxAltura = String.format(Locale.US,"%.2f", Random.nextDouble(1.50, 2.11)).toFloat()
            listaPersonas.add(Persona("Persona${i+1}", (18..100).random().toShort(), auxNota, auxAltura))
        }


        when((1..4).random()){
            1 ->{
                binding.tvId2.text = "Ordenado de mayor a menor por nombre."
                listaPersonas.sortByDescending { it.nombre.length }
                var aux = ""
                listaPersonas.forEach { aux += it }
                binding.tvId1.text = aux
            }
            2 -> {
                binding.tvId2.text = "Ordenado de mayor a menor por edad."
                listaPersonas.sortByDescending { it.edad }
                var aux = ""
                listaPersonas.forEach { aux += it }
                binding.tvId1.text = aux
            }
            3 -> {
                binding.tvId2.text = "Ordenado de menor a mayor por edad."
                listaPersonas.sortedBy { it.edad }
                var aux = ""
                listaPersonas.forEach { aux += it }
                binding.tvId1.text = aux
            }
            4 -> {
                binding.tvId2.text = "Ordenado de mayor a menor por altura."
                listaPersonas.sortByDescending { it.altura }
                var aux = ""
                listaPersonas.forEach { aux += it }
                binding.tvId1.text = aux
            }
            else -> {
                binding.tvId2.text = "Ups no pude ordenar. el parametro del when no me vale"
                var aux = ""
                listaPersonas.forEach { aux += it }
                binding.tvId1.text = aux
            }
        }
        //listaPersonas.forEach { println(it) }
    }

    override fun onStop() {
        guardarPreferencias(listaPersonas[0])
        super.onStop()
    }

    private fun cargarPreferencias() : String? {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getString("hola", "")
    }

    private fun guardarPreferencias(persona : Persona) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)

        with (sharedPref.edit()) {
            putString(TAG, persona.nombre)
            putInt(TAG, persona.edad.toInt())
            putFloat(TAG, persona.notaMedia)
            putFloat(TAG, persona.altura)
            commit()
        }
    }



}

class Persona(var nombre: String, var edad: Short, var notaMedia: Float, var altura: Float) {
    override fun toString(): String {
        return "\nNombre: $nombre\n" +
                "Edad: $edad\n" +
                "Nota media: $notaMedia\n" +
                "Altura: $altura\n"
    }
}