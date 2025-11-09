package test.task.effectivemobile.login

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context
) : ViewModel(){
    private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
    val authState = _authState.asStateFlow()
    
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _isInputsValid = MutableStateFlow(false)
    val isInputsValid = _isInputsValid.asStateFlow()

    init {
        logInSaved()
    }

    private fun logInSaved() {
        viewModelScope.launch {
            // Имитация попытки входа
            delay(1000)
            _authState.value = AuthState.NeedAuth
        }
    }

    fun logInWithInputs() {
        viewModelScope.launch {
            // Имитация попытки входа
            _authState.value = AuthState.Loading
            delay(1000)
            _authState.value = AuthState.Success
        }
    }

    fun setEmail(email: String){
        val hasCyrillic = Regex("[А-Яа-яЁё]")
        if (hasCyrillic.containsMatchIn(email)) return
        _email.value = email
        validateInputs()
    }

    fun setPassword(password: String){
        _password.value = password
        validateInputs()
    }

    fun isEmailValid(email: String) : Boolean {
        return Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+.[A-Za-z0-9.-]+\$").matches(email)
    }

    fun isPasswordValid(password: String) : Boolean {
        return password.length >= 5
    }
    
    private fun validateInputs() {
        val emailValue = _email.value
        val passwordValue = _password.value

        _isInputsValid.value = (isEmailValid(emailValue) && isPasswordValid(passwordValue))
    }
    
    fun moveToVK(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com"))
        context.startActivity(intent)
    }

    fun moveToOdnokl(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ok.ru"))
        context.startActivity(intent)
    }
}