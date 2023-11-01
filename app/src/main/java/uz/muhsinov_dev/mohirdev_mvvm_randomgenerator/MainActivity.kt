package uz.muhsinov_dev.mohirdev_mvvm_randomgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.muhsinov_dev.mohirdev_mvvm_randomgenerator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val viewModel : RandomNumberViewModel by viewModels ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenCreated {
            viewModel.flow.collectLatest {
                when(it){
                    is RandomNumberResponse.Failure -> TODO()
                    is RandomNumberResponse.Loading -> {
                        binding.progress.visibility = View.VISIBLE
                        binding.randomNumberTv.visibility = View.INVISIBLE
                    }
                    is RandomNumberResponse.Success -> {
                        binding.randomNumberTv.visibility = View.VISIBLE
                        binding.progress.visibility = View.INVISIBLE
                        binding.randomNumberTv.text = it.number.toString()
                    }
                }
            }
        }


        binding.generateRandomBtn.setOnClickListener {
           viewModel.getRandomNumber()
        }

    }
}