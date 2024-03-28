package com.senioritymeter.storage.interaction.creationoptions;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.senioritymeter.storage.enumeration.StorageType;
import com.senioritymeter.storage.interaction.StorageCreation.Input;
import com.senioritymeter.storage.utility.VerifyCanApply;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StorageCreationAWSS3Option implements StorageCreationOption {

  private final AmazonS3 amazonS3;

  @Value("${spring.storage.aws-s3.bucket.name}")
  private String bucketName;

  @Value("${spring.storage.aws-s3.enabled}")
  private String enabled;

  @Override
  public void create(final Input input) {
    var key = input.getDomain() + "/" + input.getFilename();
    var awsObj = new PutObjectRequest(bucketName, key, input.getFile());
    amazonS3.putObject(awsObj);
  }

  @Override
  public boolean canApply(final StorageType storageType) {
    final var awsS3Enabled = Boolean.TRUE.equals(Boolean.valueOf(enabled));
    return VerifyCanApply.execute(storageType, StorageType.AWS_S3, awsS3Enabled);
  }
}
