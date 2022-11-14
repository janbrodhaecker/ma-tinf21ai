package de.dhbw.ex.locationdirection.view

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import de.dhbw.ex.locationdirection.model.LocationModel
import de.dhbw.ex.locationdirection.util.LocationModelPersistence

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun Overview(context: Context, navController: NavHostController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // TODO navigate to empty LocationView
            }) {
                Icon(Icons.Filled.Add, "")
            }
        }, content = {
            // TODO load locations from LocationModelPersistence
            val locationModels = emptyList<LocationModel>()
            Column {
                locationModels.forEach {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .clickable {
                                // TODO navigate to DirectionView
                            }) {
                        Row(modifier = Modifier.padding(16.dp)) {
                            Icon(
                                Icons.Filled.Edit,
                                "edit",
                                tint = Color.Black,
                                modifier = Modifier.clickable {
                                    // TODO navigate to LocationView with id
                                })
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(it.title ?: "<NO TITLE>", fontSize = 18.sp)
                        }
                    }
                }
            }
        })
}
