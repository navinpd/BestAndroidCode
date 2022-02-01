package com.big.ds_algo.kotlin

import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime

@ExperimentalTime
class DurationLearning {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val day = 1.5.days // 1d 12h
            val hours = day.inWholeHours //36
            val seconds = 1.days.inWholeSeconds //86400
            val daysMinusHours = (1.days - 2.hours).inWholeHours //22
            val convertDuration = Duration.convert(
                value = 2.0,
                sourceUnit = DurationUnit.HOURS,
                targetUnit = DurationUnit.MINUTES
            ) // 120.0
            val isoDuration = Duration.parseIsoString("P1DT2H3M5.56S") //1d 2h 3m 5.56s
            val durationToIso = (3.days + 6.hours + 3.minutes + 20.seconds).toIsoString() //PT78H3M20S

            println("Hours $day")  //Hours 36
            println("Hours $hours")  //Hours 36
            println("Seconds $seconds") //Seconds 86400
            println("daysMinusHours $daysMinusHours") //daysMinusHours 22
            println("convertDuration $convertDuration") //convertDuration 120.0
            println("isoDuration $isoDuration") //isoDuration 1d 2h 3m 5.56s
            println("durationToIso $durationToIso") //durationToIso PT78H3M20S
        }
    }
}