package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import java.text.FieldPosition
import kotlin.math.round
import kotlin.math.sqrt

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(supportActionBar != null){
            supportActionBar!!.hide()
        }

        buttonCalc.setOnClickListener(this)
        buttonReset.setOnClickListener(this)

        val sexo = arrayOf("masculino", "feminino")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sexo)

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        tipo_sexo.adapter = adapter

        /**tipo_sexo.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, v: View, position: Int, id: Long){
                tipo_sexo = "Spinner select: ${parent.getItemAtPosition(position)}"
            }
            override fun onNothingSelected(parent: AdapterView<*>){

            }
        }*/

    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.buttonCalc){
            calcular(v)
        } else if (v?.id == R.id.buttonReset){
            resetar()
        }
    }

    private fun calcular(v: View?) {
        val pesoField: TextView = findViewById(R.id.peso) as TextView
        val alturaField: TextView = findViewById(R.id.altura) as TextView
        val peso = pesoField.text.toString().toDouble()
        val altura = alturaField.text.toString().toDouble()
        val roundAltura = (altura/100)
        val mdAltura = roundAltura * roundAltura
        val imc = round(peso / mdAltura)

        findViewById<TextView>(R.id.result).apply {
            text = imc(imc)
        }

        findViewById<TextView>(R.id.result_calc).apply {
            text = imc.toString()
        }
    }

    private fun imc(i: Double): String {
        var resultado = ""

        if( i < 18.5){
            resultado = "Magreza"
        } else if (i > 18.5 && i <= 24.9 ){
            resultado = "Normal"
        } else if (i > 25 && i <= 29.9){
            resultado = "Sobrepeso"
        } else if (i > 30 && i <= 39.9 ){
            resultado = "Obesidade"
        } else {
            resultado = "Obesidade grave"
        }

        return resultado
    }

    private fun resetar(){
        findViewById<TextView>(R.id.result).apply {
            text = "IMC"
        }
        findViewById<TextView>(R.id.result_calc).apply {
            text = "--.-"
        }
        findViewById<TextView>(R.id.peso).apply {
            text = ""
        }
        findViewById<TextView>(R.id.altura).apply {
            text = ""
        }
    }
}
