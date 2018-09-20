package no.nav;

import org.junit.Assert;
import org.junit.Test;

public class MaybeTest {

    @Test
    public void thatNoneIsNotPresent() {
        Maybe<String> obj = Maybe.None();
        Assert.assertFalse(obj.isPresent());
    }

    @Test(expected = IllegalStateException.class)
    public void thatNoneCannotHaveValue() {
        Maybe<String> obj = Maybe.None();
        obj.value();
    }

    @Test
    public void thatSomeWithNullIsPresent() {
        Maybe<String> obj = Maybe.Some(null);
        Assert.assertTrue(obj.isPresent());
    }

    @Test
    public void thatSomeCanHaveNull() {
        Maybe<String> obj = Maybe.Some(null);
        Assert.assertNull(obj.value());
    }

    @Test
    public void thatSomeWithAnythingIsPresent() {
        Maybe<String> obj = Maybe.Some("foo");
        Assert.assertTrue(obj.isPresent());
    }

    @Test
    public void thatSomeCanHaveAnything() {
        Maybe<String> obj = Maybe.Some("foo");
        Assert.assertEquals("foo", obj.value());
    }

}
