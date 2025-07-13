package data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.Database

actual class DataBaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return  AndroidSqliteDriver(
            schema = Database.Schema,
            context =  context,
            name = "Database.db"
        )
    }

}