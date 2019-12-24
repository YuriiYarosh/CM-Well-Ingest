package cmwell.ingest

import java.io.File
import java.net.URL

import scopt.OParser

object Main extends App {

  object CLI {
    val cpuCores: Int = Runtime.getRuntime.availableProcessors()

    val defaultZPagesPort = 90191
    val defaultCMWellPort = 9101
    val defaultTracingAgentPort = 9092

    case class Config(
                       files: List[File] = Array().toList,
                       streams: Int = cpuCores * 2,
                       cmwellAPIEndpoint: URL = new URL(s"https://127.0.0.1:$defaultCMWellPort"),
                       verbose: Boolean = false,
                       benchmark: Boolean = false,
                       zPagesPort: Int = defaultZPagesPort,
                       trace: Boolean = false,
                       tracingEndpoint: URL = new URL(s"grpcs://127.0.0.1:$defaultTracingAgentPort"))

    private val builder = OParser.builder[Config]

    val parser = {
      import builder._
      OParser.sequence(
        programName("cmwell_ingest"),
        head("cmwell_ingest", "1.x"),
        opt[Int]("number of streams")
          .abbr("st")
          .action((s, c) => c.copy(streams = if (s <= 0) cpuCores * 2 else s))
          .text("number of the ingest streams (number of CPU cores by default)"),
        opt[URL]("api-endpoint")
          .abbr("api")
          .hidden()
          .action((endpoint, c) => c.copy(tracingEndpoint = endpoint))
          .text("CM-Well API endpoint URL"),
        opt[Unit]("verbose")
          .action((_, c) => c.copy(verbose = true))
          .text("this option is hidden in the usage text"),
        opt[Unit]("benchmark")
          .abbr("bench")
          .hidden()
          .action((_, c) => c.copy(trace = true))
          .text("enable benchmarking, does not ingest the actual data or perform any CM-Well connectivity"),
        opt[Unit]("trace")
          .hidden()
          .action((_, c) => c.copy(trace = true))
          .text("provide tracing data"),
        opt[URL]("trace-with")
          .abbr("with")
          .hidden()
          .action((endpoint, c) => c.copy(tracingEndpoint = endpoint))
          .text("OpenCensus agent endpoint URL"),
        opt[Int]("zPages port")
          .abbr("zport")
          .action((s, c) => c.copy(streams = if (s <= 0) cpuCores * 2 else s))
          .text("OpenCensus zPages port"),
        note("notes" + sys.props("line.separator")),
        arg[File]("<file>...")
          .unbounded()
          .action((x, c) => c.copy(files = c.files :+ x))
          .text("input files"),
        checkConfig { config =>
          val notExists = config.files.filter { !_.exists() }
          if (notExists.nonEmpty) {
            val sep = if (notExists.length > 1) "," else ""
            failure(s"missing: \n${notExists.mkString(sep)}")
          } else success
        }
      )
    }

    def parse(args: List[String]): Option[Config] = OParser.parse(parser, args, CLI.Config())
  }

}
