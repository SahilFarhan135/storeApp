package com.ys.storeapp.util

import android.content.Context
import android.content.Intent

class FirebaseService { /*FirebaseMessagingService()*/
    private var notificationUtils: NotificationUtils? = null
    private var title: String? = null
    private var message: String? = null


    /* fun onMessageReceived(remoteMessage: RemoteMessage?) {
         if (remoteMessage == null) return
         if (remoteMessage.getNotification() != null && remoteMessage.getNotification().getTitle() != null) {
             if (!TextUtils.isEmpty(remoteMessage.getNotification().getTitle()) &&
                     remoteMessage.getNotification().getTitle().contains("New Order")) {
                 val intent = Intent()
                 intent.setAction(" ")
                 //sendBroadcast(intent)
             }
         }
     }


     */

    private fun showNotificationMessage(context: Context, title: String?, message: String?, timeStamp: String, intent: Intent) {
        notificationUtils = NotificationUtils(context)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        if (title != null) {
            notificationUtils!!.showNotificationMessage(title, message, timeStamp, intent)
        }
    }

    private fun showNotificationMessageWithBigImage(context: Context, title: String?, message: String?, timeStamp: String, intent: Intent, imageUrl: String) {
        notificationUtils = NotificationUtils(context)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        notificationUtils!!.showNotificationMessage(title!!, message, timeStamp, intent, imageUrl)
    }


    /* private fun createNotificationForOrder(rm: RemoteMessage, context: Context) {
         var notifManager: NotificationManager? = null
         val NOTIFY_ID = 123
         val builder: NotificationCompat.Builder
         val notif_id = context.getString(R.string.selectTextMode)
         val healthTitle: String = rm.getNotification().getTitle()
         val healthTip: String = rm.getNotification().getBody()
         if (notifManager == null) {
             notifManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
         }
         var tipIntent = Intent(context, MyOrderActivity::class.java)
         val tipPendingIntent: PendingIntent = PendingIntent.getActivity(context,
                 System.currentTimeMillis().toInt(), tipIntent, 0)
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
             val importance: Int = NotificationManager.IMPORTANCE_HIGH
             val audioAttributes: AudioAttributes = AudioAttributes.Builder()
                     .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                     .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                     .build()
             var mChannel: NotificationChannel? = notifManager.getNotificationChannel(notif_id)
             if (mChannel == null) {
                 mChannel = NotificationChannel(notif_id, "My Order channel", importance)
                 mChannel.enableVibration(true)
                 mChannel.enableLights(true)
                 mChannel.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), audioAttributes)
                 mChannel.setVibrationPattern(longArrayOf(1000, 1000))
                 notifManager.createNotificationChannel(mChannel)
             }
             builder = NotificationCompat.Builder(context, notif_id)
             builder.setContentTitle(healthTitle)
                     .setContentText(healthTip)
                     .setAutoCancel(true)
                     .setDefaults(Notification.DEFAULT_ALL)
                     .setWhen(System.currentTimeMillis())
                     .setSmallIcon(R.drawable.ic_notification_overlay)
                     .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_dialog_map))
                     .setTicker(healthTitle)
                     .setStyle(NotificationCompat.BigTextStyle())
                     .setVibrate(longArrayOf(1000, 1000))
                     .setContentIntent(PendingIntent.getActivity(context, 0, tipIntent, PendingIntent.FLAG_CANCEL_CURRENT))
                     .addAction(R.drawable.ic_menu_view, "Order", tipPendingIntent)
         } else {
             builder = NotificationCompat.Builder(context, notif_id)
             builder.setContentTitle(healthTitle)
                     .setContentText(healthTip)
                     .setDefaults(0)
                     .setWhen(System.currentTimeMillis())
                     .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                     .setAutoCancel(true)
                     .setSmallIcon(R.drawable.ic_notification_overlay)
                     .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.mipmap.sym_def_app_icon))
                     .setTicker(healthTitle)
                     .setStyle(NotificationCompat.BigTextStyle())
                     .setVibrate(longArrayOf(1000, 1000))
                     .setPriority(NotificationCompat.PRIORITY_MAX)
                     .setContentIntent(PendingIntent.getActivity(context, 0, tipIntent, PendingIntent.FLAG_CANCEL_CURRENT))
                     .addAction(R.drawable.ic_menu_view, "Order", tipPendingIntent)
         }
        notifManager.notify(NOTIFY_ID, builder.build())
     }

     */


    companion object {
        private val TAG: String = FirebaseService::class.java.getSimpleName()
    }
}