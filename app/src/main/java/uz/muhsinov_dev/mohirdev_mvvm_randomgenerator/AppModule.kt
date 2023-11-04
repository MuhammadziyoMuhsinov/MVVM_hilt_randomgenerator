package uz.muhsinov_dev.mohirdev_mvvm_randomgenerator

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getTestString()= "press button to generate new random number"

    @Singleton
    @Provides
    fun getRandomNumberModel() = RandomNumberModel()


}