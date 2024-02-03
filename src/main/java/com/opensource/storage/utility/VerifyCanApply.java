package com.opensource.storage.utility;

import com.opensource.storage.enumeration.StorageType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VerifyCanApply {

  public static boolean execute(final StorageType currentStorageType, final StorageType optionStorageType, final boolean serviceActive) {
    final var storageTypeMatch = optionStorageType.equals(currentStorageType) || StorageType.ALL.equals(currentStorageType);
    return serviceActive && storageTypeMatch;
  }
}
