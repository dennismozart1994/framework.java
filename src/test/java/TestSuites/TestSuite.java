package TestSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import testScenarios.RestPOSTTestExample;
import testScenarios.TestWebExample;

@RunWith(Suite.class)
@SuiteClasses
({
	TestWebExample.class,
	RestPOSTTestExample.class
})
public class TestSuite {
}
