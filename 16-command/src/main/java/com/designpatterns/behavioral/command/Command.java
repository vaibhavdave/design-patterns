package com.designpatterns.behavioral.command;

/**
 * Encapsulates a request as an object: the receiver, the action to perform on it, and (here) how
 * to reverse that action, all bundled behind two methods the invoker never needs receiver-specific
 * knowledge to call.
 */
public interface Command {

    void execute();

    void undo();
}
