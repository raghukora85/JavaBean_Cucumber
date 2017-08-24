import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import javabean.ReadFileProperty;


public class MyStepdefs {
    private ReadFileProperty readFileProperty = new ReadFileProperty();

    @Given("^I set the file path to \"([^\"]*)\"$")
    public void i_set_the_file_path_to(String path) throws Throwable {
        readFileProperty.setFilePath(path);
    }

    @When("^I get file data with Extension \"([^\"]*)\"$")
    public void i_get_file_data_with_Extension(String extn) throws Throwable {
        readFileProperty.setFileExtension(extn);
    }

    @Then("^the file related data should be displayed$")
    public void the_file_related_data_should_be_displayed() throws Throwable {
        readFileProperty.listFiles();
    }

    @Then("^the file related data should be displayed with filter applied$")
    public void the_file_related_data_should_be_displayed_with_filter_applied() throws Throwable {
        readFileProperty.filterFileTypes();
    }

}