package data

import com.example.Database
import domain.ExpenseRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import model.Expense
import model.ExpenseCategory
import model.NetworkExpense

private val BASE_URL ="http://192.168.100.68:8080"

class ExpenseRepoIml(private val expenseManager : ExpenseManager,
                     private val appDatabse: Database,
    private val httpClient: HttpClient) : ExpenseRepository{

    private val queries = appDatabse.expensesDbQueries

    override suspend fun getAllExpenses(): List<Expense> {

        return if(queries.selectAll().executeAsList().isEmpty()){
            val networkResponse = httpClient.get("$BASE_URL/expenses").body<List<NetworkExpense>>()
           val expenses = networkResponse.map { networkResponse->Expense(
                id = networkResponse.id,
                amount = networkResponse.amount,
                category = ExpenseCategory.valueOf(networkResponse.categoryName),
                description = networkResponse.description
            ) }

            expenses.forEach {
                queries.insert(it.amount, it.category.name, it.description)
            }
            expenses
        }else {
            queries.selectAll().executeAsList().map {
           Expense(
               id = it.id,
               amount = it.amount,
               category = ExpenseCategory.valueOf(it.categoryName),
               description = it.description
           )
       }
        }

      // return expenseManager.fakeExpenseList
    }

    override suspend fun addExpense(expense: Expense) {
       // expenseManager.addExpenses(expense)
        queries.transaction {
            queries.insert(
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            )
        }
        httpClient.post("$BASE_URL/expenses"){
            contentType(ContentType.Application.Json)
            setBody(NetworkExpense(
                amount = expense.amount,
                categoryName =expense.category.name,
                description = expense.description
            ))
        }
    }

    override suspend fun editExpense(expense: Expense) {
      //  expenseManager.editExpense(expense)
        queries.transaction {
            queries.update(
                id = expense.id,
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            )
        }

        httpClient.post("$BASE_URL/expenses/${expense.id}"){
            contentType(ContentType.Application.Json)
            setBody(NetworkExpense(
                id = expense.id,
                amount = expense.amount,
                categoryName =expense.category.name,
                description = expense.description
            ))
        }
    }

    override fun getCategories(): List<ExpenseCategory> {
   //  return expenseManager.getCategories()
        return  queries.categories().executeAsList().map {
            ExpenseCategory.valueOf(it)
        }
    }

    override suspend fun deleteExpense(expense: Expense) {
        TODO("Not yet implemented")
    }

}