package br.com.sdkopen.awss3.application.usecase;

import com.amazonaws.services.s3.AmazonS3;
import java.net.URI;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.aws-s3.enabled", havingValue = "true")
public class GetObject {
  private final AmazonS3 amazonS3;

  public Output execute(final Input input) {
    var obj = amazonS3.getObject(input.getBucket(), input.getKey());
    var uri = obj.getObjectContent().getHttpRequest().getURI();
    return Output.builder().uri(uri).build();
  }

  @Getter
  @Builder
  public static class Input {
    private String bucket;
    private String key;
  }

  @Getter
  @Builder
  public static class Output {
    private URI uri;
  }
}
