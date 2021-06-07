package com.ys.storeapp.base

import android.app.Activity
import android.app.ActivityOptions
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Navigator(var context: AppCompatActivity) {

    fun startActivity(
        activityClass: Class<out Activity>,
        noTransitionAnimation: Boolean = false
    ) {
        val (activity, intent) = getActivityIntent(activityClass)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q || noTransitionAnimation) {
            activity.startActivity(intent)
        } else {
            activity.startActivity(
                intent, ActivityOptions.makeSceneTransitionAnimation(activity)
                    .toBundle()
            )
        }
    }

    fun startActivityWithData(
        activityClass: Class<out Activity>,
        bundle: Bundle,
        noTransitionAnimation: Boolean = false
    ) {
        val (activity, intent) = getActivityIntent(activityClass)
        intent.putExtras(bundle)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q || noTransitionAnimation) {
            activity.startActivity(intent)
        } else {
            activity.startActivity(
                intent, ActivityOptions.makeSceneTransitionAnimation(activity)
                    .toBundle()
            )
        }
    }

    fun startActivityForResult(
        activityClass: Class<out Activity>,
        requestCode: Int,
        noTransitionAnimation: Boolean = false
    ) {
        val (activity, intent) = getActivityIntent(activityClass)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q || noTransitionAnimation) {
            activity.startActivityForResult(intent, requestCode)
        } else {
            activity.startActivityForResult(
                intent, requestCode,
                ActivityOptions.makeSceneTransitionAnimation(activity)
                    .toBundle()
            )
        }
    }


    fun startActivityClearStack(activityClass: Class<out Activity>) {
        val (activity, intent) = getActivityIntent(activityClass)
        intent.flags =
            (Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_SINGLE_TOP)
        activity.startActivity(intent)
        activity.finishAffinity()
    }

    fun finishActivity() {
        context.finish()
    }

    fun startActivityClearCurrent(
        activityClass: Class<out Activity>,
        noTransitionAnimation: Boolean = false
    ) {
        val (activity, intent) = getActivityIntent(activityClass)
        intent.flags =
            (Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_SINGLE_TOP)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q || noTransitionAnimation) {
            activity.startActivity(intent)
        } else {
            activity.startActivity(
                intent, ActivityOptions.makeSceneTransitionAnimation(activity)
                    .toBundle()
            )
        }
        activity.finish()
    }

    /**
     * To start view transition use these.
     */


    private fun getActivityIntent(activityClass: Class<out Activity>): Pair<AppCompatActivity, Intent> {
        val activity = context
        val intent = Intent(activity, activityClass)
        return Pair(activity, intent)
    }


    fun goToPlayStorePage() {

        val appPackageName = context.packageName
        try {
            val intentRateApp =
                Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName"))
            context.startActivity(intentRateApp)
        } catch (e: ActivityNotFoundException) {
            val intentRateApp =
                Intent(
                    Intent.ACTION_VIEW, Uri.parse(
                        "https://play.google.com/store/" +
                                "apps/details?id=" + appPackageName
                    )
                )
            context.startActivity(intentRateApp)
        }
    }

    fun openUrlInBrowser(url: String) {
        val i = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(url)
        }
        context.startActivity(i)
    }


}