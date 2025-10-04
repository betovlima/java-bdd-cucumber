package com.example.bdd;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME, value = "com.example.bdd")
@ConfigurationParameter(
        key = io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME,
        value = "pretty, json:build/reports/cucumber/cucumber.json, html:build/reports/cucumber/cucumber.html, junit:build/reports/cucumber/cucumber.xml"
)
@ConfigurationParameter(key = "cucumber.publish.enabled", value = "false")
public class RunCucumberTest {
}
