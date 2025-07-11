package data

import domain.ExpenseRepository
import model.Expense
import model.ExpenseCategory

class ExpenseRepoIml(private val expenseManager : ExpenseManager) : ExpenseRepository{
    override fun getAllExpenses(): List<Expense> {
       return expenseManager.fakeExpenseList
    }

    override fun addExpense(expense: Expense) {
        expenseManager.addExpenses(expense)
    }

    override fun editExpense(expense: Expense) {
        expenseManager.editExpense(expense)
    }

    override fun getCategories(): List<ExpenseCategory> {
      return expenseManager.getCategories()
    }

    override fun deleteExpense(expense: Expense) {
        TODO("Not yet implemented")
    }

}