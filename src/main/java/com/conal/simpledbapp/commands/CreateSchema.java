package com.conal.simpledbapp.commands;

/**
 * Create the database schema
 */
public class CreateSchema extends AppCommandBase {
    public void execute() {
        super.execute();

        dbHelper.createSchema();
    }
}
