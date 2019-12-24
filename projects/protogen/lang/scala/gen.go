package scala

import (
	"github.com/golang/protobuf/proto"
	plugin "github.com/golang/protobuf/protoc-gen-go/plugin"
)

type Message struct {

}

func (s *Message) Reset() {

}

func (s *Message) String() string {
	return ""
}

func (s *Message)  ProtoMessage() {

}

func Generate(req *plugin.CodeGeneratorRequest) proto.Message {
	msg := &Message{

	}

	return msg
}
