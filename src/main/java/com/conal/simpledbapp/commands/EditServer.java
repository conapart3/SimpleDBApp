package com.conal.simpledbapp.commands;

import org.kohsuke.args4j.Option;

/**
 * Command to edit a server
 */
public class EditServer extends AppCommandBase {

    @Option(name = "-id", aliases = "-i", required = true, usage = "The ID of the server")
    private String id;

    @Option(name = "-name", aliases = "-n", required = true, usage = "The server name")
    private String name;

    @Option(name = "-description", aliases = "-d", required = true, usage = "The server description")
    private String description;

    public void execute() {
        super.execute();

        dbHelper.editServer(id, name, description);
        System.out.println("Edit server with ID: " + id + " completed successfully.");
    }
}
