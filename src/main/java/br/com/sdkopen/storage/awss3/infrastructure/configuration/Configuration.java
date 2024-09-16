package br.com.sdkopen.storage.awss3.infrastructure.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
@ConditionalOnProperty(name = "sdkopen.aws-s3.enabled", havingValue = "true")
public class Configuration {

  @Value("${sdkopen.cloud.credentials.aws.access-key}")
  private String accessKey;

  @Value("${sdkopen.cloud.credentials.aws.secret-key}")
  private String secretKey;

  @Value("${sdkopen.storage.aws-s3.region}")
  private String region;

  @Bean
  public AmazonS3 amazonS3() {
    BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
    AWSStaticCredentialsProvider provider = new AWSStaticCredentialsProvider(credentials);
    return AmazonS3ClientBuilder.standard().withCredentials(provider).withRegion(region).build();
  }
}
