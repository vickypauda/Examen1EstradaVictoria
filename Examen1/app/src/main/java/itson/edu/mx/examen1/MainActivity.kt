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

    private lateinit var tvSubTotal: TextView
    private lateinit var tvIva: TextView
    private lateinit var tvTotal: TextView

    private var cantidad: Int = 0
    private var precio: Double = 0.0
    private var subTotal: Double = 0.0
    private val ivaRate = 0.16

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar componentes
        etCantidad = findViewById(R.id.tvCantidad)
        etProducto = findViewById(R.id.tvProducto)
        etPrecio = findViewById(R.id.tvPrecio)

        tvProducto1 = findViewById(R.id.tvProducto1)
        tvCantidad1 = findViewById(R.id.tvCantidad1)
        tvPrecio1 = findViewById(R.id.tvPrecio1)

        tvSubTotal = findViewById(R.id.tvSubTotal)
        tvIva = findViewById(R.id.tvIva)
        tvTotal = findViewById(R.id.tvTotal)

        // Agregar TextWatchers para actualizar en tiempo real
        etCantidad.addTextChangedListener(textWatcher)
        etProducto.addTextChangedListener(textWatcher)
        etPrecio.addTextChangedListener(textWatcher)
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            actualizarTabla()
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    private fun actualizarTabla() {
        val cantidadStr = etCantidad.text.toString()
        val precioStr = etPrecio.text.toString()
        val productoStr = etProducto.text.toString()

        cantidad = if (cantidadStr.isNotEmpty()) cantidadStr.toInt() else 0
        precio = if (precioStr.isNotEmpty()) precioStr.toDouble() else 0.0

        tvProducto1.text = productoStr
        tvCantidad1.text = cantidad.toString()
        tvPrecio1.text = String.format("%.2f", cantidad * precio)

        subTotal = cantidad * precio
        val iva = subTotal * ivaRate
        val total = subTotal + iva

        tvSubTotal.text = String.format("%.2f", subTotal)
        tvIva.text = String.format("%.2f", iva)
        tvTotal.text = String.format("%.2f", total)
    }
}
