package org.example.lab2back.exception;

import java.util.List;

public record ApiError(int code, String message, List<String> details) { }