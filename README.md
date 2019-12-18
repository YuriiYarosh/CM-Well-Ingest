# CM-Well-Ingest

CM-Well Ingest tool

# Status: WIP

## Usage

## Installation

Feel free to
 * use a [docker image]() as follows

 ```bash
docker run -it yuriiyarosh/cmwell_ingest:1.0 help
```

 * we also support [homebrew](https://brew.sh/) for macOS

```bash
brew tap yuriiyarosh/formulas
brew install yuriiyarosh/formulas/cmwell_ingest
```
* or just download the binary from the [releases page]()

## Development

On macOS, you need

 * [Homebrew](https://brew.sh/)
 * [JDK](https://adoptopenjdk.net/)
 * [Bazel](https://bazel.build/)
 * [GraalVM](https://www.graalvm.org/)

```bash
brew tap AdoptOpenJDK/openjdk
brew cask install adoptopenjdk8-openj9-large

echo -e "\nexport JAVA_HOME=`/usr/libexec/java_home -v 1.8`" >> ~/.zshrc
echo -e "export PATH=\"\$PATH:\$JAVA_HOME/bin\"" >> ~/.zshrc

brew tap bazelbuild/tap
brew install bazelbuild/tap/bazel

brew tap graalvm/tap
brew cask install graalvm-ce-java8

# The following prevents `Xcode version must be specified to use an Apple CROSSTOOL`
# according to #4313 https://github.com/bazelbuild/bazel/issues/4314#issuecomment-370172472

sudo xcode-select -s /Applications/Xcode.app/Contents/Developer
sudo xcodebuild -license
```

* replace `echo -e >> ~/.zshrc` with `echo -e "..." >> ~/.bashrc` if you're using **bash**

  [oh my zsh](https://ohmyz.sh/) with [powerlevel10k](https://github.com/romkatv/powerlevel10k) are advised

The general Development Setup on Windows and Linux should be similar.

Assuming current GraalVM version 19.3.0, install `native-image` tool with
```bash
GRAAL_VERSION="19.3.0"
/Library/Java/JavaVirtualMachines/graalvm-ce-java8-$GRAAL_VERSION/Contents/Home/bin/gu install native-image
```

### Building

### Testing

### [Idea](https://www.jetbrains.com/idea/)

Just install the Idea [Bazel plugin](https://plugins.jetbrains.com/plugin/8609-bazel/).
and import [.bazelproject](.bazelproject) project view file.

Make sure correct Bazel path had been specified in

`Settings > Other Settings > Bazel Settings = /usr/local/bin/bazel`

according to [#88](https://github.com/bazelbuild/intellij/issues/88#issuecomment-369848097), you'll get `Sync Failed` error otherwise.

There's no need to import [BUILD](BUILD) file directly, but feel free to do so if it's suites your tastes.

### [Scalafmt](https://scalameta.org/scalafmt/)

**Idea Bundled** scalafmt is obsolete, and autoupdate is broken atm.

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

## License

[CM-Well-Ingest](.) project is licensed under [Apache 2.0 License](LICENSE).
