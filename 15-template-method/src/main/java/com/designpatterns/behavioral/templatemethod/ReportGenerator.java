package com.designpatterns.behavioral.templatemethod;

import java.util.List;

/**
 * Defines the skeleton of the report pipeline in a {@code final} method so subclasses cannot
 * change the order — or skip a step. This is the Hollywood Principle: "don't call us, we'll call
 * you." Subclasses never invoke {@link #generate()} themselves and never orchestrate the steps;
 * this base class calls down into their overrides at the points it decides.
 */
public abstract class ReportGenerator {

    public final String generate() {
        List<String> data = fetchData();
        String formatted = formatData(data);
        if (shouldIncludeSummary()) {
            formatted = formatted + "\n[summary included]";
        }
        return exportReport(formatted);
    }

    protected abstract List<String> fetchData();

    protected abstract String formatData(List<String> data);

    /** Hook: concrete default, freely overridable. Not every step needs to be abstract. */
    protected boolean shouldIncludeSummary() {
        return true;
    }

    protected abstract String exportReport(String formattedData);
}
