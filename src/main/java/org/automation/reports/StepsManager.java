package org.automation.reports;

/**
 * This class is to set and get current keyword and step names, for reporting purpose only.
 */
public final class StepsManager {

    private static ThreadLocal<String> stepText = new ThreadLocal<String>();

    public static void setStepText(String stepName) {
        stepText.set(stepName);
    }

    public static String getStepText() {
        return stepText.get();
    }
}
