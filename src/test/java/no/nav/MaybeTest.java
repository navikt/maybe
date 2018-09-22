package no.nav;

import org.junit.Assert;
import org.junit.Test;

public class MaybeTest {

    @Test
    public void testThatNullIsNotPresent() {
        Maybe<String> obj = Maybe.of(null);
        Assert.assertFalse(obj.isPresent());
    }

    @Test
    public void testThatAnythingIsPresent() {
        Maybe<String> obj = Maybe.of("foo");
        Assert.assertTrue(obj.isPresent());
    }

    @Test
    public void testThatAnythingCanHaveValue() {
        Maybe<String> obj = Maybe.of("foo");
        Assert.assertEquals("foo", obj.getOrThrow());
    }

    @Test
    public void thatNoneIsNotPresent() {
        Maybe<String> obj = Maybe.None();
        Assert.assertFalse(obj.isPresent());
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
    public void thatSomeWithNullIsPresent() {
        Maybe<String> obj = Maybe.Some(null);
        Assert.assertTrue(obj.isPresent());
    }

    @Test
    public void thatSomeCanHaveNull() {
        Maybe<String> obj = Maybe.Some(null);
        Assert.assertNull(obj.getOrThrow());
    }

    @Test
    public void thatSomeWithAnythingIsPresent() {
        Maybe<String> obj = Maybe.Some("foo");
        Assert.assertTrue(obj.isPresent());
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
    public void thatSomeCanBePrinted() {
        Maybe<String> obj = Maybe.Some("foo");
        Assert.assertEquals("Some{value=foo}", obj.toString());
    }
}
