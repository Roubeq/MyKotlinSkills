package com.example.alarmmanagerandbroadcastreceiver

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.time.ZoneId

class AndroidAlarmScheduler(
    private val context: Context
) : AlarmScheduler {
    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    /*
    .set() - неточный одноразовый триггер
    .setExactAndAllowWhileIdle() - точный будильник, но сработает в режиме энергосбережения
     */
    @SuppressLint("MissingPermission") // Можно использовать USE_EXACT_ALARM
    override fun schedule(alarmItem: AlarmItem) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("EXTRA_MESSAGE", alarmItem.message)
        }
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            alarmItem.time.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
            PendingIntent.getBroadcast(
                context,
                alarmItem.hashCode(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    override fun cancel(alarmItem: AlarmItem) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                alarmItem.hashCode(),
                Intent(context, AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}