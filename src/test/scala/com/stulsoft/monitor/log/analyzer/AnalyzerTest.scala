/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.monitor.log.analyzer

import org.scalatest.FunSuite

/**
  * @author Yuriy Stul
  */
class AnalyzerTest extends FunSuite {
  test("testAnalyze") {
    Analyzer.analyze(List("panel-monitor-statistics.log"))
  }
}
