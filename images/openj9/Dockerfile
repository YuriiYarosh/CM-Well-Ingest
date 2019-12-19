FROM debian:buster-slim

ARG OPENJ9_VERSION="0.17.0"
ENV OPENJ9_VERSION="${OPENJ9_VERSION}"

# https://adoptopenjdk.net/archive.html?variant=openjdk8&jvmVariant=openj9

ARG JDK_VERSION="8u232-b09"
ENV JDK_VERSION="${JDK_VERSION}"
ARG JDK_VERSION_STRIPPED="8u232b09"

ENV JDK_RELEASE_FOLDER="jdk${JDK_VERSION}_openj9-${OPENJ9_VERSION}"
ENV JDK_RELEASE_ARCHIVE="OpenJDK8U-jdk_x64_linux_openj9_${JDK_VERSION_STRIPPED}_openj9-${OPENJ9_VERSION}.tar.gz"

ARG LAST_SECURITY_UPDATE="19.12.2019"
ENV LAST_SECURITY_UPDATE="${LAST_SECURITY_UPDATE}"

RUN apt-get update -qq -y \
    && apt-get upgrade -qq -y \
    && apt-get install -qq -y ca-certificates \
    && update-ca-certificates \
    && apt-get install -qq -y --no-install-recommends wget tar \
    && apt-get clean -y \
    && apt-get autoremove -y \
    && rm -rf /tmp/* /var/tmp/* \
    && rm -rf /var/lib/apt/lists/*

RUN mkdir /jdk8 \
    && cd /jdk8  \
    && wget https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/download/${JDK_RELEASE_FOLDER}/${JDK_RELEASE_ARCHIVE} \
      -O bootjdk8.tar.gz \
    && tar -xzf bootjdk8.tar.gz \
    && rm -f bootjdk8.tar.gz

ENV JAVA_HOME=/jdk8
ENV PATH="$JAVA_HOME/bin:$PATH"

WORKDIR /jdk8
