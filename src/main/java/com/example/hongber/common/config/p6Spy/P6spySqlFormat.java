package com.example.hongber.common.config.p6Spy;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class P6spySqlFormat implements MessageFormattingStrategy {
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return sql + "; #took : " + elapsed + "ms";
    }
}