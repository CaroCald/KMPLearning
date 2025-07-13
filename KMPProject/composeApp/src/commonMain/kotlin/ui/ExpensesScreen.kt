package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.ExpenseManager
import getColorsTheme
import model.Expense
import presentation.ExpensesUiState
import kotlin.math.exp

@Composable
fun ExpensesScreen(
    uiState: ExpensesUiState,
    onExpenseClick: (expense: Expense) -> Unit
) {

    val colors = getColorsTheme()

    when(uiState){
        is ExpensesUiState.Loading->{
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){

               CircularProgressIndicator()
            }
        }
        is ExpensesUiState.Success->{
            LazyColumn(
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 16.dp
                ), verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                stickyHeader {
                    Column(
                        modifier = Modifier.background(color = colors.backgroundColor)
                    ) {
                        ExpensesTotalHeader(uiState.total)
                        AllExpenses()
                    }
                }

                items(uiState.expenses)
                { item: Expense ->
                    ExpensableItem(
                        expense = item,
                        onExpenseClick = onExpenseClick
                    )
                }
            }
        }
        is ExpensesUiState.Error->{
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){

                Text(
                    text = "Error ${uiState.message}"
                )
            }
        }
    }


}

@Composable
fun ExpensesTotalHeader(total: Double) {
    Card(
        shape = RoundedCornerShape(30),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors().copy(
            containerColor = Color.Black
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth().height(130.dp).padding(16.dp)
        ) {
            Text(
                text = "$$total",
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                fontSize = 30.sp

            )
            Text(
                modifier = Modifier.align(Alignment.CenterEnd),
                text = "USD",
                color = Color.Gray

            )
        }
    }
}

@Composable
fun AllExpenses() {
    val color = getColorsTheme()

    Row(
        modifier = Modifier.padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "All expenses",
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            color = color.textColor
        )
        Button(
            colors = ButtonDefaults.buttonColors(
            ).copy(
                containerColor = Color.LightGray
            ),
            enabled = false,
            shape = RoundedCornerShape(50),
            onClick = {
//click
            }
        ) {
            Text("View All")
        }
    }
}

@Composable
fun ExpensableItem(expense: Expense, onExpenseClick: (expense: Expense) -> Unit) {

    val colors = getColorsTheme()

    Card(
        modifier = Modifier.fillMaxWidth().padding(2.dp).clickable {
            onExpenseClick(expense)
        },
        colors = CardDefaults.cardColors().copy(
            containerColor = colors.colorExpenseItem
        ),
        shape = RoundedCornerShape(30)
    ) {

        Row(
            modifier = Modifier.padding(
                horizontal = 8.dp,
                vertical = 16.dp
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Surface(
                modifier = Modifier.size(50.dp),
                shape = RoundedCornerShape(50),
                color = colors.purple
            ) {
                Image(
                    modifier = Modifier.padding(10.dp),
                    imageVector = expense.icon,
                    colorFilter = ColorFilter.tint(Color.White),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Icono"
                )
            }
            Column(
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            ) {
                Text(
                    text = expense.category.name,
                    fontWeight = FontWeight.ExtraBold,
                    color = colors.textColor,
                    fontSize = 18.sp
                )

                Text(
                    text = expense.description,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Gray,
                    fontSize = 18.sp
                )

            }

            Text(
                text = "$${expense.amount}",
                fontWeight = FontWeight.ExtraBold,
                color = colors.textColor,
                fontSize = 20.sp
            )
        }
    }
}