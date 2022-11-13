package de.dhbw.ex.locationdirection.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@Composable
@OptIn(ExperimentalPermissionsApi::class)
fun PermissionRequiredView(
    coarseLocationPermission: PermissionState,
    fineLocationPermission: PermissionState
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "This view can not be loaded, without the appropriate permissions.")
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedButton(onClick = {
            coarseLocationPermission.launchPermissionRequest()
            fineLocationPermission.launchPermissionRequest()
        }) {
            Text(text = "Request permissions.")
        }
    }
}