package com.opensourcelibrary.storage.interaction.removaloptions;

import com.amazonaws.services.s3.AmazonS3;
import com.opensourcelibrary.storage.enumeration.StorageType;
import com.opensourcelibrary.storage.interaction.StorageRemoval.Input;
import com.opensourcelibrary.storage.utility.VerifyCanApply;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StorageRemovalAWSS3Option implements StorageRemovalOption {
  private final AmazonS3 amazonS3;

  @Value("${spring.cloud.storage.aws.s3.bucket.name}")
  private String bucketName;

  @Value("${spring.cloud.storage.aws.s3.enabled}")
  private String enabled;

  @Override
  public void execute(final Input input) {
    amazonS3.deleteObject(bucketName, input.getKey());
  }

  @Override
  public boolean canApply(final StorageType storageType) {
    final var awsS3Enabled = Boolean.TRUE.equals(Boolean.valueOf(enabled));
    return VerifyCanApply.execute(storageType, StorageType.AWS_S3, awsS3Enabled);
  }
}
