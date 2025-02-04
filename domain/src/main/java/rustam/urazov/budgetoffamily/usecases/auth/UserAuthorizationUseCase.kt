package rustam.urazov.budgetoffamily.usecases.auth

import rustam.urazov.budgetoffamily.models.user.UserAuthData
import rustam.urazov.budgetoffamily.repositories.auth.UserAuthorizationRepository

class UserAuthorizationUseCase(private val userAuthorizationRepository: UserAuthorizationRepository) {

    suspend fun execute(userAuthData: UserAuthData) = userAuthorizationRepository.authorize(userAuthData)
}