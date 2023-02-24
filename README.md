# Sneaky serializer plugin

The main idea of [***this***](https://gitlab.hayotbank.uz/mobile-back/plugins/general/sneaky-serializer) plugin is to
give an opportunity of serialization/deserialization of data in [***reactive***](https://projectreactor.io/) manner
without
catching exception cases (it throws exceptions at runtime). It implements both [
***reactive***](https://projectreactor.io/)
and imperative types of operations.

## Sample code

```java
/**
 * Sample serializable model.
 *
 * @param data Sample data.
 */
record SampleModel(String data) implements Serializable {
}


/**
 * Imperative deserialization sample.
 */
@Component
@RequiredArgsConstructor
class DeserializationSample {

    private final SneakySerializer serializer;

    /**
     * Imperative deserialization sample.
     *
     * @param json Data to deserialize.
     */
    void deserializeImperative(String json) {
        SampleModel result = serializer.deserialize(json, SampleModel.class);
        System.out.println(result);
    }

    /**
     * Reactive deserialization sample.
     *
     * @param json Data to deserialize.
     */
    void deserializeReactive(String json) {
        serializer
                .reactiveDeserialization(json, SampleModel.class)
                .subscribe(System.out::println);
    }

    /**
     * Imperative serialization sample.
     *
     * @param model Sample data.
     */
    void serializeImperative(SampleModel model) {
        String result = serializer.serialize(model);
        System.out.println(result);
    }

    /**
     * Reactive serialization sample.
     *
     * @param model Sample data.
     */
    void serializeReactive(SampleModel model) {
        serializer
                .reactiveSerialization(model)
                .subscribe(System.out::println);
    }
}
```