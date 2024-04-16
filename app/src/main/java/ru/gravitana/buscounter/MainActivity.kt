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
                binding.messageTextView.setTextColor(getColorStateList(R.color.red))
                binding.messageTextView.text = getText(R.string.too_many_passengers)
            } else {
                counter++
                binding.messageTextView.setTextColor(getColorStateList(R.color.black))
                binding.messageTextView.text = getString(R.string.places_left, placesTotal - counter)
            }
            binding.counterTextView.text = counter.toString()
        }

        binding.minusButton.setOnClickListener {
            if (counter > 0) {
                counter--

                if (counter == 0) {
                    binding.messageTextView.setTextColor(getColorStateList(R.color.green))
                    binding.messageTextView.text = getText(R.string.all_seats_are_available)
                } else {
                    binding.messageTextView.setTextColor(getColorStateList(R.color.black))
                    binding.messageTextView.text = getString(R.string.places_left, placesTotal - counter)
                }
            }
            binding.counterTextView.text = counter.toString()
        }

        binding.resetButton.setOnClickListener {
            counter = 0

            binding.counterTextView.text = counter.toString()
            binding.messageTextView.setTextColor(getColorStateList(R.color.green))
            binding.messageTextView.text = getText(R.string.all_seats_are_available)
        }
    }
}