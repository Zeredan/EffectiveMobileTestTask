package test.task.effectivemobile.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import test.task.effectivemobile.login.AuthState
import test.task.effectivemobile.login.LogInViewModel
import test.task.effectivemobile.ui.R
import test.task.effectivemobile.ui.composables.LoginInputTextField
import test.task.effectivemobile.ui.composables.ODNOKLButton
import test.task.effectivemobile.ui.composables.VKButton
import test.task.effectivemobile.ui.themes.EffectiveMobileThemeManager

@Composable
fun LogInFeatureRoot(
    modifier: Modifier = Modifier,
    vm: LogInViewModel,
    onLoggedIn: () -> Unit
) {
    val context = LocalContext.current
    val colorScheme by EffectiveMobileThemeManager.colorScheme.collectAsState()
    val iconScheme by EffectiveMobileThemeManager.iconScheme.collectAsState()
    val robotoFontFamily = EffectiveMobileThemeManager.RobotoFontFamily()

    val authState by vm.authState.collectAsState()
    val email by vm.email.collectAsState()
    val password by vm.password.collectAsState()
    val isInputValid by vm.isInputsValid.collectAsState()

    LaunchedEffect(authState) {
        if (authState is AuthState.Success) {
            onLoggedIn()
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(colorScheme.bgPrimary))
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (authState is AuthState.Loading) {
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(60.dp),
                    color = colorResource(R.color.dark_img_background),
                    strokeWidth = 3.dp,
                    strokeCap = StrokeCap.Round
                )
            }
        } else {
            Spacer(Modifier.weight(1f))
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = stringResource(R.string.log_in),
                fontSize = 28.sp,
                lineHeight = 38.sp,
                color = colorResource(colorScheme.textPrimary),
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.W400,
            )
            Spacer(Modifier.height(28.dp))
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = stringResource(R.string.email),
                fontSize = 15.sp,
                lineHeight = 18.sp,
                color = colorResource(colorScheme.textPrimary),
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.W500,
            )
            Spacer(Modifier.height(8.dp))
            LoginInputTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { vm.setEmail(it) },
                placeholder = stringResource(R.string.email_placeholder)
            )
            Spacer(Modifier.height(15.dp))
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = stringResource(R.string.password),
                fontSize = 15.sp,
                lineHeight = 18.sp,
                color = colorResource(colorScheme.textPrimary),
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.W500,
            )
            Spacer(Modifier.height(8.dp))
            LoginInputTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { vm.setPassword(it) },
                placeholder = stringResource(R.string.password_placeholder)
            )
            Spacer(Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(colorResource(if (isInputValid) colorScheme.loginLogin else colorScheme.loginLoginDisabled))
                    .run {
                        if (isInputValid) {
                            clickable {
                                vm.logInWithInputs()
                            }
                        } else {
                            this
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.log_in),
                    fontSize = 15.sp,
                    lineHeight = 18.sp,
                    color = colorResource(colorScheme.textPrimary),
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.W500
                )
            }
            Spacer(Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = stringResource(R.string.no_account),
                    fontSize = 12.sp,
                    lineHeight = 15.sp,
                    color = colorResource(colorScheme.textPrimary),
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.W600
                )
                Text(
                    text = stringResource(R.string.registration),
                    fontSize = 12.sp,
                    lineHeight = 15.sp,
                    color = colorResource(colorScheme.loginLogin),
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.W600
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.forgot_pwd),
                fontSize = 15.sp,
                lineHeight = 18.sp,
                color = colorResource(colorScheme.loginLogin),
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.W600
            )
            Spacer(Modifier.height(32.dp))
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = colorResource(colorScheme.horizontalDivider)
            )
            Spacer(Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                VKButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp),
                    onClick = {
                        vm.moveToVK(context)
                    }
                )
                Spacer(Modifier.width(16.dp))
                ODNOKLButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp),
                    onClick = {
                        vm.moveToOdnokl(context)
                    }
                )
            }
            Spacer(Modifier.weight(1f))
        }
    }
}