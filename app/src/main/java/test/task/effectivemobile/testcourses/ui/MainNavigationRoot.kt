package test.task.effectivemobile.testcourses.ui

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import test.task.effectivemobile.login.AuthState
import test.task.effectivemobile.login.LogInViewModel
import test.task.effectivemobile.login.ui.LogInFeatureRoot
import test.task.effectivemobile.main.MainViewModel
import test.task.effectivemobile.favorites.FavoritesViewModel
import test.task.effectivemobile.account.AccountViewModel
import test.task.effectivemobile.main.ui.MainFeatureRoot
import test.task.effectivemobile.favorites.ui.FavoritesFeatureRoot
import test.task.effectivemobile.account.ui.AccountFeatureRoot
import test.task.effectivemobile.settings.SettingsViewModel
import test.task.effectivemobile.ui.themes.EffectiveMobileThemeManager
import java.util.Locale

@SuppressLint("NewApi", "ContextCastToActivity")
@Composable
fun MainNavigationRoot(
    modifier: Modifier = Modifier,
    deferredsToWait: List<Deferred<Any>>,
) {
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    // Создаю вьюмодели тут, а не внутри фичи - для предотвращения мерцания UI и пре-расчетов
    val mainViewModel: MainViewModel = hiltViewModel()
    val favoritesViewModel: FavoritesViewModel = hiltViewModel()
    val settingsViewModel: SettingsViewModel = hiltViewModel()
    val logInViewModel: LogInViewModel = hiltViewModel()
    val colorScheme by EffectiveMobileThemeManager.colorScheme.collectAsState()

    //val selectedLanguage by settingsViewModel.selectedLanguageStateFlow.collectAsState()
    val activity = LocalContext.current as ComponentActivity

    activity.window.navigationBarColor = colorResource(colorScheme.bgPrimary).toArgb()
    activity.window.statusBarColor = colorResource(colorScheme.bgPrimary).toArgb()
    NavHost(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(colorScheme.bgPrimary)),
        navController = navController,
        startDestination = ScreenState.SPLASH,
        enterTransition = {
            fadeIn(tween(0))
        },
        exitTransition = {
            fadeOut(tween(0))
        }
    ) {
        // не реализовываю сам экран, только функционал с выжиданием
        composable(ScreenState.SPLASH) {
            LaunchedEffect(1) {
                coroutineScope.launch {
                    deferredsToWait.forEach { it.await() }
                    logInViewModel.authState.first{ it !is AuthState.Loading }
                    navController.navigate(
                        if (logInViewModel.authState.value is AuthState.Success) ScreenState.MAIN else ScreenState.LOGIN
                    ) {
                        popUpTo(ScreenState.SPLASH) {
                            inclusive = true
                        }
                    }
                }
            }
        }
        composable(ScreenState.LOGIN){
            LogInFeatureRoot(
                vm = logInViewModel,
                onLoggedIn = {
                    navController.navigate(ScreenState.MAIN) {
                        popUpTo(ScreenState.MAIN) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(ScreenState.MAIN) {
            MainFeatureRoot(
                vm = mainViewModel,
                navigateToFavorite = {
                    navController.navigate(ScreenState.FAVORITES) {
                        popUpTo(ScreenState.MAIN)
                    }
                },
                navigateToAccount = {
                    navController.navigate(ScreenState.ACCOUNT) {
                        popUpTo(ScreenState.MAIN)
                    }
                },
                onCourseClick = { course ->

                }
            )
        }

        composable(ScreenState.FAVORITES) {
            FavoritesFeatureRoot(
                vm = favoritesViewModel,
                navigateToMain = {
                    navController.navigate(ScreenState.MAIN) {
                        popUpTo(ScreenState.MAIN) {
                            inclusive = true
                        }
                    }
                },
                navigateToAccount = {
                    navController.navigate(ScreenState.ACCOUNT) {
                        popUpTo(ScreenState.MAIN)
                    }
                },
                onCourseClick = { course ->

                }
            )
        }

        composable(ScreenState.ACCOUNT) {
            AccountFeatureRoot(
                navigateToMain = {
                    navController.navigate(ScreenState.MAIN) {
                        popUpTo(ScreenState.MAIN) {
                            inclusive = true
                        }
                    }
                },
                navigateToFavorites = {
                    navController.navigate(ScreenState.FAVORITES) {
                        popUpTo(ScreenState.MAIN)
                    }
                },
                onCourseClick = { course ->

                }
            )
        }
    }
}

fun applySelectedLanguage(
    activity: ComponentActivity,
    lang: String
) {
    with(activity) {
        println("QFASA: $lang | ${resources.configuration.locales[0]}")
        resources.apply {
            val locale = Locale(lang)
            val config = Configuration(configuration)

            createConfigurationContext(configuration)
            Locale.setDefault(locale)
            config.setLocale(locale)
            resources.updateConfiguration(config, displayMetrics)
        }
    }
}
