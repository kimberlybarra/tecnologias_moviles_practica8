package com.kimberly.repositoriofb

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.content.Intent

class MainActivity : AppCompatActivity() {

    private lateinit var txtTema: EditText
    private lateinit var spinAreas: Spinner
    private lateinit var spinSecciones: Spinner
    private lateinit var btnRegistrar: Button
    private lateinit var btnMostrar: Button
//conexion a firebase
    private lateinit var clasesRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->

            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)

            insets
        }
//conectando con el nodo firebase
        clasesRef = FirebaseDatabase
            .getInstance()
            .getReference("Clases")

        txtTema = findViewById(R.id.editTextText)
        spinAreas = findViewById(R.id.spinarea)
        spinSecciones = findViewById(R.id.spinseccion)
        btnRegistrar = findViewById(R.id.btnregistrar)
        btnMostrar = findViewById(R.id.btnmostrar)

        btnRegistrar.setOnClickListener {

            registrarClase()

        }
        btnMostrar.setOnClickListener {

            val intent = Intent(this, MostrarActivity::class.java)

            startActivity(intent)

        }

    }

    private fun registrarClase() {

        val seccion = spinSecciones.selectedItem.toString()

        val area = spinAreas.selectedItem.toString()

        val tema = txtTema.text.toString().trim()

        if (tema.isEmpty()) {

            Toast.makeText(
                this,
                "Escribe el tema antes de registrar",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        val id = clasesRef
            .child("Lecciones")
            .push()
            .key ?: return

        val leccion = Clase(

            claseid = id,
            seccion = seccion,
            area = area,
            tema = tema

        )

        clasesRef
            .child("Lecciones")
            .child(id)
            .setValue(leccion)

            .addOnSuccessListener {

                Toast.makeText(
                    this,
                    "Clase Registrada",
                    Toast.LENGTH_LONG
                ).show()

                txtTema.text.clear()

            }

            .addOnFailureListener { e ->

                Toast.makeText(
                    this,
                    "Error: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
    }
}