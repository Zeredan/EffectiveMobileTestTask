package test.task.effectivemobile.main.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import test.task.effectivemobile.courses.CoursesResult
import test.task.effectivemobile.main.MainViewModel
import test.task.effectivemobile.ui.composables.NavigationMenu
import test.task.effectivemobile.ui.themes.EffectiveMobileThemeManager
import test.task.effectivemobile.ui.R
import test.task.effectivemobile.ui.composables.RTR
import test.task.effectivemobile.ui.composables.SearchTextField
import java.net.URL

@Composable
fun MainFeatureRoot(
    modifier: Modifier = Modifier,
    vm: MainViewModel,
    navigateToFavorite: () -> Unit,
    navigateToAccount: () -> Unit,
) {
    val context = LocalContext.current
    val colorScheme by EffectiveMobileThemeManager.colorScheme.collectAsState()
    val iconScheme by EffectiveMobileThemeManager.iconScheme.collectAsState()
    val robotoFontFamily = EffectiveMobileThemeManager.RobotoFontFamily()

    val searchText by vm.searchText.collectAsState()
    val courses by vm.courses.collectAsState()
    val coursesList = when(courses) {
        null -> emptyList()
        is CoursesResult.Retrieved -> (courses as CoursesResult.Retrieved).courses
        is CoursesResult.Cached -> (courses as CoursesResult.Cached).courses
        is CoursesResult.Error -> emptyList()
    }
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
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SearchTextField(
                    modifier = Modifier
                        .weight(1f),
                    value = searchText,
                    onValueChange = vm::setSearchText,
                    placeholder = stringResource(R.string.search_courses_placeholder)
                )
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(colorResource(colorScheme.mainBgElement))
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(iconScheme.iconFilters),
                        contentDescription = null,
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .align(Alignment.End),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = stringResource(R.string.by_date),
                    fontSize = 15.sp,
                    color = colorResource(colorScheme.textTextField),
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.W500
                )
                Image(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(iconScheme.iconVerticalReverse),
                    contentDescription = null
                )
            }
            Spacer(Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(coursesList) { course ->
                    RTR(
                        imageURL = URL("https://game-tournaments.com/media/news/n24430.jpeg"),
                        title = course.title,
                        text = course.text,
                        price = course.price,
                        rate = course.rate,
                        startDate = course.startDate,
                        hasLike = course.hasLike,
                        publishDate = course.publishDate,
                        onClick = { 
                            
                        }
                    )
                }
            }
        }
        NavigationMenu(
            activeItem = 0,
            onSelect = {
                when(it) {
                    1 -> navigateToFavorite()
                    2 -> navigateToAccount()
                }
            }
        )
    }
}