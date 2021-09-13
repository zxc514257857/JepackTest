package com.zhr.mvvm.domain

import com.google.gson.annotations.SerializedName

data class OnSaleData(
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
    val mapData: MutableList<MapData>,
)

data class MapData(
    @SerializedName("category_id")
    val categoryId: Int,
    @SerializedName("category_name")
    val categoryName: Any,
    @SerializedName("click_url")
    val clickUrl: String,
    @SerializedName("commission_rate")
    val commissionRate: String,
    @SerializedName("coupon_amount")
    val couponAmount: Int,
    @SerializedName("coupon_click_url")
    val couponClickUrl: String,
    @SerializedName("coupon_end_time")
    val couponEndTime: String,
    @SerializedName("coupon_info")
    val couponInfo: Any,
    @SerializedName("coupon_remain_count")
    val couponRemainCount: Int,
    @SerializedName("coupon_share_url")
    val couponShareUrl: String,
    @SerializedName("coupon_start_fee")
    val couponStartFee: String,
    @SerializedName("coupon_start_time")
    val couponStartTime: String,
    @SerializedName("coupon_total_count")
    val couponTotalCount: Int,
    @SerializedName("item_description")
    val itemDescription: String,
    @SerializedName("item_id")
    val itemId: Long,
    @SerializedName("level_one_category_id")
    val levelOneCategoryId: Int,
    @SerializedName("level_one_category_name")
    val levelOneCategoryName: String,
    val nick: String,
    @SerializedName("pict_url")
    val pictUrl: String,
    @SerializedName("seller_id")
    val sellerId: Long,
    @SerializedName("shop_title")
    val shopTitle: Any,
    @SerializedName("small_images")
    val smallImages: SmallImages,
    val title: String,
    @SerializedName("user_type")
    val userType: Int,
    val volume: Int,
    @SerializedName("zk_final_price")
    val zkFinalPrice: String,
)

data class SmallImages(
    val string: List<String>,
)