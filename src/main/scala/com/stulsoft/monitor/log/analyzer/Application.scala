/*
 * Copyright (c) 2018. Yuriy Stul 
 */

package com.stulsoft.monitor.log.analyzer

import com.typesafe.scalalogging.LazyLogging

import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

/**
  * @author Yuriy Stul
  */
object Application extends App with LazyLogging {
  logger.info("==>Application")

  val fileNames = ArrayBuffer.empty[String]

  /**
    * Reads a file name, adds it to fileNames, and returns true, if to continue
    *
    * @return true, if to continue; otherwise - false
    */
  def readFileName(): Boolean = {
    println("Please enter path to log file or empty line to exit:")
    val fileName = StdIn.readLine()
    if (fileName == null || fileName.isEmpty)
      false
    else {
      fileNames += fileName
      true
    }
  }

  // Prepare file list
  while (readFileName()) {}

  // Analyze
  if (fileNames.nonEmpty)
    Analyzer.analyze(fileNames)

  logger.info("<==Application")
}
