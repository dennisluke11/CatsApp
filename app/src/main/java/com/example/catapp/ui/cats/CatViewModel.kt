package com.example.catapp.ui.cats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.data.local.CatEntity
import com.example.catapp.data.local.CatRepository
import com.example.catapp.utils.NetworkUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CatViewModel(
    private val repository: CatRepository,
    private val networkUtils: NetworkUtils
) : ViewModel() {

    private val _cats = MutableStateFlow<List<CatEntity>>(emptyList())
    val cats: StateFlow<List<CatEntity>> = _cats

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _showNetworkDialog = MutableStateFlow(false)
    val showNetworkDialog: StateFlow<Boolean> = _showNetworkDialog

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
            if (localCats.isNotEmpty()) {
                _cats.value = localCats
                _isLoading.value = false
            } else {
                networkUtils.isConnected.collect { connected ->
                    if (connected) {
                        repository.refreshCats()
                        _cats.value = repository.getLocalCats()
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
