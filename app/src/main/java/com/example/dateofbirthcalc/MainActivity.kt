package com.example.dateofbirthcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate : TextView? = null
    private var tvAgeInMinute : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinute = findViewById(R.id.AgeInMinute)
        btnDatePicker.setOnClickListener{
           clickDatePicker()
        }
    }
   private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            {view, selectedYear, selectedMonth, selectedDayOfMonth ->

                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate?.text = selectedDate
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                theDate?.let {  val selectedDateInMinutes = theDate!!.time / 60000
                    val curentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    val currentDateInMinutes = curentDate!!.time / 60000
                    val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                    tvAgeInMinute?.text = differenceInMinutes.toString()
                        }


            }
            , year
            , month
            , day)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }
}