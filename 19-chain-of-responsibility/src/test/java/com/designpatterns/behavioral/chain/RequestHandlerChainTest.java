package com.designpatterns.behavioral.chain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RequestHandlerChainTest {

    @Test
    void requestWithValidAuthTokenReachesBusinessLogicHandler() {
        BusinessLogicHandler business = new BusinessLogicHandler();
        RequestHandler chain = buildChain(business);
        Request request = new Request("/orders", "client-1", true);

        chain.handle(request);

        assertThat(business.isReached()).isTrue();
        assertThat(request.isRejected()).isFalse();
        assertThat(request.getVisitedHandlers())
                .containsExactly("AuthenticationHandler", "LoggingHandler", "RateLimitHandler", "BusinessLogicHandler");
    }

    @Test
    void requestWithMissingAuthTokenNeverReachesBusinessLogicHandler() {
        BusinessLogicHandler business = new BusinessLogicHandler();
        RequestHandler chain = buildChain(business);
        Request request = new Request("/orders", "client-2", false);

        chain.handle(request);

        assertThat(business.isReached()).isFalse();
        assertThat(request.isRejected()).isTrue();
        assertThat(request.getRejectionReason()).isEqualTo("Missing authentication token");
        assertThat(request.getVisitedHandlers()).containsExactly("AuthenticationHandler");
    }

    @Test
    void loggingHandlerNeverBlocksTheChain() {
        BusinessLogicHandler business = new BusinessLogicHandler();
        RequestHandler chain = buildChain(business);
        Request request = new Request("/orders", "client-3", true);

        chain.handle(request);

        assertThat(request.getVisitedHandlers()).contains("LoggingHandler");
        assertThat(business.isReached()).isTrue();
    }

    @Test
    void rateLimitHandlerRejectsAfterThresholdExceeded() {
        RateLimitHandler rateLimit = new RateLimitHandler(2);
        BusinessLogicHandler firstBusiness = new BusinessLogicHandler();
        RequestHandler chain = new AuthenticationHandler();
        chain.setNext(new LoggingHandler()).setNext(rateLimit).setNext(firstBusiness);

        chain.handle(new Request("/orders", "client-4", true));
        chain.handle(new Request("/orders", "client-4", true));
        Request thirdRequest = new Request("/orders", "client-4", true);
        chain.handle(thirdRequest);

        assertThat(thirdRequest.isRejected()).isTrue();
        assertThat(thirdRequest.getVisitedHandlers()).doesNotContain("BusinessLogicHandler");
    }

    private static RequestHandler buildChain(BusinessLogicHandler business) {
        RequestHandler auth = new AuthenticationHandler();
        auth.setNext(new LoggingHandler()).setNext(new RateLimitHandler(5)).setNext(business);
        return auth;
    }
}
