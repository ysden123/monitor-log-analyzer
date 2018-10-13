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
            .map(record => (record(0), record(1).toLong))

          val numberOfRecords = data.count
          val total = data.map(record => record._2).sum
          val minRecord = data.fold(("", Long.MaxValue))((acc, value) => {
            if (acc._2 > value._2)
              value
            else
              acc
          })

          val maxRecord = data.fold(("", Long.MinValue))((acc, value) => {
            if (acc._2 < value._2)
              value
            else
              acc
          })

          val result = s"Number of records = $numberOfRecords" +
            s", min: at ${minRecord._1} - ${minRecord._2}" +
            s", max: at ${maxRecord._1} - ${maxRecord._2}" +
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
