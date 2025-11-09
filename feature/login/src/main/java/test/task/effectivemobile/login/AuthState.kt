package test.task.effectivemobile.login

sealed class AuthState {
    object Loading : AuthState()
    object Success : AuthState()
    object NeedAuth : AuthState()
    data class Error(val message: String? = null) : AuthState()
}