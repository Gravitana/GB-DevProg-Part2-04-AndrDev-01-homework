package ru.gravitana.buscounter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.gravitana.buscounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var counter = 0
    val placesTotal = 50

    @SuppressLint("StringFormatInvalid")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.plusButton.setOnClickListener {
            if (counter == placesTotal) {
                binding.resetButton.visibility = View.VISIBLE
                setMessage(binding, R.color.red, getString(R.string.too_many_passengers))
            } else {
                counter++
                setMessage(binding, R.color.black, getString(R.string.places_left, placesTotal - counter))
                binding.resetButton.visibility = View.INVISIBLE
            }
            binding.counterTextView.text = counter.toString()
        }

        binding.minusButton.setOnClickListener {
            if (counter > 0) {
                counter--

                if (counter < placesTotal) {
                    binding.resetButton.visibility = View.INVISIBLE
                }

                if (counter == 0) {
                    setMessage(binding, R.color.green, getString(R.string.all_seats_are_available))
                } else {
                    setMessage(binding, R.color.black, getString(R.string.places_left, placesTotal - counter))
                }
            }
            binding.counterTextView.text = counter.toString()
        }

        binding.resetButton.setOnClickListener {
            counter = 0

            setMessage(binding, R.color.green, getString(R.string.all_seats_are_available))
            binding.counterTextView.text = counter.toString()
            binding.resetButton.visibility = View.INVISIBLE
        }
    }

    private fun setMessage(binding: ActivityMainBinding, color: Int, text: String) {
        binding.messageTextView.setTextColor(getColorStateList(color))
        binding.messageTextView.text = text
    }
}