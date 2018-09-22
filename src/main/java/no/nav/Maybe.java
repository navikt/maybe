package no.nav;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* a type representing a value we may or may not have.
    it does not indicate whether or not the actual value is null or non-null
 */
public interface Maybe<T> {
    boolean isPresent();
    T getOrThrow();
    T getOrElse(T defaultValue);
    T getOrElse(Supplier<T> supplier);

    default Maybe<T> orElse(Supplier<Maybe<T>> supplier) {
        return map(x -> this).getOrElse(supplier);
    }

    default Maybe<T> filter(Predicate<T> f) {
        return flatMap(x -> f.test(x) ? this : None());
    }

    <R> Maybe<R> map(Function<T, R> f);

    default <R> Maybe<R> flatMap(Function<T, Maybe<R>> f) {
        return map(f).getOrElse(Maybe::None);
    }

    static <T> Maybe<T> of(T value) {
        if (value == null) {
            return Maybe.None();
        }
        return Maybe.Some(value);
    }

    @SuppressWarnings("unchecked")
    static <T> None<T> None() {
        return (None<T>)None.INSTANCE;
    }

    static <T> Some<T> Some(T value) {
        return new Some<>(value);
    }

    /* we have something */
    final class Some<T> implements Maybe<T> {
        private final T value;

        private Some(T value) {
            this.value = value;
        }

        @Override
        public boolean isPresent() {
            return true;
        }

        @Override
        public T getOrThrow() {
            return value;
        }

        @Override
        public T getOrElse(T defaultValue) {
            return value;
        }

        @Override
        public T getOrElse(Supplier<T> supplier) {
            return value;
        }

        @Override
        public <R> Maybe<R> map(Function<T, R> f) {
            return Maybe.Some(f.apply(value));
        }

        @Override
        public String toString() {
            return "Some{value=" + value + '}';
        }
    }

    /* we have nothing */
    final class None<T> implements Maybe<T> {
        private static final None INSTANCE = new None();

        private None() {
        }

        @Override
        public boolean isPresent() {
            return false;
        }

        @Override
        public T getOrThrow() {
            throw new IllegalStateException("value is not present");
        }

        @Override
        public T getOrElse(T defaultValue) {
            return defaultValue;
        }

        @Override
        public T getOrElse(Supplier<T> supplier) {
            return supplier.get();
        }

        @Override
        public <R> Maybe<R> map(Function<T, R> f) {
            return Maybe.None();
        }

        @Override
        public String toString() {
            return "None";
        }
    }
}
