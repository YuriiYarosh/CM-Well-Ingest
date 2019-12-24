## Development

The usual development dependencies for macOS are

 * [Homebrew](https://brew.sh/)
 * [JDK](https://adoptopenjdk.net/)
 * [GraalVM](https://www.graalvm.org/)
 * [Bazel](https://bazel.build/)
 * [Docker](https://docs.docker.com/v17.12/docker-for-mac/install)
 * [nvm](https://github.com/nvm-sh/nvm)
 * [pipenv](https://github.com/pypa/pipenv)
 * [golang](https://golang.org/)

and a bunch of linters

 * [Scalafmt](https://scalameta.org/scalafmt/)
 * [Scalastyle](http://www.scalastyle.org/)
 * [golangci-lint](https://github.com/golangci/golangci-lint)
 * [eslint](https://eslint.org/)
 * [coala](https://coala.io/)

ZSH shell with [oh my zsh](https://ohmyz.sh/) are adviced [powerlevel10k](https://github.com/romkatv/powerlevel10k) theme are advised.

See the provided [bootstrap](bootstrap_macos.sh) shell script for installation details.

In general, Development Setup on Windows and Linux should be similar.

Feel free to setup the 

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

### [Scalastyle](http://www.scalastyle.org)

[Scalastyle](http://www.scalastyle.org) does not support [nailgun](https://github.com/facebook/nailgun) atm,
but that's ok, because it works better as a precommit [git hook](https://www.atlassian.com/git/tutorials/git-hooks).

### [golangci-lint](https://github.com/golangci/golangci-lint)

Wraps a ton of different golang linters, make sure it's updated from time to time.

### [eslint](https://eslint.org/) and [coala](https://coala.io/)

Are installed using [nvm](https://github.com/nvm-sh/nvm) and [pipenv](https://github.com/pypa/pipenv) respectively.
