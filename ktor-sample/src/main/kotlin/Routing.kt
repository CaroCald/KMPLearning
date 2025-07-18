package com.example

import com.example.routes.expensesRouting
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        expensesRouting()
        // Static plugin. Try to access `/static/index.html`
        staticResources("/static", "static")
    }
}
