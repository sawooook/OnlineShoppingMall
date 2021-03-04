package util;

import jdk.jshell.Snippet;

import javax.persistence.Enumerated;

public class ResponseMessage {

    private Object data;

    private ResponseStatusMessage responseStatusMessage;

    private enum ResponseStatusMessage {
        OK(200, "gello");

        private int message;
        private String ResponseCode;

        ResponseStatusMessage(int message, String responseCode) {
            this.message = message;
            this.ResponseCode = responseCode;
        }
    }
}