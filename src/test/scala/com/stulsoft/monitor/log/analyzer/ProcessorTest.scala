/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.monitor.log.analyzer

import org.scalatest.{FunSuite, Matchers}

/**
  * @author Yuriy Stul
  */
class ProcessorTest extends FunSuite with Matchers {

  test("testProcess") {
    val processor = Processor()

    processor.process(Status("test", "time1", 1))
    processor.process(Status("test", "time2", 2))
    processor.process(Status("test", "time3", 3))
    val results = processor.result()
    results.length shouldBe 1
    results.head.min shouldBe Status("test", "time1", 1)
    results.head.max shouldBe Status("test", "time3", 3)
    results.head.average shouldBe 2.0
  }
}
