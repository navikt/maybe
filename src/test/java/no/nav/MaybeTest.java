package no.nav;

import org.junit.Assert;
import org.junit.Test;

public class MaybeTest {

    @Test
    public void testThatNullIsNone() {
        Maybe<String> obj = Maybe.of(null);
        Assert.assertTrue(obj.isNone());
    }

    @Test
    public void testThatAnythingIsSome() {
        Maybe<String> obj = Maybe.of("foo");
        Assert.assertFalse(obj.isNone());
    }

    @Test
    public void testThatAnythingCanHaveValue() {
        Maybe<String> obj = Maybe.of("foo");
        Assert.assertEquals("foo", obj.getOrThrow());
    }

    @Test
    public void thatNoneIsNone() {
        Maybe<String> obj = Maybe.None();
        Assert.assertTrue(obj.isNone());
    }

    @Test(expected = IllegalStateException.class)
    public void thatNoneCannotHaveValue() {
        Maybe<String> obj = Maybe.None();
        obj.getOrThrow();
    }

    @Test
    public void thatDefaultValueIsReturned() {
        Maybe<String> obj = Maybe.None();
        Assert.assertEquals("foo", obj.getOrElse("foo"));
    }

    @Test
    public void thatDefaultValueIsReturnedFromSupplier() {
        Maybe<String> obj = Maybe.None();
        Assert.assertEquals("foo", obj.getOrElse(() -> "foo"));
    }

    @Test
    public void thatNoneCanBePrinted() {
        Maybe<String> obj = Maybe.None();
        Assert.assertEquals("None", obj.toString());
    }

    @Test
    public void thatNoneMapsToNone() {
        Assert.assertTrue(Maybe.None().map(a -> a).isNone());
    }

    @Test
    public void thatNoneFlatMapsToNone() {
        Maybe<Integer> obj1 = Maybe.None();
        Maybe<String> obj2 = obj1.flatMap(a -> Maybe.Some("foo"));
        Assert.assertTrue(obj2.isNone());
    }

    @Test
    public void thatNoneReturnsElse() {
        Maybe<String> obj = Maybe.<String>None().orElse(() -> Maybe.Some("foo"));
        Assert.assertEquals("foo", obj.getOrThrow());
    }

    @Test
    public void thatNoneFiltersToNone() {
        Maybe<String> obj = Maybe.<String>None().filter((o) -> true);
        Assert.assertTrue(obj.isNone());
    }

    @Test
    public void thatSomeWithNullIsSome() {
        Maybe<String> obj = Maybe.Some(null);
        Assert.assertFalse(obj.isNone());
    }

    @Test
    public void thatSomeCanHaveNull() {
        Maybe<String> obj = Maybe.Some(null);
        Assert.assertNull(obj.getOrThrow());
    }

    @Test
    public void thatSomeWithAnythingIsSome() {
        Maybe<String> obj = Maybe.Some("foo");
        Assert.assertFalse(obj.isNone());
    }

    @Test
    public void thatSomeCanHaveAnything() {
        Maybe<String> obj = Maybe.Some("foo");
        Assert.assertEquals("foo", obj.getOrThrow());
    }

    @Test
    public void thatDefaultValueIsNotReturned() {
        Maybe<String> obj = Maybe.Some("foo");
        Assert.assertEquals("foo", obj.getOrElse("bar"));
    }

    @Test
    public void thatDefaultValueIsNotReturnedFromSupplier() {
        Maybe<String> obj = Maybe.Some("foo");
        Assert.assertEquals("foo", obj.getOrElse(() -> "bar"));
    }

    @Test
    public void thatSomeCanBeMapped() {
        Maybe<String> obj1 = Maybe.Some("foo");
        Maybe<Integer> obj2 = obj1.map(a -> 5);
        Assert.assertEquals(5, (int)obj2.getOrThrow());
    }

    @Test
    public void thatSomeCanBeMappedToNone() {
        Maybe<String> obj1 = Maybe.Some("foo");
        Maybe<Integer> obj2 = obj1.flatMap(a -> Maybe.None());
        Assert.assertTrue(obj2.isNone());
    }

    @Test
    public void thatSomeDoesNotReturnElse() {
        Maybe<String> obj = Maybe.Some("foo").orElse(() -> Maybe.Some("bar"));
        Assert.assertEquals("foo", obj.getOrThrow());
    }

    @Test
    public void thatSomeFiltersToNone() {
        Maybe<String> obj = Maybe.Some("foo").filter("bar"::equals);
        Assert.assertTrue(obj.isNone());
    }

    @Test
    public void thatSomeCanBePrinted() {
        Maybe<String> obj = Maybe.Some("foo");
        Assert.assertEquals("Some{value=foo}", obj.toString());
    }
}
