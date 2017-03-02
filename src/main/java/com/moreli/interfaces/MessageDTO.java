package com.moreli.interfaces;

public class MessageDTO {
    private String message;
    private String author;

    private MessageDTO(Builder builder) {
        message = builder.message;
        author = builder.author;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }


    public static final class Builder {
        private String message;
        private String author;

        private Builder() {
        }

        public Builder withMessage(String val) {
            message = val;
            return this;
        }

        public Builder withAuthor(String val) {
            author = val;
            return this;
        }

        public MessageDTO build() {
            return new MessageDTO(this);
        }
    }
}
