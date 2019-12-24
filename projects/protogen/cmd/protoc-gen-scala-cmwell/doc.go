/*
	A plugin for the Google protocol buffer compiler to generate Scala CMWell code.

	Once the protocol compiler (protoc) is installed you can run
		protoc --scala-cmwell_out=output_directory input_directory/file.proto
	to generate Scala bindings for the protocol defined by file.proto.

	With that input, the output will be written to
		output_directory/file.pb.go

	The generated code is documented in the package comment for the library.
*/
package main
