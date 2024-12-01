package com.example.tuyasample.presentation.iot

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tuyasample.domain.model.IOTDeviceModel
import com.example.tuyasample.domain.usecase.ToggleSwitchUseCase
import com.example.tuyasample.presentation.ui.theme.Blue

@Composable
fun IOTScreen(viewModel: IOTViewModel = hiltViewModel()) {
    val isLoading by viewModel.isLoading.collectAsState()
    val isLogin by viewModel.isLogin.collectAsState()
    val deviceList by viewModel.deviceList.collectAsState()

    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Blue
            )
        }
    } else {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(deviceList) { item ->
                DeviceItem(item, viewModel)
            }
        }
    }
}

@Composable
fun DeviceItem(item: IOTDeviceModel, viewModel: IOTViewModel) {
    Column(
        modifier = Modifier
            .width(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            item.name,
            textAlign = TextAlign.Center,
        )
        Switch(
            checked = item.isOn,
            onCheckedChange = {
                viewModel.toggleSwitch(ToggleSwitchUseCase.Param(
                    devId = item.id,
                    value = it
                ))
            }
        )
    }

}