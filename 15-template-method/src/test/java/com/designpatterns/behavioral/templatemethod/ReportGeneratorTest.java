package com.designpatterns.behavioral.templatemethod;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ReportGeneratorTest {

    @Test
    void pdfReportIncludesTheSummaryByDefault() {
        String result = new PdfReportGenerator().generate();

        assertThat(result).startsWith("[PDF]");
        assertThat(result).contains("[summary included]");
    }

    @Test
    void csvReportOmitsTheSummaryBecauseTheHookIsOverridden() {
        String result = new CsvReportGenerator().generate();

        assertThat(result).startsWith("[CSV]");
        assertThat(result).doesNotContain("[summary included]");
    }

    @Test
    void theStepsRunInTheOrderFixedByTheTemplateMethod() {
        List<String> callOrder = new java.util.ArrayList<>();
        ReportGenerator generator = new ReportGenerator() {
            @Override
            protected List<String> fetchData() {
                callOrder.add("fetchData");
                return List.of("x");
            }

            @Override
            protected String formatData(List<String> data) {
                callOrder.add("formatData");
                return "formatted";
            }

            @Override
            protected boolean shouldIncludeSummary() {
                callOrder.add("shouldIncludeSummary");
                return false;
            }

            @Override
            protected String exportReport(String formattedData) {
                callOrder.add("exportReport");
                return formattedData;
            }
        };

        generator.generate();

        assertThat(callOrder).containsExactly(
                "fetchData", "formatData", "shouldIncludeSummary", "exportReport");
    }

    @Test
    void differentSubclassesProduceDifferentFormattingForTheSameKindOfData() {
        String pdf = new PdfReportGenerator().generate();
        String csv = new CsvReportGenerator().generate();

        assertThat(pdf).isNotEqualTo(csv);
    }
}
