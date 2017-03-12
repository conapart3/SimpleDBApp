package com.conal.simpledbapp.commands;

/**
 * Each command will have execute and showHelp methods.
 */
public interface AppCommand {
    void execute();

    void showHelp();
}
