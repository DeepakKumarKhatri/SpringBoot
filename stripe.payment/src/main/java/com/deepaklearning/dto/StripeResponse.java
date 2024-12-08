package com.deepaklearning.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StripeResponse {
    private static final long serialVersionUID = 1L;
    private String status;
    private String message;
    private String sessionId;
    private String sessionURL;

    // No-argument constructor
    public StripeResponse() {
    }

    // All-arguments constructor
    public StripeResponse(String status, String message, String sessionId, String sessionURL) {
        this.status = status;
        this.message = message;
        this.sessionId = sessionId;
        this.sessionURL = sessionURL;
    }

    public static StripeResponseBuilder builder() {
        return new StripeResponseBuilder();
    }

    public static class StripeResponseBuilder {
        private String status;
        private String message;
        private String sessionId;
        private String sessionURL;

        public StripeResponseBuilder() {
        }

        public StripeResponseBuilder status(String status) {
            this.status = status;
            return this;
        }

        public StripeResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public StripeResponseBuilder sessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public StripeResponseBuilder sessionURL(String sessionURL) {
            this.sessionURL = sessionURL;
            return this;
        }

        public StripeResponse build() {
            return new StripeResponse(
                    this.status,
                    this.message,
                    this.sessionId,
                    this.sessionURL
            );
        }
    }
}