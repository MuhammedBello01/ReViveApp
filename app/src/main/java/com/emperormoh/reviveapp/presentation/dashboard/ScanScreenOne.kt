package com.emperormoh.reviveapp.presentation.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emperormoh.reviveapp.ui.theme.ReViveAppTheme


@Composable
fun ScanScreenOne() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(imageVector = Icons.Default.CameraAlt, contentDescription = null, modifier = Modifier.size(120.dp))
        Spacer(Modifier.height(20.dp))
        Text("Tap to Scan or Upload")
        Spacer(Modifier.height(20.dp))
        Button(onClick = {}) {
            Text("Open Camera")
        }
        Spacer(Modifier.height(10.dp))
        OutlinedButton(onClick = {}) {
            Text("Upload Image")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ScanScreenOnePreview() {
    ReViveAppTheme {
        ScanScreenOne()
    }

}