package com.conal.simpledbapp.commands;

import org.kohsuke.args4j.Option;

/**
 * Command to edit a server
 */
public class EditServer extends AppCommandBase {

    @Option(name = "-id", aliases = "-i", required = true, usage = "name of the server")
    private String id;

    @Option(name = "-name", aliases = "-n", required = true, usage = "name of the server")
    private String name;

    @Option(name = "-desc", aliases = "-d", required = true, usage = "desc of the server")
    private String desc;

    public void execute() {
        super.execute();

        dbHelper.editServer(id, name, desc);
        System.out.println("Edit server with ID: " + id +" completed successfully.");
    }
}
