package test.task.effectivemobile.favorites.ui

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import test.task.effectivemobile.courses.Course
import test.task.effectivemobile.favorites.FavoritesViewModel
import test.task.effectivemobile.ui.composables.NavigationMenu
import test.task.effectivemobile.ui.themes.EffectiveMobileThemeManager
import test.task.effectivemobile.ui.R
import test.task.effectivemobile.ui.composables.CourseCard

@Composable
fun FavoritesFeatureRoot(
    modifier: Modifier = Modifier,
    vm: FavoritesViewModel,
    navigateToMain: () -> Unit,
    navigateToAccount: () -> Unit,
    onCourseClick: (Course) -> Unit
) {
    val context = LocalContext.current
    val colorScheme by EffectiveMobileThemeManager.colorScheme.collectAsState()
    val iconScheme by EffectiveMobileThemeManager.iconScheme.collectAsState()
    val robotoFontFamily = EffectiveMobileThemeManager.RobotoFontFamily()

    val coursesList by vm.resultingCourses.collectAsState()
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
            Spacer(Modifier.height(16.dp))
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = stringResource(R.string.favorite),
                fontSize = 22.sp,
                lineHeight = 28.sp,
                color = colorResource(colorScheme.textPrimary),
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.W400
            )
            Spacer(Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(coursesList) { course ->
                    CourseCard(
                        imageUri = course.imageUri?.let{ Uri.parse(it) },
                        title = course.title,
                        text = course.text,
                        price = course.price,
                        rate = course.rate,
                        startDate = course.startDate,
                        hasLike = course.hasLike,
                        publishDate = course.publishDate,
                        onClick = {
                            onCourseClick(course)
                        },
                        onFavoriteClick = {
                            if (!course.hasLike) vm.addToFavorites(course.id) else vm.removeFromFavorites(course.id)
                        }
                    )
                }
                item{
                    Spacer(Modifier.height(16.dp))
                }
            }
        }
        NavigationMenu(
            activeItem = 1,
            onSelect = {
                when(it) {
                    0 -> navigateToMain()
                    2 -> navigateToAccount()
                }
            }
        )
    }
}