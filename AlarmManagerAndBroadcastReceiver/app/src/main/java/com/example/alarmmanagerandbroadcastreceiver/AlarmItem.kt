package com.example.alarmmanagerandbroadcastreceiver

import java.time.LocalDateTime

data class AlarmItem(
    val time: LocalDateTime,
    val message: String
)
