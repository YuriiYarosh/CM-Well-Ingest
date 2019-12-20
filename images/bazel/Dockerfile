FROM yuriiyarosh/graalvm:19.3.0

# https://github.com/bazelbuild/bazel/releases

ARG BAZEL_VERSION="2.0.0"
ENV BAZEL_VERSION="${BAZEL_VERSION}"

ARG SECURITY_UPDATE="19.12.2019"
ENV SECURITY_UPDATE="${SECURITY_UPDATE}"

RUN apt-get update -qq -y \
    && apt-get upgrade -qq -y \
    && apt-get install -qq -y ca-certificates g++ unzip zip \
    && update-ca-certificates \
    && apt-get clean -y \
    && apt-get autoremove -y \
    && rm -rf /tmp/* /var/tmp/* \
    && rm -rf /var/lib/apt/lists/*

RUN mkdir /bazel \
    && cd /bazel  \
    && wget https://github.com/bazelbuild/bazel/releases/download/${BAZEL_VERSION}/bazel-${BAZEL_VERSION}-installer-linux-x86_64.sh \
      -O bazel-installer.sh \
    && chmod u+x bazel-installer.sh \
    && ./bazel-installer.sh --prefix=/bazel  \
    && rm bazel-installer.sh

ENV BAZEL_HOME=/bazel
ENV PATH="$BAZEL_HOME/bin:$PATH"

WORKDIR /bazel
