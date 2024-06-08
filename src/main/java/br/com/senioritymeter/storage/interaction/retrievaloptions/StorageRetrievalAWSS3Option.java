package br.com.senioritymeter.storage.interaction.retrievaloptions;

import br.com.senioritymeter.storage.enumeration.StorageType;
import br.com.senioritymeter.storage.interaction.StorageRetrieval.Input;
import br.com.senioritymeter.storage.interaction.StorageRetrieval.Output;
import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.storage.aws-s3.enabled", havingValue = "true")
public class StorageRetrievalAWSS3Option implements StorageRetrievalOption {
  private final AmazonS3 amazonS3;

  @Value("${spring.storage.aws-s3.bucket.name}")
  private String bucketName;

  @Override
  public Output execute(final Input input) {
    var obj = amazonS3.getObject(bucketName, input.getKey());
    var uri = obj.getObjectContent().getHttpRequest().getURI();
    return Output.builder().uri(uri).build();
  }

  @Override
  public boolean canApply(final StorageType storageType) {
    return StorageType.AWS_S3.equals(storageType);
  }
}
