package com.sample.tests.junit.kdt;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.sample.framework.utils.jira.JiraUtils;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "html:build/cucumber-html-report",
                "junit:build/cucumber-junit.xml",
                "json:build/cucumber.json",
                "pretty:build/cucumber-pretty.txt",
                "usage:build/cucumber-usage.json"
                },
        features = { "src/test/java/com/sample/tests/junit/kdt/features" },
        glue = { "com/sample/tests/junit/kdt/steps" },
        tags = {"@gen"}
)
public class BankingKDTTest {
	
	@BeforeClass
	public static void farBefore() throws Exception {
		String featurePath = "build/features";
		Map<String, String> content = JiraUtils.getField("http://localhost:9090", "mkolisnyk", "password",
				"project = Sample and type = Test and status != Done", "description");
		for (Entry<String, String> entry : content.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
			String fileName = entry.getKey().replaceAll("(\\W+)", "_") + ".feature";
			String result = "@gen" + System.lineSeparator() + entry.getValue();
			File output = new File(featurePath + File.separator + fileName);
			output.getParentFile().mkdirs();
			FileUtils.writeStringToFile(new File(featurePath + File.separator + fileName), result, "UTF-8");
		}
	}
}
