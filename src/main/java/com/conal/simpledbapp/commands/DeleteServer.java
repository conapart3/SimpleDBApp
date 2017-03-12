package com.conal.simpledbapp.commands;

import org.kohsuke.args4j.Option;

/**
 * Command to delete a server
 */
public class DeleteServer extends AppCommandBase {

    @Option(name = "-id", aliases = "-i", required = true, usage = "The server ID")
    private String id;

    public void execute() {
        super.execute();

        dbHelper.deleteServer(id);
        System.out.println("Deletion of server with ID = " + id + " completed successfully.");
    }
}
