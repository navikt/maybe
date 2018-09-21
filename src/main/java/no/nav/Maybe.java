package no.nav;

/* a type representing a value we may or may not have.
    it does not indicate whether or not the actual value is null or non-null
 */
public interface Maybe<T> {
    boolean isPresent();
    T value();

    static <T> Maybe<T> of(T value) {
        if (value == null) {
            return Maybe.None();
        }
        return Maybe.Some(value);
    }

    static <T> None<T> None() {
        return new None<>();
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

        public T value() {
            return value;
        }

        public boolean isPresent() {
            return true;
        }
    }

    /* we have nothing */
    final class None<T> implements Maybe<T> {
        private None() {
        }

        @Override
        public boolean isPresent() {
            return false;
        }

        @Override
        public T value() {
            throw new IllegalStateException("value is not present");
        }
    }
}
