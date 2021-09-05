package com.github.djbamba.db0921.exception;

public class NoSuchToolException extends RuntimeException {
  public NoSuchToolException(String id) {
    super(
        String.format(
            "Unable to locate tool with matching code [%s]. Ensure correct code is entered.", id));
  }
}
