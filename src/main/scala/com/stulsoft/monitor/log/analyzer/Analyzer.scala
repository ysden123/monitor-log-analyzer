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
  def analyze(fileName: String, statisticsName: String): Unit = {
    logger.info(s"Analyzing $fileName file for $statisticsName")
    var source: Source = null
    try {
      val processor = Processor()
      source = Utils.source(fileName).get
      val iterator = source.getLines()
      while (iterator.hasNext) {
        iterator.next() // Skip 1st line
        val statisticsName = iterator.next().toString.replace(":", "")
        val date = iterator.next().toString
          .replace(" time = ", "")
          .replace(".", "")
        iterator.next() // Skip previous value
        val currentValue = iterator.next().toString
          .replace(" current value = ", "")
          .replace(",", "")
        if (statisticsName.startsWith(statisticsName))
          processor.process(Status(date, currentValue.toLong))

        iterator.next() // Skip difference
        iterator.next() // Skip average difference
        iterator.next() // Skip number of measurements
        iterator.next() // Skip empty line
      }

      val count = processor.recordNumber()
      val min = processor.minStatus()
      val max = processor.maxStatus()
      val average = processor.averageValue()
      val result = if (count > 0
        && max.isDefined
        && min.isDefined
        && average.isDefined) {
        s"Number of records = $count" +
          s", min: at ${min.get.date} - ${min.get.value}" +
          s", max: at ${max.get.date} - ${max.get.value}" +
          f", average = ${average.get}%.2f"
      } else
        "No records fo analysis"
      logger.info(result)
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
