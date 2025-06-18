package com.example.alarmmanagerandbroadcastreceiver

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.service.carrier.MessagePdu
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.example.alarmmanagerandbroadcastreceiver.ui.theme.AlarmManagerAndBroadcastReceiverTheme
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.SCHEDULE_EXACT_ALARM
                ),
                0
            )
        }
        super.onCreate(savedInstanceState)
        val alarmScheduler = AndroidAlarmScheduler(this)
        var alarmItem: AlarmItem? = null
        setContent {
            AlarmManagerAndBroadcastReceiverTheme {
                var secondText by remember {
                    mutableStateOf("")
                }
                var firstText by remember {
                    mutableStateOf("")
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = secondText,
                        onValueChange = { secondText = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text("Trigger alarm in seconds")
                        }
                    )
                    OutlinedTextField(
                        value = firstText,
                        onValueChange = { firstText = it },
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Button(
                            onClick = {
                                val newAlarm = AlarmItem(
                                    time = LocalDateTime.now().plusSeconds(
                                        secondText.toLong()
                                    ),
                                    message = firstText
                                )
                                alarmScheduler.schedule(
                                    alarmItem = newAlarm
                                )
                                alarmItem = newAlarm
                                secondText = ""
                                firstText = " "
                            }
                        ) {
                            Text("Schedule")
                        }
                        Button(
                            onClick = {
                                alarmItem?.let(alarmScheduler::cancel)
                            }
                        ) {
                            Text("Cancel")
                        }
                    }
                }
            }
        }
    }
}
