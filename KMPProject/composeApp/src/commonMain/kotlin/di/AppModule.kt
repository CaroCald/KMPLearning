package di

import com.example.Database
import data.ExpenseManager
import data.ExpenseRepoIml
import domain.ExpenseRepository
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module
import presentation.ExpensesViewModel

fun AppModule(appDatabase : Database) = module {
    single {
        ExpenseManager
    }.withOptions {
        createdAtStart()
    }

    single<ExpenseRepository> {
        ExpenseRepoIml(get(), appDatabase)
    }
    factory {
        ExpensesViewModel(get())
    }
}