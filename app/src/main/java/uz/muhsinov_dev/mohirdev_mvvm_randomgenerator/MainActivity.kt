package uz.muhsinov_dev.mohirdev_mvvm_randomgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import uz.muhsinov_dev.mohirdev_mvvm_randomgenerator.databinding.ActivityMainBinding
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    private val viewModel : RandomNumberViewModel by viewModels ()

    @Inject
    lateinit var string:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.randomNumberTv.text = string

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