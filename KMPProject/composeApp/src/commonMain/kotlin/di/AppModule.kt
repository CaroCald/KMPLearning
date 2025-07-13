package di

import com.example.Database
import data.ExpenseManager
import data.ExpenseRepoIml
import domain.ExpenseRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module
import presentation.ExpensesViewModel

fun AppModule(appDatabase : Database) = module {

    single <HttpClient>{ HttpClient{
        install(ContentNegotiation) {json()}
    } }

    single {
        ExpenseManager
    }.withOptions {
        createdAtStart()
    }

    single<ExpenseRepository> {
        ExpenseRepoIml(get(), appDatabase, get())
    }
    factory {
        ExpensesViewModel(get())
    }
}