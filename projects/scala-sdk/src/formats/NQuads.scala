package cmwell.ingest.formats

import java.io.File

import cmwell.ingest.RDFFormat

object NQuads extends RDFFormat {
  RDFFormat.register(NQuads.getClass.getSimpleName, NQuads)

  override def recognized(f: File): Boolean = { false }

  override def parser(): NQuads.Parser = {}

}
