package test.task.effectivemobile.account.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import test.task.effectivemobile.account.AccountViewModel
import test.task.effectivemobile.courses.Course
import test.task.effectivemobile.ui.composables.NavigationMenu
import test.task.effectivemobile.ui.themes.EffectiveMobileThemeManager

@Composable
fun AccountFeatureRoot(
    modifier: Modifier = Modifier,
    vm: AccountViewModel = hiltViewModel(),
    navigateToMain: () -> Unit,
    navigateToFavorites: () -> Unit,
    onCourseClick: (Course) -> Unit
) {
    val context = LocalContext.current
    val colorScheme by EffectiveMobileThemeManager.colorScheme.collectAsState()
    val iconScheme by EffectiveMobileThemeManager.iconScheme.collectAsState()
    val robotoFontFamily = EffectiveMobileThemeManager.RobotoFontFamily()

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = modifier
                .weight(1f)
                .background(colorResource(colorScheme.bgPrimary))
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }
        NavigationMenu(
            activeItem = 2,
            onSelect = {
                when(it) {
                    0 -> navigateToMain()
                    1 -> navigateToFavorites()
                }
            }
        )
    }
}