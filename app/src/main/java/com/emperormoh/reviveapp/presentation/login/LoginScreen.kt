package com.emperormoh.reviveapp.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emperormoh.reviveapp.ui.theme.ReViveAppTheme


@Composable
fun LoginScreen(onLoginSuccess: () -> Unit, onSignUp: () -> Unit) {


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {


        Text("Welcome Back", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))


        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(Modifier.height(12.dp))


        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(Modifier.height(20.dp))


        Button(
            onClick = onLoginSuccess,
            modifier = Modifier.fillMaxWidth()
        ) { Text("Login") }


        TextButton(
            onClick = onSignUp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create an account")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    ReViveAppTheme {
        LoginScreen(onLoginSuccess = {}, onSignUp = {})
    }
}