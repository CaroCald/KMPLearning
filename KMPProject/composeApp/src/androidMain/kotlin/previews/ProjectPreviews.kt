package previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import data.ExpenseManager
import model.Expense
import model.ExpenseCategory
import presentation.ExpensesUiState
import ui.ExpensableItem
import ui.ExpensesScreen
import ui.ExpensesTotalHeader

@Preview(showBackground = true)
@Composable
fun ExpensesTotalHeaderPreview() {
    ExpensesTotalHeader(total = 400.5)
}

@Preview(showBackground = true)
@Composable
fun ExpensesScreenPreview() {
    ExpensesScreen(
        uiState = ExpensesUiState(
            expensesList = ExpenseManager.fakeExpenseList,
            total = 485.3
        ),
        onExpenseClick = {})
}

@Preview(showBackground = true)
@Composable
fun ExpensableItemPreview() {
    ExpensableItem(
        expense =ExpenseManager.fakeExpenseList[0],
        onExpenseClick = {

        }
    )
}
