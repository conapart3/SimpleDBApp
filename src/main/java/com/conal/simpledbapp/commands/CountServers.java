package com.conal.simpledbapp.commands;

/**
 * Command to count the total number of servers
 */
public class CountServers extends AppCommandBase {

    public void execute() {
        super.execute();

        long serverCount = dbHelper.countServers();
        System.out.println("The total number of servers is " + serverCount);
    }
}
