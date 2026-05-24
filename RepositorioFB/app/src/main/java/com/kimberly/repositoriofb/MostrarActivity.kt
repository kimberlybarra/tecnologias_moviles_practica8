package com.kimberly.repositoriofb

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class MostrarActivity : AppCompatActivity() {

    private lateinit var txtMostrar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_mostrar)

        txtMostrar = findViewById(R.id.txtMostrar)

        mostrarDatos()
    }

    private fun mostrarDatos() {

        val referencia = FirebaseDatabase
            .getInstance()
            .getReference("Clases")
            .child("Lecciones")

        referencia.addValueEventListener(

            object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val datos = StringBuilder()

                    for (item in snapshot.children) {

                        val clase = item.getValue(Clase::class.java)

                        datos.append(
                            "Sección: ${clase?.seccion}\n" +
                                    "Área: ${clase?.area}\n" +
                                    "Tema: ${clase?.tema}\n\n"
                        )
                    }

                    txtMostrar.text = datos.toString()
                }

                override fun onCancelled(error: DatabaseError) {

                    txtMostrar.text = "Error: ${error.message}"
                }
            }
        )
    }
}