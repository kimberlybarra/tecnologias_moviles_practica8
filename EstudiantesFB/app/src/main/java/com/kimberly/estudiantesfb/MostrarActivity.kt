package com.kimberly.estudiantesfb

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

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
            .getReference("Estudiantes")

        referencia.addValueEventListener(

            object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val datos = StringBuilder()

                    for (item in snapshot.children) {

                        val estudiante =
                            item.getValue(Estudiante::class.java)

                        datos.append(
                            "Nombre: ${estudiante?.nombre}\n" +
                                    "Carrera: ${estudiante?.carrera}\n" +
                                    "Curso: ${estudiante?.curso}\n\n"
                        )
                    }

                    txtMostrar.text = datos.toString()
                }

                override fun onCancelled(error: DatabaseError) {

                    txtMostrar.text =
                        "Error: ${error.message}"
                }
            }
        )
    }
}