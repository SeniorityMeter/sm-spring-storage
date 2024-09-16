package br.com.sdkopen.storage.awss3.application.usecase;

import br.com.sdkopen.storage.awss3.infrastructure.exception.SDKOpenAwsS3Exception;
import br.com.sdkopen.storage.awss3.infrastructure.utility.FileConverter;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.InputStream;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.storage.aws-s3.enabled", havingValue = "true")
public class PutObject {
  private final AmazonS3 amazonS3;

  public void execute(final Input input) {
    try {
      var file = FileConverter.of(input.getFile(), input.getFilename());
      var awsObj = new PutObjectRequest(input.getBucket(), input.getKey(), file);
      amazonS3.putObject(awsObj);
    } catch (Exception e) {
      throw SDKOpenAwsS3Exception.with(e.getMessage());
    }
  }

  @Getter
  @Builder
  public static class Input {
    private InputStream file;
    private String key;
    private String filename;
    private String bucket;
  }
}
