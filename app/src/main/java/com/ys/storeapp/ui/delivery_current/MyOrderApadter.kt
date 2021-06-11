package com.ys.storeapp.ui.delivery_current

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ys.storeapp.R
import com.ys.storeapp.databinding.RvCurrentOrderBinding
import com.ys.storeapp.ui.home.model.ProductItem

class MyOrderApadter : RecyclerView.Adapter<MyOrderApadter.MyOrderViewHolder>() {

    private val items = ArrayList<OrderItem>()


    fun submitList(orderItemList: List<OrderItem>) {
        items.clear()
        items.addAll(orderItemList)
        notifyDataSetChanged()
    }

    inner class MyOrderViewHolder(private val binding: RvCurrentOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun attachItem(orderItem: OrderItem) {
            with(binding) {
                if(orderItem.deliveryTag.equals("p")){
                    imgStatusCheck.setBackground(itemView.resources.getDrawable(R.drawable.ic_checked_red));
                }
                tvDeliveryStatus.text=orderItem.deliveryStatus.toString()
                tvOrderId.text=orderItem.orderId.toString()
                tvPaymentStatus.text=orderItem.paymentStatus.toString()
                productName.text=orderItem.productName.toString()
                productColor.text=orderItem.productColor.toString()
                productImg.setImageResource(orderItem.productImg)
                productPaymentAmount.text=orderItem.amountPaid
                productQuantity.text=orderItem.productQuantity.toString()

            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyOrderViewHolder {
        val binding =
            RvCurrentOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyOrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyOrderViewHolder, position: Int) {
        holder.attachItem(items[position])
    }

    override fun getItemCount(): Int = items.size
}