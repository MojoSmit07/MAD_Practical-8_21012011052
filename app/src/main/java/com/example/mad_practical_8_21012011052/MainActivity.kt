package com.example.mad_practical_8_21012011052

import android.app.AlarmManager
import android.app.Notification.Action
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextClock
import android.widget.TextView
import android.widget.TimePicker
import com.google.android.material.card.MaterialCardView
import java.sql.Time
import java.time.Month
import java.time.Year
import java.util.Calendar
import java.util.Date

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val card= findViewById<MaterialCardView>(R.id.card2)
        val alarmbutton=findViewById<Button>(R.id.CreateBtn)
        val cancelbtn=findViewById<Button>(R.id.CancelBtn)
        val TimeText = findViewById<TextClock>(R.id.clocktime2)

        card.visibility=View.GONE
        alarmbutton.setOnClickListener {
            card.visibility=View.VISIBLE
            TimePickerDialog(this,{tp,hour,minute->
                TimeText.format12Hour = String.format("%02d:%02d", hour, minute)
                setalarmtime(hour, minute)
            }, Calendar.HOUR,Calendar.MINUTE,false).show()
        }

        cancelbtn.setOnClickListener(){
            stop()
            card.visibility=View.GONE
        }
    }
    fun setalarmtime(hour:Int,minute:Int){
    val alarmtime = Calendar.getInstance()
        val year = alarmtime.get(Calendar.YEAR)
        val month = alarmtime.get(Calendar.MONTH)
        val date = alarmtime.get(Calendar.DATE)
        val hour = alarmtime.get(Calendar.HOUR)
        val minute = alarmtime.get(Calendar.MINUTE)
        alarmtime.set(year, month, date, hour, minute)
        setalarm(alarmtime.timeInMillis, AlarmBroadcastReceiver.ALARMSTART)
    }

    fun stop(){
        setalarm(-1, AlarmBroadcastReceiver.ALARMSTOP)
    }
    fun setalarm(militime:Long,action:String){
        val intentalarm=Intent(applicationContext,AlarmBroadcastReceiver::class.java)
        intentalarm.putExtra(AlarmBroadcastReceiver.ALARMKEY,action)
        val pendingIntent= PendingIntent.getBroadcast(applicationContext,4356,intentalarm,PendingIntent.FLAG_UPDATE_CURRENT)
        
        val alarmmanager = getSystemService(ALARM_SERVICE) as AlarmManager

        if (action == AlarmBroadcastReceiver.ALARMSTART){
            alarmmanager.setExact(AlarmManager.RTC_WAKEUP,militime,pendingIntent)
        }
        else if(action == AlarmBroadcastReceiver.ALARMSTOP){
            alarmmanager.cancel(pendingIntent)
            sendBroadcast(intentalarm)
        }
    }
}