package com.example.exercise2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.imageViewProfile)

        val btnCal = findViewById(R.id.buttonCalculate) as Button

        btnCal.setOnClickListener(){
            calculation(it)
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }

        val btnReset = findViewById(R.id.buttonReset) as Button

        btnReset.setOnClickListener(){
            reset(it)
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }


    }

    private fun calculation(view: View) {
        var weight = findViewById<EditText>(R.id.editTextWeight).text.toString()
        var height = findViewById<EditText>(R.id.editTextHeight).text.toString()


        var bmi = weight.toDouble() / ((height.toDouble() * height.toDouble()) / 10000)

        if(bmi < 18.5){
            imageView.setImageResource(R.drawable.under)
            textViewBMI.text = "%.2f. skinny ".format(bmi).toString()
        }
        else if(bmi in 18.5..24.9){
            imageView.setImageResource(R.drawable.normal)
            textViewBMI.text = "%.2f. Good job".format(bmi).toString()
        }
        else{
            imageView.setImageResource(R.drawable.over)
            textViewBMI.text = "%.2f. You fat,takecare yout body".format(bmi).toString()
        }

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun reset(view: View){
        imageView.setImageResource(R.drawable.empty)
        editTextHeight.getText().clear()
        editTextWeight.getText().clear()
        textViewBMI.text = "BMI :"
    }
}
