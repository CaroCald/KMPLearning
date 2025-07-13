package data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.example.Database

actual class DataBaseDriverFactory {
    actual fun createDriver(): SqlDriver {

        return  NativeSqliteDriver(
            schema = Database.Schema,
            name = "Database.db"
        )

    }

}