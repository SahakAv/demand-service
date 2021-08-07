package com.disqo.requestservice;

import org.testcontainers.containers.PostgreSQLContainer;

public class DatabaseContainer extends PostgreSQLContainer<DatabaseContainer> {
    private static final String IMAGE_VERSION = "postgres:11.1";
    private static DatabaseContainer container;


    public DatabaseContainer() {
        super(IMAGE_VERSION);
    }

    public static DatabaseContainer getInstance() {
        if (container == null) {
            container = new DatabaseContainer().withInitScript("db/init.sql");
        }
        return container;
    }


    @Override
    public void start() {
        super.start();

    }
}
