package developer.abdulahad.notification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import developer.abdulahad.notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var id = 1
    var channel_id = "1"
    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.show.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this,1,intent,0)

            val builder: NotificationCompat.Builder = NotificationCompat.Builder(this, channel_id)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Android 7")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setContentText("Android 7 dan salomlar")

            val notification: Notification = builder.build()
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = getString(R.string.app_name)
                val descriptionText = getString(R.string.app_name)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(channel_id, name, importance).apply {
                    description = descriptionText
                }
                // Register the channel with the system
                notificationManager.createNotificationChannel(channel)
            }

            notificationManager.notify(id, notification)
        }

        binding.hide.setOnClickListener {
            val notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.cancel(id)
        }
    }
}