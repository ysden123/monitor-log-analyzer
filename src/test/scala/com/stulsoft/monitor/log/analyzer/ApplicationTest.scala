/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.monitor.log.analyzer

import com.stulsoft.monitor.log.analyzer.Application.analyze
import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{BeforeAndAfter, BeforeAndAfterEach, FunSuite}

/**
  * @author Yuriy Stul
  */
class ApplicationTest extends FunSuite with BeforeAndAfter {
  var sc: SparkContext = _
  before {
    sc = new SparkContext(new SparkConf().setAppName("ApplicationTest").setMaster("local[*]"))
  }

  after {
    sc.stop()
  }

  test("testAnalyze stats_clicks") {
    analyze("src/test/resources/panel-monitor-statistics.log", "stats_clicks")
  }

  test("testAnalyze event_daily") {
    analyze("src/test/resources/panel-monitor-statistics.log", "event_daily")
  }
  test("testAnalyze stats_conversions") {
    analyze("src/test/resources/panel-monitor-statistics.log", "stats_conversions")
  }

}
