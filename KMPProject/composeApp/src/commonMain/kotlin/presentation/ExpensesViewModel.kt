package presentation

import domain.ExpenseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.Expense
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModel
import moe.tlaster.precompose.viewmodel.viewModelScope



sealed class ExpensesUiState{
    object Loading: ExpensesUiState()

    data class  Success(val expenses : List<Expense>, val total:Double) : ExpensesUiState()

    data class Error(val message: String) : ExpensesUiState()

}

class ExpensesViewModel(private val repo: ExpenseRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<ExpensesUiState>(ExpensesUiState.Loading)
    val uiState = _uiState.asStateFlow()
    //private val allExpenses = repo.getAllExpenses()

    init {
        getAllExpensesList()
    }

    private fun getAllExpensesList(){
        viewModelScope.launch {
            try {
                val expenses = repo.getAllExpenses()
                _uiState.value = ExpensesUiState.Success(expenses, expenses.sumOf { it.amount })

            }catch (e: Exception){
                _uiState.value = ExpensesUiState.Error(e.message ?: "")
            }
        }
    }

    private  suspend fun updateExpensesList(){
        try {
            val expenses = repo.getAllExpenses()
            _uiState.value = ExpensesUiState.Success(expenses, expenses.sumOf { it.amount })

        } catch (e: Exception){
            _uiState.value = ExpensesUiState.Error(e.message ?: "")

        }
    }


//    private fun getAllExpenses() {
//        viewModelScope.launch {
//            updateState()
//        }
//    }

    fun addExpenses(expense: Expense) {
        viewModelScope.launch {
            try {
                repo.addExpense(expense)
                updateExpensesList()
            }catch (e:Exception){
                _uiState.value = ExpensesUiState.Error(e.message ?: "")

            }

        }
    }

    fun editExpense(expense: Expense) {
        viewModelScope.launch {
            try {
                repo.editExpense(expense)
                updateExpensesList()

            }catch (e:Exception){
                _uiState.value = ExpensesUiState.Error(e.message ?: "")

            }

        }
    }

    fun getExpenseWithId(id: Long): Expense? {
        return (_uiState.value as? ExpensesUiState.Success )?.expenses?.firstOrNull{
            it.id == id
        }
    }

}