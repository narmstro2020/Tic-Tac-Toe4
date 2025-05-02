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
    Column(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)) {
        TicTacToeRow()
        TicTacToeRow()
        TicTacToeRow()
    }
}

@Composable
private fun TicTacToeRow() {
    Row {
        TicTacToeCell()
        TicTacToeCell()
        TicTacToeCell()
    }
}

@Composable
private fun TicTacToeCell() {
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

    Button(
        onClick = {},
        colors = buttonColors,
        shape = RectangleShape,
        border = BorderStroke(1.dp, Color.Blue),
        modifier = modifier
    ) {
        Text(text = "X", textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true)
@Composable
fun TicTacToePreview() {
    TicTacToeTheme {
        TicTacToe()
    }
}