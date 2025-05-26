package com.example.viceriseidorteste.presentation.features.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.viceriseidorteste.presentation.features.details.ui.theme.ViceriSeidorTesteTheme

class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserDetailScreen(1)
        }
    }
}


@Composable
fun UserDetailScreen(userId: Int) {
    // Aqui você pode buscar os dados com base no ID
    Text("Detalhes do usuário ID: $userId")
}
