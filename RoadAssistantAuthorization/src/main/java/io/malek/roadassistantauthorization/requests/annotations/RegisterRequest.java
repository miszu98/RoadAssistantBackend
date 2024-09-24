package io.malek.roadassistantauthorization.requests.annotations;

import io.malek.roadassistantauthorization.requests.enums.RequestType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RegisterRequest {

    RequestType requestType();

}
