/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.monitor.log.analyzer.util

import scala.io.Source
import scala.util.{Failure, Try}

/**
  * @author Yuriy Stul
  */
object Utils {
  /**
    * Returns a Source from specified file name.
    *
    * @param name specifies the file; it may be a file in resources and in JAR as well, or it may be any file
    * @return the Source from specified file name.
    */
  def source(name: String): Try[Source] = {
    try {
      val resourceStream = getClass.getClassLoader.getResourceAsStream(name)
      if (resourceStream != null) {
        Try(Source.fromInputStream(resourceStream))
      } else {
        Try(Source.fromFile(name))
      }
    }
    catch {
      case e: Exception => Failure(e)
    }
  }
}
