package edu.ucne.reparacion.ui.Agenda

import android.widget.CalendarView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarioView(
    navHostController: NavHostController
) {
    var date by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Calendario") }) },
    ) { it

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            AndroidView(factory = {CalendarView(it)}, update = {
                it.setOnDateChangeListener{calendarView, year, month, day ->
                    date = "$day - ${month + 1} - $year"
                }
            })
            Text(text = date)
        }
    }
}
