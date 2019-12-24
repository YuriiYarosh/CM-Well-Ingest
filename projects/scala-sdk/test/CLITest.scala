package cmwell.ingest

import org.scalatest._

import scala.collection.immutable.HashMap

class CLITest extends FlatSpec {
  "CLI" should "be able to parse args" in {
    val testTable = HashMap(
      List("", "") -> CLI.Config()
    )

    testTable.foreach { case (args, conf) => assert(CLI.parse(args) === conf) }
  }
}
