package com.set.sazib.utils.logger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.set.sazib.utils.logger.LogUtils.checkNotNull;

public class AndroidLogAdapter implements LogAdapter {

  @NonNull
  private final FormatStrategy formatStrategy;

  public AndroidLogAdapter() {
    this.formatStrategy = PrettyFormatStrategy.newBuilder().build();
  }

  public AndroidLogAdapter(@NonNull FormatStrategy formatStrategy) {
    this.formatStrategy = checkNotNull(formatStrategy);
  }

  @Override
  public boolean isLoggable(int priority, @Nullable String tag) {
    return true;
  }

  @Override
  public void log(int priority, @Nullable String tag, @NonNull String message) {
    formatStrategy.log(priority, tag, message);
  }
}
