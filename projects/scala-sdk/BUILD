load("@io_bazel_rules_scala//scala:scala.bzl", "scala_binary", "scala_library", "scala_test_suite", "scala_doc")
load("@io_bazel_rules_scala//scala:scala_toolchain.bzl", "scala_toolchain")

load("//:deps.bzl", "scala_version_short", "maven_deps", "maven_test_deps")

package(default_visibility = ["//visibility:public"])

scala_toolchain(
    name = "cm_well_scala_toolchain_impl",
    scalacopts = [
        "-target:jvm-1.8",
        "-encoding", "UTF-8",
        "-Xsource:{}".format(scala_version_short),
        "-Xlint",
        "-Xmacro-settings:materialize-derivations",
        "-Ypartial-unification",
        "-Ywarn-unused",
        "-Ywarn-unused-import",
        "-Ywarn-value-discard",
        "-Ywarn-dead-code",
        "-Ywarn-numeric-widen",
        "-Ywarn-adapted-args",
        "-language:existentials",
        "-language:experimental.macros",
        "-language:higherKinds",
        "-language:implicitConversions",
        "-language:postfixOps",
        "-deprecation",
        "-feature",
        "-unchecked",
    ],
    scalac_jvm_flags = ["-Xmx2G"],
    scala_test_jvm_flags = ["-Xmx4G"],
    enable_code_coverage_aspect = "on",
    unused_dependency_checker_mode = "off",
)

toolchain(
    name = "cm_well_scala_toolchain",
    toolchain = "cm_well_scala_toolchain_impl",
    toolchain_type = "@io_bazel_rules_scala//scala:toolchain_type",
)

java_library(
    name = "flatbuf",
    srcs = glib([
        "src/flatbuffers/generated/**/*.java"
    ])
)

scala_library(
    name = "lib",
    srcs = glob([
        "src/**/*.scala"
        ]),
    deps = maven_deps,
    unused_dependency_checker_mode = "off",
)

scala_test_suite(
    name = "test",
    srcs = glob([
        "test/**/*Test.scala",
    ]),
    deps = [
        ":lib",
    ] + maven_test_deps
)

scala_binary(
    name = "ingest",
    main_class = "cmwell.ingest.Main",
    deps = [
        ":lib",
    ],
    unused_dependency_checker_mode = "off",
)

scala_library(
    name = "benchmark_lib",
    srcs = glob([
        "test/benchmark/**/*.scala"
        ]),
    deps = [":lib"] + maven_deps,
    unused_dependency_checker_mode = "off",
)

scala_binary(
    name = "ingest_benchmark",
    main_class = "cmwell.ingest.benchmark.Main",
    deps = [
        ":benchmark_lib",
    ],
    unused_dependency_checker_mode = "off",
)

scala_doc(
    name = "doc",
    deps = [
        ":lib",
        ":benchmark_lib"
    ],
)
