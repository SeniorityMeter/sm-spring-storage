package br.com.sdkopen.awss3.infrastructure.exception;

public class SDKOpenAwsS3Exception extends RuntimeException {
  public static final String IDENTIFIER = "[AWS S3] %s";

  public SDKOpenAwsS3Exception(String message) {
    super(message);
  }

  public static SDKOpenAwsS3Exception typeNotSupported() {
    return new SDKOpenAwsS3Exception("Storage type not supported");
  }

  public static SDKOpenAwsS3Exception with(String message) {
    return new SDKOpenAwsS3Exception(String.format(IDENTIFIER, message));
  }
}
