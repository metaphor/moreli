package com.moreli.interfaces.facade.dto;

import java.util.Objects;

public class QueryUserReposResponse {
    private String content;

    public QueryUserReposResponse(String firstHundred) {
        this.content = firstHundred;
    }

    public QueryUserReposResponse() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final QueryUserReposResponse that = (QueryUserReposResponse) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
