Maybe
=====

`Maybe<T>` representerer en verdi som vi kanskje har. 
Det er situasjoner hvor `null` er en gyldig verdi og da vil `Optional<T>` 
være ubrukelig (noe som kan argumenteres for på et generelt basis også).

`None<T>` angir at man ikke har en verdi, mens `Some<T>` angir at man har _noe_ (kan fortsatt være `null`).

`Maybe.isNone()` gir `true` dersom typen er `None`, og `false` dersom typen er `Some`.

`Maybe.of(T)` gir et `None` dersom `T` er `null`, og `Some` ellers.

## Eksempler
`Maybe<T>` kan for eksempel brukes når man deserialiserer JSON og trenger å gjøre forskjell på
om et felt ikke er satt (`None`) eller har verdi satt (`Some`). 
Dersom feltet har `null` som gyldig verdi, og man vil føle seg "trygg", kan man for eksempel bruke `Maybe<Maybe<T>>` som type:

## Komme i gang

### Bygge

```
./gradlew assemble
```

### Teste

```
./gradlew test
```

### Bruke

```
Maybe<String> harIkkeVerdi = Maybe.None();
Assert.assertTrue(harIkkeVerdi.isNone());
harIkkeVerdi.getOrThrow(); // throws IllegalStateException
Assert.assertEquals("foo", harIkkeVerdi.getOrElse("foo"));
Assert.assertEquals("foo", harIkkeVerdi.getOrElse(() -> "foo"));

Maybe<String> harVerdi = Maybe.Some("foo");
Assert.assertFalse(harVerdi.isNone());
Assert.assertEquals("foo", harVerdi.getOrThrow());
Assert.assertEquals("foo", harVerdi.getOrElse("bar"));
Assert.assertEquals("foo", harVerdi.getOrElse(() -> "bar"));

Maybe<String> erNull = Maybe.Some(null);
Assert.assertFalse(erNull.isNone());
Assert.assertNull(erNull.getOrThrow());
Assert.assertNull(erNull.getOrElse("bar"));
Assert.assertNull(erNull.getOrElse(() -> "bar"));

Assert.assertTrue(Maybe.of(null).isNone());
Assert.assertFalse(Maybe.of("foo").isNone());

```
