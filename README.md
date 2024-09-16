<img src="https://github.com/user-attachments/assets/801ecb0c-455c-41a8-bb52-15d4318f2e78" alt="logo" width="100" style="border-radius: 50%;" />

# SDK Open
## Spring Storage AWS S3

### Description
A simple Storage AWS S3 SDK for Spring Boot applications.

___

### How to use
#### 1. Add the following parent to your `pom.xml` file:

```xml
<parent>
    <groupId>br.com.sdkopen</groupId>
    <artifactId>parent</artifactId>
    <version>1.0.0</version>
</parent>
```
___

#### 2. add scanBasePackages to your SpringBootApplication
```java
@SpringBootApplication(scanBasePackages = {"br.com.sdkopen", "your.package.name.here"})
```
___

#### 3. Add the following dependency to your `pom.xml` file:

```xml
<dependencies>
    <dependency>
        <groupId>br.com.sdkopen</groupId>
        <artifactId>storage-aws-s3</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

___

#### 4. Add the following properties to your `application.yaml` file:

##### a - Configuration:

```yaml
sdkopen:
  cloud:
    aws:
      credentials:
        access-key: ${AWS_ACCESS_KEY:sm-spring-storage-access-key}
        secret-key: ${AWS_SECRET_KEY:sm-spring-storage-secret-key}
  storage:
    aws-s3:
      enabled: true
      region: ${AWS_REGION:sa-east-1}
```
___

#### 5. Use the `PutObject` to upload files in AWS S3:

Inject the `PutObject` bean in your class and use it to save files.
```java
private final PutObject putObject;
```

Prepare your payload and call the `execute` method.

```java
final var input = PutObject.Input.builder()
    .file(new byte[0])
    .filename("filename.extension")
    .key("path/on/aws/filename.extension") // without "/" at the beginning and at the end
    .bucket("bucket-name")
    .build();

putObject.execute(input);
```
___

#### 6. Use the `GetObject` to get files in AWS S3:

Inject the `GetObject` bean in your class and use it to get files.
```java
private final GetObject getObject;
```

Prepare your payload and call the `execute` method.

```java
final var input = GetObject.Input.builder()
    .key("path/on/aws/filename.extension")
    .bucket("bucket-name")
    .build();

final var output = getObject.execute(input);
```

The response will contain the URI of the file stored. You can get it using the following code:

```java
output.getUri();
```
___

#### 7. Use the `DeleteObject` to delete files in AWS S3:

Inject the `DeleteObject` bean in your class and use it to delete files.
```java
private final DeleteObject deleteObject;
```

Prepare your payload and call the `execute` method.

```java
final var input = DeleteObject.Input.builder()
    .key("path/on/aws/filename.extension")
    .bucket("bucket-name")    
    .build();
    
deleteObject.execute(input);
```
