package com.conal.simpledbapp.commands;

import com.conal.simpledbapp.DatabaseHelper;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/**
 * Base command abstract class for sub command help functionality.
 */
public abstract class AppCommandBase implements AppCommand {

    @Option(name = "-help", aliases = "-h", usage = "show help for specific command", help = true)
    private boolean help;

    protected DatabaseHelper dbHelper;

    public void execute() {
        if (help) {
            showHelp();
            System.exit(0);
        }
        dbHelper = new DatabaseHelper();
    }

    public void showHelp() {
        CmdLineParser cmdLineParser = new CmdLineParser(this);
        cmdLineParser.printUsage(System.out);
    }
}
