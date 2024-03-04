package com.opensourcelibrary.storage.interaction.retrievaloptions;

import com.amazonaws.services.s3.AmazonS3;
import com.opensourcelibrary.storage.enumeration.StorageType;
import com.opensourcelibrary.storage.utility.VerifyCanApply;
import com.opensourcelibrary.storage.valueobject.StorageRequest;
import com.opensourcelibrary.storage.valueobject.StorageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StorageRetrievalAWSS3Option implements StorageRetrievalOption {
  private final AmazonS3 amazonS3;

  @Value("${spring.cloud.storage.aws.s3.bucket.name}")
  private String bucketName;

  @Value("${spring.cloud.storage.aws.s3.enabled}")
  private String enabled;

  @Override
  public StorageResponse execute(final StorageRequest request, final StorageResponse response) {
    var key = request.getDomain() + request.getFilename();
    var obj = amazonS3.getObject(bucketName, key);
    var uri = obj.getObjectContent().getHttpRequest().getURI();
    response.getUrls().put(StorageType.AWS_S3, uri);
    return response;
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
