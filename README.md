# [CM-Well](https://github.com/CM-Well/CM-Well) Ingest Tool

[![Maintainability](https://api.codeclimate.com/v1/badges/2d9f113b82dc0b06469b/maintainability)](https://codeclimate.com/github/YuriiYarosh/CM-Well-Ingest/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/2d9f113b82dc0b06469b/test_coverage)](https://codeclimate.com/github/YuriiYarosh/CM-Well-Ingest/test_coverage)

CM-Well Ingest Tool - uploads graph data into the [CM-Well](https://cm-well.github.io/CM-Well/index.html) cluster.

# Status: WIP

### Contents:

 * [Usage](https://github.com/YuriiYarosh/CM-Well-Ingest#Usage)
 * [Installation](https://github.com/YuriiYarosh/CM-Well-Ingest#Installation)
 * [Building](https://github.com/YuriiYarosh/CM-Well-Ingest#Building)
 * [Testing](https://github.com/YuriiYarosh/CM-Well-Ingest#Testing)
 * [Benchmarks](https://github.com/YuriiYarosh/CM-Well-Ingest#Benchmarks)
 * [Development](https://github.com/YuriiYarosh/CM-Well-Ingest#Development)
 * [Contributing](https://github.com/YuriiYarosh/CM-Well-Ingest#Contributing)
 * [License](https://github.com/YuriiYarosh/CM-Well-Ingest#License)

## Usage

## Installation

Feel free to
 * use a [docker image]() as follows

 ```bash
docker run -it yuriiyarosh/cmwell_ingest:1.0 help
```
or
```bash
docker run -it yuriiyarosh/cmwell_ingest_jvm:1.0 help
```

 * we also support [homebrew](https://brew.sh/) for macOS

```bash
brew tap yuriiyarosh/formulas
brew install yuriiyarosh/formulas/cmwell_ingest
```
* or just download the binary from the [releases page]()

## Building

### Docker images

To build the respective docker images you'll need

 * [JDK or JRE](https://adoptopenjdk.net/)
 * [Docker](https://docs.docker.com/v17.12/docker-for-mac/install)
 
See the [Development](https://github.com/YuriiYarosh/CM-Well-Ingest#development) section bellow for details.

**Make sure** you've [enabled](https://i.imgur.com/jQ4WFj1.png) docker [experimental features](https://docs.docker.com/assemble/install/), 
it's needed only for image [layers squashing](https://docs.docker.com/engine/reference/commandline/build/#squash-an-images-layers---squash-experimental).  

Build all the images manually with
```bash
cd images
export SECURITY_UPDATE="19.12.2019" # set to different date to initiate a security update
alias dockersec="docker build --build-arg SECURITY_UPDATE=\$SECURITY_UPDATE"
dockersec -t yuriiyarosh/openj9:8u232 openj9
dockersec -t yuriiyarosh/graalvm:19.3.0 graalvm
dockersec -t yuriiyarosh/bazel:2.0.0 bazel
dockersec -t yuriiyarosh/cmwell_ingest:1.0.0 cmwell_ingest
dockersec -t yuriiyarosh/cmwell_ingest_jvm:1.0.0 cmwell_ingest_jvm
dockersec -t yuriiyarosh/cmwell_ingest_test:1.0.0 cmwell_ingest_test
dockersec -t yuriiyarosh/cmwell_ingest_benchmark:1.0.0 cmwell_ingest_benchmark
```

### With [Bazel](https://bazel.build/)

In addition to JDK you'll need 
 * [Bazel](https://bazel.build/)
 * [GraalVM](https://www.graalvm.org/)
 
 See the [Development](https://github.com/YuriiYarosh/CM-Well-Ingest#development) section bellow for details.


for bazel builds

## Testing

## Benchmarks

## Development

The usual development dependencies for macOS are

 * [Homebrew](https://brew.sh/)
 * [JDK](https://adoptopenjdk.net/)
 * [Bazel](https://bazel.build/)
 * [GraalVM](https://www.graalvm.org/)
 
 and optionally

 * [Docker](https://docs.docker.com/v17.12/docker-for-mac/install)

to build the docker images

```bash
# JDK

brew tap AdoptOpenJDK/openjdk
brew cask install adoptopenjdk8-openj9-large

echo -e "\nexport JAVA_HOME=`/usr/libexec/java_home -v 1.8`" >> ~/.zshrc
echo -e "export PATH=\"\$PATH:\$JAVA_HOME/bin\"" >> ~/.zshrc

# Bazel

brew tap bazelbuild/tap
brew install bazelbuild/tap/bazel

# GraalVM

brew tap graalvm/tap
brew cask install graalvm-ce-java8

# The following prevents `Xcode version must be specified to use an Apple CROSSTOOL`
# according to #4313 https://github.com/bazelbuild/bazel/issues/4314#issuecomment-370172472

sudo xcode-select -s /Applications/Xcode.app/Contents/Developer
sudo xcodebuild -license

# docker to build the respective container images 

brew cask install docker
```

* replace `echo -e >> ~/.zshrc` with `echo -e "..." >> ~/.bashrc` if you're using **bash**

  [oh my zsh](https://ohmyz.sh/) with [powerlevel10k](https://github.com/romkatv/powerlevel10k) are advised

The general Development Setup on Windows and Linux should be similar.

Assuming current GraalVM version 19.3.0, install `native-image` tool with
```bash
GRAAL_VERSION="19.3.0"
/Library/Java/JavaVirtualMachines/graalvm-ce-java8-$GRAAL_VERSION/Contents/Home/bin/gu install native-image
```

### [Idea](https://www.jetbrains.com/idea/)

Just install the Idea [Bazel plugin](https://plugins.jetbrains.com/plugin/8609-bazel/).
and import [.bazelproject](.bazelproject) project view file.

Make sure correct Bazel path had been specified in

`Settings > Other Settings > Bazel Settings = /usr/local/bin/bazel`

according to [#88](https://github.com/bazelbuild/intellij/issues/88#issuecomment-369848097), you'll get `Sync Failed` error otherwise.

There's no need to import [BUILD](BUILD) file directly, but feel free to do so if it's suites your tastes.

### [Scalafmt](https://scalameta.org/scalafmt/)

**Idea Bundled** scalafmt is outdated, and auto-update is broken atm.

Up-to-date [nailgun enabled](http://www.martiansoftware.com/nailgun/) scalafmt installation is advised,
due to scalafmt benefiting from JIT, this method also works great for editors like [VSCode](https://code.visualstudio.com/).

Feel free to install nailgun and coursier using following

```bash
brew install nailgun

brew tap coursier/formulas
brew install coursier/formulas/coursier

coursier bootstrap --standalone org.scalameta:scalafmt-cli_2.12:2.3.2 \
  -r sonatype:snapshots \
  -o /usr/local/bin/scalafmt_ng -f --main com.martiansoftware.nailgun.NGServer

ng org.scalafmt.cli.Cli --version # should be 2.3.2

cat <<EOT >> /usr/local/bin/scalafmt
#!/usr/bin/env bash
[ \`ps aux | grep scalafmt_ng | grep jar | wc -l\` = "1" ] || \
  /usr/local/bin/scalafmt_ng &
exec ng org.scalafmt.cli.Cli "\$@"
EOT

chmod u+x /usr/local/bin/scalafmt
```

This will create `/usr/local/bin/scalafmt_ng` and `/usr/local/bin/scalafmt` wrapper scripts,
so you can setup efficient [On Save Formatting](https://i.imgur.com/6qbZbpH.png) with the [File Watchers](https://plugins.jetbrains.com/plugin/7177-file-watchers)
Idea plugin afterwards.

### [Scalastyle](http://www.scalastyle.org)

[Scalastyle](http://www.scalastyle.org) does not support [nailgun](https://github.com/facebook/nailgun) atm,
but that's ok, because it works better as a precommit [git hook](https://www.atlassian.com/git/tutorials/git-hooks).

```bash
coursier bootstrap --standalone org.scalastyle:scalastyle_2.12:1.0.0 \
  -r sonatype:snapshots \
  -o /usr/local/bin/scalastyle -f --main org.scalastyle.Main

/usr/local/bin/scalastyle -c scalastyle-config.xml src test
```

## About CM-Well project

You can learn more at [CM-Well documentation site](https://cm-well.github.io/CM-Well/index.html).

## Contributing

[CM-Well Ingest Tool](https://github.com/YuriiYarosh/CM-Well-Ingest) shares [contribution terms](https://github.com/CM-Well/CM-Well/blob/master/CONTRIBUTING.md) with the [CM-Well](https://github.com/CM-Well/CM-Well) Project.

Contribution terms are not completely established at the moment and will change over time.

## License

[CM-Well-Ingest](.) project is licensed under [Apache 2.0 License](LICENSE).
