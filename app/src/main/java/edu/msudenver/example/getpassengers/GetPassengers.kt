package edu.msudenver.example.getpassengers

//Cristian Gomez Cedillo - Project

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class GetPassengers : AppCompatActivity() {

    private val passList: MutableList<Passenger> = ArrayList()
    private lateinit var textPut: TextView
    private lateinit var textFirst: EditText
    private lateinit var textLast: EditText
    private lateinit var textPhone: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_passengers)

        textPut = findViewById(R.id.accum_list)
        textFirst = findViewById(R.id.first_name)
        textLast = findViewById(R.id.last_name)
        textPhone = findViewById(R.id.phone_number)

        findViewById<Button>(R.id.add_button).setOnClickListener {
            enterPassenger()
        }

        findViewById<Button>(R.id.back_to_main).setOnClickListener {
            backToMain()
        }
    }

    private fun enterPassenger() {
        val first = textFirst.text.toString().trim()
        val last = textLast.text.toString().trim()
        val phone = textPhone.text.toString().trim()

        if (first.isNotEmpty() && last.isNotEmpty() && phone.isNotEmpty()) {
            val newPass = Passenger(first, last, phone)
            passList.add(newPass) // Add passenger to the list

            // Ensure the text view has a newline after the separator before appending passengers
            if (!textPut.text.contains("\n-----------------------------\n")) {
                textPut.append("\n")  // Ensure a newline exists after the separator
            }

            textPut.append(newPass.toString() + "\n")  // Append new passenger below the separator

            // Clear input fields
            textFirst.text.clear()
            textLast.text.clear()
            textPhone.text.clear()
        }
    }



    private fun backToMain() {
        val intent = Intent()
        intent.putExtra("COUNT", passList.size.toString())

        for (i in passList.indices) {
            intent.putExtra("PASS${i + 1}", passList[i].toString())
        }

        setResult(RESULT_OK, intent)
        finish()
    }
}
