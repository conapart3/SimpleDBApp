package com.conal.simpledbapp.commands;

import org.kohsuke.args4j.Option;

/**
 * Command to add server information
 */
public class AddServer extends AppCommandBase {

    @Option(name = "-id", aliases = "-i", required = true, usage = "id of the server")
    private String id;

    @Option(name = "-name", aliases = "-n", required = true, usage = "name of the server")
    private String name;

    @Option(name = "-desc", aliases = "-d", required = true, usage = "desc of the server")
    private String desc;

    public void execute() {
        super.execute();

        dbHelper.addServer(id, name, desc);
        System.out.println("Operation to add server with name: " + name +" completed successfully.");
    }
}
