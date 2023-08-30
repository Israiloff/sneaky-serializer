package io.github.israiloff.serializer.service;

import reactor.core.publisher.Mono;

/**
 * Simple handy serializer.
 */
public interface SneakySerializer {

    /**
     * Byte array reactive deserialization operation with runtime exception.
     *
     * @param bytes  Data for deserialization.
     * @param tClass Result class.
     * @param <T>    Type of deserialization result.
     * @return Deserialized data.
     */
    <T> Mono<T> reactiveDeserialization(byte[] bytes, Class<T> tClass);

    /**
     * String overload of reactive deserialization operation with runtime exception.
     *
     * @param text   Text for deserialization.
     * @param tClass Result class.
     * @param <T>    Type of deserialization result.
     * @return Deserialized data.
     */
    <T> Mono<T> reactiveDeserialization(String text, Class<T> tClass);

    /**
     * Byte array deserialization operation with runtime exception.
     *
     * @param bytes  Data for deserialization.
     * @param tClass Result class.
     * @param <T>    Type of deserialization result.
     * @return Deserialized data.
     */
    <T> T deserialize(byte[] bytes, Class<T> tClass);

    /**
     * String overload of deserialization operation with runtime exception.
     *
     * @param text   Text for deserialization.
     * @param tClass Result class.
     * @param <T>    Type of deserialization result.
     * @return Deserialized data.
     */
    <T> T deserialize(String text, Class<T> tClass);

    /**
     * Serialization method.
     *
     * @param data Data to serialize.
     * @param <T>  Type of data to serialize.
     * @return Serialized text.
     */
    <T> String serialize(T data);

    /**
     * Reactive serialization method.
     *
     * @param data Data to serialize.
     * @param <T>  Type of data to serialize.
     * @return Serialized text.
     */
    <T> Mono<String> reactiveSerialization(T data);
}
