## Building

### Docker images

To build the respective docker images you'll need

 * [Docker](https://docs.docker.com/v17.12/docker-for-mac/install)
 
See the [Development](https://github.com/YuriiYarosh/CM-Well-SDK#development) section bellow for details.

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
dockersec -t yuriiyarosh/nvm:0.35.2 nvm
dockersec -t yuriiyarosh/pipenv:2018.11.26 pipenv
dockersec -t yuriiyarosh/golang:1.13.5 golang

CMWELL_SDK_VERSION="1.0.0"

dockersec -t yuriiyarosh/cmwell_sdk_benchmark:$CMWELL_SDK_VERSION cmwell_sdk_benchmark
dockersec -t yuriiyarosh/cmwell_sdk_golang:$CMWELL_SDK_VERSION cmwell_sdk_golang
dockersec -t yuriiyarosh/cmwell_sdk_node:$CMWELL_SDK_VERSION cmwell_sdk_node
dockersec -t yuriiyarosh/cmwell_sdk_python:$CMWELL_SDK_VERSION cmwell_sdk_python
dockersec -t yuriiyarosh/cmwell_sdk_scala_graal:$CMWELL_SDK_VERSION cmwell_sdk_scala_graal
dockersec -t yuriiyarosh/cmwell_sdk_scala_jvm:$CMWELL_SDK_VERSION cmwell_sdk_scala_jvm
```

### Building with [Bazel](https://bazel.build/)

To build and test decontainerized project from scratch, you'll need 
 * [JDK or JRE](https://adoptopenjdk.net/)
 * [Bazel](https://bazel.build/)
 * [GraalVM](https://www.graalvm.org/)
 * [nvm](https://github.com/nvm-sh/nvm)
 * [pipenv](https://github.com/pypa/pipenv)
 * [golang](https://golang.org/)
