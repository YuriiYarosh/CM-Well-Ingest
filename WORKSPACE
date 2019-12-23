load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")
load("//:deps.bzl",
    "project_deps",
    "project_test_deps",
    "opencensus_deps_base",

    "scala_version_short",
    "opencensus_version",
    "scalameter_version")

## Scala ##

rules_scala_version="886bc9cf6d299545510b39b4872bbb5dc7526cb3"
rules_scala_version_sha256="b6ecd5ad81be1490408e6714914e5766bed6b25a0ca0c8671473c84bb7b3ae51"

http_archive(
    name = "io_bazel_rules_scala",
    type = "tar.gz",
    url = "https://github.com/bazelbuild/rules_scala/archive/%s.tar.gz" % rules_scala_version,
    strip_prefix = "rules_scala-%s" % rules_scala_version,
    sha256 = rules_scala_version_sha256,
)

load("@io_bazel_rules_scala//scala:scala.bzl", "scala_repositories")
scala_repositories()

load("@io_bazel_rules_scala//scala:toolchains.bzl", "scala_register_toolchains")
register_toolchains("//:cm_well_scala_toolchain")

## Maven Dependencies ##

rules_jvm_external_version = "140a3a24d38913b17051c738218ddc659ce93026"
rules_jvm_external_version_sha256 = "38f81e3d1bf38ede56aed2d34f6c4a2752c53db5506006f6f6e42c92b9538b70"

http_archive(
    name = "rules_jvm_external",
    type = "tar.gz",
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.tar.gz" % rules_jvm_external_version,
    strip_prefix = "rules_jvm_external-%s" % rules_jvm_external_version,
    sha256 = rules_jvm_external_version_sha256,
)

load("@rules_jvm_external//:defs.bzl", "maven_install", "artifact")
load("@rules_jvm_external//:specs.bzl", "maven")

## Protobuf ##

protobuf_version="fe1790ca0df67173702f70d5646b82f48f412b99"
protobuf_version_sha256="7adbf4833bc56e201db3076e864f6f4fd3043b5895e5f7e6ab953d385b49a926"

http_archive(
    name = "com_google_protobuf",
    type = "tar.gz",
    url = "https://github.com/protocolbuffers/protobuf/archive/%s.tar.gz" % protobuf_version,
    strip_prefix = "protobuf-%s" % protobuf_version,
    sha256 = protobuf_version_sha256,
)

## Protobuf rules ##

protobuf_rules_version="97d8af4dc474595af3900dd85cb3a29ad28cc313"
protobuf_rules_version_sha256="602e7161d9195e50246177e7c55b2f39950a9cf7366f74ed5f22fd45750cd208"

http_archive(
    name = "rules_proto",
    type = "tar.gz",
    sha256 = protobuf_rules_version_sha256,
    strip_prefix = "rules_proto-%s" % protobuf_rules_version,
    urls = [
        "https://mirror.bazel.build/github.com/bazelbuild/rules_proto/archive/%s.tar.gz" % protobuf_rules_version,
        "https://github.com/bazelbuild/rules_proto/archive/%s.tar.gz" % protobuf_rules_version,
    ],
)
load("@rules_proto//proto:repositories.bzl", "rules_proto_dependencies", "rules_proto_toolchains")
rules_proto_dependencies()
rules_proto_toolchains()

## Skylib ##

skylib_version = "1.0.2"
http_archive(
    name = "bazel_skylib",
    type = "tar.gz",
    urls = [
        "https://mirror.bazel.build/github.com/bazelbuild/bazel-skylib/releases/download/{}/bazel-skylib-{}.tar.gz".format (skylib_version, skylib_version),
        "https://github.com/bazelbuild/bazel-skylib/releases/download/{}/bazel-skylib-{}.tar.gz".format (skylib_version, skylib_version),
    ],
    sha256 = "97e70364e9249702246c0e9444bccdc4b847bed1eb03c5a3ece4f83dfe6abc44",
)
load("@bazel_skylib//:workspace.bzl", "bazel_skylib_workspace")
bazel_skylib_workspace()

## Project dependencies ##

load("@rules_jvm_external//:specs.bzl", "maven")

opencensus_deps_with_exclusion = [
    maven.artifact(
        group = "io.opencensus",
        artifact = dep
            .replace("io.opencensus:", "")
            .replace(":{}", ""),
        version = opencensus_version,
        exclusions = [
            maven.exclusion(group = "io.netty", artifact = "netty-codec-http2"),
        ]
    ) for dep in opencensus_deps_base
]



scalameter_dep_with_exclusion = [
    maven.artifact(
        group = "com.storm-enroute",
        artifact = "scalameter_{}".format(scala_version_short),
        version = scalameter_version,
        exclusions = [
            maven.exclusion(group = "org.mongodb", artifact = "casbah_2.112"),
        ]
    )
]

maven_install(
    artifacts = project_deps + project_test_deps + opencensus_deps_with_exclusion + scalameter_dep_with_exclusion,
    maven_install_json = "//:maven_install.json",
    repositories = [
        "https://repo1.maven.org/maven2",
        "https://jcenter.bintray.com/",
        "https://maven.google.com",
        "https://oss.sonatype.org/content/repositories/releases/"
    ],
    fetch_sources = True,
    fail_on_missing_checksum = True,
    version_conflict_policy = "pinned",
    strict_visibility = True
)

load("@maven//:defs.bzl", "pinned_maven_install")
pinned_maven_install()
