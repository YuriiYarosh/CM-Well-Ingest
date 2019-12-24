package cmwell.ingest.formats

import java.io.File

import cmwell.ingest.RDFFormat

object NTriples extends RDFFormat {
  RDFFormat.register(NTriples.getClass.getSimpleName, NTriples)

  override def recognized(f: File): Boolean = false

  override def parser(): NTriples.Parser = {}
}
