package curso.kotlin.motiovational.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import curso.kotlin.motiovational.infrastructure.Constants
import curso.kotlin.motiovational.R
import curso.kotlin.motiovational.data.Mock
import curso.kotlin.motiovational.infrastructure.SecurityPreferences
import curso.kotlin.motiovational.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = Constants.ICONS.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        handleUserName()
        handleImage(R.id.image_all)
        handlePhrases()

        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase) {
            handlePhrases()
        } else if (view.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_sunny)) {
            handleImage(view.id)
        }
    }

    private fun handlePhrases() {
        binding.textPhrases.text = Mock().getPhrases(categoryId)
    }

    private fun handleImage(id: Int) {

        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.pink))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.pink))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.pink))

        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = Constants.ICONS.ALL
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = Constants.ICONS.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = Constants.ICONS.SUNNY
            }
        }
    }

    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(Constants.KEY.USER_NAME)
        binding.textUserName.text = "Olá, $name!"
    }
}