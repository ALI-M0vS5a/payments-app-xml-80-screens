package com.example.montypays.settings.presentation.share

import androidx.compose.material.BottomSheetState
import androidx.compose.material.ExperimentalMaterialApi
import com.example.montypays.settings.data.ShareSettings

@ExperimentalMaterialApi
sealed class ShareSettingsEvent {

    data class OnAddShareClicked(
        val sheetState: BottomSheetState
    ) : ShareSettingsEvent()
    data class OnMerchantNameChange(val merchantName: String) : ShareSettingsEvent()
    data class OnShareContentChange(val shareContent: String) : ShareSettingsEvent()
    data class OnSaveClick(val sheetState: BottomSheetState) : ShareSettingsEvent()

    data class OnMoreClicked(
        val sheetState: BottomSheetState,
        val shareSettings: ShareSettings
    ) : ShareSettingsEvent()

    data class OnEditClicked(
        val moreSheetState: BottomSheetState,
        val editSheetState: BottomSheetState
        ) : ShareSettingsEvent()
    data class OnEditMerchantNameChange(val merchantName: String) : ShareSettingsEvent()
    data class OnEditShareContentChange(val shareContent: String) : ShareSettingsEvent()
    data class OnEditSaveClick(val sheetState: BottomSheetState) : ShareSettingsEvent()

    data class OnDeleteClicked(
        val sheetState: BottomSheetState,
        val moreSheetState: BottomSheetState
    ) : ShareSettingsEvent()

    data class OnSureDeleteClicked(
        val deleteSheetState: BottomSheetState,
        val sheetState: BottomSheetState,
        val shareSettings: ShareSettings
        ) : ShareSettingsEvent()

    data class OnCancelClicked(
        val sheetState: BottomSheetState
    ) : ShareSettingsEvent()

    data class OnSearchChanged(val text: String) : ShareSettingsEvent()


}
