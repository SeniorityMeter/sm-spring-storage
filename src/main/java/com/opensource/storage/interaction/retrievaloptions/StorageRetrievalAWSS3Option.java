package com.opensource.storage.interaction.retrievaloptions;

import com.amazonaws.services.s3.AmazonS3;
import com.opensource.storage.enumeration.StorageType;
import com.opensource.storage.utility.VerifyCanApply;
import com.opensource.storage.valueobject.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
public class StorageRetrievalAWSS3Option implements StorageRetrievalOption {
  private final AmazonS3 amazonS3;

  @Value("${spring.cloud.storage.aws.s3.bucket.name}")
  private String bucketName;

  @Value("${spring.cloud.storage.aws.s3.enabled}")
  private String enabled;

  @Override
  public void execute(final Storage storage) {
    var obj = amazonS3.getObject(bucketName, storage.getPathname());
    var uri = obj.getObjectContent().getHttpRequest().getURI();
    storage.getUrls().put(StorageType.AWS_S3, uri);
  }

  @Override
  public boolean canApply(final StorageType storageType) {
    final var awsS3Enabled = Boolean.TRUE.equals(Boolean.valueOf(enabled));
    return VerifyCanApply.execute(storageType, getType(), awsS3Enabled);
  }

  @Override
  public StorageType getType() {
    return StorageType.AWS_S3;
  }
}
