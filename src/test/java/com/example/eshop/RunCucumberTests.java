package com.example.eshop;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "json:target/cucumber.json"},
                 features = {"src/test/resources/featureFiles"},
                 glue = {"com.example.eshop.stepDefs"},
                 tags = "@Component")
public class RunCucumberTests
{

}
