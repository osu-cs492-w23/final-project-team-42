package com.example.team42fitness.data.locationLookback

/**
 * For use with LocationAdapter to represent a single date.
 * Passing this when a particular day is clicked in LocationFragment.kt,
 * as the only data we know that will be passed to ClickDayFragment would
 * be the particular day. LocationData is for ClickedDayFragment.
 */
data class LocationDate(val date: String) : java.io.Serializable