package br.com.senioritymeter.storage.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfiguration {

  @Value("${spring.cloud.credentials.aws.access-key}")
  private String accessKey;

  @Value("${spring.cloud.credentials.aws.secret-key}")
  private String secretKey;

  @Value("${spring.storage.aws-s3.region}")
  private String region;

  @Bean
  public AmazonS3 amazonS3() {
    BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
    AWSStaticCredentialsProvider provider = new AWSStaticCredentialsProvider(credentials);
    return AmazonS3ClientBuilder.standard().withCredentials(provider).withRegion(region).build();
  }
}
