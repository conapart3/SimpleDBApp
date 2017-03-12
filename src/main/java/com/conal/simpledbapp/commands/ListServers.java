package com.conal.simpledbapp.commands;

import com.conal.simpledbapp.Server;

import java.util.List;

/**
 * Command to list all servers
 */
public class ListServers extends AppCommandBase {

    public void execute() {
        super.execute();

        List<Server> response = dbHelper.listServers();
        response.stream().forEach(server -> server.printServer());
    }
}
