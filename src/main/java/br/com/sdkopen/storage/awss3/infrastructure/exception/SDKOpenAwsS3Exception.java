package br.com.sdkopen.storage.awss3.infrastructure.exception;

public class SDKOpenAwsS3Exception extends RuntimeException {
  public static final String IDENTIFIER = "[AWS S3] %s";

  private SDKOpenAwsS3Exception(String message) {
    super(message);
  }

  public static SDKOpenAwsS3Exception with(String message) {
    return new SDKOpenAwsS3Exception(String.format(IDENTIFIER, message));
  }
}
