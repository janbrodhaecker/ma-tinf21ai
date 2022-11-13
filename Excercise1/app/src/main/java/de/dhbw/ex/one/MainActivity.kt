package de.dhbw.ex.one

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import de.dhbw.ex.one.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.model = Model(showMeme = false)
        setContentView(binding.root)

        binding.buttonShow.setOnClickListener {
            binding.model?.showMeme = !(binding.model?.showMeme)!!
            binding.invalidateAll()
        }

    }
}