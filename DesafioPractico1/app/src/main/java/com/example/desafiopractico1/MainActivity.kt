package com.example.desafiopractico1

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etMontoTotal: EditText
    private lateinit var etNumeroPersonas: EditText
    private lateinit var rgPropina: RadioGroup
    private lateinit var rb10: RadioButton
    private lateinit var rb15: RadioButton
    private lateinit var rb20: RadioButton
    private lateinit var rbOtro: RadioButton
    private lateinit var etOtroPorcentaje: EditText
    private lateinit var swIva: Switch
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var tvResultados: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupListeners()
    }

    private fun initViews() {
        etMontoTotal = findViewById(R.id.etMontoTotal)
        etNumeroPersonas = findViewById(R.id.etNumeroPersonas)
        rgPropina = findViewById(R.id.rgPropina)
        rb10 = findViewById(R.id.rb10)
        rb15 = findViewById(R.id.rb15)
        rb20 = findViewById(R.id.rb20)
        rbOtro = findViewById(R.id.rbOtro)
        etOtroPorcentaje = findViewById(R.id.etOtroPorcentaje) // Inicializar el EditText
        swIva = findViewById(R.id.swIva)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        tvResultados = findViewById(R.id.tvResultados)
    }

    private fun setupListeners() {
        btnCalcular.setOnClickListener {
            calcularPropina()
        }

        btnLimpiar.setOnClickListener {
            limpiarCampos()
        }

        // Listener para mostrar/ocultar el campo de porcentaje personalizado
        rgPropina.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.rbOtro) {
                etOtroPorcentaje.visibility = android.view.View.VISIBLE
                etOtroPorcentaje.requestFocus()
            } else {
                etOtroPorcentaje.visibility = android.view.View.GONE
                etOtroPorcentaje.text.clear()
            }
        }
    }

    private fun calcularPropina() {
        try {
            // Verificar que los campos no estén vacíos
            val montoTexto = etMontoTotal.text.toString().trim()
            val personasTexto = etNumeroPersonas.text.toString().trim()

            if (montoTexto.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa el monto total", Toast.LENGTH_SHORT).show()
                return
            }

            if (personasTexto.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa el número de personas", Toast.LENGTH_SHORT).show()
                return
            }

            val montoTotal = montoTexto.toDouble()
            val numeroPersonas = personasTexto.toInt()

            if (montoTotal <= 0) {
                Toast.makeText(this, "El monto debe ser mayor a 0", Toast.LENGTH_SHORT).show()
                return
            }

            if (numeroPersonas <= 0) {
                Toast.makeText(this, "El número de personas debe ser mayor a 0", Toast.LENGTH_SHORT).show()
                return
            }

            // Verificar que se haya seleccionado una propina
            if (rgPropina.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Por favor selecciona un porcentaje de propina", Toast.LENGTH_SHORT).show()
                return
            }

            val porcentajePropina = when (rgPropina.checkedRadioButtonId) {
                R.id.rb10 -> 10.0
                R.id.rb15 -> 15.0
                R.id.rb20 -> 20.0
                R.id.rbOtro -> {
                    // Validar que se haya ingresado un porcentaje personalizado
                    val otroPorcentajeTexto = etOtroPorcentaje.text.toString().trim()
                    if (otroPorcentajeTexto.isEmpty()) {
                        Toast.makeText(this, "Por favor ingresa el porcentaje de propina personalizado", Toast.LENGTH_SHORT).show()
                        return
                    }
                    val porcentajePersonalizado = otroPorcentajeTexto.toDouble()
                    if (porcentajePersonalizado < 0 || porcentajePersonalizado > 100) {
                        Toast.makeText(this, "El porcentaje debe estar entre 0 y 100", Toast.LENGTH_SHORT).show()
                        return
                    }
                    porcentajePersonalizado
                }
                else -> 0.0
            }

            var subtotal = montoTotal
            var iva = 0.0

            if (swIva.isChecked) {
                iva = montoTotal * 0.16 // IVA del 16%
                subtotal = montoTotal + iva
            }

            val propina = subtotal * (porcentajePropina / 100)
            val totalConPropina = subtotal + propina
            val totalPorPersona = totalConPropina / numeroPersonas

            mostrarResultados(montoTotal, iva, propina, totalConPropina, totalPorPersona, numeroPersonas, porcentajePropina)

        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Por favor ingresa valores numéricos válidos", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Error en el cálculo: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun mostrarResultados(
        montoOriginal: Double,
        iva: Double,
        propina: Double,
        totalConPropina: Double,
        totalPorPersona: Double,
        numeroPersonas: Int,
        porcentajePropina: Double
    ) {
        val resultado = StringBuilder()

        resultado.append("Monto original: $${String.format("%.2f", montoOriginal)}\n")

        if (iva > 0) {
            resultado.append("IVA (16%): $${String.format("%.2f", iva)}\n")
            resultado.append("Subtotal con IVA: $${String.format("%.2f", montoOriginal + iva)}\n")
        }

        // Mostrar porcentaje con decimales si es necesario
        val porcentajeFormateado = if (porcentajePropina % 1.0 == 0.0) {
            porcentajePropina.toInt().toString()
        } else {
            String.format("%.1f", porcentajePropina)
        }

        resultado.append("Propina ($porcentajeFormateado%): $${String.format("%.2f", propina)}\n")
        resultado.append("Total a pagar: $${String.format("%.2f", totalConPropina)}\n\n")
        resultado.append("Total por persona ($numeroPersonas personas): $${String.format("%.2f", totalPorPersona)}\n")

        tvResultados.text = resultado.toString()
    }

    private fun limpiarCampos() {
        etMontoTotal.text.clear()
        etNumeroPersonas.text.clear()
        etOtroPorcentaje.text.clear()
        etOtroPorcentaje.visibility = android.view.View.GONE
        rgPropina.clearCheck()
        swIva.isChecked = false
        tvResultados.text = ""

        Toast.makeText(this, "Campos limpiados", Toast.LENGTH_SHORT).show()
    }
}