package com.covalent.locationfinderapp.ui.screens.location

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.covalent.locationfinderapp.data.services.LocationService

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreen(viewModel: LocationViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current

    var checked by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = checked) {
        if (checked) {
            Log.d("CO_", "Checked")
            viewModel.getLocationUpdates()
            Intent(context, LocationService::class.java).apply {
                action = LocationService.ACTION_START
                context.startService(this)
            }
        } else {
            Intent(context, LocationService::class.java).apply {
                action = LocationService.ACTION_STOP
                context.startService(this)
            }
        }

    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shadowElevation = 8.dp
            ) {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    title = {
                        Text(text = "Location Finder", color = Color.White)
                    },
                    actions = {
                        Switch(checked = checked, onCheckedChange = {
                            checked = it

                        }, thumbContent = {
                            if (checked) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "",
                                    modifier = Modifier.size(SwitchDefaults.IconSize)
                                )
                            }
                        },
                            colors = SwitchDefaults.colors(
                                checkedBorderColor = Color.DarkGray,

                            )
                        )
                    }
                )
            }
        }) {
        Surface(
            modifier = Modifier.padding(it),
            color = Color(0xFFFAFAF)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "My Location")

                }
                Spacer(modifier = Modifier.height(20.dp))
                Divider(
                    thickness = 0.5.dp,
                    color = Color.LightGray
                )

                if (uiState.isLoading) {
                    CircularProgressIndicator()
                }

                if (uiState.isSuccess) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Latitude: ${uiState.lat}", color = MaterialTheme.colorScheme.primaryContainer)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Longitude: ${uiState.long}",color = MaterialTheme.colorScheme.primaryContainer)
                }

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        uiState.let {
//                            viewModel.getWhat3WordsString(uiState.lat.toDouble(),uiState.long.toDouble())
                            viewModel.sendDataToServiceBus()
                        }
                }) {
                    Text(text = "What3Word")
                }

            }
        }
    }
}