package com.example.navcompose3.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.Scene
import androidx.navigation3.ui.SceneStrategy
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_MEDIUM_LOWER_BOUND

class TwoPayScene<T : Any>(
    override val key: Any,
    override val previousEntries: List<NavEntry<T>>,
    val firstEntry: NavEntry<T>,
    val secondEntry: NavEntry<T>,
) : Scene<T> {
    override val entries: List<NavEntry<T>>
        get() = listOf(firstEntry, secondEntry)

    override val content: @Composable () -> Unit
        get() = {
            Row(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .weight(0.3f)
                ) {
                    firstEntry.Content()
                }
                Box(
                    modifier = Modifier
                        .weight(0.7f)
                ) {
                    secondEntry.Content()
                }
            }
        }

    companion object {
        const val TWO_PANE_KEY = "TwoPaneKey"
        fun twoPane() = mapOf(TWO_PANE_KEY to true)
    }
}

class TwoPaySceneStrategy<T : Any> : SceneStrategy<T> {
    @Composable
    override fun calculateScene(
        entries: List<NavEntry<T>>,
        onBack: (count: Int) -> Unit
    ): Scene<T>? {
        val windowClass = currentWindowAdaptiveInfo().windowSizeClass

        if (!windowClass.isWidthAtLeastBreakpoint(WIDTH_DP_MEDIUM_LOWER_BOUND))
            return null

        val lastTwoEntries = entries.takeLast(2)
        val hasTwoPaneKey = lastTwoEntries.all {
            it.metadata.containsKey(TwoPayScene.TWO_PANE_KEY) && it.metadata[TwoPayScene.TWO_PANE_KEY] == true
        }

        return if (lastTwoEntries.size == 2 && hasTwoPaneKey) {
            val firstEntry = lastTwoEntries.first()
            val secondEntry = lastTwoEntries.last()

            TwoPayScene(
                key = firstEntry.contentKey to secondEntry.contentKey,
                previousEntries = entries.dropLast(1),
                firstEntry,
                secondEntry
            )
        }
        else null
    }
}