package com.senioritymeter.storage.interaction;

import com.senioritymeter.storage.enumeration.StorageType;
import com.senioritymeter.storage.exception.StorageException;
import com.senioritymeter.storage.interaction.creationoptions.StorageCreationOption;
import com.senioritymeter.storage.utility.FileConverter;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageCreation {
  private final List<StorageCreationOption> options;
  private final StorageRetrieval storageRetrieval;

  public Output execute(final Input input) {
    try {
      var fileConverted = FileConverter.of(input.getFileArray(), input.getFilename());
      input.setFile(fileConverted);

      var option =
          options.stream()
              .filter(o -> o.canApply(input.getType()))
              .findFirst()
              .orElseThrow(StorageException::typeNotSupported);

      final var output = executeOption(input, option);

      Files.delete(fileConverted.toPath());

      return output;
    } catch (Exception e) {
      throw new StorageException(e.getMessage());
    }
  }

  private Output executeOption(final Input input, final StorageCreationOption option) {
    option.create(input);

    var retrievalInput =
        StorageRetrieval.Input.builder()
            .type(input.getType())
            .key(input.getDomain() + input.getFilename())
            .build();

    var retrievalOutput = storageRetrieval.execute(retrievalInput);

    return Output.builder().uri(retrievalOutput.getUri()).build();
  }

  @Getter
  @Builder
  public static class Input {
    private InputStream fileArray;
    private StorageType type;
    private String domain;
    private String filename;
    @Setter private File file;
  }

  @Getter
  @Builder
  public static class Output {
    private URI uri;
  }
}
