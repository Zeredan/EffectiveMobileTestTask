package test.task.effectivemobile.testcourses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import test.task.effectivemobile.settings.usecases.UCGetAppThemeAsFlow
import test.task.effectivemobile.testcourses.ui.MainNavigationRoot
import test.task.effectivemobile.ui.themes.EMColorScheme
import test.task.effectivemobile.ui.themes.EMIconScheme
import test.task.effectivemobile.ui.themes.EMTheme
import test.task.effectivemobile.ui.themes.EffectiveMobileThemeManager
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var ucGetAppThemeAsFlow: UCGetAppThemeAsFlow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Привязываю тему из domain-слоя, чтобы всегда в :core:ui была актуальная и реактивная тема
        lifecycleScope.launch {
            ucGetAppThemeAsFlow().collect { appTheme ->
                EffectiveMobileThemeManager.colorScheme.value = when(appTheme){
                    EMTheme.DARK -> EMColorScheme.DARK
                }
                EffectiveMobileThemeManager.iconScheme.value = when(appTheme){
                    EMTheme.DARK -> EMIconScheme.DARK
                }
                EffectiveMobileThemeManager.isInitialized.value = true
            }
        }
        // Жду инициализацию темы, лишь потом привязываю контент
        lifecycleScope.launch {
            EffectiveMobileThemeManager.isInitialized.first { it }
            setContent {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainNavigationRoot(
                        modifier = Modifier.padding(innerPadding),
                        deferredsToWait = listOf()
                    )
                }
            }
        }
    }
}