package com.opensourcelibrary.storage.utility;

import com.opensourcelibrary.storage.exception.StorageException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileConverter {
  public static File of(final MultipartFile file, final String filename) throws IOException {
    File fileConverted = new File(Objects.requireNonNull(filename));

    try (FileOutputStream fos = new FileOutputStream(fileConverted)) {
      fos.write(file.getBytes());
    } catch (IOException e) {
      log.info(e.getMessage());
      Files.delete(fileConverted.toPath());
      throw new StorageException("Error on file conversion");
    }

    return fileConverted;
  }
}
