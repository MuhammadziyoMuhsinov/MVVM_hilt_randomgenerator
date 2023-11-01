package uz.muhsinov_dev.mohirdev_mvvm_randomgenerator

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.util.Random


class RandomNumberModel {
    fun getRandomNumber() = flow{
        emit(RandomNumberResponse.Loading(true))
        delay(1000)
        emit(RandomNumberResponse.Success(Random().nextInt(10000)))
    }
}