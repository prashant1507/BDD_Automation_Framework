package org.automation.steps;

import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.After;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCase;
import org.automation.reports.StepsManager;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class Hooks {

    private int currentStepDefIndex = 0;

    @BeforeStep
    public void getStepText(Scenario scenario) throws Exception {

        String  currentStepText;
        String currentKeyword;
        Field f = scenario.getClass().getDeclaredField("delegate");
        f.setAccessible(true);
        TestCaseState tcs = (TestCaseState) f.get(scenario);

        Field f2 = tcs.getClass().getDeclaredField("testCase");
        f2.setAccessible(true);
        TestCase r = (TestCase) f2.get(tcs);

        List<PickleStepTestStep> stepDefs = r.getTestSteps()
                .stream()
                .filter(x -> x instanceof PickleStepTestStep)
                .map(x -> (PickleStepTestStep) x)
                .collect(Collectors.toList());

        PickleStepTestStep currentStepDef = stepDefs.get(currentStepDefIndex);
        currentKeyword = currentStepDef.getStep().getKeyword();
        currentStepText = currentStepDef.getStep().getText();
        currentStepDefIndex++;
        StepsManager.setStepText(currentKeyword + " " +currentStepText);
    }

    @After
    public void restCounter() {
        currentStepDefIndex = 0;
    }

}
