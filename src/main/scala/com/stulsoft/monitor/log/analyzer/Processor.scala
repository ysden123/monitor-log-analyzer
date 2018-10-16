/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.monitor.log.analyzer

/**
  * @author Yuriy Stul
  */
class Processor {

  private var min: Option[Status] = None
  private var max: Option[Status] = None
  private var average: Double = _
  private var total: Long = _
  private var count: Long = _

  def process(status: Status): Unit = {
    if (min.isEmpty)
      min = Some(status)
    else if (min.get.value > status.value)
      min = Some(status)

    if (max.isEmpty)
      max = Some(status)
    else if (max.get.value < status.value)
      max = Some(status)

    total += status.value
    count += 1
  }

  def minStatus(): Option[Status] = min

  def maxStatus(): Option[Status] = max

  def averageValue(): Option[Double] = {
    if (count > 0)
      Some(total / count)
    else
      None
  }
}

object Processor {
  def apply(): Processor = new Processor()
}
