package uz.cbssolutions.serializer.service;

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
}
