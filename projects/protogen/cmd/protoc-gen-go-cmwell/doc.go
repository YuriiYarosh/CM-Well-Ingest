/*
	A plugin for the Google protocol buffer compiler to generate Go CMWell code.

	Once the protocol compiler (protoc) is installed you can run
		protoc --go-cmwell_out=output_directory input_directory/file.proto
	to generate Go bindings for the protocol defined by file.proto.

	With that input, the output will be written to
		output_directory/file.pb.go

	The generated code is documented in the package comment for the library.
*/
package main
