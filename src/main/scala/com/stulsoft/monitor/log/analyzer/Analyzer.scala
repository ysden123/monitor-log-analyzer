/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.monitor.log.analyzer

import java.io.File

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.SparkContext

import scala.util.{Failure, Success}

/**
  * @author Yuriy Stul
  */
object Analyzer extends LazyLogging {
  def analyze(sc: SparkContext, fileName: String, statisticsName: String): Unit = {
    logger.info(s"Analyzing $fileName file for $statisticsName")
    PrepareStatisticsLog(fileName).prepare(statisticsName) match {
      case Success(tempFileName) =>
        //        logger.debug("Prepared {} file", tempFileName)
        try {
          val data = sc.textFile(tempFileName)
            .map(line => line.split('|'))
            .map(record => Status(record(0), record(1).toLong))

          val numberOfRecords = data.count
          val total = data.map(status => status.value).sum
          val minRecord = data.fold(Status("", Long.MaxValue))((acc, current) => {
            if (acc.value > current.value)
              current
            else
              acc
          })

          val maxRecord = data.fold(Status("", Long.MinValue))((acc, current) => {
            if (acc.value < current.value)
              current
            else
              acc
          })

          val result = s"Number of records = $numberOfRecords" +
            s", min: at ${minRecord.date} - ${minRecord.value}" +
            s", max: at ${maxRecord.date} - ${maxRecord.value}" +
            f", average = ${total / numberOfRecords}%.2f"
          logger.info(result)

          new File(tempFileName).delete()
        } catch {
          case e: Exception =>
            logger.error(s"Failed analyzing. Error: ${e.getMessage}", e)
        }
      case Failure(error) =>
        logger.error(s"Failed to prepare data. Error: ${error.getMessage}", error)
    }
  }
}
