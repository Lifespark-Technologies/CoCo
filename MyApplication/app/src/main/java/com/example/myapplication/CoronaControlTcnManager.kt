package com.example.myapplication

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.activities.MainActivity
import org.tcncoalition.tcnclient.TcnKeys
import org.tcncoalition.tcnclient.TcnManager
import org.tcncoalition.tcnclient.crypto.MemoType
import java.util.Date

class CoronaControlTcnManager(
    private val context: Context,
    private val tcnKeys: TcnKeys
) : TcnManager(context) {
    private val advertisedTcns = mutableListOf<ByteArray>()

    override fun foregroundNotification(): NotificationCompat.Builder {
        createNotificationChannelIfNeeded()

        val notificationIntent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        return NotificationCompat.Builder(
            context,
            CHANNEL_ID
        )
            .setContentTitle(context.getString(R.string.foreground_notification_title))
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationManagerCompat.IMPORTANCE_HIGH)
            .setCategory(Notification.CATEGORY_SERVICE)
    }

    override fun generateTcn(): ByteArray {
        val tcn = tcnKeys.generateTcn()
        advertisedTcns.add(tcn)
        if (advertisedTcns.size > 65535) {
            advertisedTcns.removeAt(0)
        }
        return tcn
    }

    override fun onTcnFound(tcn: ByteArray, estimatedDistance: Double?) {
        if (advertisedTcns.contains(tcn)) return
        logTcn(tcn, estimatedDistance)
    }

    /**
     * This notification channel is only required for android versions above
     * android O. This creates the necessary notification channel for the foregroundService
     * to function.
     */
    private fun createNotificationChannelIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = ContextCompat.getSystemService(
                context, NotificationManager::class.java
            )
            manager?.createNotificationChannel(serviceChannel)
        }
    }

    override fun bluetoothStateChanged(bluetoothOn: Boolean) {
        val title = if (bluetoothOn) {
            R.string.foreground_notification_title
        } else {
            R.string.foreground_notification_ble_off
        }

        ContextCompat.getSystemService(
            context,
            NotificationManager::class.java
        )?.notify(
            NOTIFICATION_ID,
            foregroundNotification().setContentTitle(context.getString(title)).build()
        )
    }

    fun generateAndUploadReport() {
        // Create a new Signed Report with `uploadState` set to `.notUploaded` and store it in the local persistent store.
        // This will kick off an observer that watches for signed reports which were not uploaded and will upload it.
        val signedReport = SignedReport(tcnKeys.createReport("Hello, World!".toByteArray(),MemoType.CovidWatchV1))
        signedReport.isProcessed = true
        signedReport.uploadState = SignedReport.UploadState.NOTUPLOADED

        CoronaControlDatabase.databaseWriteExecutor.execute {
            val dao =
                CoronaControlDatabase.getInstance(context).signedReportDAO()
            dao.insert(signedReport)
        }
    }

    private fun logTcn(tcnBytes: ByteArray, estimatedDistance: Double?) {
        CoronaControlDatabase.databaseWriteExecutor.execute {

            val temporaryContactNumberDAO: TemporaryContactNumberDAO =
                CoronaControlDatabase.getInstance(context).temporaryContactNumberDAO()

            var tcn = temporaryContactNumberDAO.findByPrimaryKey(tcnBytes)
            if (tcn == null) {
                tcn = TemporaryContactNumber()
                tcn.bytes = tcnBytes
                if (estimatedDistance != null && estimatedDistance < tcn.closestEstimatedDistanceMeters) {
                    tcn.closestEstimatedDistanceMeters = estimatedDistance
                }
                temporaryContactNumberDAO.insert(tcn)
            } else {
                tcn.lastSeenDate = Date()
                if (estimatedDistance != null && estimatedDistance < tcn.closestEstimatedDistanceMeters) {
                    tcn.closestEstimatedDistanceMeters = estimatedDistance
                }
                temporaryContactNumberDAO.update(tcn)
            }
        }
    }

    companion object {
        private const val CHANNEL_ID = "CovidWatchContactTracingNotificationChannel"//check what this is
    }
}