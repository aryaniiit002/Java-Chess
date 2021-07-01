package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
	@CucumberOptions(
			features = "/home/aryaniiit002/Desktop/Java-Chess/src/bddTest/java/resources/", //the path of the feature files
			glue={"stepDefinitions"}, //the path of the step definition files
			plugin= {"pretty"}, //to generate different types of reporting
			monochrome = true //display the console output in a proper readable format
			)
	 
	public class TestRunner {
	}