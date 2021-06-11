package com.ys.storeapp.ui.delivery_current

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ys.storeapp.R
import com.ys.storeapp.base.BaseActivity
import com.ys.storeapp.databinding.ActivityCurrentOrderBinding
import com.ys.storeapp.injection.component.AppComponent

class MyOrderActivity : BaseActivity<ActivityCurrentOrderBinding, MyOrderViewModel>() {

    override fun layoutId(): Int = R.layout.activity_current_order
    override fun addObservers() {}
    override fun getViewModelClass(): Class<MyOrderViewModel> = MyOrderViewModel::class.java
    override fun injectActivity(appComponent: AppComponent) {
        appComponent.homeComponent().create().inject(this)
    }

    lateinit var allOrderList: ArrayList<OrderItem>
    lateinit var pendingOrderList: ArrayList<OrderItem>
    lateinit var deliveredOrderList: ArrayList<OrderItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        initRvData()
        clickListner()
        initializeRv(allOrderList)
    }


    private fun initUi() {
        allOrderList = ArrayList()
        pendingOrderList = ArrayList()
        deliveredOrderList = ArrayList()
    }

    private fun initRvData() {
        allOrderList.add(
            OrderItem(
                "ODMkSAHIL7687262",
                "$2888",
                "Nike Air Flag NF833",
                R.drawable.demo_shoe,
                " Quantity : 9pcs",
                " Color : RED",
                " COD",
                "Delivered",
                "d"
            )
        )
        allOrderList.add(
            OrderItem(
                "ODMkSAHIL7687231",
                "$5688",
                "Puma Light Orange T-Shirt",
                R.drawable.demo_shoe,
                " Quantity : 2pcs",
                " Color : BLUE",
                " Paid",
                "On Packagning",
                "p"
            )
        )
        allOrderList.add(
            OrderItem(
                "ODMkSAHIL7687466",
                "$2000",
                "Nike Super Black F893",
                R.drawable.demo_shoe,

                " Quantity : 1pcs",
                " Color : BLACK",
                " COD",
                "On-Delivery",
                ""
            )
        )
        allOrderList.add(
            OrderItem(
                "ODMkSAHIL7687264",
                "$2345",
                "Nike Alpha Supere K882",
                R.drawable.demo_shoe,

                " Quantity : 10pcs",
                " Color : ALPHA BLACK",
                " Paid",
                "Processing",
                "d"
            )
        )
        allOrderList.add(
            OrderItem(
                "ODMkSAHIL7687456",
                "$7542",
                "Nike Bokia Red Azure BRNF33",
                R.drawable.demo_shoe,

                " Quantity : 2pcs",
                " Color : ORIENT BLUE",
                " Paid",
                "Delivered",
                "p"
            )
        )
        allOrderList.add(
            OrderItem(
                "ODMkSAHIL7687244",
                "$7434",
                "Nike Binka Grey B833",
                R.drawable.demo_shoe,
                " Quantity : 1pcs",
                " Color : BLUE SAPPHIRE",
                " NOT PAID",
                "On-Delivery",
                "p"
            )
        )
        allOrderList.add(
            OrderItem(
                "ODMkSAHIL7687255",
                "$9000",
                "Nike Orange Flag B99i",
                R.drawable.demo_shoe,
                " Quantity : 1pcs",
                " Color : ELEGANT RED",
                " NOT Paid",
                "Processing",
                "d"
            )
        )
        allOrderList.add(
            OrderItem(
                "ODMkSAHIL3884844",
                "$7000",
                "Nike Blood Flag B99i",
                R.drawable.demo_shoe,
                " Quantity : 1pcs",
                " Color : ELEGANT PINK",
                "  Paid",
                "Delivered",
                "d"
            )
        )
        allOrderList.add(
            OrderItem(
                "ODMkSAHIL7135662",
                "$5000",
                "Nike Yellow Flag B99i",
                R.drawable.demo_shoe,
                " Quantity : 2pcs",
                " Color : ELEGANT BLACK",
                " NOT Paid",
                "Processing",
                "p"
            )
        )
        allOrderList.add(
            OrderItem(
                "ODMkSAHIL7135662",
                "$5000",
                "Nike Yellow Flag B99i",
                R.drawable.demo_shoe,
                " Quantity : 2pcs",
                " Color : ELEGANT BLACK",
                " NOT Paid",
                "Processing",
                "p"
            )
        )
        allOrderList.add(
            OrderItem(
                "ODMkSAHIL7135662",
                "$5000",
                "Nike Yellow Flag B99i",
                R.drawable.demo_shoe,
                " Quantity : 2pcs",
                " Color : ELEGANT BLACK",
                " NOT Paid",
                "Processing",
                "p"
            )
        )
        allOrderList.add(
            OrderItem(
                "ODMkSAHIL7135662",
                "$5000",
                "Nike Yellow Flag B99i",
                R.drawable.demo_shoe,
                " Quantity : 2pcs",
                " Color : ELEGANT BLACK",
                " NOT Paid",
                "Processing",
                "p"
            )
        )
    }

    private fun clickListner() {
        binding.tvCompletedFilter.setOnClickListener {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                binding.tvPendingFilter.setBackgroundColor(getColor(R.color.white))
                binding.tvPendingFilter.setTextColor(getColor(R.color.color_primary))

                binding.tvCompletedFilter.setBackgroundColor(getColor(R.color.color_primary))
                binding.tvCompletedFilter.setTextColor(getColor(R.color.white))

                binding.tvAllFilter.setBackgroundColor(getColor(R.color.white))
                binding.tvAllFilter.setTextColor(getColor(R.color.color_primary))
            }
            sortListWrtDelivered()
        }
        binding.tvPendingFilter.setOnClickListener {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                binding.tvPendingFilter.setBackgroundColor(getColor(R.color.color_primary))
                binding.tvPendingFilter.setTextColor(getColor(R.color.white))

                binding.tvCompletedFilter.setBackgroundColor(getColor(R.color.white))
                binding.tvCompletedFilter.setTextColor(getColor(R.color.color_primary))

                binding.tvAllFilter.setBackgroundColor(getColor(R.color.white))
                binding.tvAllFilter.setTextColor(getColor(R.color.color_primary))
            }
            sortListWrtPending()
        }
        binding.tvAllFilter.setOnClickListener {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                binding.tvPendingFilter.setBackgroundColor(getColor(R.color.white))
                binding.tvPendingFilter.setTextColor(getColor(R.color.color_primary))

                binding.tvCompletedFilter.setBackgroundColor(getColor(R.color.white))
                binding.tvCompletedFilter.setTextColor(getColor(R.color.color_primary))

                binding.tvAllFilter.setBackgroundColor(getColor(R.color.color_primary))
                binding.tvAllFilter.setTextColor(getColor(R.color.white))
            }
            initializeRv(allOrderList)
        }
        binding.btBack.setOnClickListener { onBackPressed() }
    }

    private fun sortListWrtPending() {
        for (items in allOrderList) {
            if (items.deliveryTag.equals("p")) {
                pendingOrderList.add(items)
            }
        }
        initializeRv(pendingOrderList)
    }

    private fun sortListWrtDelivered() {
        for (items in allOrderList) {
            if (items.deliveryTag.equals("d")) {
                deliveredOrderList.add(items)
            }
        }
        initializeRv(deliveredOrderList)
    }


    private fun initializeRv(allOrderList: ArrayList<OrderItem>) {
        val adapter1 = MyOrderApadter()
        binding.rvOrderList.adapter = adapter1
        binding.rvOrderList.setLayoutManager(LinearLayoutManager(this))
        (binding.rvOrderList.adapter as MyOrderApadter).submitList(allOrderList)
    }

}
/*
                list.sortWith(Comparator { lhs, rhs ->
                    // -1 = less than, 1 = greater than, 0 =equal, all inversed for descending
                    if (lhs.issuePostTime!! > rhs.issuePostTime!!) -1 else if (lhs.issuePostTime!! < rhs.issuePostTime!!) 1 else 0
                })

 */