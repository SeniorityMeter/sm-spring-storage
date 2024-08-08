package br.com.senioritymeter.storage.interaction.creationoptions;

import br.com.senioritymeter.storage.enumeration.StorageType;
import br.com.senioritymeter.storage.interaction.StorageCreation.Input;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.storage.aws-s3.enabled", havingValue = "true")
public class StorageCreationAWSS3Option implements StorageCreationOption {

  private final AmazonS3 amazonS3;

  @Override
  public void create(final Input input) {
    var key = input.getDomain() + "/" + input.getFilename();
    var awsObj = new PutObjectRequest(input.getBucket(), key, input.getFile());
    amazonS3.putObject(awsObj);
  }

  @Override
  public boolean canApply(final StorageType storageType) {
    return StorageType.AWS_S3.equals(storageType);
  }
}
