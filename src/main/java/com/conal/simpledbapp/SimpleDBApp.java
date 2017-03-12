package com.conal.simpledbapp;

import com.conal.simpledbapp.commands.*;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.SubCommand;
import org.kohsuke.args4j.spi.SubCommandHandler;
import org.kohsuke.args4j.spi.SubCommands;

/**
 * Created by Conal on 11/03/2017.
 */
public class SimpleDBApp {
    /**
     * Global help flag
     */
    @Option(name = "-help", aliases = "-h", usage = "displays help for the application", help = true)
    public boolean help;

    /**
     * Sub commands
     */
    @Argument(required = true,
            usage = "subcommands to add, edit, delete a server, count servers, list servers and create schema",
            handler = SubCommandHandler.class)
    @SubCommands({
            @SubCommand(name = "add-server", impl = AddServer.class),
            @SubCommand(name = "edit-server", impl = EditServer.class),
            @SubCommand(name = "delete-server", impl = DeleteServer.class),
            @SubCommand(name = "count-servers", impl = CountServers.class),
            @SubCommand(name = "list-servers", impl = ListServers.class),
            @SubCommand(name = "create-schema", impl = CreateSchema.class)
    })
    AppCommand cmd;

    public static void main(String[] args) {
        System.exit(new SimpleDBApp().run(args));
    }

    private int run(String[] args) {
        CmdLineParser cmdLineParser = new CmdLineParser(this);

        try {
            cmdLineParser.parseArgument(args);

            if (help) {
                showHelp(cmdLineParser);
                return 0;
            }

            cmd.execute();
            return 0;
        } catch (CmdLineException e) {
            System.out.println(e);
            showHelp(cmdLineParser);
            return 1;
        }
    }

    /**
     * Global help.
     *
     * @param cmdLineParser
     */
    private static void showHelp(CmdLineParser cmdLineParser) {
        cmdLineParser.printUsage(System.out);
    }
}
