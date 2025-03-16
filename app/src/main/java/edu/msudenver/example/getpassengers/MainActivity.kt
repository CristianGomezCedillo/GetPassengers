package edu.msudenver.example.getpassengers

//Cristian Gomez Cedillo - Project

import android.os.Bundle
import android.app.Activity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private lateinit var listText: TextView

    private val startForResult =
        registerForActivityResult(androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val count = (data?.getStringExtra("COUNT") ?: "0").toInt()
                listText.text = "RETURNED PASSENGER LIST:\n"

                for (i in 1..count) {
                    listText.append((data?.getStringExtra("PASS$i") ?: "") + "\n")
                }
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listText = findViewById(R.id.show_list)
        val getListButton = findViewById<Button>(R.id.get_list_button)

        getListButton.setOnClickListener {
            getList()
        }
    }

    private fun getList() {
        listText.text = "RETURNED PASSENGER LIST:"
        val intent = Intent(this, GetPassengers::class.java)
        startForResult.launch(intent)
    }
}

data class Passenger(val fName: String, val lName: String, val phone: String) {
    override fun toString(): String {
        return "$fName $lName $phone"
    }
}