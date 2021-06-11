package com.ys.storeapp.ui.delivery_current

data class OrderItem (
    var orderId:String,
    var amountPaid:String,
    var productName:String,
    var productImg:Int,
    var productQuantity:String,
    var productColor:String,
    var paymentStatus:String,
    var deliveryStatus:String,
    var deliveryTag:String,
)
