package com.jts.agrodei.utils

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager.NameNotFoundException
import android.os.Build
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.floor
import kotlin.system.exitProcess


object Utility {

    const val FIRE_TAGS = "Tags"
    const val DIGIT_COUNT = 5
    //    formats
     private const val DATE_FORMAT_CUSTOM = "EEEE, MMMM yyyy"
     private const val DATE_FORMAT = "MMM dd, yyyy"
     private const val SIMPLE_DATE_FORMAT = "dd MMM"
     private const val SIMPLE_MONTH_FORMAT = "MMM"
     private const val TIME_FORMAT = "HH:mm:ss"
     private const val TIME_FORMAT_ext = "hhmmss"
     private const val SHORT_TIME_FORMAT = "HH:mm"
     private const val SHORT_TIME_FORMAT_ext = "hh:mm a"
     private const val YEAR_FORMAT = "yyyy"
     private const val CHARACTER_RANGE = "0123456789qwertyuiopasdfghjklzxcvbnm"

    //    special dots
    const val dots = "• = \\u2022,   ● = \\u25CF,   ○ = \\u25CB,   ▪ = \\u25AA,   ■ = \\u25A0,   □ = \\u25A1,   ► = \\u25BA"

    fun restartApp(context: Context?, activity: Activity) {
        val intent = Intent(context, activity::class.java)
        val mPendingIntentId = 22445
        val mPendingIntent = PendingIntent.getActivity(
            context,
            mPendingIntentId,
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT
        )
        val mgr = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        mgr[AlarmManager.RTC, System.currentTimeMillis() + 100] = mPendingIntent
        exitProcess(0)
    }

     fun short_toast(context: Context?, message: String?) {
          val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
          toast.setGravity(Gravity.BOTTOM, 0, 325)
          toast.show()
     }

    fun long_toast(context: Context?, message: String?) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.BOTTOM, 0, 325)
        toast.show()
    }

    fun short_snack(view: View, message: String?){
        Snackbar.make(view, message.toString(), Snackbar.LENGTH_SHORT).show()
    }

    fun long_snack(view: View, message: String?){
        Snackbar.make(view, message.toString(), Snackbar.LENGTH_LONG).show()
    }
     //    activity linking
     fun startActivity(context: Context?, from: Activity, to: Activity){
          val intent = Intent(from, to::class.java)
               .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
          from.startActivity(intent)
     }
    fun getEmoji(unicode: Int): String {
        return String(Character.toChars(unicode))
    }
    fun currentHomeDate(): String?{
        val df: DateFormat = SimpleDateFormat(DATE_FORMAT_CUSTOM, Locale.US)
        val today = Calendar.getInstance().time
        return df.format(today)
    }
     fun currentDate(): String?{
          val df: DateFormat = SimpleDateFormat(DATE_FORMAT, Locale.US)
          val today = Calendar.getInstance().time
          return df.format(today)
     }
    fun currentMonth(): String?{
          val df: DateFormat = SimpleDateFormat(SIMPLE_MONTH_FORMAT, Locale.US)
          val today = Calendar.getInstance().time
          return df.format(today)
     }

     @RequiresApi(Build.VERSION_CODES.O)
     fun short_timeStamp(): String?{
          val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern(SHORT_TIME_FORMAT, Locale.US)
          val now: LocalDateTime = LocalDateTime.now()
          return dtf.format(now)
     }
     @RequiresApi(Build.VERSION_CODES.O)
     fun timeStamp(): String?{
          val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern(TIME_FORMAT, Locale.US)
          val now: LocalDateTime = LocalDateTime.now()
          return dtf.format(now)
     }
     @RequiresApi(Build.VERSION_CODES.O)
     fun yearStamp(): String?{
          val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern(YEAR_FORMAT, Locale.US)
          val now: LocalDateTime = LocalDateTime.now()
          return dtf.format(now)
     }

     fun shortTime(time: String): String? {
          val outputFormat: DateFormat = SimpleDateFormat(SHORT_TIME_FORMAT_ext, Locale.US)
          val inputFormat: DateFormat = SimpleDateFormat(TIME_FORMAT, Locale.US)

          val date = inputFormat.parse(time)
          return outputFormat.format(date)
     }

     fun startActivityWithMoreExtras(context: Context?, from: Activity, to: Activity, path: String, search: String, extraTitle: String, extraPath: String){
          val intent = Intent(from, to::class.java)
               .putExtra("path", path)
               .putExtra("search", search)
               .putExtra("extraTitle", extraTitle)
               .putExtra("extraPath", extraPath)
               .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
          from.startActivity(intent)
     }

    fun getRandomString(sizeOfRandomString: Int): String {
        val random = Random()
        val sb = StringBuilder(sizeOfRandomString)
        for (i in 0 until sizeOfRandomString)
            sb.append(CHARACTER_RANGE[random.nextInt(CHARACTER_RANGE.length)])
        return sb.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun randomId(): String {
        val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern(TIME_FORMAT_ext, Locale.US)
        val now: LocalDateTime = LocalDateTime.now()
        val min = 100
        val max = 999
        val id = floor(Math.random() * (max - min + 1) + min).toInt()
        return dtf.format(now)+""+id.toString()
    }

    fun getAge(year: Int, month: Int, day: Int): String? {
        val dob: Calendar = Calendar.getInstance()
        val today: Calendar = Calendar.getInstance()
        dob.set(year, month, day)
        var age: Int = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--
        }
        val ageInt = age
        return ageInt.toString()
    }

    fun getAppName(context: Context): String? {
        val packageManager = context.packageManager
        var applicationInfo: ApplicationInfo? = null
        try {
            applicationInfo = packageManager.getApplicationInfo(context.applicationInfo.packageName, 0)
        } catch (e: NameNotFoundException) {
        }
        return (if (applicationInfo != null) packageManager.getApplicationLabel(applicationInfo) else "Unknown") as String
    }
}