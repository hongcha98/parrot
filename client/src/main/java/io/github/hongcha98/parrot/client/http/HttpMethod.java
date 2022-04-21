package io.github.hongcha98.parrot.client.http;

public enum HttpMethod {
    GET,
    POST(true),
    PUT(true),
    DELETE;


    boolean body;

    HttpMethod() {
        this(false);
    }

    HttpMethod(boolean body) {
        this.body = body;
    }

    public boolean isHaveBody() {
        return body;
    }


}
