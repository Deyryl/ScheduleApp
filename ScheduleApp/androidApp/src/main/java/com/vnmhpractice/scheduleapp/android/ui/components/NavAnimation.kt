package com.vnmhpractice.scheduleapp.android.ui.components

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.composableAnimated(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    isHierarchical: Boolean = false,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    val animationDuration = 250 // Уменьшено для более быстрого отклика
    composable(
        route = route,
        arguments = arguments,
        deepLinks = deepLinks,
        enterTransition = {
            if (isHierarchical) {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(animationDuration),
                    initialOffset = { it / 2 } // Плавный сдвиг
                ) + fadeIn(
                    animationSpec = tween(animationDuration)
                )
            } else {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(animationDuration),
                    initialOffset = { it }
                ) + fadeIn(
                    animationSpec = tween(animationDuration)
                )
            }
        },
        exitTransition = {
            if (isHierarchical) {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(animationDuration),
                    targetOffset = { it / 2 }
                ) + fadeOut(
                    animationSpec = tween(animationDuration),
                    targetAlpha = 0f // Полная прозрачность
                )
            } else {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(animationDuration),
                    targetOffset = { it }
                ) + fadeOut(
                    animationSpec = tween(animationDuration),
                    targetAlpha = 0f
                )
            }
        },
        popEnterTransition = {
            if (isHierarchical) {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(animationDuration),
                    initialOffset = { it / 2 }
                ) + fadeIn(
                    animationSpec = tween(animationDuration)
                )
            } else {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(animationDuration),
                    initialOffset = { it }
                ) + fadeIn(
                    animationSpec = tween(animationDuration)
                )
            }
        },
        popExitTransition = {
            if (isHierarchical) {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(animationDuration),
                    targetOffset = { it / 2 }
                ) + fadeOut(
                    animationSpec = tween(animationDuration),
                    targetAlpha = 0f
                )
            } else {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(animationDuration),
                    targetOffset = { it }
                ) + fadeOut(
                    animationSpec = tween(animationDuration),
                    targetAlpha = 0f
                )
            }
        },
        content = content
    )
}