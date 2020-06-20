package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.tcncoalition.tcnclient.crypto.TemporaryContactNumber
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [TemporaryContactNumber::class, SignedReport::class], version = 6, exportSchema = false)
abstract class CoronaControlDatabase : RoomDatabase() {

    abstract fun temporaryContactNumberDAO(): TemporaryContactNumberDAO
    abstract fun signedReportDAO(): SignedReportDAO

    companion object {
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor: ExecutorService =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        @Volatile
        private var INSTANCE: CoronaControlDatabase? = null

        fun getInstance(context: Context): CoronaControlDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CoronaControlDatabase::class.java, "covidwatch.db"
            ).fallbackToDestructiveMigration().build()
    }
}