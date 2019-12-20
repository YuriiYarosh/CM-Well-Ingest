# CM-Well-Ingest

CM-Well Ingest Tool - uploads graph data into the [CM-Well](https://cm-well.github.io/CM-Well/index.html) cluster.


Contents:

 * [Usage](https://github.com/YuriiYarosh/CM-Well-Ingest#Usage)
 * [Installation](https://github.com/YuriiYarosh/CM-Well-Ingest#Installation)
 * [Building](https://github.com/YuriiYarosh/CM-Well-Ingest#Building)
 * [Testing](https://github.com/YuriiYarosh/CM-Well-Ingest#Testing)
 * [Benchmarks](https://github.com/YuriiYarosh/CM-Well-Ingest#Benchmarks)
 * [Development](https://github.com/YuriiYarosh/CM-Well-Ingest#Development)
 * [Contributing](https://github.com/YuriiYarosh/CM-Well-Ingest#Contributing)
 * [License](https://github.com/YuriiYarosh/CM-Well-Ingest#License)

# Status: WIP

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
 * Optionally [Ammonite](https://ammonite.io/)
 
See the [Development](https://github.com/YuriiYarosh/CM-Well-Ingest#development) section bellow for details.

**Make sure** you've [enabled](https://i.imgur.com/jQ4WFj1.png) docker [experimental features](https://docs.docker.com/assemble/install/), 
it's needed only for image [layers squashing](https://docs.docker.com/engine/reference/commandline/build/#squash-an-images-layers---squash-experimental).  

 ```bash
amm images/build.sc
```
will build all the respective docker images. 

Feel free to build all the images manually with
```bash
cd images
docker build -t yuriiyarosh/openj9:8u232 openj9
docker build -t yuriiyarosh/graalvm:19.3.0 graalvm
docker build -t yuriiyarosh/bazel:2.0.0 bazel
docker build -t yuriiyarosh/cmwell_ingest:1.0.0 cmwell_ingest
docker build -t yuriiyarosh/cmwell_ingest_jvm:1.0.0 cmwell_ingest_jvm
docker build -t yuriiyarosh/cmwell_ingest_test:1.0.0 cmwell_ingest_test
docker build -t yuriiyarosh/cmwell_ingest_benchmark:1.0.0 cmwell_ingest_benchmark
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
 * [Ammonite](https://ammonite.io/)

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

# Ammonite and docker to build the images 

brew cask install docker

sudo sh -c '(echo "#!/usr/bin/env sh" && \
    curl -L https://github.com/lihaoyi/Ammonite/releases/download/1.9.1/2.12-1.9.1) > /usr/local/bin/amm && \
    chmod +x /usr/local/bin/amm'
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

## License

[CM-Well-Ingest](.) project is licensed under [Apache 2.0 License](LICENSE).
