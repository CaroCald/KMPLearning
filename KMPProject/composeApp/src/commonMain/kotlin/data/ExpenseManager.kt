package data

import model.Expense
import model.ExpenseCategory

object ExpenseManager {
    private var currentId = 1L

    val fakeExpenseList = mutableListOf(
        Expense(
            id = currentId++,
            amount = 23.0,
            category = ExpenseCategory.CAR,
            description = "Carro"
        ),
        Expense(
            id = currentId++,
            amount = 60.0,
            category = ExpenseCategory.GROCERIES,
            description = "Grocery"
        ),
        Expense(
            id = currentId++,
            amount = 45.5,
            category = ExpenseCategory.COFFEE,
            description = "Cafe"
        ),
        Expense(
            id = currentId++,
            amount = 23.0,
            category = ExpenseCategory.HOUSE,
            description = "Casa"
        ),
        Expense(
            id = currentId++,
            amount = 35.44,
            category = ExpenseCategory.PARTY,
            description = "Fiesta"
        ),
        Expense(
            id = currentId++,
            amount = 23.0,
            category = ExpenseCategory.SNACKS,
            description = "Snacks"
        )
        ,  Expense(
            id = currentId++,
            amount = 89.0,
            category = ExpenseCategory.OTHER,
            description = "Otros"
        )
    )

    fun addExpenses(expense: Expense){
        fakeExpenseList.add(expense.copy( id = currentId++))
    }
    fun editExpense(expense: Expense){
        val index = fakeExpenseList.indexOfFirst {
            fakeExpense-> fakeExpense.id == expense.id
        }
        if(index!=-1){
            fakeExpenseList[index] = fakeExpenseList[index].copy(
                amount = expense.amount,
                category = expense.category,
                description = expense.description
            )
        }
    }
    fun getCategories(): List<ExpenseCategory>{
        return listOf(
            ExpenseCategory.GROCERIES,
            ExpenseCategory.CAR,
            ExpenseCategory.COFFEE,
            ExpenseCategory.SNACKS,
            ExpenseCategory.HOUSE,
            ExpenseCategory.OTHER,
            ExpenseCategory.PARTY,
            )
    }
    fun deleteExpense(expense: Expense){
        val index = fakeExpenseList.indexOfFirst {
                fakeExpense-> fakeExpense.id == expense.id
        }
        if(index!=-1){
            fakeExpenseList.removeAt(index)
        }
    }
}

