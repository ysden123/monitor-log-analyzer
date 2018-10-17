/*
 * Copyright (c) 2018. Yuriy Stul 
 */

package com.stulsoft.monitor.log.analyzer

import com.typesafe.scalalogging.LazyLogging

import scala.io.StdIn

/**
  * @author Yuriy Stul
  */
object Application extends App with LazyLogging {
  logger.info("==>Application")

  println("Please enter path to log file:")

  val fileName = StdIn.readLine()
  if (fileName != null && !fileName.isEmpty) {
    Analyzer.analyze(fileName, "stats_clicks")
    Analyzer.analyze(fileName, "event_daily")
    Analyzer.analyze(fileName, "stats_conversions")
  }
  logger.info("<==Application")
}
