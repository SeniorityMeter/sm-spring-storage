package com.opensourcelibrary.storage.exception;

public class StorageException extends RuntimeException {

  public StorageException(String message) {
    super(message);
  }

  public static StorageException typeNotSupported() {
    return new StorageException("Storage type not supported");
  }
}
