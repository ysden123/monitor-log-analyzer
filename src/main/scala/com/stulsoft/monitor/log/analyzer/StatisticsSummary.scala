/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.monitor.log.analyzer

/**
  * @author Yuriy Stul
  */

/**
  * Contains statistics summary for a statistics name
  *
  * @param statisticsName the statistics name
  * @param min            the Status with minimum value
  * @param max            the Status with maximum value
  * @param average        the average value
  * @param total          the total value
  * @param count          the amount of statistics
  */
case class StatisticsSummary(statisticsName: String, var min: Status,
                             var max: Status, var average: Double,
                             var total: Long, var count: Long)
