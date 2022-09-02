package com.example.montypays.settings.presentation.share

import android.os.Handler
import android.os.Looper
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.montypays.settings.data.Repository
import com.example.montypays.settings.data.ShareSettings
import com.example.montypays.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalMaterialApi
@HiltViewModel
class ShareSettingsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {


    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var arrayList: ArrayList<ShareSettings> = arrayListOf()

    var textSearch by mutableStateOf("")
        private set

    var share by mutableStateOf<ShareSettings?>(null)
        private set
    var merchantName by mutableStateOf("")
        private set
    var shareContent by mutableStateOf("")
        private set

    var editMerchantName by mutableStateOf("")
        private set
    var editShareContent by mutableStateOf("")
        private set


    fun onEvent(
        event: ShareSettingsEvent,
        scope: CoroutineScope? = null
    ) {
        when (event) {
            is ShareSettingsEvent.OnAddShareClicked -> {
                scope!!.launch {
                    if (event.sheetState.isCollapsed) {
                        event.sheetState.expand()
                    }
                }
            }

            is ShareSettingsEvent.OnMoreClicked -> {
                arrayList.clear()
                arrayList.add(event.shareSettings)
                scope!!.launch {
                    if (event.sheetState.isCollapsed) {
                        event.sheetState.expand()
                    }
                }
            }
            is ShareSettingsEvent.OnEditClicked -> {
                scope!!.launch {
                    event.moreSheetState.collapse()
                    event.editSheetState.expand()
                }
                editMerchantName = arrayList[0].merchantName
                editShareContent = arrayList[0].shareContent
            }
            is ShareSettingsEvent.OnEditMerchantNameChange -> {
                editMerchantName = event.merchantName
            }
            is ShareSettingsEvent.OnEditShareContentChange -> {
                editShareContent = event.shareContent
            }
            is ShareSettingsEvent.OnEditSaveClick -> {
                viewModelScope.launch {
                    repository.edit(
                        ShareSettings(
                            id = arrayList[0].id,
                            merchantName = editMerchantName,
                            shareContent = editShareContent
                        )
                    )
                }
                scope!!.launch {
                    event.sheetState.collapse()
                }
            }
            is ShareSettingsEvent.OnDeleteClicked -> {
                scope!!.launch {
                    event.moreSheetState.collapse()
                    event.sheetState.expand()
                }
            }
            is ShareSettingsEvent.OnSureDeleteClicked -> {
                scope!!.launch {
                    repository.delete(arrayList[0])
                    event.deleteSheetState.collapse()
                    event.sheetState.expand()
                    Handler(Looper.getMainLooper()).postDelayed({
                        scope.launch {
                            event.sheetState.collapse()
                        }
                    }, 2000)
                }
            }

            is ShareSettingsEvent.OnCancelClicked -> {
                scope!!.launch {
                    event.sheetState.collapse()
                }
            }
            is ShareSettingsEvent.OnSearchChanged -> {
                textSearch = event.text
            }
            is ShareSettingsEvent.OnMerchantNameChange -> {
                merchantName = event.merchantName
            }
            is ShareSettingsEvent.OnShareContentChange -> {
                shareContent = event.shareContent
            }
            is ShareSettingsEvent.OnSaveClick -> {
                viewModelScope.launch {
                    if (merchantName.isBlank()) {
                        sendUiEvent(
                            UiEvent.ShowSnackBar(
                                message = "The title can't be empty"
                            )
                        )
                        return@launch
                    }

                    repository.save(
                        ShareSettings(
                            id = share?.id,
                            merchantName = merchantName,
                            shareContent = shareContent
                        )
                    )
                    merchantName = ""
                    shareContent = ""
                    scope!!.launch {
                        event.sheetState.collapse()
                    }
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<ShareSettings>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }
}