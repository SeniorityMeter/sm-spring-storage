package com.opensourcelibrary.storage.interaction.creationoptions;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.opensourcelibrary.storage.enumeration.StorageType;
import com.opensourcelibrary.storage.utility.VerifyCanApply;
import com.opensourcelibrary.storage.valueobject.StorageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StorageCreationAWSS3Option implements StorageCreationOption {

  private final AmazonS3 amazonS3;

  @Value("${spring.cloud.storage.aws.s3.bucket.name}")
  private String bucketName;

  @Value("${spring.cloud.storage.aws.s3.enabled}")
  private String enabled;

  @Override
  public void create(final StorageRequest request) {
    var key = request.getDomain() + request.getFilename();
    var awsObj = new PutObjectRequest(bucketName, key, request.getFile());
    amazonS3.putObject(awsObj);
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
