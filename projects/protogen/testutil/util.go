package testutil

import (
	. "github.com/smartystreets/goconvey/convey"
	"os/exec"

	"io/ioutil"
	"os"
	"strings"
	"testing"
)

func AssertFileExists(filePath string) {
	stat, err := os.Stat(filePath);
	So(err, ShouldBeNil)
	So(stat.IsDir(), ShouldBeFalse)
}

func AssertEqualFiles(t *testing.T, files ...string) {
	So(len(files), ShouldBeGreaterThanOrEqualTo, 2)

	contents := make([][]byte, 0, len(files))
	for _, f := range files {
		AssertFileExists(f)
		c, err := ioutil.ReadFile(f)
		So(err, ShouldBeNil)
		contents = append(contents, c)
	}

	r := strings.NewReplacer("\r\n", "", "\n", "")

	for i, _ := range contents {
		if i == 0 {
			continue
		}

		one :=  r.Replace(string(contents[i]))
		other := r.Replace(string(contents[i - 1]))

		So(one, ShouldResemble, other)
	}
}

func Protoc(t *testing.T, name, tmpDir string, passedArgs ...string) {

	args := append([]string{"--plugin=protoc-gen-" + name + "-grpc=" + os.Args[0]}, []string{"--" + name + "-grpc_out=" + tmpDir}...)
	args = append(args, passedArgs...)

	cmd := exec.Command("protoc", args...)
	cmd.Args = append(cmd.Args, args...)
	cmd.Env = append(os.Environ(), "RUN_AS_PROTOGEN=1")
	out, err := cmd.CombinedOutput()

	if len(out) > 0 || err != nil {
		t.Log("RUNNING: ", strings.Join(cmd.Args, " "))
	}

	if len(out) > 0 {
		t.Log(string(out))
	}

	So(err, ShouldBeNil)
}
