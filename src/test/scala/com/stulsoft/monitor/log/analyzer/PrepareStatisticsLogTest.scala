/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.monitor.log.analyzer

import java.io.File

import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

/**
  * @author Yuriy Stul
  */
class PrepareStatisticsLogTest extends FunSuite with Matchers {

  test("testPrepare: stats_clicks") {
    val tempFileName = PrepareStatisticsLog("panel-monitor-statistics.log").prepare("stats_clicks")
    tempFileName.isSuccess shouldBe true
//    println(s"Temp file name is ${tempFileName.get}")
    new File(tempFileName.get).delete()
  }

  test("testPrepare: event_daily") {
    val tempFileName = PrepareStatisticsLog("panel-monitor-statistics.log").prepare("event_daily")
    tempFileName.isSuccess shouldBe true
//    println(s"Temp file name is ${tempFileName.get}")
    new File(tempFileName.get).delete()
  }

  test("testPrepare: stats_conversions") {
    val tempFileName = PrepareStatisticsLog("panel-monitor-statistics.log").prepare("stats_conversions")
    tempFileName.isSuccess shouldBe true
//    println(s"Temp file name is ${tempFileName.get}")
    new File(tempFileName.get).delete()
  }
}
