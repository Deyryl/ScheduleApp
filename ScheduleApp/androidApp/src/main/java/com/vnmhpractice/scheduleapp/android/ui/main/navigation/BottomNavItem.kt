package com.vnmhpractice.scheduleapp.android.ui.main.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.vnmhpractice.scheduleapp.android.R

data class BottomNavItem(
    val route:              String,
    @StringRes val title:   Int,
    @DrawableRes val icon:  Int
)

val bottomNavItems = listOf<BottomNavItem>(
    BottomNavItem(
        route   = "Search",
        title   = R.string.search,
        icon    = R.drawable.ic_search
    ),
    BottomNavItem(
        route   = "Schedule",
        title   = R.string.schedule,
        icon    = R.drawable.ic_schedule
    ),
    BottomNavItem(
        route   = "Calendar",
        title   = R.string.calendar,
        icon    = R.drawable.ic_calendar
    ),
    BottomNavItem(
        route   = "Menu",
        title   = R.string.menu,
        icon    = R.drawable.ic_menu
    )
)