package com.vnmhpractice.scheduleapp.android.navigation.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.vnmhpractice.scheduleapp.android.R

data class BottomNavItem(
    val route:              String,
    @StringRes val label:   Int,
    @DrawableRes val icon:  Int
)

val bottomNavItems = listOf<BottomNavItem>(
    BottomNavItem(
        route   = MainScreen.Search.name,
        label   = R.string.search,
        icon    = R.drawable.ic_search
    ),
    BottomNavItem(
        route   = MainScreen.Schedule.name,
        label   = R.string.schedule,
        icon    = R.drawable.ic_schedule
    ),
    BottomNavItem(
        route   = MainScreen.Calendar.name,
        label   = R.string.calendar,
        icon    = R.drawable.ic_calendar
    ),
    BottomNavItem(
        route   = MainScreen.Menu.name,
        label   = R.string.menu,
        icon    = R.drawable.ic_menu
    )
)