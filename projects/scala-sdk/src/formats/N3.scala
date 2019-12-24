package cmwell.ingest.formats

import java.io.File

import cmwell.ingest.RDFFormat

object N3 extends RDFFormat {
  RDFFormat.register(N3.getClass.getSimpleName, N3)

  override def recognized(f: File): Boolean = false

  RDFFormat.register("N3", N3)

  override def parser(): N3.Parser = {}
}
