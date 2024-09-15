package br.com.sdkopen.awss3.infrastructure.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageError {
  private String message;
  private String details;
}
