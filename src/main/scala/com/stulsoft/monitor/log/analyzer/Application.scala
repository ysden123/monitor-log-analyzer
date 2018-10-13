/*
 * Copyright (c) 2018. Yuriy Stul 
 */

package com.stulsoft.monitor.log.analyzer

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author Yuriy Stul
  */
object Application extends App with LazyLogging {
  logger.info("==>Application")

  val conf = new SparkConf().setAppName("MonitorLogAnalyzer").setMaster("local[*]")
  val sc = new SparkContext(conf)
  Analyzer.analyze(sc, "src/test/resources/panel-monitor-statistics.log", "stats_clicks")
  Analyzer.analyze(sc, "src/test/resources/panel-monitor-statistics.log", "event_daily")
  Analyzer.analyze(sc, "src/test/resources/panel-monitor-statistics.log", "stats_conversions")

  sc.stop()
  logger.info("<==Application")
}
