/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.monitor.log.analyzer

import com.stulsoft.monitor.log.analyzer.util.Utils
import com.typesafe.scalalogging.LazyLogging

import scala.io.Source

/**
  * @author Yuriy Stul
  */
object Analyzer extends LazyLogging {
  def analyze(fileName: String): Unit = {
    logger.info(s"""Analyzing "$fileName" file""")
    var source: Source = null
    try {
      val processor = Processor()
      source = Utils.source(fileName).get
      val iterator = source.getLines()
      while (iterator.hasNext) {
        iterator.next() // Skip 1st line
        val currentStatisticsName = iterator.next().toString.replace(":", "")
        val date = iterator.next().toString
          .replace(" time = ", "")
          .replace(".", "")
        iterator.next() // Skip previous value
        val currentValue = iterator.next().toString
          .replace(" current value = ", "")
          .replace(",", "")
        val theStatisticsName =
          if (currentStatisticsName.startsWith("stats_clicks"))
            "stats_clicks"
          else
            currentStatisticsName
        processor.process(Status(theStatisticsName, date, currentValue.toLong))

        iterator.next() // Skip difference
        iterator.next() // Skip average difference
        iterator.next() // Skip number of measurements
        iterator.next() // Skip empty line
      }

      processor.result().foreach(result=>{
        val resultText = s"Statistics name: ${result.statisticsName}" +
          s", number of records = ${result.count}" +
          s", min: at ${result.min.date} = ${result.min.value}" +
          s", max: at ${result.max.date} = ${result.max.value}" +
          f", average =  ${result.average}%.2f"
        logger.info(resultText)
      })
    }
    catch {
      case e: Exception =>
        logger.error(s"Failed analyzing. error: ${e.getMessage}", e)
    }
    finally {
      if (source != null) source.close()
    }
  }
}
