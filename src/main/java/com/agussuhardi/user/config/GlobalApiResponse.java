package com.agussuhardi.user.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.StringJoiner;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class GlobalApiResponse<T> {

    private final long timestamp = System.currentTimeMillis();
    private int status;
    private String error;
    private String message = "success";
    private T data;

    public GlobalApiResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    public GlobalApiResponse(int status) {
        this.status = status;
    }

    public String toJson() {
        return new StringJoiner(", ", "{", "}")
                .add("\"timestamp\": \"" + timestamp + "\"")
                .add("\"status\": " + status)
                .add("\"error\": \"" + error + "\"")
                .add("\"message\": \"" + message + "\"")
                .add("\"data\": \"" + data + "\"")
                .toString();
    }

    public ResponseEntity<?> entity() {
        return ResponseEntity.status(status).headers(HttpHeaders.EMPTY).body(this);
    }
}
