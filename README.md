<img src="https://github.com/SeniorityMeter/spring-sm-starter-bom/assets/36059306/ebfcb364-caea-48eb-972a-2d1ae63f4cdb" alt="logo" width="100"/>

# Seniority Meter
## Open Source Library - Spring Storage

### Description
This library is a simple storage library for Spring Boot applications. It provides a simple way to store and retrieve files from the file system.

### How to use
#### 1. Add the following dependency to your `pom.xml` file:

```xml
<dependency>
    <groupId>com.opensourcelibrary.spring</groupId>
    <artifactId>storage</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### 2. add scanBasePackages to your SpringBootApplication
```java
@SpringBootApplication(scanBasePackages = {"com.opensourcelibrary", "your.package.name.here"})
```

#### 3. Add the following properties to your `application.yaml` file:

##### a - Configuration for AWS S3:

```yaml
spring:
  cloud:
    credentials:
      aws:
        access-key: ${AWS_ACCESS_KEY:os-spring-storage-access-key}
        secret-key: ${AWS_SECRET_KEY:os-spring-storage-secret-key}
    storage:
      aws:
        s3:
          enabled: true
          region: ${AWS_REGION:sa-east-1}
          bucket:
            name: ${AWS_S3_BUCKET_NAME:os-spring-storage-bucket-name}
```

#### 4. Use the `StorageCreation` to save files:

Inject the `StorageCreation` bean in your class and use it to save files.
```java
private final StorageCreation storageCreation;
```

Prepare your payload and call the `execute` method.

```java
StorageRequest request = StorageRequest.builder()
    .fileArray(new byte[0])
    .filename("filename.extension")
    .domain("path/on/aws")
    .type(StorageType.AWS_S3)
    .build();

storageCreation.execute(request);
```

#### 5. Use the `StorageRetrieval` to get files:

Inject the `StorageRetrieval` bean in your class and use it to get files.
```java
private final StorageRetrieval storageRetrieval;
```

Prepare your payload and call the `execute` method.

```java
StorageRequest request = StorageRequest.builder()
    .filename("filename.extension")
    .domain("path/on/aws")
    .type(StorageType.AWS_S3)
    .build();

StorageResponse response = storageRetrieval.execute(request);
```

The response will contain the URL of the file based of StorageType. And you can get the URL using the following code:

```java
response.getUrl().get(StorageType.AWS_S3);
```

### License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

