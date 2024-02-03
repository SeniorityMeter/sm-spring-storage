package com.opensource.storage.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.opensource.storage.interaction.options.StorageCreationAWSS3Option;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class AWSConfiguration {

  @Value("${spring.cloud.storage.aws.credentials.access-key}")
  private String accessKey;

  @Value("${spring.cloud.storage.aws.credentials.secret-key}")
  private String secretKey;

  @Value("${spring.cloud.storage.aws.s3.enabled}")
  private String enabled;

  @Bean
  public AmazonS3 amazonS3() {
    if (Boolean.FALSE.equals(Boolean.valueOf(enabled))) {
      return null;
    }
    BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
    AWSStaticCredentialsProvider provider = new AWSStaticCredentialsProvider(credentials);
    return AmazonS3ClientBuilder.standard()
        .withCredentials(provider)
        .withRegion(Regions.US_EAST_1)
        .build();
  }

  @Bean
  public StorageCreationAWSS3Option storageCreationAWSS3Option() {
    if (Boolean.FALSE.equals(Boolean.valueOf(enabled))) {
      return new StorageCreationAWSS3Option(null);
    }
    return new StorageCreationAWSS3Option(amazonS3());
  }
}
