package data

import com.example.Database
import domain.ExpenseRepository
import model.Expense
import model.ExpenseCategory

class ExpenseRepoIml(private val expenseManager : ExpenseManager, private val appDatabse: Database) : ExpenseRepository{

    private val queries = appDatabse.expensesDbQueries

    override fun getAllExpenses(): List<Expense> {
       return  queries.selectAll().executeAsList().map {
           Expense(
               id = it.id,
               amount = it.amount,
               category = ExpenseCategory.valueOf(it.categoryName),
               description = it.description
           )
       }
      // return expenseManager.fakeExpenseList
    }

    override fun addExpense(expense: Expense) {
       // expenseManager.addExpenses(expense)
        queries.transaction {
            queries.insert(
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            )
        }
    }

    override fun editExpense(expense: Expense) {
      //  expenseManager.editExpense(expense)
        queries.transaction {
            queries.update(
                id = expense.id,
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            )
        }
    }

    override fun getCategories(): List<ExpenseCategory> {
   //  return expenseManager.getCategories()
        return  queries.categories().executeAsList().map {
            ExpenseCategory.valueOf(it)
        }
    }

    override fun deleteExpense(expense: Expense) {
        TODO("Not yet implemented")
    }

}