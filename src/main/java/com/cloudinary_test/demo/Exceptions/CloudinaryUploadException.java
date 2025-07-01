package com.cloudinary_test.demo.Exceptions;

public class CloudinaryUploadException extends RuntimeException{
    public CloudinaryUploadException(String message, Throwable cause){
        super(message, cause);
    }
}
