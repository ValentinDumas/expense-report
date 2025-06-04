package com.example.expensereport;

import org.approvaltests.core.Options;

public class ScruberUtils {

    public static Options scrubDate() {
        // example: Wed Nov 17 22:28:33 EET 2021
        return new Options().withScrubber(text -> text.replaceAll(
                "\\w{3} \\w{3} \\d{2} \\d{2}:\\d{2}:\\d{2} \\w{3,4} \\d{4}",
                "<DATE>"));
    }
}
