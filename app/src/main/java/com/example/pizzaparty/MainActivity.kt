package com.example.pizzaparty

import android.util.Log
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

const val TAG = "MainActivity"
private const val KEY_TOTAL_PIZZAS = "totalPizzas"

class MainActivity : AppCompatActivity() {

    private lateinit var numAttendEditText: EditText
    private lateinit var numPizzasTextView: TextView
    private lateinit var howHungryRadioGroup: RadioGroup
    private lateinit var calculateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate was called")
        numAttendEditText = findViewById(R.id.num_attend_edit_text)
        numPizzasTextView = findViewById(R.id.num_pizzas_text_view)
        numPizzasTextView.text = getString(R.string.total_pizzas, 0)
        howHungryRadioGroup = findViewById(R.id.hungry_radio_group)
        calculateButton = findViewById(R.id.calc_button)

        numAttendEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (p0.isNotEmpty()) {
                    calculateButton.callOnClick()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    /**
     * handler function for clicking on calculate button
     *
     * creates a PizzaCalculator object and sets the result at the new text of the
     * total pizzas textview.
     *
     * @param view of the project
     */
    fun calculateClick(view: View) {

        // Get the text that was typed into the EditText
        val numAttendStr = numAttendEditText.text.toString()

        // Convert the text into an integer
        val numAttend = numAttendStr.toIntOrNull() ?: 0

        // Get hunger level selection
        val hungerLevel = when (howHungryRadioGroup.getCheckedRadioButtonId()) {
            R.id.light_radio_button -> PizzaCalculator.HungerLevel.LIGHT
            R.id.medium_radio_button -> PizzaCalculator.HungerLevel.MEDIUM
            else -> PizzaCalculator.HungerLevel.RAVENOUS
        }

        // Get the number of pizzas needed
        val calc = PizzaCalculator(numAttend, hungerLevel)
        val totalPizzas = calc.totalPizzas

        // Place totalPizzas into the string resource and display
        val totalText = getString(R.string.total_pizzas, totalPizzas)
        numPizzasTextView.setText(totalText)
    }
}