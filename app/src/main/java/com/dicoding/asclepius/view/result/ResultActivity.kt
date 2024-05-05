package com.dicoding.asclepius.view.result

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import com.dicoding.asclepius.R
import com.dicoding.asclepius.data.local.PredictionHistory
import com.dicoding.asclepius.databinding.ActivityResultBinding
import com.dicoding.asclepius.helper.DateHelper
import com.dicoding.asclepius.helper.ViewModelFactory
import com.dicoding.asclepius.view.article.ArticleActivity
import com.dicoding.asclepius.view.history.PredictionHistoryActivity
import com.dicoding.asclepius.view.history.PredictionHistoryViewModel
import com.dicoding.asclepius.view.main.MainActivity

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private val predictionHistoryViewModel by viewModels<PredictionHistoryViewModel> {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUri = Uri.parse(intent.getStringExtra(EXTRA_IMAGE_URI))
        if (imageUri != null) {
            imageUri.let {
                Log.d("Image URI", "showImage: $it")
                binding.resultImage.setImageURI(it)
            }
        } else {
            Log.e("ResultActivity", "Image URI is null")
        }

        val result = intent.getStringExtra(EXTRA_RESULT)
        result?.let {
            binding.resultText.text = it
        }

        if (result != null) {
            val history = PredictionHistory(
                result = result,
                imageUri = imageUri.toString(),
                date = DateHelper.getCurrentDate()
            )
            predictionHistoryViewModel.insert(history)
            Toast.makeText(this, "Analyze saved", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_history -> {
                val intent = Intent(this@ResultActivity, PredictionHistoryActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_article -> {
                val intent = Intent(this@ResultActivity, ArticleActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
        const val EXTRA_RESULT = "extra_result"
    }
}