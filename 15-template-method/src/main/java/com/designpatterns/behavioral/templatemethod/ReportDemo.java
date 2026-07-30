package com.designpatterns.behavioral.templatemethod;

/** Runnable walkthrough: {@code ./gradlew :15-template-method:run} (or run this class from your IDE). */
public final class ReportDemo {

    private ReportDemo() {
    }

    public static void main(String[] args) {
        ReportGenerator pdf = new PdfReportGenerator();
        ReportGenerator csv = new CsvReportGenerator();

        System.out.println("-- PDF report (summary hook = true) --");
        System.out.println(pdf.generate());

        System.out.println();
        System.out.println("-- CSV report (summary hook overridden to false) --");
        System.out.println(csv.generate());
    }
}
