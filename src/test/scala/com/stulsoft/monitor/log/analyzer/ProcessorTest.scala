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

    processor.process(Status("time1", 1))
    processor.process(Status("time2", 2))
    processor.process(Status("time3", 3))
    processor.minStatus() shouldBe Some(Status("time1", 1))
    processor.maxStatus() shouldBe Some(Status("time3", 3))
    processor.averageValue() shouldBe Some(2.0)

  }

}
