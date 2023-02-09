package uz.cbssolutions.serializer.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import uz.cbssolutions.serializer.service.SneakySerializer;

import java.util.function.BiFunction;

/**
 * Json serializer. Uses Jackson serializer for serialization/deserialization operations.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SneakySerializerImpl implements SneakySerializer {

    /**
     * A base instance of Jackson's serializer.
     */
    private final ObjectMapper objectMapper;

    private <TResult, TData> Mono<TResult> reactiveCall(BiFunction<TData, Class<TResult>, TResult> serializer,
                                                        TData data, Class<TResult> resultClass) {
        return Mono.<TResult>create(sink -> {
            TResult result;
            try {
                result = serializer.apply(data, resultClass);
            } catch (Throwable e) {
                sink.error(e);
                return;
            }
            sink.success(result);
        }).publishOn(Schedulers.boundedElastic());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Mono<T> reactiveDeserialization(byte[] bytes, Class<T> tClass) {
        return reactiveCall(this::deserialize, bytes, tClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Mono<T> reactiveDeserialization(String text, Class<T> tClass) {
        return reactiveCall(this::deserialize, text, tClass);
    }

    /**
     * {@inheritDoc}
     */
    @SneakyThrows
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> tClass) {
        var result = objectMapper.readValue(bytes, tClass);
        log.debug("deserialization result : {}", result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @SneakyThrows
    @Override
    public <T> T deserialize(String text, Class<T> tClass) {
        var result = objectMapper.readValue(text, tClass);
        log.debug("deserialization result : {}", result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @SneakyThrows
    @Override
    public <T> String serialize(T data) {
        var result = objectMapper.writeValueAsString(data);
        log.debug("serialization result : {}", result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @SneakyThrows
    @Override
    public <T> Mono<String> reactiveSerialization(T data) {
        return Mono.<String>create(sink -> {
            String result;
            try {
                result = objectMapper.writeValueAsString(data);
            } catch (Throwable e) {
                sink.error(e);
                return;
            }
            sink.success(result);
        }).publishOn(Schedulers.boundedElastic());
    }
}
