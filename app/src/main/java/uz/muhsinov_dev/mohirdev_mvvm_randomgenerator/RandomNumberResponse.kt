package uz.muhsinov_dev.mohirdev_mvvm_randomgenerator

sealed class RandomNumberResponse{
    data class Loading(val status:Boolean):RandomNumberResponse()
    data class Success(val number:Int):RandomNumberResponse()
    data class Failure(val exception:String):RandomNumberResponse()
}
