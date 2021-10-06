package com.example.databinding.domain

import com.google.gson.annotations.SerializedName

data class OnSaleItem(

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
) {
    override fun toString(): String {
        return "OnSaleItem(categoryId=$categoryId, categoryName=$categoryName, clickUrl='$clickUrl', commissionRate='$commissionRate', couponAmount=$couponAmount, couponClickUrl='$couponClickUrl', couponEndTime='$couponEndTime', couponInfo=$couponInfo, couponRemainCount=$couponRemainCount, couponShareUrl='$couponShareUrl', couponStartFee='$couponStartFee', couponStartTime='$couponStartTime', couponTotalCount=$couponTotalCount, itemDescription='$itemDescription', itemId=$itemId, levelOneCategoryId=$levelOneCategoryId, levelOneCategoryName='$levelOneCategoryName', nick='$nick', pictUrl='$pictUrl', sellerId=$sellerId, shopTitle=$shopTitle, smallImages=$smallImages, title='$title', userType=$userType, volume=$volume, zkFinalPrice='$zkFinalPrice')"
    }
}

data class SmallImages(
    val string: List<String>,
)

