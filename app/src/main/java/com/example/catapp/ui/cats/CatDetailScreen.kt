package com.example.catapp.ui.cats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.catapp.R
import com.example.catapp.data.local.CatEntity
import com.example.catapp.ui.Dimens
import com.example.catapp.ui.cats.components.NoNetworkDialog


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatDetailScreen(cat: CatEntity, onBack: () -> Unit, viewModel: CatViewModel) {

    val showDialog by viewModel.showNetworkDialog.collectAsState()

    val context = LocalContext.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri("android.resource://${context.packageName}/raw/cat")
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    NoNetworkDialog(show = showDialog, onDismiss = { viewModel.dismissNetworkDialog() })
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(cat.title) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(cat.url)
                    .crossfade(true)
                    .build(),
                contentDescription = cat.title,
                placeholder = painterResource(id = R.drawable.placeholder),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(Dimens.ImageHeightFraction)
            )

            Spacer(modifier = Modifier.height(Dimens.SpacerMedium))

            Text(
                text = cat.title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = Dimens.PaddingMedium)
            )
            Text(
                text = cat.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = Dimens.PaddingMedium)
            )
        }
    }
}