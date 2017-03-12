package com.conal.simpledbapp.commands;

import org.kohsuke.args4j.Option;

/**
 * Command to add server information
 */
public class AddServer extends AppCommandBase {

    @Option(name = "-name", aliases = "-n", required = true, usage = "The server name")
    private String name;

    @Option(name = "-description", aliases = "-d", required = true, usage = "The server description")
    private String description;

    public void execute() {
        super.execute();

        dbHelper.addServer(name, description);
        System.out.println("Operation to add server with name: " + name + " completed successfully.");
    }
}
