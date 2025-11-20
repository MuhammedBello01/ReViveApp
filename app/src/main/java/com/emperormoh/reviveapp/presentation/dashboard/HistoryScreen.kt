package com.emperormoh.reviveapp.presentation.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emperormoh.reviveapp.ui.theme.ReViveAppTheme

@Composable
fun HistoryScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        items(10) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text("Recycled: Plastic Bottle")
                    Text("Points Earned: 20", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HistoryScreenPreview() {
    ReViveAppTheme {
        HistoryScreen()
    }

}