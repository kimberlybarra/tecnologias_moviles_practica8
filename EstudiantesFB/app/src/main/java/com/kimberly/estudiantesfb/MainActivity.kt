package com.kimberly.estudiantesfb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var txtNombre: EditText
    private lateinit var txtCarrera: EditText
    private lateinit var txtCurso: EditText
    private lateinit var btnGuardar: Button
    private lateinit var btnMostrar: Button
    private lateinit var btnActualizar: Button
    private lateinit var btnEliminar: Button

    private lateinit var txtId: EditText


    private lateinit var estudiantesRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        estudiantesRef = FirebaseDatabase
            .getInstance()
            .getReference("Estudiantes")

        txtNombre = findViewById(R.id.txtNombre)
        txtCarrera = findViewById(R.id.txtCarrera)
        txtCurso = findViewById(R.id.txtCurso)
        btnGuardar = findViewById(R.id.btnGuardar)
        btnMostrar = findViewById(R.id.btnMostrar)
        btnActualizar = findViewById(R.id.btnActualizar)
        btnEliminar = findViewById(R.id.btnEliminar)
        txtId = findViewById(R.id.txtId)

        btnGuardar.setOnClickListener {

            guardarEstudiante()

        }
        btnMostrar.setOnClickListener {

            startActivity(
                Intent(this, MostrarActivity::class.java)
            )
        }
        btnActualizar.setOnClickListener {
            actualizarEstudiante()
        }
        btnEliminar.setOnClickListener {
            eliminarEstudiante()
        }


    }


    private fun guardarEstudiante() {

        val nombre = txtNombre.text.toString()
        val carrera = txtCarrera.text.toString()
        val curso = txtCurso.text.toString()

        val id = estudiantesRef.push().key ?: return

        val estudiante = Estudiante(
            id,
            nombre,
            carrera,
            curso
        )

        estudiantesRef.child(id)
            .setValue(estudiante)

            .addOnSuccessListener {

                Toast.makeText(
                    this,
                    "Guardado correctamente",
                    Toast.LENGTH_LONG
                ).show()
            }
    }
    private fun actualizarEstudiante() {

        val id = txtId.text.toString()

        val datosActualizar = HashMap<String, Any>()

        datosActualizar["nombre"] = txtNombre.text.toString()
        datosActualizar["carrera"] = txtCarrera.text.toString()
        datosActualizar["curso"] = txtCurso.text.toString()

        estudiantesRef
            .child(id)
            .updateChildren(datosActualizar)

            .addOnSuccessListener {

                Toast.makeText(
                    this,
                    "Estudiante actualizado",
                    Toast.LENGTH_LONG
                ).show()
            }
    }
    private fun eliminarEstudiante() {

        val id = txtId.text.toString()

        estudiantesRef
            .child(id)
            .removeValue()

            .addOnSuccessListener {

                Toast.makeText(
                    this,
                    "Estudiante eliminado",
                    Toast.LENGTH_LONG
                ).show()
            }
    }
}