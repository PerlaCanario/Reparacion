package edu.ucne.reparacion.ui.Agenda

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import edu.ucne.reparacion.R
import edu.ucne.reparacion.ui.navegacion.Screen
import java.util.*

@Composable
fun RegistroAgenda(
    navHostController: NavHostController,
    viewModel: AgendaViewModel = hiltViewModel()
) {
    val ScaffoldState = rememberScaffoldState()
    var validarNombre by remember { mutableStateOf(false)  }
    var validarDescripcion by remember { mutableStateOf(false) }

    val context = LocalContext.current


    val c = Calendar.getInstance()
    val año = c.get(Calendar.YEAR)
    val mes = c.get(Calendar.MONTH)
    val dia = c.get(Calendar.DAY_OF_MONTH)

    var textfecha by remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        { datePicker,year,mon,day ->
            val month = mon + 1
            textfecha = " Fecha: $day - $month - $year"
        },año,mes,dia
    )
    Scaffold(

        floatingActionButton = {
            FloatingActionButton(onClick = {
                validarNombre = viewModel.nombreAgenda.isBlank()
                validarDescripcion = viewModel.descripcion.isBlank()

                if (viewModel.nombreAgenda == ""){
                    Toast.makeText(context, "El nombre no debe estar vacio", Toast.LENGTH_SHORT).show()
                }
                if (viewModel.descripcion == ""){
                    Toast.makeText(context, "La descripción no debe estar vacio", Toast.LENGTH_SHORT).show()
                }

                if(!validarNombre && !validarDescripcion){
                    viewModel.Guardar()
                    Toast.makeText(context, "Guardado", Toast.LENGTH_SHORT).show()


                }else{
                    Toast.makeText(context, " No fue guardado verifica los datos", Toast.LENGTH_SHORT).show()
                } }) {
                Modifier.background(colorResource(id = R.color.Verde2))
                /* FAB content */
                Icon(
                    painter = painterResource(id = R.drawable.save),
                    contentDescription = "Guardar",
                    modifier = Modifier
                        .clickable(onClick = {})
                        .size(40.dp), tint = Color.White,

                )

            }
        },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = true,

        bottomBar = {
            BottomAppBar(

                // Defaults to null, that is, No cutout
                backgroundColor = colorResource(id = R.color.Verde4),
                cutoutShape = MaterialTheme.shapes.small.copy(
                    CornerSize(percent = 50)
                )
            ) {

                /* Bottom app bar content */
                Spacer(modifier = Modifier.padding(5.dp))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Filled.Home,
                        contentDescription = "Inicio",
                        modifier = Modifier
                            .clickable(onClick = {navHostController.navigate(Screen.InicioScreen.route)})
                            .size(40 .dp)
                    )
                }
                Spacer(modifier = Modifier.padding(30.dp))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Filled.Notifications,
                        contentDescription = "Lista de pendientes",
                        modifier = Modifier
                            .clickable(onClick = {navHostController.navigate(Screen.ConsultaAgenda.route)})
                            .size(40.dp)
                    )
                }

                Spacer(modifier = Modifier.padding(40.dp))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painterResource(id = R.drawable.calendario),
                        contentDescription = "Calendario",
                        modifier = Modifier
                            .clickable(onClick = {navHostController.navigate(Screen.ConsultaAgenda.route)})
                            .size(40.dp)
                    )
                }
                /*  Spacer(modifier = Modifier.padding(13.dp))
                  IconButton(onClick = { /*TODO*/ }) {
                      Icon(
                          Icons.Filled.Edit,
                          contentDescription = "Lista de pendientes",
                          modifier = Modifier.size(40.dp))
                  }*/
            }
        }
    ){


        Scaffold (
            topBar = {
                TopAppBar(title = { Text(text = "Tarea")

                })

            },


            scaffoldState = ScaffoldState

        )
        {
            it

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center)
            {
                Spacer(modifier = Modifier.padding(10.dp))

                OutlinedTextField(
                    value = viewModel.fecha,
                    onValueChange = { viewModel.fecha = it },
                    modifier = Modifier.fillMaxWidth(0.8f),
                    label = {
                        Text(text = "dd/mm/año")
                    },
                    readOnly = true,

                    trailingIcon = {
                        IconButton(
                            onClick = { datePickerDialog.show() }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.calendario),
                                contentDescription = null,
                            )
                        }
                    }

                )
                /*Button(onClick = {datePickerDialog.show()},
                    contentPadding = PaddingValues(
                        start = 20.dp,
                        top = 12.dp,
                        end = 20.dp,
                        bottom = 12.dp
                    )
                ){
                    Icon(painter = painterResource(id = R.drawable.calendario),
                        contentDescription = "Fecha",
                        modifier = Modifier.size(ButtonDefaults.IconSize))
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Selecione Fecha")
                }*/
                //TextField
                Spacer(modifier = Modifier.padding(10.dp))

                OutlinedTextField(value = viewModel.nombreAgenda,
                    onValueChange = {viewModel.nombreAgenda = it},
                    label = { Text(text = "Actividad") },
                    placeholder = { Text(text = "Actividad") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                Spacer(modifier = Modifier.padding(15.dp))

                OutlinedTextField(value = viewModel.descripcion, onValueChange = {viewModel.descripcion = it},
                    label = { Text(text = "Descripción") },
                    placeholder = { Text(text = "Descripción") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)

                )
                Spacer(modifier = Modifier.padding(10.dp))

                //  Text de las fecha
                Text( text = "${textfecha}", fontWeight = FontWeight.Medium)

                Spacer(modifier = Modifier.padding(40.dp))

            }
        }
    }

}