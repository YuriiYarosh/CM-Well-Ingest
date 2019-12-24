package cmwell.ingest

import java.io.File

import scala.collection.mutable

trait RDFFormat {
  def recognized(f: File): Boolean
  def parser(): Parser

  trait Parser {}

  class Data {}
}

object RDFFormat {
  private val knownFormats = new mutable.HashMap[String, RDFFormat]()
  def register(name: String, f: RDFFormat) = knownFormats.getOrElseUpdate(name, f)

  def guess(file: File): Option[RDFFormat] =
    RDFFormat.knownFormats.filter { case ((_, format)) => format.recognized(file) }.collectFirst {
      case ((_, format)) => format
    }
}
