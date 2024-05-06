<img src="https://github.com/SeniorityMeter/spring-sm-starter-bom/assets/36059306/ebfcb364-caea-48eb-972a-2d1ae63f4cdb" alt="logo" width="100"/>

# Seniority Meter
## Spring Storage

### Description
This library is a simple storage library for Spring Boot applications. It provides a simple way to store and retrieve files from the file system.

___

### How to use
#### 1. Add the following dependency to your `pom.xml` file:

```xml
<dependencies>
    <dependency>
        <groupId>br.com.senioritymeter.spring</groupId>
        <artifactId>storage</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```
___

#### 2. Add the following properties to your `application.yaml` file:

##### a - Configuration for AWS S3:

```yaml
spring:
  cloud:
    aws:
      credentials:
        access-key: ${AWS_ACCESS_KEY:sm-spring-storage-access-key}
        secret-key: ${AWS_SECRET_KEY:sm-spring-storage-secret-key}
  storage:
    aws-s3:
      enabled: true
      region: ${AWS_REGION:sa-east-1}
      bucket:
        name: ${AWS_S3_BUCKET_NAME:sm-spring-storage-bucket-name}
```
___

#### 3. Use the `StorageCreation` to save files:

Inject the `StorageCreation` bean in your class and use it to save files.
```java
private final StorageCreation storageCreation;
```

Prepare your payload and call the `execute` method.

```java
final var input = StorageCreation.Input.builder()
    .fileArray(new byte[0])
    .filename("filename.extension")
    .domain("path/on/aws") // without "/" at the beginning and at the end
    .type(StorageType.AWS_S3)
    .build();

var output = storageCreation.execute(input);
```
___

#### 4. Use the `StorageRetrieval` to get files:

Inject the `StorageRetrieval` bean in your class and use it to get files.
```java
private final StorageRetrieval storageRetrieval;
```

Prepare your payload and call the `execute` method.

```java
final var input = StorageRetrieval.Input.builder()
    .key("path/on/aws/filename.extension")
    .type(StorageType.AWS_S3)
    .build();

final var output = storageRetrieval.execute(input);
```

The response will contain the URI of the file stored. You can get it using the following code:

```java
output.getUri();
```
___

#### 5. Use the `StorageRemoval` to delete files:

Inject the `StorageRemoval` bean in your class and use it to delete files.
```java
private final StorageRemoval storageRemoval;
```

Prepare your payload and call the `execute` method.

```java
final var input = StorageRemoval.Input.builder()
    .key("path/on/aws/filename.extension")
    .type(StorageType.AWS_S3)
    .build();
    
storageRemoval.execute(input);
```

### License

