package main

import (
	"github.com/YuriiYarosh/CM-Well-SDK/contrib/protogen/testutil"
	"io/ioutil"
	"os"
	"testing"
)

func init() {
	if os.Getenv("RUN_AS_PROTOGEN") != "" {
		main()
		os.Exit(0)
	}
}

func TestAll(t *testing.T) {
	workDir, _ := os.Getwd()
	tmpDir, err := ioutil.TempDir("", "proto-test")

	if err != nil {
		t.Fatal(err)
	}
	defer os.RemoveAll(tmpDir)

	for name, testCase := range testutil.TestCases {
		testutil.Protoc(t, "python-cmwell", tmpDir, "simple/simple.proto")
		testutil.AssertEqualFiles(
			t,
			workDir+"/testdata/simple/TestSimple/",
			tmpDir+"/TestSimple/SimpleServiceInterface.php",
		)
	}
}
