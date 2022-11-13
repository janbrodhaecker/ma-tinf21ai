package de.dhbw.ex.locationdirection.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import de.dhbw.ex.locationdirection.R.*
import de.dhbw.ex.locationdirection.model.LocationModel
import de.dhbw.ex.locationdirection.util.FileStorage
import de.dhbw.ex.locationdirection.util.LocationModelPersistence
import java.time.Instant

@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationView(context: Context, navigationController: NavHostController, id: String?) {

    val initialLocationModel = LocationModelPersistence.loadLocationModel(context, id) ?: LocationModel()

    var locationModel by remember { mutableStateOf(initialLocationModel) }
    var selectedPosition by remember { mutableStateOf(LatLng(1.35, 103.87)) }
    locationModel.location?.let {
        selectedPosition = it
    }

    var currentPosition by remember { mutableStateOf(selectedPosition) }
    val showCameraDialog = remember { mutableStateOf(false) }

    val defaultImageBitmap = ImageBitmap.imageResource(drawable.placeholder_image)
    var imageBitmap by remember { mutableStateOf(defaultImageBitmap) }

    if (locationModel.imageUrl?.isEmpty() == false) {
        val loadedImage = BitmapFactory.decodeFile(locationModel.imageUrl)
        imageBitmap = loadedImage.asImageBitmap()
    }

    val fineLocationPermission = rememberPermissionState(
        Manifest.permission.ACCESS_FINE_LOCATION,
    )

    val coarseLocationPermission = rememberPermissionState(
        Manifest.permission.ACCESS_COARSE_LOCATION,
    )

    val cameraPermission = rememberPermissionState(
        Manifest.permission.CAMERA,
    )

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(selectedPosition, 10f)
    }

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        bitmap?.let {
            // TODO save image, use FileStorage.writeImage() - method
            // TODO update filePath in locationModel
            // TODO update imageBitmap state variable
        }
    }

    if (fineLocationPermission.status.isGranted && coarseLocationPermission.status.isGranted) {
        val locationProvider = LocationServices.getFusedLocationProviderClient(context)

        locationProvider.lastLocation.addOnSuccessListener {
            if (it != null) {
                currentPosition = LatLng(it.latitude, it.longitude)
                cameraPositionState.position = CameraPosition.fromLatLngZoom(currentPosition, 10f)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Image(
                    bitmap = imageBitmap,
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape)
                        .border(BorderStroke(3.dp, Color.Gray), CircleShape)
                        .clickable {
                            showCameraDialog.value = true
                        })
            }

            if (showCameraDialog.value) {
                imageSourceDialog(showCameraDialog, cameraPermission, cameraLauncher)
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth(),
                leadingIcon = {
                    Icon(Icons.Filled.Edit, "")
                },
                value = "${locationModel.title ?: ""}",
                onValueChange = {
                    locationModel = locationModel.updateTitle(it)
                },
                label = { Text("Title") })

            OutlinedTextField(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth(),
                leadingIcon = { Icon(Icons.Filled.LocationOn, "") },
                value = "${locationModel.locationText() ?: ""}",
                onValueChange = { },
                label = { Text("Lat/Lng") })

            Spacer(modifier = Modifier.height(10.dp))

            GoogleMap(
                modifier = Modifier.weight(1f),
                cameraPositionState = cameraPositionState,
                onMapClick = {
                    locationModel = locationModel.updateLocation(it)
                    selectedPosition = it
                }
            ) {
                Marker(
                    state = MarkerState(selectedPosition),
                    title = "Selected Position")

                Marker(
                    state = MarkerState(currentPosition),
                    title = "Position")
            }

            OutlinedButton(modifier = Modifier.fillMaxWidth(), onClick = {
                LocationModelPersistence.saveLocationModel(context, locationModel)
                navigationController.navigateUp()
            }) {
                Text(text = "Save")
            }
        }
    } else {
        PermissionRequiredView(coarseLocationPermission, fineLocationPermission)
    }
}