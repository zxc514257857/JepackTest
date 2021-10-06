package com.example.databinding.domain

import com.google.gson.annotations.SerializedName

class OnSaleResult(
    
    @SerializedName("tbk_dg_optimus_material_response")
    val tbkBgOptimusMaterialResponse: TbkDgOptimusMaterialResponse,
)

data class TbkDgOptimusMaterialResponse(
    @SerializedName("is_default")
    val isDefault: String,
    @SerializedName("request_id")
    val requestId: String,
    @SerializedName("result_list")
    val resultList: ResultList,
)

data class ResultList(
    @SerializedName("map_data")
    val mapData: MutableList<OnSaleItem>,
)
