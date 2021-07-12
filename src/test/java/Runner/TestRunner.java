package Runner;



//import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;




//@RunWith(Cucumber.class)
@CucumberOptions(
      features = {"src/test/java/Feature/"},
      glue = {"stepDef","hook"},
      plugin = {"pretty",
              "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
      },
      tags = "@TestQA"
)
public class TestRunner extends AbstractTestNGCucumberTests {

	@Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}

