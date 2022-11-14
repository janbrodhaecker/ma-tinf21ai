package de.dhbw.ex.locationdirection

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import de.dhbw.ex.locationdirection.view.DirectionView
import de.dhbw.ex.locationdirection.view.LocationView
import de.dhbw.ex.locationdirection.view.Overview

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = this

        setContent {
            val navController = rememberNavController()

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(getString(R.string.app_title))
                    }, navigationIcon = {  })
                }) {
                    NavHost(navController = navController, startDestination = "overview") {
                        // TODO add navigations as requested
                        // - for locationview
                        // - for directionview
                        composable("overview") { Overview(context, navController) }
                    }
                }
            }
        }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}