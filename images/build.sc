interp.load.ivy(
  "com.lihaoyi" %
    s"ammonite-shell_${scala.util.Properties.versionNumberString}" %
    ammonite.Constants.version
)
@
val shellSession = ammonite.shell.ShellSession()
import shellSession._
import ammonite.ops._
import ammonite.shell._

val dockerHubOrg = "yuriiyarosh"

List(
  ("openj9", "8u232"),
//  ("graalvm", "latest"),
//  ("cmwell_ingest_jvm", "latest"),
//  ("cmwell_ingest", "latest"),
).foreach {
  case (name, version) => %docker("build", "-t", s"${dockerHubOrg}/${name}:${version}", name)
}
