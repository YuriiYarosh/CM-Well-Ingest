package testutil

type TestCase struct {
	input string
	expected string
}

var TestCases = map[string]TestCase{
	"base": {
		baseProto3,
		baseProto3ExpectedResult,
	},
}
