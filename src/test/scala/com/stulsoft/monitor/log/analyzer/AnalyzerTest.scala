/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.monitor.log.analyzer

import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{BeforeAndAfter, FunSuite}

/**
  * @author Yuriy Stul
  */
class AnalyzerTest extends FunSuite with BeforeAndAfter {
  var sc: SparkContext = _
  before {
    sc = new SparkContext(new SparkConf().setAppName("AnalyzerTest").setMaster("local[*]"))
  }

  after {
    sc.stop()
  }

  test("testAnalyze stats_clicks") {
    Analyzer.analyze(sc, "panel-monitor-statistics.log", "stats_clicks")
  }

  test("testAnalyze event_daily") {
    Analyzer.analyze(sc, "panel-monitor-statistics.log", "event_daily")
  }
  test("testAnalyze stats_conversions") {
    Analyzer.analyze(sc, "panel-monitor-statistics.log", "stats_conversions")
  }

}
