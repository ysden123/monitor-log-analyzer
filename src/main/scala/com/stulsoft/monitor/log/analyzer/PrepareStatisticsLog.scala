/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.monitor.log.analyzer

import java.io.{File, PrintWriter}

import com.stulsoft.monitor.log.analyzer.util.Utils

import scala.util.Try

/**
  * @author Yuriy Stul
  */
case class PrepareStatisticsLog(name: String) {
  def prepare(statisticsNamePrefix: String): Try[String] = {
    Try {
      val source = Utils.source(name).get
      val tempFileName = File.createTempFile("mla", ".txt").getAbsolutePath
      val iterator = source.getLines()
      val tempFile = new PrintWriter(new File(tempFileName))
      try {
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
          iterator.next() // Skip difference
          iterator.next() // Skip average difference
          iterator.next() // Skip number of measurements
          iterator.next() // Skip empty line
          if (statisticsName.startsWith(statisticsNamePrefix))
            tempFile.printf("%s%n", s"$date|$currentValue")
        }
      } catch {
        case _: Exception =>
      }
      tempFile.close()
      tempFileName
    }
  }

}
