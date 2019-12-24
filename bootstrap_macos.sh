#!/usr/bin/env bash

# JDK

brew tap AdoptOpenJDK/openjdk
brew cask install adoptopenjdk8-openj9-large

echo -e "\nexport JAVA_HOME=`/usr/libexec/java_home -v 1.8`" >> ~/.zshrc
echo -e "export PATH=\"\$PATH:\$JAVA_HOME/bin\"" >> ~/.zshrc

# replace `echo -e >> ~/.zshrc` with `echo -e "..." >> ~/.bashrc` if you're using **bash**

# Bazel

brew tap bazelbuild/tap
brew install bazelbuild/tap/bazel

# GraalVM

brew tap graalvm/tap
brew cask install graalvm-ce-java8

# Assuming current GraalVM version 19.3.0, install `native-image` tool with

GRAAL_VERSION="19.3.0"
/Library/Java/JavaVirtualMachines/graalvm-ce-java8-$GRAAL_VERSION/Contents/Home/bin/gu install native-image

# The following prevents `Xcode version must be specified to use an Apple CROSSTOOL`
# according to #4313 https://github.com/bazelbuild/bazel/issues/4314#issuecomment-370172472

sudo xcode-select -s /Applications/Xcode.app/Contents/Developer
sudo xcodebuild -license

# docker to build the respective container images

brew cask install docker

# Golang

brew install go

# setting up GOPATH env var
echo -e "\nexport GOPATH=\"\$HOME/go\"" >> ~/.zshrc
echo -e "export PATH=\"\$PATH:\$GOPATH/bin\"" >> ~/.zshrc

# replace `echo -e >> ~/.zshrc` with `echo -e "..." >> ~/.bashrc` if you're using **bash**

# NVM

NVM_VERSION="0.35.2"
wget -qO- "https://raw.githubusercontent.com/nvm-sh/nvm/v${NVM_VERSION}/install.sh" | bash

# pipenv

brew install pipenv

## Linters ##

# Scalafmt

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

# Scalastyle

coursier bootstrap --standalone org.scalastyle:scalastyle_2.12:1.0.0 \
  -r sonatype:snapshots \
  -o /usr/local/bin/scalastyle -f --main org.scalastyle.Main

# golangci-lint

# binary will be $(go env GOPATH)/bin/golangci-lint
curl -sSfL https://raw.githubusercontent.com/golangci/golangci-lint/master/install.sh | \
  sh -s -- -b $(go env GOPATH)/bin v1.21.0

# eslint and coala are installed wia nvm and pipenv respectively
