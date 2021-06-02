package com.example.listapersonasordenada

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listapersonasordenada.databinding.ActivityMainBinding
import kotlinx.serialization.Serializable
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val listaPersonas = mutableListOf<Persona>()

    companion object {
        const val TAGNOMBRE = "nombre"
        const val TAGEDAD = "edad"
        const val TAGNOTA = "nota"
        const val TAGALTURA = "altura"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        //le indico abajo que ventana quiero mostrar
        setContentView(binding.root)

        for(i in 0..9){
            val auxNota = String.format(Locale.US,"%.2f", Random.nextDouble(0.0, 10.01)).toFloat()
            val auxAltura = String.format(Locale.US,"%.2f", Random.nextDouble(1.50, 2.11)).toFloat()
            listaPersonas.add(Persona("Persona${i+1}", (18..100).random(), auxNota, auxAltura))
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
        binding.tvId3.text = cargarPreferencias().toString()
        //listaPersonas.forEach { println(it) }
    }

    override fun onStop() {
        guardarPreferencias(listaPersonas[0])
        println("STOOOOOOOOOOOOOOOOOOOOOP")
        super.onStop()
    }

    private fun cargarPreferencias() : Persona?{
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        var auxPersona : Persona? = null
        with(sharedPref){
            val auxString = getString(TAGNOMBRE,"")?.let {
                val persona = Persona(it,
                    getInt(TAGEDAD, 0),
                    getFloat(TAGNOTA, 0f),
                    getFloat(TAGALTURA, 0f))
                auxPersona = persona
            }

        }
        return auxPersona
    }

    private fun guardarPreferencias(persona : Persona) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)

        with (sharedPref.edit()) {
            putString(TAGNOMBRE, persona.nombre)
            putInt(TAGEDAD, persona.edad.toInt())
            putFloat(TAGNOTA, persona.notaMedia)
            putFloat(TAGALTURA, persona.altura)
            commit()
        }
    }
}

@Serializable
data class Persona(var nombre: String, var edad: Int, var notaMedia: Float, var altura: Float) {
    override fun toString(): String {
        return "\nNombre: $nombre\n" +
                "Edad: $edad\n" +
                "Nota media: $notaMedia\n" +
                "Altura: $altura\n"
    }
}