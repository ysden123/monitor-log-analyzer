/*
 * Copyright (c) 2018. Yuriy Stul 
 */

package com.stulsoft.monitor.log.analyzer

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.{SparkConf, SparkContext}

import scala.io.StdIn

/**
  * @author Yuriy Stul
  */
object Application extends App with LazyLogging {
  logger.info("==>Application")

  val conf = new SparkConf().setAppName("MonitorLogAnalyzer").setMaster("local[*]")
  val sc = new SparkContext(conf)
  println("Please enter path to log file:")

  val fileName = StdIn.readLine()
  if (fileName != null && !fileName.isEmpty) {
    Analyzer.analyze(sc, fileName, "stats_clicks")
    Analyzer.analyze(sc, fileName, "event_daily")
    Analyzer.analyze(sc, fileName, "stats_conversions")
  }
  sc.stop()
  logger.info("<==Application")
}
