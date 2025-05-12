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
    val grid = remember { Array(3) { Array(3) { mutableStateOf("") } } }
    val player = remember { mutableStateOf("X") }
    val winner = remember { mutableStateOf("") }

    fun checkForWinner() {
        val lines = listOf(
            // Rows
            listOf(grid[0][0], grid[0][1], grid[0][2]),
            listOf(grid[1][0], grid[1][1], grid[1][2]),
            listOf(grid[2][0], grid[2][1], grid[2][2]),
            // Columns
            listOf(grid[0][0], grid[1][0], grid[2][0]),
            listOf(grid[0][1], grid[1][1], grid[2][1]),
            listOf(grid[0][2], grid[1][2], grid[2][2]),
            // Diagonals
            listOf(grid[0][0], grid[1][1], grid[2][2]),
            listOf(grid[0][2], grid[1][1], grid[2][0])
        )

        for (line in lines) {
            val values = line.map { it.value }
            if (values.all { it == "X" }) {
                winner.value = "X"
                return
            } else if (values.all { it == "O" }) {
                winner.value = "O"
                return
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        for (rowNumber in 0..2) {
            TicTacToeRow(rowNumber, grid, player, winner, ::checkForWinner)
        }
        if(winner.value.isNotEmpty()){
            Text(
                text = "Winner: ${winner.value}",
                modifier = Modifier.padding(top = 16.dp),
                color = Color.Red
            )
        }
    }
}

@Composable
private fun TicTacToeRow(
    rowNumber: Int,
    grid: Array<Array<MutableState<String>>>,
    player: MutableState<String>,
    winner: MutableState<String>,
    checkForWinner: () -> Unit
) {
    Row {
        for (columnNumber in 0..2) {
            TicTacToeCell(rowNumber, columnNumber, grid, player, winner, checkForWinner)
        }
    }
}

@Composable
private fun TicTacToeCell(
    rowNumber: Int,
    colNumber: Int,
    grid: Array<Array<MutableState<String>>>,
    player: MutableState<String>,
    winner: MutableState<String>,
    checkForWinner: () -> Unit
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

    val isEnabled = remember { mutableStateOf(true) }

    Button(
        onClick = {
            if (isEnabled.value && winner.value.isEmpty()) {
                grid[rowNumber][colNumber].value = player.value
                checkForWinner()
                if (winner.value.isEmpty()) {
                    player.value = if (player.value == "X") "O" else "X"
                }
                isEnabled.value = false
            }
        },
        colors = buttonColors,
        shape = RectangleShape,
        border = BorderStroke(1.dp, Color.Blue),
        modifier = modifier,
        enabled = isEnabled.value
    ) {
        Text(text = grid[rowNumber][colNumber].value, textAlign = TextAlign.Center)
    }
}


@Preview(showBackground = true)
@Composable
fun TicTacToePreview() {
    TicTacToeTheme {
        TicTacToe()
    }
}