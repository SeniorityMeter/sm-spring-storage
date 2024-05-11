package br.com.senioritymeter.storage.interaction.retrievaloptions;

import br.com.senioritymeter.storage.enumeration.StorageType;
import br.com.senioritymeter.storage.interaction.StorageRetrieval.Input;
import br.com.senioritymeter.storage.interaction.StorageRetrieval.Output;
import br.com.senioritymeter.storage.utility.VerifyCanApply;
import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StorageRetrievalAWSS3Option implements StorageRetrievalOption {
  private final AmazonS3 amazonS3;

  @Value("${spring.storage.aws-s3.bucket.name}")
  private String bucketName;

  @Value("${spring.storage.aws-s3.enabled}")
  private String enabled;

  @Override
  public Output execute(final Input input) {
    var obj = amazonS3.getObject(bucketName, input.getKey());
    var uri = obj.getObjectContent().getHttpRequest().getURI();
    return Output.builder().uri(uri).build();
  }

  @Override
  public boolean canApply(final StorageType storageType) {
    final var awsS3Enabled = Boolean.TRUE.equals(Boolean.valueOf(enabled));
    return VerifyCanApply.execute(storageType, StorageType.AWS_S3, awsS3Enabled);
  }
}