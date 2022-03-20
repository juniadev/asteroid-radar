package com.udacity.asteroidradar.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.domain.PictureOfDay

@Dao
interface AsteroidDao {

    @Query("SELECT * FROM DatabaseAsteroid WHERE closeApproachDate >= :closeApproachDate " +
                 "ORDER BY closeApproachDate")
    fun getAsteroidsAfterDate(closeApproachDate: String): LiveData<List<DatabaseAsteroid>>

    @Query("SELECT * FROM DatabaseAsteroid ORDER BY closeApproachDate")
    fun getAllAsteroids(): LiveData<List<DatabaseAsteroid>>

    @Query("SELECT * FROM DatabaseAsteroid WHERE closeApproachDate = :closeApproachDate")
    fun getAsteroidsWithDate(closeApproachDate: String): LiveData<List<DatabaseAsteroid>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroids: DatabaseAsteroid)

    @Query("DELETE FROM DatabaseAsteroid WHERE closeApproachDate < :closeApproachDate")
    fun deleteAsteroidsBefore(closeApproachDate: String)

}

@Dao
interface PictureOfDayDao {

    @Query("SELECT * FROM DatabasePictureOfDay WHERE date = :date")
    fun getPictureOfDay(date: String): LiveData<DatabasePictureOfDay>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(databasePictureOfDay: DatabasePictureOfDay)

    @Query("DELETE FROM DatabasePictureOfDay WHERE date < :date")
    fun deletePicturesBefore(date: String)
}

@Database(entities = [DatabaseAsteroid::class, DatabasePictureOfDay::class], version = 1)
abstract class AsteroidsDatabase : RoomDatabase() {
    abstract val asteroidDao: AsteroidDao
    abstract val pictureOfDayDao: PictureOfDayDao
}

private lateinit var INSTANCE: AsteroidsDatabase

fun getDatabase(context: Context): AsteroidsDatabase {
    synchronized(AsteroidsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                                            AsteroidsDatabase::class.java,
                                            "asteroids").build()
        }
    }
    return INSTANCE
}