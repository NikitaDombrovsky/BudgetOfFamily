package rustam.urazov.budgetoffamily.screen.signin

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import rustam.urazov.budgetoffamily.network.API
import rustam.urazov.budgetoffamily.repositories.TokenRepositoryImpl
import rustam.urazov.budgetoffamily.repositories.UserAuthorizationRepositoryImpl
import rustam.urazov.budgetoffamily.storage.TokenStorageServiceImpl
import rustam.urazov.budgetoffamily.usecases.SaveTokenUseCase
import rustam.urazov.budgetoffamily.usecases.UserAuthorizationUseCase

class SignInScreenFactory(context: Context): ViewModelProvider.Factory {

    private val dispatcher = Dispatchers.IO

    private val tokenStorageService by lazy {
        TokenStorageServiceImpl(context)
    }
    private val tokenRepository by lazy {
        TokenRepositoryImpl(tokenStorageService)
    }
    private val saveTokenUseCase by lazy {
        SaveTokenUseCase(tokenRepository)
    }

    private val service = API.mInstance.service
    private val userAuthorizationRepository by lazy {
        UserAuthorizationRepositoryImpl(service, dispatcher)
    }
    private val userAuthorizationUseCase by lazy {
        UserAuthorizationUseCase(userAuthorizationRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignInScreenViewModel(userAuthorizationUseCase, saveTokenUseCase) as T
    }
}