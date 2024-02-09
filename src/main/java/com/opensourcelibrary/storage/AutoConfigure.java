package com.opensourcelibrary.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.opensourcelibrary.storage.interaction.StorageCreation;
import com.opensourcelibrary.storage.interaction.StorageRetrieval;
import com.opensourcelibrary.storage.interaction.creationoptions.StorageCreationAWSS3Option;
import com.opensourcelibrary.storage.interaction.creationoptions.StorageCreationOption;
import com.opensourcelibrary.storage.interaction.retrievaloptions.StorageRetrievalAWSS3Option;
import com.opensourcelibrary.storage.interaction.retrievaloptions.StorageRetrievalOption;
import java.util.List;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
public class AutoConfigure {

  /** Create a bean of StorageCreationAWSS3Option */
  @Bean
  @ConditionalOnMissingBean
  public StorageCreationAWSS3Option storageCreationAWSS3Option(AmazonS3 amazonS3) {
    return new StorageCreationAWSS3Option(amazonS3);
  }

  @Bean
  @ConditionalOnMissingBean
  public List<StorageCreationOption> storageCreationOptions(AmazonS3 amazonS3) {
    return List.of(storageCreationAWSS3Option(amazonS3));
  }

  @Bean
  @ConditionalOnMissingBean
  public StorageCreation storageCreation(AmazonS3 amazonS3) {
    return new StorageCreation(storageCreationOptions(amazonS3));
  }

  /** Create a bean of StorageRetrievalAWSS3Option */
  @Bean
  @ConditionalOnMissingBean
  public StorageRetrievalAWSS3Option storageRetrievalAWSS3Option(AmazonS3 amazonS3) {
    return new StorageRetrievalAWSS3Option(amazonS3);
  }

  @Bean
  @ConditionalOnMissingBean
  public List<StorageRetrievalOption> storageRetrievalOptions(AmazonS3 amazonS3) {
    return List.of(storageRetrievalAWSS3Option(amazonS3));
  }

  @Bean
  @ConditionalOnMissingBean
  public StorageRetrieval storageRetrieval(AmazonS3 amazonS3) {
    return new StorageRetrieval(storageRetrievalOptions(amazonS3));
  }
}
