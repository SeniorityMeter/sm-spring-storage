package com.opensource.storage.interaction.options;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

import com.opensource.storage.enumeration.StorageType;
import com.opensource.storage.valueobject.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;


@RequiredArgsConstructor
public class StorageCreationAWSS3Option implements StorageCreationOption {

  private final AmazonS3 amazonS3;

  @Value("${spring.cloud.storage.aws.s3.bucket.name}")
  private String bucketName;

  @Value("${spring.cloud.storage.aws.s3.enabled}")
  private String enabled;

  @Override
  public void create(final Storage storage) {
    var awsObj = new PutObjectRequest(bucketName, storage.getPathname(), storage.getFile());
    amazonS3.putObject(awsObj);
  }

  @Override
  public boolean canApply(final StorageType storageType) {
    final var awsS3Enabled = Boolean.TRUE.equals(Boolean.valueOf(enabled));
    final var storageTypeMatch = getType().equals(storageType) || StorageType.ALL.equals(storageType);
    return awsS3Enabled && storageTypeMatch;
  }

  @Override
  public StorageType getType() {
    return StorageType.AWS_S3;
  }
}
