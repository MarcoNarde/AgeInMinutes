package com.marconardelotto.ageinminuteskotlin

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {
            clickDatePicker(it)
        }
    }

    fun clickDatePicker(v: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, { view, year, month, dayOfMonth ->
            val dateSelected = "$dayOfMonth/${month+1}/$year"
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN)

            selectedDate.text = dateSelected

            val dateMinut = sdf.parse(dateSelected)!!.time / 60000

            val currentTime = sdf.parse(sdf.format(System.currentTimeMillis()))!!.time / 60000

            val finalTime = currentTime - dateMinut

            dateInMinutes.text = finalTime.toString()

        },year,month,dayOfMonth)


        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}