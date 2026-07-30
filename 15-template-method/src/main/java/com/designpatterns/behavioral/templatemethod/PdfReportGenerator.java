package com.designpatterns.behavioral.templatemethod;

import java.util.List;

/** Simulated PDF pipeline. Keeps the default {@code shouldIncludeSummary() == true} hook. */
public final class PdfReportGenerator extends ReportGenerator {

    @Override
    protected List<String> fetchData() {
        return List.of("row1", "row2", "row3");
    }

    @Override
    protected String formatData(List<String> data) {
        return "PDF layout of: " + String.join(", ", data);
    }

    @Override
    protected String exportReport(String formattedData) {
        return "[PDF] " + formattedData;
    }
}
