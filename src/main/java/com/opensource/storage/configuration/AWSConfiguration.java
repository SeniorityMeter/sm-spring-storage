package com.opensource.storage.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.opensource.storage.interaction.creationoptions.StorageCreationAWSS3Option;
import com.opensource.storage.interaction.retrievaloptions.StorageRetrievalAWSS3Option;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AWSConfiguration {

  @Value("${spring.cloud.credentials.aws.access-key}")
  private String accessKey;

  @Value("${spring.cloud.credentials.aws.secret-key}")
  private String secretKey;

  @Value("${spring.cloud.storage.aws.s3.enabled}")
  private String enabled;

  @Value("${spring.cloud.storage.aws.s3.region}")
  private String region;

  @Bean
  public AmazonS3 amazonS3() {
    if (Boolean.FALSE.equals(Boolean.valueOf(enabled))) {
      return null;
    }
    BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
    AWSStaticCredentialsProvider provider = new AWSStaticCredentialsProvider(credentials);
    return AmazonS3ClientBuilder.standard().withCredentials(provider).withRegion(region).build();
  }

  @Bean
  public StorageCreationAWSS3Option storageCreationAWSS3Option() {
    if (Boolean.FALSE.equals(Boolean.valueOf(enabled))) {
      return new StorageCreationAWSS3Option(null);
    }
    return new StorageCreationAWSS3Option(amazonS3());
  }

  @Bean
  public StorageRetrievalAWSS3Option storageRetrievalAWSS3Option() {
    if (Boolean.FALSE.equals(Boolean.valueOf(enabled))) {
      return new StorageRetrievalAWSS3Option(null);
    }
    return new StorageRetrievalAWSS3Option(amazonS3());
  }
}
