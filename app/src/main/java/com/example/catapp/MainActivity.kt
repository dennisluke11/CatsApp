package com.example.catapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.catapp.data.local.CatEntity
import com.example.catapp.ui.cats.CatDetailScreen
import com.example.catapp.ui.cats.CatListScreen
import com.example.catapp.ui.cats.CatViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val catViewModel: CatViewModel = getViewModel()
            var selectedCat by remember { mutableStateOf<CatEntity?>(null) }

            selectedCat?.let { cat ->
                CatDetailScreen(
                    cat = cat,
                    onBack = { selectedCat = null },
                    catViewModel
                )
            } ?: CatListScreen(
                viewModel = catViewModel,
                onCatClick = { selectedCat = it }
            )
        }
    }
}
