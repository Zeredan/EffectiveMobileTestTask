package test.task.effectivemobile.ui.composables

import android.net.Uri
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.primex.core.ExperimentalToolkitApi
import test.task.effectivemobile.ui.R
import test.task.effectivemobile.ui.themes.EffectiveMobileThemeManager

@OptIn(ExperimentalToolkitApi::class)
@Composable
fun CourseCard(
    modifier: Modifier = Modifier,
    imageUri: Uri?,
    title: String,
    text: String,
    price: String,
    rate: String,
    startDate: String,
    hasLike: Boolean,
    publishDate: String,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    val robotoFontFamily = EffectiveMobileThemeManager.RobotoFontFamily()
    val colorScheme by EffectiveMobileThemeManager.colorScheme.collectAsState()
    val iconScheme by EffectiveMobileThemeManager.iconScheme.collectAsState()
    Column(
        modifier = modifier
            .height(236.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(colorScheme.mainBgElement))
            .clickable { onClick() },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(114.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            if (imageUri != null) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    model = imageUri,
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.image_placeholder),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillWidth
                )
            }
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopEnd)
                    .size(28.dp)
                    .clip(CircleShape)
                    .clickable {
                        onFavoriteClick()
                    }
                    .background(colorResource(colorScheme.blurredBg)),
                    /*.legacyBackgroundBlur(
                        radius = 20f
                    ),*/
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(if (hasLike) iconScheme.iconMiniFavoriteActive else iconScheme.iconMiniFavorite),
                    contentDescription = null,
                    modifier = Modifier
                        .size(16.dp)
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .height(22.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(colorResource(colorScheme.blurredBg))
                        .padding(horizontal = 6.dp, vertical = 5.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(iconScheme.iconStar),
                        contentDescription = null,
                        modifier = Modifier
                            .size(12.dp)
                    )
                    Text(
                        text = rate,
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        color = colorResource(colorScheme.textPrimary),
                        fontFamily = robotoFontFamily,
                        fontWeight = FontWeight.W400,
                    )
                }
                Box(
                    modifier = Modifier
                        .height(22.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(colorResource(colorScheme.blurredBg))
                        .padding(horizontal = 6.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = publishDate,
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        color = colorResource(colorScheme.textPrimary),
                        fontFamily = robotoFontFamily,
                        fontWeight = FontWeight.W400,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .padding(16.dp, 0.dp, 16.dp, 16.dp)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                lineHeight = 18.sp,
                color = colorResource(colorScheme.textPrimary),
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.W500,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = text,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                color = colorResource(colorScheme.textTextField),
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.W500,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$price ₽",
                    fontSize = 16.sp,
                    lineHeight = 18.sp,
                    color = colorResource(colorScheme.textPrimary),
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.W500,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.details),
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        color = colorResource(colorScheme.mainTextSpecial),
                        fontFamily = robotoFontFamily,
                        fontWeight = FontWeight.W600,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Image(
                        modifier = Modifier
                            .size(16.dp),
                        painter = painterResource(iconScheme.iconForwardMiniArrow),
                        contentDescription = null
                    )
                }
            }
        }
    }
}