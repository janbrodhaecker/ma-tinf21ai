package de.dhbw.ex.locationdirection.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import de.dhbw.ex.locationdirection.R.*
import de.dhbw.ex.locationdirection.util.LocationModelPersistence
import java.util.*

@Composable
@SuppressLint("MissingPermission")
fun DirectionView(context: Context, id: String?) {
    val locationModel = LocationModelPersistence.loadLocationModel(context, UUID.fromString(id))
    locationModel?.let {
        var distance by remember { mutableStateOf(0.00f) }
        var bearingTo by remember { mutableStateOf(0.00f) }

        // TODO requestLocationUpdates, use interval of i.e 5sec

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TODO show distance and bearing to location
        }
    }
}
