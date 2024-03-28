package com.mybar.bartender.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
public class RetryableDataSource extends AbstractDataSource {

    private final DataSource dataSource;
    @Override
    @Retryable(maxAttemptsExpression = "#{${db.connect.maxAttempts}}",
            backoff = @Backoff(delayExpression = "#{${db.connect.backOffDelay}}"))
    public Connection getConnection() throws SQLException {
        log.info("Getting connection...");
        return dataSource.getConnection();
    }

    @Override
    @Retryable(maxAttemptsExpression = "#{${db.connect.maxAttempts}}",
            backoff = @Backoff(delayExpression = "#{${db.connect.backOffDelay}}"))
    public Connection getConnection(String username, String password) throws SQLException {
        log.info("Getting connection by username and password");
        return dataSource.getConnection(username, password);
    }
}
