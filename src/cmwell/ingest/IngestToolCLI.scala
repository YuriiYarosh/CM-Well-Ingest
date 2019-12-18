package cmwell.ingest

import scopt.OParser

object IngestToolCLI {

  case class Config() {}

  val builder = OParser[Config]

  val parser = {
    import builder._
    OParser.sequence(
      programName("cm-ingest"),
      head("cm-ingest", "1.x"),
      arg[File]("<file>...")
        .unbounded()
        .action((x, c) => c.copy(files = c.files :+ x))
        .text("input files"),
      note("some notes." + sys.props("line.separator")),
      cmd("injest")
        .action((_, c) => c.copy(mode = "update"))
        .text("update is a command.")
        .children(
          opt[Int]("number of streams")
            .abbr("nk")
            .action((_, c) => c.copy(keepalive = false))
            .text("disable keepalive"),
          opt[Boolean]("xyz")
            .action((x, c) => c.copy(xyz = x))
            .text("xyz is a boolean property"),
          opt[Unit]("debug-update")
            .hidden()
            .action((_, c) => c.copy(debug = true))
            .text("this option is hidden in the usage text"),
          checkConfig(c =>
            if (c.keepalive && c.xyz) failure("xyz cannot keep alive")
            else success
          )
        )
    )
  }
}
