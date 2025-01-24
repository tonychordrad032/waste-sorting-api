package com.enviro.assessment.grad001.senelenyaba.utils;

import lombok.Getter;
import lombok.Setter;

// This class is used to customize the structure of the response sent to the client.
// It encapsulates a status code, a message, and the result data, making responses more consistent and easier to understand.

@Getter // Lombok's annotation to automatically generate getter methods for all fields.
@Setter // Lombok's annotation to automatically generate setter methods for all fields.
public class ResponseResult {

    private int statusCode; // The HTTP status code of the response (e.g., 200, 400, 404).
    private String message; // A descriptive message about the response (e.g., success or error details).
    private Object results; // The data or results returned in the response (can hold any object).

    // Constructor to initialize the ResponseResult with a status code, message, and result data.
    public ResponseResult(int statusCode, String message, Object results) {
        this.statusCode = statusCode;
        this.message = message;
        this.results = results;
    }
}

