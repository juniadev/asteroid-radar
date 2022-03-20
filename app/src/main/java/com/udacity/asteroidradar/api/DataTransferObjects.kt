package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.database.DatabaseAsteroid
import com.udacity.asteroidradar.database.DatabasePictureOfDay
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.PictureOfDay
import com.udacity.asteroidradar.utils.DateUtils

fun List<Asteroid>.asDatabaseModel(): Array<DatabaseAsteroid> {
    return this.map {
        DatabaseAsteroid(
            id = it.id,
            isPotentiallyHazardous = it.isPotentiallyHazardous,
            distanceFromEarth = it.distanceFromEarth,
            relativeVelocity = it.relativeVelocity,
            estimatedDiameter = it.estimatedDiameter,
            absoluteMagnitude = it.absoluteMagnitude,
            closeApproachDate = it.closeApproachDate,
            codename = it.codename
        )
    }.toTypedArray()
}

fun PictureOfDay.asDatabaseModel(): DatabasePictureOfDay {
    return DatabasePictureOfDay(
        mediaType = this.mediaType,
        url = this.url,
        title = this.title,
        date = DateUtils.getTodayAsString()
    )
}