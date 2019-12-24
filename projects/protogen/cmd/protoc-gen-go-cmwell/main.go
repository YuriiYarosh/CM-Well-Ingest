package main

import (
	"github.com/YuriiYarosh/CM-Well-SDK/contrib/protogen/lang/scala"
	"github.com/golang/protobuf/proto"
	plugin "github.com/golang/protobuf/protoc-gen-go/plugin"
	"io/ioutil"
	"os"
)

func main() {
	data, err := ioutil.ReadAll(os.Stdin)
	if err != nil {
		panic(err)
	}

	req := new(plugin.CodeGeneratorRequest)
	if err = proto.Unmarshal(data, req); err != nil {
		panic(err)
	}

	data, err = proto.Marshal(scala.Generate(req))
	if err != nil {
		panic(err)
	}

	if _, err = os.Stdout.Write(data); err != nil {
		panic(err)
	}
}
