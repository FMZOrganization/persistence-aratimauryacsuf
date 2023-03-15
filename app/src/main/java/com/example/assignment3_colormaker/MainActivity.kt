package com.example.assignment3_colormaker

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Switch
import androidx.annotation.RequiresApi

const val LOG_TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var colorView: View

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var switchRed: Switch
    private lateinit var seekBarRed: SeekBar
    private lateinit var editTextRed: EditText

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var switchGreen: Switch
    private lateinit var seekBarGreen: SeekBar
    private lateinit var editTextGreen: EditText

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var switchBlue: Switch
    private lateinit var seekBarBlue: SeekBar
    private lateinit var editTextBlue: EditText

    private lateinit var resetButton: Button


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @RequiresApi(Build.VERSION_CODES.S)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        colorView = findViewById(R.id.colorView)

        switchRed = findViewById(R.id.switchRed)
        seekBarRed = findViewById(R.id.seekBarRed)
        editTextRed = findViewById(R.id.editTextNumDecRed)

        switchGreen = findViewById(R.id.switchGreen)
        seekBarGreen = findViewById(R.id.seekBarGreen)
        editTextGreen = findViewById(R.id.editTextNumDecGreen)

        switchBlue = findViewById(R.id.switchBlue)
        seekBarBlue = findViewById(R.id.seekBarBlue)
        editTextBlue = findViewById(R.id.editTextNumDecBlue)

        resetButton = findViewById(R.id.buttonReset)

        switchRed.isChecked = true
        switchBlue.isChecked = true
        switchGreen.isChecked = true

