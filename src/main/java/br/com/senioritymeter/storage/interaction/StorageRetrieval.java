package br.com.senioritymeter.storage.interaction;

import br.com.senioritymeter.storage.enumeration.StorageType;
import br.com.senioritymeter.storage.exception.StorageException;
import br.com.senioritymeter.storage.interaction.retrievaloptions.StorageRetrievalOption;
import java.net.URI;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageRetrieval {
  private final List<StorageRetrievalOption> options;

  public Output execute(final Input input) {
    try {
      var option =
          options.stream()
              .filter(o -> o.canApply(input.getType()))
              .findFirst()
              .orElseThrow(StorageException::typeNotSupported);

      return option.execute(input);
    } catch (Exception e) {
      throw StorageException.with(e.getMessage());
    }
  }

  @Getter
  @Builder
  public static class Input {
    private StorageType type;
    private String key;
  }

  @Getter
  @Builder
  public static class Output {
    private URI uri;
  }
}
