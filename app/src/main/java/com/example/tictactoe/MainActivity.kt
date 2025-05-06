package com.example.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TicTacToe()
                }
            }
        }
    }
}

@Composable
fun TicTacToe() {
    val grid = Array(3) { Array(3) { remember { mutableStateOf("") } } }
    val player = remember { mutableStateOf("X") }
    val winner = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        for (rowNum in 0..2) {
            TicTacToeRow(rowNum, grid, player, winner)
        }
    }
}

@Composable
private fun TicTacToeRow(
    rowNum: Int,
    grid: Array<Array<MutableState<String>>>,
    player: MutableState<String>,
    winner: MutableState<String>
) {
    Row {
        for (colNum in 0..2) {
            TicTacToeCell(rowNum, colNum, grid, player, winner)
        }
    }
}

@Composable
private fun TicTacToeCell(
    rowNum: Int,
    colNum: Int,
    grid: Array<Array<MutableState<String>>>,
    player: MutableState<String>,
    winner: MutableState<String>
) {
    val buttonColors = ButtonColors(
        containerColor = Color.Black,
        contentColor = Color.Green,
        disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor,
        disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor
    )

    val modifier = Modifier
        .padding(2.dp)
        .height(50.dp)
        .width(50.dp)

    val isEnabled = remember { mutableStateOf(true)}

    Button(
        onClick = {
            isEnabled.value = false
            grid[rowNum][colNum].value = player.value
            if (player.value == "X"){
                player.value = "O"
            }else{
                player.value = "X"
            }
        },
        colors = buttonColors,
        shape = RectangleShape,
        border = BorderStroke(1.dp, Color.Blue),
        modifier = modifier,
        enabled = isEnabled.value
    ) {
        Text(text = grid[rowNum][colNum].value, textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true)
@Composable
fun TicTacToePreview() {
    TicTacToeTheme {
        TicTacToe()
    }
}