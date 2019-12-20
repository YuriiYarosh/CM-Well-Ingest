scala_version = "2.12.10"
scala_version_short = "2.12"

finch_version = "0.31.0"
circe_derivation_version = "0.12.0-M7"

scopt_version = "4.0.0-RC2"
opencensus_version = "0.24.0"

scalatest_version = "3.0.7"
scalacheck_version = "1.14.2"
discipline_version = "0.11.1"

deps_pkg="maven"

# Finch

finch_deps_base = [
    "com.github.finagle:finch-core_{}:{}",
    "com.github.finagle:finch-generic_{}:{}",
    "com.github.finagle:finch-circe_{}:{}",
    "com.github.finagle:finch-iteratee_{}:{}",
    "com.github.finagle:finch-refined_{}:{}",
    "com.github.finagle:finch-test_{}:{}"
]
finch_deps = [ dep.format(scala_version_short, finch_version) for dep in finch_deps_base ]

# Circe

circe_deps_base = [
    "io.circe:circe-derivation_{}:{}"
]
circe_deps = [ dep.format(scala_version_short, circe_derivation_version) for dep in circe_deps_base ]

# Scopt

scopt_deps_base = [
    "com.github.scopt:scopt_{}:{}"
]
scopt_deps = [ dep.format(scala_version_short, scopt_version) for dep in scopt_deps_base ]


# OpenCensus

opencensus_deps_base = [
    "io.opencensus:opencensus-api:{}",
    "io.opencensus:opencensus-impl:{}",
    "io.opencensus:opencensus-exporter-metrics-ocagent:{}",
    "io.opencensus:opencensus-exporter-trace-ocagent:{}",
    "io.opencensus:opencensus-contrib-zpages:{}"
]

# we're performing exclusion at the WORKSPACE, so there's no need in `opencensus_deps` here

# Scala

scala_deps_base = [
    "org.scala-lang:scala-reflect:{}".format(scala_version),
    "org.scala-lang:scala-library:{}".format(scala_version),
]
scala_deps = [ dep.format(scala_version_short, scala_version) for dep in scala_deps_base ]

# Project Deps

project_deps = scala_deps  + scopt_deps + circe_deps + finch_deps

# Test deps

project_test_deps = [
    "org.scalacheck:scalacheck_{}:{}".format(scala_version_short, scalacheck_version),
    "org.scalatest:scalatest_{}:{}".format(scala_version_short, scalatest_version),
    "org.typelevel:discipline_{}:{}".format(scala_version_short, discipline_version)
]

# Target Maven

maven_deps = [ # formats to "@maven//:com_github_finagle_finch_core_2_13"
    "@{}//:{}".format(deps_pkg,
        dep.replace("_{}:{}", "_{}".format(scala_version_short))
            .replace(":{}", "")
            .replace(":", "_")
            .replace("-", "_")
            .replace(".", "_")
    ) for dep in scala_deps_base + opencensus_deps_base + scopt_deps_base + circe_deps_base + finch_deps_base
]

maven_test_deps = [
    dep.format(deps_pkg, scala_version_short)
        .replace(".", "_") for dep in [
        "@{}//:org_scalacheck_scalacheck_{}",
        "@{}//:org_scalatest_scalatest_{}",
        "@{}//:org_typelevel_discipline_{}"
    ]
]
