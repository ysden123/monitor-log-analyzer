/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.monitor.log.analyzer

import org.scalatest.FunSuite

/**
  * @author Yuriy Stul
  */
class AnalyzerTest extends FunSuite {
  test("testAnalyze stats_clicks") {
    Analyzer.analyze("panel-monitor-statistics.log", "stats_clicks")
  }

  test("testAnalyze event_daily") {
    Analyzer.analyze("panel-monitor-statistics.log", "event_daily")
  }
  test("testAnalyze stats_conversions") {
    Analyzer.analyze("panel-monitor-statistics.log", "stats_conversions")
  }

}
