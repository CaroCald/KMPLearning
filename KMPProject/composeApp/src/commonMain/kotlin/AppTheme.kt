import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import  androidx.compose.ui.unit.dp

@Composable
fun AppTheme( content: @Composable () -> Unit){
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(primary = Color.Black),
        shapes = MaterialTheme.shapes.copy(
            small = AbsoluteCutCornerShape(0.dp),
            medium = AbsoluteCutCornerShape(0.dp),
            large = AbsoluteCutCornerShape(0.dp),
            )
    ){
        content()
    }
}

@Composable
fun getColorsTheme() : DarkModeColors{
    val isDarkMode = false

    val purple = Color(0xDD6A66FF)
    val colorExpenseItem = if(isDarkMode) Color(0xFF09808) else Color(0xFFF1F1F1)
    val backgroundColor =  if(isDarkMode) Color(0xFF1E1C1C) else Color.White
    val textColor =  if(isDarkMode) Color.White else Color.Black
    val addIconColor =  if(isDarkMode) purple else Color.Black
    val colorArrowRound =  if(isDarkMode) purple else Color.Gray.copy(alpha = .2f)

    return DarkModeColors(
        purple = purple,
        colorExpenseItem = colorExpenseItem,
        backgroundColor = backgroundColor,
        textColor = textColor,
        addIconColor = addIconColor,
        colorArrowRound = colorArrowRound
    )
}

data class DarkModeColors(
    val purple : Color,
    val colorExpenseItem : Color,
    val backgroundColor : Color,
    val textColor : Color,
    val addIconColor : Color,
    val colorArrowRound : Color

)
