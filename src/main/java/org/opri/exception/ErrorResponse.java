package org.opri.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ErrorResponse(int status, String message, LocalDateTime time) implements Serializable {
}
