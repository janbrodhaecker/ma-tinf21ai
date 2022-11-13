package de.dhbw.ex.locationdirection.view

import android.graphics.Bitmap
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import de.dhbw.ex.locationdirection.R

@Composable
@OptIn(ExperimentalPermissionsApi::class)
fun imageSourceDialog(
    showCameraDialog: MutableState<Boolean>,
    cameraPermission: PermissionState,
    cameraLauncher: ManagedActivityResultLauncher<Void?, Bitmap?>
) {

    AlertDialog(
        onDismissRequest = {
            showCameraDialog.value = false
        },
        title = { Text(text = "Select a photo") },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Column() {
                    val cameraIcon = AppCompatResources
                        .getDrawable(LocalContext.current, R.drawable.photo_camera_48px)
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            showCameraDialog.value = false
                            if (!cameraPermission.status.isGranted) {
                                cameraPermission.launchPermissionRequest()
                                return@Button
                            }
                            cameraLauncher.launch(null)
                        }) {
                        Icon(
                            rememberDrawablePainter(drawable = cameraIcon),
                            "contentDescription",
                            tint = Color.White,
                            modifier = Modifier.height(20.dp))
                        Text("Camera",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f))
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    val imagePicker = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.GetContent(),
                        onResult = { uri ->
                            // TODO show image from file system
                        }
                    )
                    val photoLibraryIcon = AppCompatResources
                        .getDrawable(LocalContext.current, R.drawable.photo_library_48px)
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            showCameraDialog.value = false
                            imagePicker.launch("image/*")
                        }) {
                        Icon(
                            rememberDrawablePainter(drawable = photoLibraryIcon),
                            "contentDescription",
                            tint = Color.White,
                            modifier = Modifier.height(20.dp))
                        Text("File",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f))
                    }
                }
            }
        })
}