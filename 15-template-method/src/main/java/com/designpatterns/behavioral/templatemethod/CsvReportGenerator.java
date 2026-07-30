package com.designpatterns.behavioral.templatemethod;

import java.util.List;

/** Simulated CSV pipeline. Overrides the hook — CSV exports are meant to be raw data, no summary. */
public final class CsvReportGenerator extends ReportGenerator {

    @Override
    protected List<String> fetchData() {
        return List.of("a,b,c", "1,2,3");
    }

    @Override
    protected String formatData(List<String> data) {
        return String.join("\n", data);
    }

    @Override
    protected boolean shouldIncludeSummary() {
        return false;
    }

    @Override
    protected String exportReport(String formattedData) {
        return "[CSV]\n" + formattedData;
    }
}
