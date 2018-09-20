Maybe
=====

`Maybe<T>` representerer en verdi som vi kanskje har. 
Det er situasjoner hvor `null` er en gyldig verdi og da vil `Optional<T>` 
være ubrukelig (noe som kan argumenteres for på et generelt basis også).

`None<T>` angir at man ikke har en verdi, mens `Some<T>` angir at man har _noe_ (kan fortsatt være `null`).

`Maybe.isPresent()` gir `false` dersom typen er `None`, og `true` dersom typen er `Some`.

## Eksempler
`Maybe<T>` kan for eksempel brukes når man deserialiserer JSON og trenger å gjøre forskjell på
om et felt ikke er satt (`None`) eller har verdi satt (`Some`). 
Dersom feltet har `null` som gyldig verdi, og man vil føle seg "trygg", kan man for eksempel bruke `Maybe<Optional<T>>` som type.

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
Assert.assertFalse(harIkkeVerdi.isPresent());
harIkkeVerdi.value(); // throws IllegalStateException

Maybe<String> harVerdi = Maybe.Some("foo");
Assert.assertTrue(harVerdi.isPresent());
Assert.assertEquals("foo", harVerdi.value());

Maybe<String> erNull = Maybe.Some(null);
Assert.assertTrue(erNull.isPresent());
Assert.assertNull(erNull.value());

```
