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
import test.task.effectivemobile.main.MainViewModel
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
    val mainViewModel: MainViewModel = hiltViewModel()
    val settingsViewModel: SettingsViewModel = hiltViewModel()
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
        composable(ScreenState.SPLASH) {
            selectedLanguage?.let { it1 ->
                applySelectedLanguage(
                    activity = activity,
                    lang = it1
                )
            }
            SplashFeatureRoot(
                jobsToWait = remember {
                    emptyList()
                },
                isDarkMode = isDarkMode,
                deferredsToWait = remember {
                    listOf(
                        coroutineScope.async {
                            mainViewModel.isLoading.first { !it }
                        }
                    ) + deferredsToWait
                },
                onSuccess = {
                    coroutineScope.launch {
                        navController.navigate(ScreenState.MAIN) {
                            popUpTo(ScreenState.SPLASH) {
                                inclusive = true
                            }
                        }
                    }
                },
                onError = {
                    navController.navigate(ScreenState.MAIN) {
                        popUpTo(ScreenState.SPLASH) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(ScreenState.MAIN) {
            selectedLanguage?.let { it1 ->
                applySelectedLanguage(
                    activity = activity,
                    lang = it1
                )
            }
            MainFeatureRoot(
                vm = mainViewModel,
                isDarkMode = isDarkMode,
                onSettingsClicked = {
                    navController.navigate(ScreenState.SETTINGS)
                },
                onCharacterClicked = {
                    navController.navigate("${ScreenState.CHARACTER}/$it")
                }
            )
        }
        composable(
            route = "${ScreenState.CHARACTER}/{characterId}",
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId")?.toIntOrNull() ?: 1
            CharacterFeatureRoot(
                id = characterId,
                isDarkMode = isDarkMode,
                onBack = {
                    navController.navigateUp()
                }
            )
        }
        composable(ScreenState.SETTINGS) {
            selectedLanguage?.let { it1 ->
                applySelectedLanguage(
                    activity = activity,
                    lang = it1
                )
            }
            SettingsFeatureRoot(
                vm = settingsViewModel,
                isDarkMode = isDarkMode,
                onBack = {
                    navController.navigateUp()
                },
                onLangClicked = {
                    navController.navigate(ScreenState.LANGUAGE)
                }
            )
        }
        composable(ScreenState.LANGUAGE) {
            selectedLanguage?.let { it1 ->
                applySelectedLanguage(
                    activity = activity,
                    lang = it1
                )
            }
            LanguageFeatureRoot(
                isDarkMode = isDarkMode,
                onBack = {
                    navController.navigateUp()
                },
                onOkay = {
                    navController.navigate(ScreenState.MAIN) {
                        popUpTo(ScreenState.MAIN) {
                            inclusive = true
                        }
                    }
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
