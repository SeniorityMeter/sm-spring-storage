package br.com.senioritymeter.storage.interaction.removaloptions;

import br.com.senioritymeter.storage.enumeration.StorageType;
import br.com.senioritymeter.storage.interaction.StorageRemoval.Input;
import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.storage.aws-s3.enabled", havingValue = "true")
public class StorageRemovalAWSS3Option implements StorageRemovalOption {
  private final AmazonS3 amazonS3;

  @Override
  public void execute(final Input input) {
    amazonS3.deleteObject(input.getBucket(), input.getKey());
  }

  @Override
  public boolean canApply(final StorageType storageType) {
    return StorageType.AWS_S3.equals(storageType);
  }
}
