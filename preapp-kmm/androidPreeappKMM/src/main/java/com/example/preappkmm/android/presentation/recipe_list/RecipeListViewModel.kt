package com.example.preappkmm.android.presentation.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.preappkmm.domain.model.*
import com.example.preappkmm.domain.util.GenericMessageInfoQueueUtil
import com.example.preappkmm.domain.util.Queue
import com.example.preappkmm.interactors.recipe_list.RecipeListEvents
import com.example.preappkmm.interactors.recipe_list.SearchRecipes
import com.example.preappkmm.presentation.recipe_list.FoodCategory
import com.example.preappkmm.presentation.recipe_list.RecipeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchRecipes: SearchRecipes
): ViewModel() {

    val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())

    init {
        onTriggerEvent(RecipeListEvents.LoadRecipes)


        val messageInfoBuilder  = GenericMessageInfo.Builder()
            .id(UUID.randomUUID().toString())
            .title("XDDD")
            .uiComponentType(UIComponentType.Dialog)
            .description("HELO FROM THE OTHER side")
            .positive(
                PositiveAction(
                    positiveBtnTxt = "YOO",
                    onPositiveAction = {
                        state.value = state.value.copy(query = "Kale")
                        onTriggerEvent(RecipeListEvents.NewSearch)
                    }
                )
            )
            .negative(
                NegativeAction(
                    negativeBtnTxt = "cancel",
                    onNegativeAction = {
                        state.value = state.value.copy(query = "bob")
                        onTriggerEvent(RecipeListEvents.NewSearch)
                    }
                )
            )
        appendToMessageQueue(msgInfo = messageInfoBuilder)
    }

    fun onTriggerEvent(event: RecipeListEvents) {
        when(event){
            RecipeListEvents.LoadRecipes -> {
                loadRecipes()
            }
            RecipeListEvents.NextPage -> {
                nextPage()
            }
            RecipeListEvents.NewSearch -> {
                newSearch()
            }
            is RecipeListEvents.OnUpdateQuery -> {
                state.value = state.value.copy(query = event.query, selectedCategory = null)
            }
            is RecipeListEvents.OnSelectCategory -> {
                onSelectCategory(event.category)
            }
            RecipeListEvents.OnRemoveHeadMessageFromQueue -> {
                removeHeadMessage()
            }
            else -> {
                appendToMessageQueue(
                    GenericMessageInfo.Builder()
                        .id(UUID.randomUUID().toString())
                        .title("Error")
                        .uiComponentType(UIComponentType.Dialog)
                        .description("Dummy error")
                )
            }
        }
    }

    private fun onSelectCategory(category: FoodCategory) {
        state.value = state.value.copy(selectedCategory = category, query = category.value)
        newSearch()
    }

    private fun nextPage() {
        state.value = state.value.copy(page = state.value.page + 1)
        loadRecipes()
    }

    private fun newSearch() {
        state.value = state.value.copy(page = 1, recipes = listOf())
        loadRecipes()
    }

    private fun loadRecipes() {
        searchRecipes.execute(
            page = state.value.page,
            query = state.value.query
        ).onEach { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)


            dataState.data?.let { recipes ->
                println(recipes)
                appendRecipes(recipes)
            }

            dataState.message?.let { message ->
                appendToMessageQueue(message)
            }

        }.launchIn(viewModelScope)
    }

    private fun appendRecipes(recipes: List<Recipe>){
        val curr = ArrayList(state.value.recipes)
        curr.addAll(recipes)
        state.value = state.value.copy(recipes=curr)
    }

    private fun appendToMessageQueue(msgInfo: GenericMessageInfo.Builder) {
        if (!GenericMessageInfoQueueUtil().doesMessageAlreadyExistInQueue(
                queue = state.value.queue, messageInfo = msgInfo.build()
        )) {
            val queue = state.value.queue
            queue.add(msgInfo.build())
            state.value = state.value.copy(queue = queue)
        }
    }

    private fun removeHeadMessage() {
        try {
            val queue = state.value.queue
            queue.remove()
            state.value = state.value.copy(queue = Queue(mutableListOf())) //force recompose -> tricky xd
            state.value = state.value.copy(queue = queue)
        } catch (e: Exception) {
            // nothing to remove
        }
    }
}