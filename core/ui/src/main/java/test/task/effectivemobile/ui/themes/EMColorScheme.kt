package test.task.effectivemobile.ui.themes

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import test.task.effectivemobile.ui.R

enum class EMColorScheme(
    @ColorRes val bgPrimary: Int,
    @ColorRes val textPrimary: Int,
    @ColorRes val loginBgTextField: Int,
    @ColorRes val textTextField: Int,
    @ColorRes val loginLogin: Int,
    @ColorRes val loginLoginDisabled: Int,
    @ColorRes val vkBackground: Int,
    @ColorRes val odnoklLbBackground: Int,
    @ColorRes val odnoklRtBackground: Int,
    @ColorRes val imgBackground: Int,
    @ColorRes val mainBgElement: Int,
    @ColorRes val mainTextSpecial: Int,
    @ColorRes val horizontalDivider: Int,
    @ColorRes val blurredBg: Int

) {
    DARK(
        bgPrimary = R.color.dark_bg_primary,
        textPrimary = R.color.dark_text_primary,
        loginBgTextField = R.color.dark_login_bg_textfield,
        textTextField = R.color.dark_text_textfield,
        loginLogin = R.color.dark_login_login,
        loginLoginDisabled = R.color.dark_login_login_disabled,
        vkBackground = R.color.dark_vk_background,
        odnoklLbBackground = R.color.dark_odnokl_lb_background,
        odnoklRtBackground = R.color.dark_odnokl_rt_background,
        imgBackground = R.color.dark_img_background,
        mainBgElement = R.color.dark_main_bg_element,
        mainTextSpecial = R.color.dark_main_text_special,
        horizontalDivider = R.color.dark_horizontal_divider,
        blurredBg = R.color.dark_blurred_glass
    )
}