package com.example.catapp.ui.cats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.catapp.data.local.CatEntity
import com.example.catapp.ui.cats.components.NoNetworkDialog
import com.example.catapp.R
import com.example.catapp.ui.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatListScreen(viewModel: CatViewModel, onCatClick: (CatEntity) -> Unit) {
    val cats by viewModel.cats.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val showDialog by viewModel.showNetworkDialog.collectAsState()

    NoNetworkDialog(show = showDialog, onDismiss = { viewModel.dismissNetworkDialog() })

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(id = R.string.cat_gallery)) })
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(Dimens.PaddingMedium),
                    verticalArrangement = Arrangement.spacedBy(Dimens.ListItemSpacing)
                ) {
                    items(cats) { cat ->
                        CatItem(cat = cat, onClick = { onCatClick(cat) })
                    }
                }
            }
        }
    }
}



