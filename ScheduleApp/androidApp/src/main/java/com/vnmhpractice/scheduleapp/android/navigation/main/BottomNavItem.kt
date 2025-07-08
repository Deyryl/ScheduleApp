package com.vnmhpractice.scheduleapp.android.navigation.main

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
        route   = MainDestination.Search.name,
        title   = R.string.search,
        icon    = R.drawable.ic_search
    ),
    BottomNavItem(
        route   = MainDestination.Schedule.name,
        title   = R.string.schedule,
        icon    = R.drawable.ic_schedule
    ),
    BottomNavItem(
        route   = MainDestination.Calendar.name,
        title   = R.string.calendar,
        icon    = R.drawable.ic_calendar
    ),
    BottomNavItem(
        route   = MainDestination.Menu.name,
        title   = R.string.menu,
        icon    = R.drawable.ic_menu
    )
)