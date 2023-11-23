package com.covalent.locationfinderapp.ui.screens.location

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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LocationScreen(viewModel: LocationViewModel = hiltViewModel()) {
    
    val uiState by viewModel.uiState.collectAsState()

    var checked by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = checked){
        Log.d("CO_","Checked")
        viewModel.getLocationUpdates()
    }
    
    Scaffold (modifier = Modifier.fillMaxSize()) {
        Surface (modifier = Modifier.padding(it)) {
            Column (modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)) {
                Spacer(modifier = Modifier.height(20.dp))
                Row (modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Enable Location")
                    Switch(checked = checked, onCheckedChange = {
                        checked = it

                    },
                        thumbContent = {
                            if (checked){
                                Icon(imageVector = Icons.Default.Check, contentDescription ="", modifier = Modifier.size(SwitchDefaults.IconSize) )
                            }
                        }
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Divider(
                    thickness = 0.5.dp,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Latitude: ${uiState.lat}")
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Longitude: ${uiState.long}")
            }
        }
    }
}