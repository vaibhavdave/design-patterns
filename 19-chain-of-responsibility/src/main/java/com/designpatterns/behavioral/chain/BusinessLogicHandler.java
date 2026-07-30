package com.designpatterns.behavioral.chain;

/** Terminal handler: if a request reaches here, it passed every earlier check in the chain. */
public final class BusinessLogicHandler extends RequestHandler {

    private boolean reached;

    @Override
    public void handle(Request request) {
        request.recordVisit("BusinessLogicHandler");
        reached = true;
        System.out.println("Processing business logic for " + request.getPath());
    }

    public boolean isReached() {
        return reached;
    }
}
