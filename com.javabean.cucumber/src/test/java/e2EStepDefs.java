import Helpers.CsvCarDetails;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import javabean.ReadFileProperty;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.io.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class e2EStepDefs extends RunTest {

    private ReadFileProperty readFile = new ReadFileProperty();
    private File carDetailsFile;
    private List<CsvCarDetails> carDetails;
    private String url;
    private Function<String, CsvCarDetails> mapToItem = (line) -> {
        String[] p = line.split(",");
        CsvCarDetails item = new CsvCarDetails();

        item.carreg = p[0];
        item.color = p[1];
        item.make = p[2];
        return item;
    };

    @Given("^I Scanned Details from example csv file$")
    public void iScannedDetailsFromExampleCsvFile() throws Throwable {
        readFile.setFilePath("/src/main/resources/");

        carDetailsFile = readFile.getMatchingFiles(new File(readFile.getFilePath()), "csv")[0];
        InputStream is = new FileInputStream(carDetailsFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        carDetails = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
        br.close();
    }

    @When("^I Navigated to \"([^\"]*)\" page$")
    public void iNavigatedToPage(String url) throws Throwable {
        this.url = url;
        driver.get(url);
    }

    @And("^I enter car registration number$")
    public void iEnterCarRegistrationNumber() throws Throwable {

        // this step is also covered in below step

    }

    @Then("^I should see related model and color$")
    public void iShouldBeSeeRelatedModelAndColor() throws Throwable {

        for (CsvCarDetails details : carDetails) {

            driver.get(this.url);
            driver.findElement(By.cssSelector("#get-started a")).click();
            driver.findElement(By.cssSelector("input.input-upper")).sendKeys(details.carreg);
            driver.findElement(By.cssSelector("[name='Continue']")).click();

            Assert.assertEquals("Car make is different",
                    (driver.findElement(By.cssSelector(" .list-summary-item:nth-child(2)>span>strong"))).
                            getText().toLowerCase(), details.make);

            Assert.assertEquals("Car make is different",
                    (driver.findElement(By.cssSelector(" .list-summary-item:nth-child(3)>span>strong"))).
                            getText().toLowerCase(), details.color);

        }
    }
}

