package com.atlascc.kontribute.util

class TimeUtil {

    companion object {

        fun getFormattedDisplayTime(hr: Int, min: Int): String {
            var hours = hr
            var timeSet = ""
            when {
                hours > 12 -> {
                    hours -= 12
                    timeSet = "PM"
                }
                hours == 0 -> {
                    hours += 12
                    timeSet = "AM"
                }
                hours == 12 -> timeSet = "PM"
                else -> timeSet = "AM"
            }

            var minutes = ""
            minutes = if (min < 10)
                "0$min"
            else
                min.toString()

            return StringBuilder().append(hours).append(':').append(minutes).append(" ").append(timeSet).toString()
        }
    }
}