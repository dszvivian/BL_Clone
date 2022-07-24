package com.example.bl_clone

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bl_clone.Repositories.AuthRepository
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.IllegalArgumentException

class LoginViewModel(
    private val repository: AuthRepository = AuthRepository(),
) : ViewModel() {

    val currentUser = repository.currentUser

    var loginUiState by mutableStateOf(LoginUiState())

    val hasUser: Boolean
        get() = repository.hasUser()




    fun onUserNameChange(userName: String) {
        loginUiState = loginUiState.copy(userName = userName)
    }

    fun onPasswordChange(password: String) {
        loginUiState = loginUiState.copy(password = password)
    }

    fun onSignUpUserNameChange(userName: String) {
        loginUiState = loginUiState.copy(signUpUserName = userName)
    }

    fun onSignUpPasswordChange(password: String) {
        loginUiState = loginUiState.copy(signUpPassword = password)
    }

    fun onSignUpConfirmPasswordChange(password: String) {
        loginUiState = loginUiState.copy(confirmPassword = password)
    }


    private fun validateLoginForm() =
        loginUiState.userName.isNotBlank() &&
                loginUiState.password.isNotBlank()

    private fun validateSignUpForm() =
        loginUiState.signUpUserName.isNotBlank() &&
                loginUiState.signUpPassword.isNotBlank() &&
                loginUiState.confirmPassword.isNotBlank()


    fun createUser(context: Context) = viewModelScope.launch {

        /*Four checks before creating New User
            1. check if all the fields are filled
            2. set isLoading to true
            3. check if password and confirm Password match each other
            4. set SignUp Error to null
        */

        try {

            if (!validateSignUpForm()) {
                throw IllegalArgumentException("Some Fields are Empty")
            }
            loginUiState = loginUiState.copy(isLoading = true)


            if (loginUiState.signUpPassword != loginUiState.confirmPassword) {
                throw IllegalArgumentException("Passwords Don not Match")
            }

            loginUiState = loginUiState.copy(signUpError = null)

            repository.createUser(
                loginUiState.signUpUserName,
                loginUiState.signUpPassword
            ) { isSuccessful ->

                if (isSuccessful) {
                    Toast.makeText(context, "New user Added", Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = true)
                } else {
                    Toast.makeText(context, "Error Adding User", Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = false)
                }

            }

        } catch (e: Exception) {
            loginUiState = loginUiState.copy(signUpError = e.localizedMessage)
            e.printStackTrace()
        } finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }


    }

    fun loginUser(context: Context) = viewModelScope.launch {

        /*Four checks before creating New User
            1. check if all the fields are filled
            2. set isLoading to true
            3. set Login Error to null
        */

        try {

            if (!validateLoginForm()) {
                throw IllegalArgumentException("Some Fields are Empty")
            }

            loginUiState = loginUiState.copy(isLoading = true)

            loginUiState = loginUiState.copy(loginError = null)

            repository.loginUser(
                loginUiState.userName,
                loginUiState.password
            ) { isSuccessful ->

                if (isSuccessful) {
                    Toast.makeText(context, "New user Added", Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = true)
                } else {
                    Toast.makeText(context, "Error Adding User", Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = false)
                }

            }

        } catch (e: Exception) {
            loginUiState = loginUiState.copy(loginError = e.localizedMessage)
            e.printStackTrace()
        } finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }


    }



}

data class LoginUiState(
    var userName: String = "",
    var password: String = "",
    val signUpUserName: String = "",
    val signUpPassword: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val isSuccessLogin: Boolean = false,
    val loginError: String? = "",
    val signUpError: String? = "",
)