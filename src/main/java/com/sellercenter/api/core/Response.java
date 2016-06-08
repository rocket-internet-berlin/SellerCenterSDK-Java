package com.sellercenter.api.core;

import javax.json.JsonObject;

public interface Response {

    /**
     * Simple getter for request action
     *
     * @return the request action
     */
    String getRequestAction();

    /**
     * Get the body of the response, in a JSON like representation,
     * including List<Object> and primitive value Object.
     *
     * @return A map representing the content of the body
     */
    JsonObject getBody();
}
