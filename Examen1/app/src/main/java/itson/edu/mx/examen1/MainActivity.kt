package itson.edu.mx.examen1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etCantidad: EditText
    private lateinit var etProducto: EditText
    private lateinit var etPrecio: EditText

    private lateinit var tvProducto1: TextView
    private lateinit var tvCantidad1: TextView
    private lateinit var tvPrecio1: TextView

    private lateinit var tvProducto2: TextView
    private lateinit var tvCantidad2: TextView
    private lateinit var tvPrecio2: TextView

    private lateinit var tvProducto3: TextView
    private lateinit var tvCantidad3: TextView
    private lateinit var tvPrecio3: TextView

    private lateinit var tvSubTotal: TextView
    private lateinit var tvIva: TextView
    private lateinit var tvTotal: TextView

    private val productos = mutableListOf<Triple<String, Int, Double>>()
    private val ivaRate = 0.16

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCantidad = findViewById(R.id.tvCantidad)
        etProducto = findViewById(R.id.tvProducto)
        etPrecio = findViewById(R.id.tvPrecio)

        tvProducto1 = findViewById(R.id.tvProducto1)
        tvCantidad1 = findViewById(R.id.tvCantidad1)
        tvPrecio1 = findViewById(R.id.tvPrecio1)

        tvProducto2 = findViewById(R.id.tvProducto2)
        tvCantidad2 = findViewById(R.id.tvCantidad2)
        tvPrecio2 = findViewById(R.id.tvPrecio2)

        tvProducto3 = findViewById(R.id.tvProducto3)
        tvCantidad3 = findViewById(R.id.tvCantidad3)
        tvPrecio3 = findViewById(R.id.tvPrecio3)

        tvSubTotal = findViewById(R.id.tvSubTotal)
        tvIva = findViewById(R.id.tvIva)
        tvTotal = findViewById(R.id.tvTotal)

        etCantidad.addTextChangedListener(textWatcher)
        etProducto.addTextChangedListener(textWatcher)
        etPrecio.addTextChangedListener(textWatcher)
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            agregarProducto()
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    private fun agregarProducto() {
        val cantidadStr = etCantidad.text.toString()
        val precioStr = etPrecio.text.toString()
        val productoStr = etProducto.text.toString()

        if (cantidadStr.isNotEmpty() && precioStr.isNotEmpty() && productoStr.isNotEmpty()) {
            val cantidad = cantidadStr.toInt()
            val precio = precioStr.toDouble()

            if (productos.size < 3) {
                productos.add(Triple(productoStr, cantidad, precio))
            } else {
                productos[0] = productos[1]
                productos[1] = productos[2]
                productos[2] = Triple(productoStr, cantidad, precio)
            }

            actualizarTabla()
        }
    }

    private fun actualizarTabla() {
        var subTotal = 0.0

        val filas = listOf(
            Triple(tvProducto1, tvCantidad1, tvPrecio1),
            Triple(tvProducto2, tvCantidad2, tvPrecio2),
            Triple(tvProducto3, tvCantidad3, tvPrecio3)
        )


        for (fila in filas) {
            fila.first.text = ""
            fila.second.text = ""
            fila.third.text = ""
        }

        for (i in productos.indices) {
            val (producto, cantidad, precio) = productos[i]
            filas[i].first.text = producto
            filas[i].second.text = cantidad.toString()
            filas[i].third.text = String.format("%.2f", cantidad * precio)

            subTotal += cantidad * precio
        }

        val iva = subTotal * ivaRate
        val total = subTotal + iva

        tvSubTotal.text = String.format("%.2f", subTotal)
        tvIva.text = String.format("%.2f", iva)
        tvTotal.text = String.format("%.2f", total)
    }
}
