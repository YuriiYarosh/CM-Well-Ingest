# [CM-Well](https://github.com/CM-Well/CM-Well) SDK

[![Maintainability](https://api.codeclimate.com/v1/badges/2d9f113b82dc0b06469b/maintainability)](https://codeclimate.com/github/YuriiYarosh/CM-Well-SDK/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/2d9f113b82dc0b06469b/test_coverage)](https://codeclimate.com/github/YuriiYarosh/CM-Well-SDK/test_coverage)

CM-Well SDK for

 * Graph Data Ingestion into the [CM-Well](https://cm-well.github.io/CM-Well/index.html) cluster
 * Querying the Infoton Store
 * Scheduling CM-Well cluster Maintenance 

# Status: WIP

Version: 1.0-SNAPSHOT

### Contents:

 * [Language Support](https://github.com/YuriiYarosh/CM-Well-SDK#Language_Support)
 * [Installation](https://github.com/YuriiYarosh/CM-Well-SDK#InstallationandUsage)
 * [Benchmarks](https://github.com/YuriiYarosh/CM-Well-SDK#Benchmarks)
 * [Contributing](https://github.com/YuriiYarosh/CM-Well-SDK#Contributing)
 * [License](https://github.com/YuriiYarosh/CM-Well-SDK#License)
 
 [Building](BUILDING.md) and [Development](DEVELOPMENT.md) instructions are also available.

## Language Support

 CMWell SDK currently supports
 
 * [scala](projects/scala-sdk) and JVM languages
 * [go](projects/golang-sdk)
 * [python](projects/python-sdk) 
 * [node.js](projects/node-sdk)

CLI's are available in every SDK port.

## Installation and Usage

 ```bash
docker run -it yuriiyarosh/cmwell_sdk_golang:1.0 help
docker run -it yuriiyarosh/cmwell_sdk_node:1.0 help
docker run -it yuriiyarosh/cmwell_sdk_python:1.0 help
docker run -it yuriiyarosh/cmwell_sdk_scala_graal:1.0 help
docker run -it yuriiyarosh/cmwell_sdk_scala_jvm:1.0 help
```

 * we also support [homebrew](https://brew.sh/) for macOS

```bash
brew tap yuriiyarosh/formulas

# Golang SDK is adviced for CLI's
brew install yuriiyarosh/formulas/cmwell_sdk_golang

# but feel to use any other 

brew install yuriiyarosh/formulas/cmwell_sdk_node
brew install yuriiyarosh/formulas/cmwell_sdk_python
brew install yuriiyarosh/formulas/cmwell_sdk_scala_graal
brew install yuriiyarosh/formulas/cmwell_sdk_scala_jvm
```
Or just download the distributions from the [releases page]().

## Benchmarks

You can run all the respective benchmarks using provided docker images

```
docker run -it yuriiyarosh/cmwell_sdk_benchmark:1.0
```

## Contributing

[CM-Well SDK](https://github.com/YuriiYarosh/CM-Well-SDK) shares [contribution terms](https://github.com/CM-Well/CM-Well/blob/master/CONTRIBUTING.md) with the [CM-Well](https://github.com/CM-Well/CM-Well) Project.

Contribution terms are not completely established at the moment and will change over time.

## About CM-Well project

You can learn more at [CM-Well documentation site](https://cm-well.github.io/CM-Well/index.html).

## License

[CM-Well-SDK](../..) project is licensed under [Apache 2.0 License](../../LICENSE).
