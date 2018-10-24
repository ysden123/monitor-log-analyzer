/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.monitor.log.analyzer

import scala.collection.mutable

/**
  * @author Yuriy Stul
  */
class Processor {
  private val statisticsSummaries = mutable.HashMap.empty[String, StatisticsSummary]

  def process(status: Status): Unit = {
    val currentStatisticsSummary = statisticsSummaries.get(status.statisticsName)
    if (currentStatisticsSummary.isDefined) {
      val theCurrentStatisticsSummary = currentStatisticsSummary.get
      theCurrentStatisticsSummary.total += status.value
      theCurrentStatisticsSummary.count += 1

      if (theCurrentStatisticsSummary.min.value > status.value)
        theCurrentStatisticsSummary.min = status

      if (theCurrentStatisticsSummary.max.value < status.value)
        theCurrentStatisticsSummary.max = status
    } else {
      statisticsSummaries(status.statisticsName) = StatisticsSummary(status.statisticsName, status, status, 0.0, status.value, 1)
    }
  }

  def result(): List[StatisticsSummary] = {
    statisticsSummaries.foreach(statisticsSummary => {
      statisticsSummary._2.average = statisticsSummary._2.total / statisticsSummary._2.count
    })
    statisticsSummaries.values.toList
  }
}


object Processor {
  def apply(): Processor = new Processor()
}
