FROM yuriiyarosh/openj9:8u232

# https://github.com/oracle/graal/releases
# https://github.com/graalvm/graalvm-ce-builds/releases/tag/vm-19.3.0

ARG GRAALVM_VERSION="19.3.0"
ENV GRAALVM_VERSION="${GRAALVM_VERSION}"

ARG SECURITY_UPDATE="19.12.2019"
ENV SECURITY_UPDATE="${SECURITY_UPDATE}"

RUN apt-get update -qq -y \
    && apt-get upgrade -qq -y \
    && apt-get install -qq -y ca-certificates libc-dev \
    && update-ca-certificates \
    && apt-get clean -y \
    && apt-get autoremove -y \
    && rm -rf /tmp/* /var/tmp/* \
    && rm -rf /var/lib/apt/lists/*

RUN mkdir /graalvm \
    && cd /graalvm  \
    && wget https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-${GRAALVM_VERSION}/graalvm-ce-java8-linux-amd64-${GRAALVM_VERSION}.tar.gz \
      -O graalvm.tar.gz \
    && tar -xzf graalvm.tar.gz \
    && mv graalvm-ce-java8-${GRAALVM_VERSION}/* . || true \
    && mv graalvm-ce-java8-${GRAALVM_VERSION}/.* . || true \
    && rm -rf graalvm-ce-java8-${GRAALVM_VERSION} \
    && rm -f graalvm.tar.gz \
    && /graalvm/bin/gu install native-image

ENV GRAAL_HOME=/graalvm
ENV PATH="$GRAAL_HOME/bin:$PATH"

WORKDIR /graalvm
