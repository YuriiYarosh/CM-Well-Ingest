# Protogen

Is a set of custom [protocol buffers]() code generator plugins for the [CM-Well-SDK](https://github.com/YuriiYarosh/CM-Well-SDK) project.

## Usage

```
protoc --go-cmwell_out=output_directory input.proto
protoc --python-cmwell_out=output_directory input.proto
protoc --scala-cmwell_out=output_directory input.proto
```

## Installing

```bash
go get -u https://github.com/YuriiYarosh/CM-Well-SDK/projects/protogen/cmd/...
```

## Testing

```
go get -u github.com/smartystreets/goconvey
goconvey
```

## Contribution

Contribution terms are shared with [CM-Well-SDK]() project.

## License

[protogen](.) plugins are licensed under the [Apache v2]() license.