package com.example.alarmmanagerandbroadcastreceiver

interface AlarmScheduler {
    fun schedule(alarmItem: AlarmItem)
    fun cancel(alarmItem: AlarmItem)
}