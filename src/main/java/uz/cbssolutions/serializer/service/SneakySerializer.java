package uz.cbssolutions.serializer.service;

import reactor.core.publisher.Mono;

public interface SneakySerializer {
    <T> Mono<T> reactiveDeserialization(byte[] bytes, Class<T> tClass);

    <T> Mono<T> reactiveDeserialization(String text, Class<T> tClass);

    <T> T deserialize(byte[] bytes, Class<T> tClass);

    <T> T deserialize(String text, Class<T> tClass);
}
