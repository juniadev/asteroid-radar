package com.udacity.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.repository.AsteroidsRepository
import timber.log.Timber

class RefreshDataWork(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val asteroidsRepository = AsteroidsRepository(database)

        return try {
            asteroidsRepository.refreshData()
            Result.success()
        } catch (e: Exception) {
            Timber.e(e, "Error refreshing data.")
            Result.retry()
        }
    }

    companion object {
        const val WORK_NAME = "RefreshDataWork"
    }
}