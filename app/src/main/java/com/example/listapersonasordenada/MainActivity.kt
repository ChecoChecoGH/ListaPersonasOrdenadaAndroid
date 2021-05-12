package com.example.listapersonasordenada

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listapersonasordenada.databinding.ActivityMainBinding
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val listaPersonas = mutableListOf<Persona>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        for(i in 0..9){
            val auxNota = String.format(Locale.US,"%.2f", Random.nextDouble(0.0, 10.01)).toFloat()
            val auxAltura = String.format(Locale.US,"%.2f", Random.nextDouble(1.50, 2.11)).toFloat()
            listaPersonas.add(Persona("Persona${i+1}", (18..100).random().toShort(), auxNota, auxAltura))
        }
        when((1..4).random()){
            1 ->{ println("Ordenado de mayor a menor por nombre.")
                binding.tvId.text = listaPersonas.sortByDescending { it.nombre.length }.toString()
            }
            2 -> {println("Ordenado de mayor a menor por edad.")
                binding.tvId.text = listaPersonas.sortByDescending { it.edad }.toString()
            }
            3 -> {println("Ordenado de menor a mayor por edad.")
                binding.tvId.text = listaPersonas.sortedBy { it.edad }.toString()
            }
            4 -> {println("Ordenado de mayor a menor por altura.")
                binding.tvId.text = listaPersonas.sortByDescending { it.altura }.toString()
            }
            else -> println("Ups no pude ordenar. el parametro del when no me vale")
        }
        listaPersonas.forEach { println(it) }
    }

    /*
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        //outState.putParcelableArrayList() No me vale, preguntar como sería
         ¿¿Iría aqui??
        //Cuando rote genero un numero del 1 al 4 aleatoriamente y ordeno
    when((1..4).random()){
        1 ->{ println("Ordenado de mayor a menor por nombre.")
              listaPersonas.sortByDescending { it.nombre.length }
        }
        2 -> {println("Ordenado de mayor a menor por edad.")
              listaPersonas.sortByDescending { it.edad }
        }
        3 -> {println("Ordenado de menor a mayor por edad.")
              listaPersonas.sortedBy { it.edad }
        }
        4 -> {println("Ordenado de mayor a menor por altura.")
              listaPersonas.sortByDescending { it.altura }
        }
        else -> println("Ups no pude ordenar. el parametro del when no me vale")
    }
         */
}

class Persona(var nombre: String, var edad: Short, var notaMedia: Float, var altura: Float) {
    override fun toString(): String {
        return "Nombre: $nombre\n" +
                "Edad: $edad\n" +
                "Nota media: $notaMedia\n" +
                "Altura: $altura\n"
    }
}