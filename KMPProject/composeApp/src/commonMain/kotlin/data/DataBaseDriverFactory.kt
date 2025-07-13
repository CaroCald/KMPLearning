package data

import app.cash.sqldelight.db.SqlDriver

expect class DataBaseDriverFactory {

    fun createDriver(): SqlDriver
}