//       resetButton.setBackgroundColor(Color.rgb(250,249,246))
        resetButton.setTextColor(Color.WHITE)

        resetButton.setOnClickListener { view: View ->
            val resetBtn = view as Button
            val txtResetBtn =resetBtn.text.toString()
            editTextRed.setText(R.string.editTextResetValue)
            editTextBlue.setText(R.string.editTextResetValue)
            editTextGreen.setText(R.string.editTextResetValue)

            seekBarRed.progress = 0
            seekBarGreen.progress = 0
            seekBarBlue.progress = 0

            switchRed.isChecked = true
            switchBlue.isChecked = true
            switchGreen.isChecked = true

            seekBarRed.isEnabled = true
            seekBarGreen.isEnabled = true
            seekBarBlue.isEnabled = true

            editTextRed.isEnabled = true
            editTextGreen.isEnabled = true
            editTextBlue.isEnabled = true

            Log.i(LOG_TAG, "button \"$txtResetBtn\" was called")
        }

        editTextGreen.setOnKeyListener { view, i, keyEvent ->
            Boolean
            val txtGreen = view as EditText

            seekBarGreen.progress =
                if (txtGreen.text.toString().isNotEmpty()) (txtGreen.text.toString()
                    .toFloat() * 100).toInt() else 0
            if (txtGreen.text.toString().isEmpty()) {
                editTextGreen.setText(((seekBarGreen.progress.div(100.00)).toString()))
            }

            Log.i(LOG_TAG, "Test key pressed $i $keyEvent")
            false

        }
        editTextRed.setOnKeyListener { view, i, keyEvent ->
            Boolean
            val txtRed = view as EditText
            seekBarRed.progress = if (txtRed.text.toString().isNotEmpty()) (txtRed.text.toString()
                .toFloat() * 100).toInt() else 0
            if (txtRed.text.toString().isEmpty()) {
                editTextRed.setText(((seekBarRed.progress.div(100.00)).toString()))
            }

            Log.i(LOG_TAG, txtRed.text.toString())
            Log.i(LOG_TAG, "Test key pressed $i $keyEvent")
            false

        }
        editTextBlue.setOnKeyListener { view, i, keyEvent ->
            Boolean
            val txtBlue = view as EditText
            seekBarBlue.progress =
                if (txtBlue.text.toString().isNotEmpty()) (txtBlue.text.toString()
                    .toFloat() * 100).toInt() else 0
            if (txtBlue.text.toString().isEmpty()) {
                editTextBlue.setText(((seekBarBlue.progress.div(100.00)).toString()))
            }
            Log.i(LOG_TAG, txtBlue.text.toString())
            Log.i(LOG_TAG, "Test key pressed $i $keyEvent")
            false

        }

        switchGreen.setOnClickListener {
            val swGreen: Switch = it as Switch
            if (!swGreen.isChecked) {
                Log.i(LOG_TAG, "in Switch Red ${swGreen.isChecked}")
                seekBarGreen.isEnabled = false
                editTextGreen.isEnabled = false
                val red = if (switchRed.isChecked) ((editTextRed.text.toString()
                    .toFloat()) * 255).toInt() else 0
                val green = 0
                val blue = if (switchBlue.isChecked) ((editTextGreen.text.toString()
                    .toFloat()) * 255).toInt() else 0

                colorView.setBackgroundColor(Color.rgb(red, green, blue))
            } else {
                val red = if (switchRed.isChecked) ((editTextRed.text.toString()
                    .toFloat()) * 255).toInt() else 0
                val green = ((editTextGreen.text.toString().toFloat()) * 255).toInt()
                val blue = if (switchBlue.isChecked) ((editTextGreen.text.toString()
                    .toFloat()) * 255).toInt() else 0
                seekBarGreen.isEnabled = true
                editTextGreen.isEnabled = true
                colorView.setBackgroundColor(Color.rgb(red, green, blue))
            }
        }

        switchBlue.setOnClickListener {
            val swBlue: Switch = it as Switch
            if (!swBlue.isChecked) {
                Log.i(LOG_TAG, "in Switch Red ${swBlue.isChecked}")
                seekBarBlue.isEnabled = false
                editTextBlue.isEnabled = false
                val red = if (switchRed.isChecked) ((editTextRed.text.toString()
                    .toFloat()) * 255).toInt() else 0
                val green = if (switchGreen.isChecked) ((editTextGreen.text.toString()
                    .toFloat()) * 255).toInt() else 0
                val blue = 0

                colorView.setBackgroundColor(Color.rgb(red, green, blue))
            } else {
                val red = if (switchRed.isChecked) ((editTextRed.text.toString()
                    .toFloat()) * 255).toInt() else 0
                val green = if (switchGreen.isChecked) ((editTextGreen.text.toString()
                    .toFloat()) * 255).toInt() else 0
                val blue = ((editTextBlue.text.toString().toFloat()) * 255).toInt()
                seekBarBlue.isEnabled = true
                editTextBlue.isEnabled = true
                colorView.setBackgroundColor(Color.rgb(red, green, blue))

            }
        }
        switchRed.setOnClickListener {
            val swRed: Switch = it as Switch
            if (!swRed.isChecked) {
                Log.i(LOG_TAG, "in Switch Red ${swRed.isChecked}")
                seekBarRed.isEnabled = false
                editTextRed.isEnabled = false
                val red = 0
                val green = if (switchGreen.isChecked) ((editTextGreen.text.toString()
                    .toFloat()) * 255).toInt() else 0
                val blue = if (switchBlue.isChecked) ((editTextGreen.text.toString()
                    .toFloat()) * 255).toInt() else 0

                colorView.setBackgroundColor(Color.rgb(red, green, blue))
            } else {
                val red = ((editTextRed.text.toString().toFloat()) * 255).toInt()
                val green = if (switchGreen.isChecked) ((editTextGreen.text.toString()
                    .toFloat()) * 255).toInt() else 0
                val blue = if (switchBlue.isChecked) ((editTextGreen.text.toString()
                    .toFloat()) * 255).toInt() else 0
                seekBarRed.isEnabled = true
                editTextRed.isEnabled = true
                colorView.setBackgroundColor(Color.rgb(red, green, blue))
            }
        }
        seekBarRed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(sb: SeekBar?, p1: Int, p2: Boolean) {
                val progressRed = (sb?.progress)
                if (progressRed != null) {
                    val seekBarProgressRed = progressRed.div(100.00)

                    editTextRed.setText((seekBarProgressRed.toString()))

                }
                val red = ((editTextRed.text.toString().toFloat()) * 255).toInt()
                val green = ((editTextGreen.text.toString().toFloat()) * 255).toInt()
                val blue = ((editTextGreen.text.toString().toFloat()) * 255).toInt()

                colorView.setBackgroundColor(Color.rgb(red, green, blue))

                Log.i(LOG_TAG, "SeekBar Red  $red")
                Log.i(LOG_TAG, "SeekBar green  $green")
                Log.i(LOG_TAG, "SeekBar blue  $blue")
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        seekBarGreen.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(sb: SeekBar?, p1: Int, p2: Boolean) {
                val progressGreen = (sb?.progress)
                if (progressGreen != null) {
                    val seekBarProgressGreen = progressGreen.div(100.00)

                    editTextGreen.setText((seekBarProgressGreen.toString()))


                }
                val red = ((editTextRed.text.toString().toFloat()) * 255).toInt()
                val green = ((editTextGreen.text.toString().toFloat()) * 255).toInt()
                val blue = ((editTextGreen.text.toString().toFloat()) * 255).toInt()

                colorView.setBackgroundColor(Color.rgb(red, green, blue))

                Log.i(LOG_TAG, "SeekBar Red  $red")
                Log.i(LOG_TAG, "SeekBar green  $green")
                Log.i(LOG_TAG, "SeekBar blue  $blue")
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        seekBarBlue.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(sb: SeekBar?, p1: Int, p2: Boolean) {
                val progressBlue = (sb?.progress)
                if (progressBlue != null) {
                    val seekBarProgressBlue = progressBlue.div(100.00)
                    editTextBlue.setText((seekBarProgressBlue.toString()))
                }

                val red = ((editTextRed.text.toString().toFloat()) * 255).toInt()
                val green = ((editTextGreen.text.toString().toFloat()) * 255).toInt()
                val blue = ((editTextGreen.text.toString().toFloat()) * 255).toInt()

                colorView.setBackgroundColor(Color.rgb(red, green, blue))

                Log.i(LOG_TAG, "SeekBar Red  $red")
                Log.i(LOG_TAG, "SeekBar green  $green")
                Log.i(LOG_TAG, "SeekBar blue  $blue")

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })


    }
}