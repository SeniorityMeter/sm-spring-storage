package br.com.sdkopen.storage.awss3.application.usecase;

import com.amazonaws.services.s3.AmazonS3;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.storage.aws-s3.enabled", havingValue = "true")
public class DeleteObject {
  private final AmazonS3 amazonS3;

  public void execute(final Input input) {
    amazonS3.deleteObject(input.getBucket(), input.getKey());
  }

  @Getter
  @Builder
  public static class Input {
    private String key;
    private String bucket;
  }
}
