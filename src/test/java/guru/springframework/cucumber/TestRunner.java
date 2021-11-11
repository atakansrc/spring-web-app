package guru.springframework.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/feature" })
public class TestRunner extends AbstractTestNGCucumberTests {

}
