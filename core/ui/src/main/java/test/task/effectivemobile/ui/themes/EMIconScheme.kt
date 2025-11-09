package test.task.effectivemobile.ui.themes

import androidx.annotation.DrawableRes
import test.task.effectivemobile.ui.R

enum class EMIconScheme(
    @DrawableRes val iconActiveFavorite: Int,
    @DrawableRes val iconMiniFavoriteActive: Int,
    @DrawableRes val iconVerticalReverse: Int,
    @DrawableRes val iconBackArrow: Int,
    @DrawableRes val iconFavorite: Int,
    @DrawableRes val iconFavoriteMenuActive: Int,
    @DrawableRes val iconFavoriteMenuInactive: Int,
    @DrawableRes val iconFilters: Int,
    @DrawableRes val iconForwardMiniArrow: Int,
    @DrawableRes val iconHouseActive: Int,
    @DrawableRes val iconHouseInactive: Int,
    @DrawableRes val iconMiniFavorite: Int,
    @DrawableRes val iconOdnokl: Int,
    @DrawableRes val iconProfileActive: Int,
    @DrawableRes val iconProfileInactive: Int,
    @DrawableRes val iconSearch: Int,
    @DrawableRes val iconVk: Int,
    @DrawableRes val iconStar: Int
) {
    DARK(
        iconActiveFavorite = R.drawable.dark_active_favorite,
        iconMiniFavoriteActive = R.drawable.dark_active_mini_favorite,
        iconVerticalReverse = R.drawable.dark_arrows_vertical_reverse,
        iconBackArrow = R.drawable.dark_back_arrow,
        iconFavorite = R.drawable.dark_favorite,
        iconFavoriteMenuActive = R.drawable.dark_favorite_menu_active,
        iconFavoriteMenuInactive = R.drawable.dark_favorite_menu_inactive,
        iconFilters = R.drawable.dark_filters,
        iconForwardMiniArrow = R.drawable.dark_forward_mini_arrow,
        iconHouseActive = R.drawable.dark_home_active,
        iconHouseInactive = R.drawable.dark_home_inactive,
        iconMiniFavorite = R.drawable.dark_mini_favorite,
        iconOdnokl = R.drawable.dark_odnokl,
        iconProfileActive = R.drawable.dark_account_active,
        iconProfileInactive = R.drawable.dark_account_inactive,
        iconSearch = R.drawable.dark_search,
        iconVk = R.drawable.dark_vk,
        iconStar = R.drawable.dark_star
    )
}