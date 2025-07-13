package navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import data.ExpenseManager
import data.ExpenseRepoIml
import getColorsTheme
import model.Expense
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import presentation.ExpensesViewModel
import ui.ExpensesScreen

@Composable
fun Navigation(navigator: Navigator) {

    val colors = getColorsTheme()
    val viewModel = koinViewModel(ExpensesViewModel::class){ org.koin.core.parameter.parametersOf()}

    NavHost(
        modifier = Modifier.background(color = colors.backgroundColor),
        navigator = navigator,
        initialRoute = "/home"
    ) {
        scene(route = "/home") {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ExpensesScreen(
                uiState = uiState,
            ) { expense: Expense ->
                navigator.navigate("/addExpenses/${expense.id}")
            }
        }

        scene( route = "/addExpenses/{id}"){
            val idFromPath = it.path<Long>("id")
            val isAddExpense = idFromPath?.let { id-> viewModel.getExpenseWithId(id)

                //

            }
        }

    }
}