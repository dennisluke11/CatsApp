package com.example.catapp.ui.cats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.data.local.CatEntity
import com.example.catapp.data.local.CatRepositoryInterface
import com.example.catapp.utils.NetworkUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log

class CatViewModel(
    private val repository: CatRepositoryInterface,
    private val networkUtils: NetworkUtils
) : ViewModel() {

    private val _cats = MutableStateFlow<List<CatEntity>>(emptyList())
    val cats: StateFlow<List<CatEntity>> = _cats

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _showNetworkDialog = MutableStateFlow(false)
    val showNetworkDialog: StateFlow<Boolean> = _showNetworkDialog

    private val TAG = "CatViewModel"

    init {
        observeNetwork()
        loadCats()
    }

    private fun observeNetwork() {
        viewModelScope.launch {
            networkUtils.isConnected.collect { connected ->
                _showNetworkDialog.value = !connected
                if (connected) {
                    refreshRemoteCatsIfNeeded()
                }
            }
        }
    }

    private fun loadCats() {
        viewModelScope.launch {
            _isLoading.value = true

            val localCats = repository.getLocalCats()
            Log.d(TAG, "Fetched ${localCats.size} cats from local DB")
            if (localCats.isNotEmpty()) {
                _cats.value = localCats
                _isLoading.value = false
            } else {
                Log.d(TAG, "No local cats found, checking network...")
                networkUtils.isConnected.collect { connected ->
                    if (connected) {
                        Log.d(TAG, "Network available: Fetching from remote API")
                        repository.refreshCats()
                        val updatedLocalCats = repository.getLocalCats()
                        Log.d(TAG, "Fetched ${updatedLocalCats.size} cats from remote and saved to DB")
                        _cats.value = repository.getLocalCats()
                    } else {
                        Log.d(TAG, "No internet connection. Cannot fetch cats from remote.")
                    }
                }
                _isLoading.value = false
            }
        }
    }

    private fun refreshRemoteCatsIfNeeded() {
        viewModelScope.launch {
            val localCats = repository.getLocalCats()
            if (localCats.isEmpty()) {
                _isLoading.value = true
                repository.refreshCats()
                _cats.value = repository.getLocalCats()
                _isLoading.value = false
            }
        }
    }

    fun dismissNetworkDialog() {
        _showNetworkDialog.value = false
    }
}
