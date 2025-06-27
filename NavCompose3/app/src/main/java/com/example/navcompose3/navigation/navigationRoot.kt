package com.example.navcompose3.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.DialogSceneStrategy
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.example.navcompose3.note.NoteDetailScreenUi
import com.example.navcompose3.note.NoteListScreenUi
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Serializable
data object NoteListScreen : NavKey

@Serializable
data class NoteDetailScreen(val id: Int) : NavKey


@Composable
fun NavigationRoot(
    modifier: Modifier
) {
    val backStack = rememberNavBackStack(NoteListScreen)
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        sceneStrategy = TwoPaySceneStrategy(),
        entryProvider = { key ->
            when (key) {
                is NoteListScreen -> {
                    NavEntry(
                        key = key,
                        metadata = TwoPayScene.twoPane()
                    ) {
                        NoteListScreenUi(
                            onNoteClick = { noteId ->
                                backStack.add(NoteDetailScreen(noteId))

                            }
                        )
                    }
                }
                is NoteDetailScreen -> {
                    NavEntry(
                        key = key,
                        metadata = TwoPayScene.twoPane()
                    ) {
                        NoteDetailScreenUi(
                            viewModel = koinViewModel{
                                parametersOf(key.id)
                            }
                        )
                    }
                }
                else -> throw Exception("god damn bro")
            }
        }
    )

}