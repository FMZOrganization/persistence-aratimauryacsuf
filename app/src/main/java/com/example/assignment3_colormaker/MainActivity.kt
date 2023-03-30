package com.example.assignment3_colormaker

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider

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

    private fun connectViews() {
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

    }

    private fun loadViews() {

        resetButton.setTextColor(Color.WHITE)

        colorMakerModel.loadRedText()

        editTextRed.setText(colorMakerModel.getRedTextValue().toString())
        seekBarRed.progress =
            if (editTextRed.text.toString().isNotEmpty()) (editTextRed.text.toString()
                .toFloat() * 100).toInt() else 0

        editTextGreen.setText(colorMakerModel.getGreenTextValue().toString())
        seekBarGreen.progress =
            if (editTextGreen.text.toString().isNotEmpty()) (editTextGreen.text.toString()
                .toFloat() * 100).toInt() else 0


        editTextBlue.setText(colorMakerModel.getBlueTextValue().toString())
        seekBarBlue.progress =
            if (editTextBlue.text.toString().isNotEmpty()) (editTextBlue.text.toString()
                .toFloat() * 100).toInt() else 0

        switchRed.isChecked = colorMakerModel.getRedSwitchValue()
        switchGreen.isChecked = colorMakerModel.getGreenSwitchValue()
        switchBlue.isChecked = colorMakerModel.getBlueSwitchValue()

        if (!switchRed.isChecked) {
            seekBarRed.isEnabled = false
            editTextRed.isEnabled = false
        }

        if (!switchGreen.isChecked) {
            seekBarGreen.isEnabled = false
            editTextGreen.isEnabled = false
        }

        if (!switchBlue.isChecked) {
            seekBarBlue.isEnabled = false
            editTextBlue.isEnabled = false
        }


        val red = if (switchRed.isChecked) ((editTextRed.text.toString()
            .toFloat()) * 255).toInt() else 0

        val green = if (switchGreen.isChecked) ((editTextGreen.text.toString()
            .toFloat()) * 255).toInt() else 0

        val blue = if (switchBlue.isChecked) ((editTextBlue.text.toString()
            .toFloat()) * 255).toInt() else 0



        Log.d(LOG_TAG, "colorView colors red: $red green: $green blue: $blue")

        colorView.setBackgroundColor(Color.rgb(red, green, blue))


    }


    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(LOG_TAG, "onCreate called")
        this.connectViews()
        this.loadViews()
        this.setUpEditTextListeners()
        this.setUpSwitchBarListeners()
        this.setUpSeekBarListeners()
        this.resetButtonListener()

    }

    private fun setUpEditTextListeners() {
        editTextGreen.setOnKeyListener { view, i, keyEvent ->
            Boolean
            val txtGreen = view as EditText
            val txtValidation = if (txtGreen.text.toString().isNotEmpty()) (this.validateInput(
                txtGreen.text.toString()
            )) else true
            Log.i(LOG_TAG, "In editGreen text Listener input text validation value $txtValidation")
            if (txtValidation) {
                seekBarGreen.progress =
                    if (txtGreen.text.toString().isNotEmpty()) (txtGreen.text.toString()
                        .toFloat() * 100).toInt() else 0
                if (txtGreen.text.toString().isEmpty()) {
                    editTextGreen.setText(((seekBarGreen.progress.div(100.00)).toString()))
                }
                colorMakerModel.setGreenTextValue(editTextGreen.text.toString().toFloat())
                Log.i(LOG_TAG, "Test key pressed $i $keyEvent")
            } else {
                Toast.makeText(this, "Enter number between 0 - 1", Toast.LENGTH_LONG).show()
                editTextGreen.setText(((seekBarGreen.progress.div(100.00)).toString()))

            }
            false

        }
        editTextRed.setOnKeyListener { view, i, keyEvent ->
            Boolean
            val txtRed = view as EditText
            val txtValidation = if (txtRed.text.toString()
                    .isNotEmpty()
            ) (this.validateInput(txtRed.text.toString())) else true
            if (txtValidation) {

                seekBarRed.progress =
                    if (txtRed.text.toString().isNotEmpty()) (txtRed.text.toString()
                        .toFloat() * 100).toInt() else 0
                if (txtRed.text.toString().isEmpty()) {
                    editTextRed.setText(((seekBarRed.progress.div(100.00)).toString()))
                }
                colorMakerModel.setRedTextValue(editTextRed.text.toString().toFloat())

                Log.i(LOG_TAG, txtRed.text.toString())
                Log.i(LOG_TAG, "Test key pressed $i $keyEvent")

            } else {
                Toast.makeText(this, "Enter number between 0 - 1", Toast.LENGTH_LONG).show()
                editTextRed.setText(((seekBarRed.progress.div(100.00)).toString()))

            }

            false


        }
        editTextBlue.setOnKeyListener { view, i, keyEvent ->
            Boolean
            val txtBlue = view as EditText
            val txtValidation = if (txtBlue.text.toString().isNotEmpty()) (this.validateInput(
                txtBlue.text.toString()
            )) else true

            if (txtValidation) {
                seekBarBlue.progress =
                    if (txtBlue.text.toString().isNotEmpty()) (txtBlue.text.toString()
                        .toFloat() * 100).toInt() else 0
                if (txtBlue.text.toString().isEmpty()) {
                    editTextBlue.setText(((seekBarBlue.progress.div(100.00)).toString()))
                }
                colorMakerModel.setBlueTextValue(editTextBlue.text.toString().toFloat())
                Log.i(LOG_TAG, txtBlue.text.toString())
                Log.i(LOG_TAG, "Test key pressed $i $keyEvent")
            } else {
                Toast.makeText(this, "Enter number between 0 - 1", Toast.LENGTH_LONG).show()
                editTextBlue.setText(((seekBarBlue.progress.div(100.00)).toString()))

            }
            false

        }

    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private fun setUpSwitchBarListeners() {
        switchGreen.setOnClickListener {
            val swGreen: Switch = it as Switch
            if (!swGreen.isChecked) {
                Log.i(LOG_TAG, "in Switch Red ${swGreen.isChecked}")
                seekBarGreen.isEnabled = false
                editTextGreen.isEnabled = false
                val red = if (switchRed.isChecked) ((editTextRed.text.toString()
                    .toFloat()) * 255).toInt() else 0
                val green = 0
                val blue = if (switchBlue.isChecked) ((editTextBlue.text.toString()
                    .toFloat()) * 255).toInt() else 0

                colorMakerModel.setGreenSwitchValue(swGreen.isChecked)

                colorView.setBackgroundColor(Color.rgb(red, green, blue))
            } else {
                val red = if (switchRed.isChecked) ((editTextRed.text.toString()
                    .toFloat()) * 255).toInt() else 0
                val green = ((editTextGreen.text.toString().toFloat()) * 255).toInt()
                val blue = if (switchBlue.isChecked) ((editTextBlue.text.toString()
                    .toFloat()) * 255).toInt() else 0
                seekBarGreen.isEnabled = true
                editTextGreen.isEnabled = true

                colorMakerModel.setGreenSwitchValue(swGreen.isChecked)
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

                colorMakerModel.setBlueSwitchValue(swBlue.isChecked)
                colorView.setBackgroundColor(Color.rgb(red, green, blue))
            } else {
                val red = if (switchRed.isChecked) ((editTextRed.text.toString()
                    .toFloat()) * 255).toInt() else 0
                val green = if (switchGreen.isChecked) ((editTextGreen.text.toString()
                    .toFloat()) * 255).toInt() else 0
                val blue = ((editTextBlue.text.toString().toFloat()) * 255).toInt()
                seekBarBlue.isEnabled = true
                editTextBlue.isEnabled = true

                colorMakerModel.setBlueSwitchValue(swBlue.isChecked)
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
                val blue = if (switchBlue.isChecked) ((editTextBlue.text.toString()
                    .toFloat()) * 255).toInt() else 0

                colorMakerModel.setRedSwitchValue(swRed.isChecked)
                colorView.setBackgroundColor(Color.rgb(red, green, blue))
            } else {
                val red = ((editTextRed.text.toString().toFloat()) * 255).toInt()
                val green = if (switchGreen.isChecked) ((editTextGreen.text.toString()
                    .toFloat()) * 255).toInt() else 0
                val blue = if (switchBlue.isChecked) ((editTextBlue.text.toString()
                    .toFloat()) * 255).toInt() else 0
                seekBarRed.isEnabled = true
                editTextRed.isEnabled = true
                colorMakerModel.setRedSwitchValue(swRed.isChecked)

                colorView.setBackgroundColor(Color.rgb(red, green, blue))
            }
        }
    }

    private fun setUpSeekBarListeners() {
        seekBarRed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(sb: SeekBar?, p1: Int, p2: Boolean) {
                val progressRed = (sb?.progress)
                if (progressRed != null) {
                    val seekBarProgressRed = progressRed.div(100.00)

                    editTextRed.setText((seekBarProgressRed.toString()))
                    colorMakerModel.setRedTextValue(editTextRed.text.toString().toFloat())

                    Log.i(LOG_TAG, "seekBarRed change: saveRedText called -  $editTextRed")
                }
                val red = ((editTextRed.text.toString().toFloat()) * 255).toInt()
                val green = ((editTextGreen.text.toString().toFloat()) * 255).toInt()
                val blue = ((editTextBlue.text.toString().toFloat()) * 255).toInt()

                colorView.setBackgroundColor(Color.rgb(red, green, blue))

                Log.i(LOG_TAG, "seekBarRed change: Red  $red")
                Log.i(LOG_TAG, "seekBarRed change: green  $green")
                Log.i(LOG_TAG, "seekBarRed change: blue  $blue")
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

                    colorMakerModel.setGreenTextValue(editTextGreen.text.toString().toFloat())
                }
                val red = ((editTextRed.text.toString().toFloat()) * 255).toInt()
                val green = ((editTextGreen.text.toString().toFloat()) * 255).toInt()
                val blue = ((editTextBlue.text.toString().toFloat()) * 255).toInt()

                colorView.setBackgroundColor(Color.rgb(red, green, blue))

                Log.i(LOG_TAG, "seekBarGreen change: Red  $red")
                Log.i(LOG_TAG, "seekBarGreen change: green  $green")
                Log.i(LOG_TAG, "seekBarGreen change: blue  $blue")
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

                    colorMakerModel.setBlueTextValue(editTextBlue.text.toString().toFloat())
                }

                val red = ((editTextRed.text.toString().toFloat()) * 255).toInt()
                val green = ((editTextGreen.text.toString().toFloat()) * 255).toInt()
                val blue = ((editTextBlue.text.toString().toFloat()) * 255).toInt()

                colorView.setBackgroundColor(Color.rgb(red, green, blue))

                Log.i(LOG_TAG, "seekBarBlue change: Red  $red")
                Log.i(LOG_TAG, "seekBarBlue change: green  $green")
                Log.i(LOG_TAG, "seekBarBlue change: blue  $blue")

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

    }

    private fun resetButtonListener() {
        resetButton.setOnClickListener { view: View ->
            val resetBtn = view as Button
            val txtResetBtn = resetBtn.text.toString()
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
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy called")

    }

    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "onStart called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG, "onStop called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "onPause called")
    }

    private val colorMakerModel: ColorMakerViewModel by lazy {
        ColorMakerPreferencesDataStore.initialize(this)
        ViewModelProvider(this)[ColorMakerViewModel::class.java]
    }

    private fun validateInput(editTextValue: String): Boolean {

        if (editTextValue == ".") {
            return false
        }
        if ((editTextValue.toFloat() < 0.00) or (editTextValue.toFloat() > 1.00)) {
            return false
        }
        return true
    }
}
