# Types Primitifs Numériques & Classes Wrapper en Java

## 1. Types Primitifs Numériques

Java fournit **8 types primitifs**, dont 6 sont numériques. Les primitifs ne sont pas des objets — ils stockent les valeurs directement sur la pile pour la performance.

| Type | Taille | Plage | Défaut | Suffixe Littéral |
|------|--------|-------|--------|------------------|
| `byte` | 8-bit | -128 à 127 | 0 | — |
| `short` | 16-bit | -32 768 à 32 767 | 0 | — |
| **`int`** | **32-bit** | **-2³¹ à 2³¹-1** (~±2,14Mds) | **0** | **—** |
| **`long`** | **64-bit** | **-2⁶³ à 2⁶³-1** (~±9,22×10¹⁸) | **0L** | **`L` ou `l`** |
| **`float`** | **32-bit** | **±3,4×10⁻³⁸ à ±3,4×10³⁸** (~7 chiffres) | **0,0f** | **`F` ou `f`** |
| **`double`** | **64-bit** | **±1,7×10⁻³⁰⁸ à ±1,7×10³⁰⁸** (~15 chiffres) | **0,0** | **`D` ou `d` (optionnel)** |

> **Bonne Pratique** : Utilisez `int` par défaut pour les entiers et `double` pour les décimaux. Utilisez `long` quand les valeurs dépassent la plage de `int` ; utilisez `float` uniquement pour les tableaux contraints en mémoire ou l'interop GPU.

### Exemples de Littéraux
```java
// Entiers
int a = 42;
int hex = 0x2A;        // hexadécimal
int bin = 0b101010;    // binaire (Java 7+)
int oct = 052;         // octal
long grand = 3_000_000_000L;  // underscores pour lisibilité (Java 7+)

// Virgule flottante
float f = 3.14f;       // 'f' obligatoire
double d = 3.14;       // défaut = double
double sci = 1.5e2;    // notation scientifique = 150,0
float hexFloat = 0x1.91p1f; // float hexadécimal (Java 12+)
```

### Constantes (Entiers & Virgule Flottante)
```java
// Limites entiers
int maxInt = Integer.MAX_VALUE;   // 2 147 483 647
int minInt = Integer.MIN_VALUE;   // -2 147 483 648
long maxLong = Long.MAX_VALUE;    // 9 223 372 036 854 775 807

// Limites virgule flottante
double maxDouble = Double.MAX_VALUE;   // ~1,79e308
double minDouble = Double.MIN_VALUE;   // ~4,9e-324 (plus petit positif)
float maxFloat = Float.MAX_VALUE;      // ~3,4e38
float minFloat = Float.MIN_VALUE;      // ~1,4e-45

// Valeurs spéciales float/double
double posInf = Double.POSITIVE_INFINITY;
double negInf = Double.NEGATIVE_INFINITY;
double nan = Double.NaN;
```

---

## 2. Transtypage & Conversion

### Élargissement (Implicite) — Sûr, Automatique
```java
byte b = 10;
short s = b;      // byte → short
int i = s;        // short → int
long l = i;       // int → long
float f = l;      // long → float (peut perdre précision)
double d = f;     // float → double
```
**Ordre** : `byte → short → int → long → float → double`

### Rétrécissement (Explicite) — Nécessite Cast, Peut Perdre Données
```java
double d = 3.14;
int i = (int) d;      // tronque à 3

long l = 130L;
byte b = (byte) l;    // débordement à -126 (complément à deux)

float f = 1_000_000_000_000f;
int j = (int) f;      // perte de précision
```

> ⚠️ **Attention** : Le rétrécissement peut tronquer ou déborder silencieusement. Validez toujours les plages avant de caster.

### Boxing & Unboxing (Automatique depuis Java 5)
```java
// Boxing: primitif → wrapper
Integer boxed = 42;           // Integer.valueOf(42)
Double boxedD = 3.14;         // Double.valueOf(3.14)

// Unboxing: wrapper → primitif
int unboxed = boxed;          // boxed.intValue()
double unboxedD = boxedD;     // boxedD.doubleValue()

// Danger du null
Integer nullInt = null;
// int crash = nullInt;        // NullPointerException à l'exécution !
```

---

## 3. Classes Wrapper

Chaque primitif a une classe wrapper immuable dans `java.lang` :

| Primitif | Wrapper | Constantes Courantes |
|----------|---------|---------------------|
| `byte` | `Byte` | `MIN_VALUE`, `MAX_VALUE`, `SIZE`, `BYTES` |
| `short` | `Short` | `MIN_VALUE`, `MAX_VALUE`, `SIZE`, `BYTES` |
| `int` | **`Integer`** | `MIN_VALUE`, `MAX_VALUE`, `SIZE`, `BYTES` |
| `long` | **`Long`** | `MIN_VALUE`, `MAX_VALUE`, `SIZE`, `BYTES` |
| `float` | **`Float`** | `MIN_VALUE`, `MAX_VALUE`, `NaN`, `POSITIVE_INFINITY`, `NEGATIVE_INFINITY`, `SIZE`, `BYTES` |
| `double` | **`Double`** | `MIN_VALUE`, `MAX_VALUE`, `NaN`, `POSITIVE_INFINITY`, `NEGATIVE_INFINITY`, `SIZE`, `BYTES` |
| `char` | `Character` | `MIN_VALUE`, `MAX_VALUE`, `SIZE`, `BYTES` |
| `boolean` | `Boolean` | `TRUE`, `FALSE`, `SIZE`, `BYTES` |

### Parsing Chaînes → Primitives (Méthodes Statiques)
```java
// parseXxx: retourne primitif
int i = Integer.parseInt("42");           // lance NumberFormatException si invalide
int i2 = Integer.parseInt("2A", 16);      // base 16 → 42
long l = Long.parseLong("12345678901");
float f = Float.parseFloat("3.14f");
double d = Double.parseDouble("2.71828");

// valueOf: retourne objet wrapper (préféré pour réutilisation/cache)
Integer iObj = Integer.valueOf("42");     // cache -128 à 127
Double dObj = Double.valueOf("3.14");
```

> **Astuce** : Préférez `parseXxx` quand vous avez besoin d'un primitif (évite allocation objet). Utilisez `valueOf` quand vous avez besoin d'un wrapper (tire parti du cache).

### Primitifs/Objets → Chaînes
```java
// toString statique sur wrapper
String s1 = Integer.toString(42);         // "42"
String s2 = Double.toString(3.14);        // "3.14"
String s3 = Long.toString(123L, 16);      // "7b" (hex)

// String.valueOf (surchargé pour tous les primitifs)
String s4 = String.valueOf(42);           // "42"
String s5 = String.valueOf(true);         // "true"

// toString d'instance (sur objet boxé)
Integer obj = 42;
String s6 = obj.toString();               // "42"
```

### Méthodes Utilitaires (Exemples Integer/Long)
```java
// Manipulation de bits
int bits = Integer.bitCount(7);           // 3 (compteur de population)
int highest = Integer.highestOneBit(10);  // 8
int lowest = Integer.lowestOneBit(10);    // 2
int leadingZeros = Integer.numberOfLeadingZeros(10); // 28
int trailingZeros = Integer.numberOfTrailingZeros(10); // 1
int reversed = Integer.reverse(0b1101);   // 0b10110000... (bits inversés)
int rotated = Integer.rotateLeft(0b1001, 2); // 0b0110

// Comparaison
int cmp = Integer.compare(5, 10);         // -1 (5 < 10)
int cmpU = Integer.compareUnsigned(5, 10); // -1

// Min/Max (Java 8+)
int max = Math.max(5, 10);                // 10
int min = Math.min(5, 10);                // 5

// Somme avec détection débordement (Java 8+)
int sum = Math.addExact(1_000_000_000, 2_000_000_000); // lance ArithmeticException
```

---

## 4. Opérations Mathématiques

### Opérateurs Arithmétiques
```java
int a = 10, b = 3;
System.out.println(a + b);   // 13
System.out.println(a - b);   // 7
System.out.println(a * b);   // 30
System.out.println(a / b);   // 3 (division entière tronque)
System.out.println(a % b);   // 1 (reste)

// Assignment composée
a += 5;  // a = a + 5
a -= 2;  // a = a - 2
a *= 3;  // a = a * 3
a /= 2;  // a = a / 2
a %= 4;  // a = a % 4
```

### Piège Division Entière
```java
int resultat = 5 / 2;        // 2, pas 2,5 !
double correct = 5.0 / 2;    // 2,5
double aussiCorrect = 5 / 2.0; // 2,5
```

### Incrément/Décrément
```java
int x = 5;
int pre = ++x;   // x=6, pre=6 (préfixe: incrémente puis utilise)
int post = x++;  // post=6, x=7 (postfixe: utilise puis incrémente)
```

### Classe Math (java.lang.Math)
```java
// Base
Math.abs(-5);           // 5
Math.max(3, 7);         // 7
Math.min(3, 7);         // 3

// Puissances & Racines
Math.sqrt(16);          // 4,0
Math.pow(2, 10);        // 1024,0
Math.cbrt(27);          // 3,0

// Arrondi
Math.ceil(3.14);        // 4,0
Math.floor(3.14);       // 3,0
Math.round(3.14);       // 3 (long)
Math.round(3.14f);      // 3 (int)
Math.rint(3.5);         // 4,0 (arrondi au pair)

// Trigonométrie (radians)
Math.sin(Math.PI / 2);  // 1,0
Math.cos(0);            // 1,0
Math.toDegrees(Math.PI); // 180,0
Math.toRadians(180);    // 3,14159...

// Aléatoire
Math.random();          // [0,0, 1,0)
int de = (int)(Math.random() * 6) + 1; // 1-6

// Arithmétique exacte (lance si débordement)
Math.addExact(Integer.MAX_VALUE, 1); // ArithmeticException
Math.multiplyExact(1000, 1000000);   // ArithmeticException
```

### StrictMath
API identique à `Math` mais garantit des résultats bit-à-bit reproductibles cross-platform (plus lent).

---

## 5. Spécificités Virgule Flottante

### Problèmes de Précision
```java
// N'utilisez JAMAIS float/double pour calculs monétaires exacts !
System.out.println(0.1 + 0.2); // 0.30000000000000004

// Utilisez BigDecimal pour la finance
import java.math.BigDecimal;
BigDecimal prix = new BigDecimal("19.99");
BigDecimal taxe = prix.multiply(new BigDecimal("0.08"));
```

### Comparaison Floats/Doubles
```java
// FAUX - problèmes de précision
double a = 0.1 + 0.2;
double b = 0.3;
System.out.println(a == b); // false

// CORRECT - comparaison epsilon
double eps = 1e-9;
System.out.println(Math.abs(a - b) < eps); // true

// OU utilisez Double.compare (gère NaN/inf)
System.out.println(Double.compare(a, b) == 0); // true

// Vérifications NaN
Double.isNaN(0.0 / 0.0);        // true
Double.isInfinite(1.0 / 0.0);   // true
Double.isFinite(1.0);           // true
```

### Représentation Bits Float/Double
```java
// Voir les bits
int bits = Float.floatToIntBits(3.14f);    // bits IEEE 754 en int
long bitsD = Double.doubleToLongBits(3.14); // bits IEEE 754 en long

// Reconstruire
float f = Float.intBitsToFloat(bits);
double d = Double.longBitsToDouble(bitsD);
```

---

## 6. Formatage des Nombres

### String.format / printf
```java
// Décimal
System.out.format("%d%n", 42);              // "42"
System.out.format("%05d%n", 42);            // "00042"
System.out.format("%+d%n", 42);             // "+42"
System.out.format("%,d%n", 1_000_000);      // "1,000,000"

// Virgule flottante
System.out.format("%.2f%n", 3.14159);       // "3,14"
System.out.format("%10.2f%n", 3.14);        // "      3,14"
System.out.format("%e%n", 1000.0);          // "1,000000e+03"
System.out.format("%g%n", 1000.0);          // "1000"

// Hex/Octal/Binaire
System.out.format("%x%n", 255);             // "ff"
System.out.format("%o%n", 255);             // "377"
// Binaire non supporté directement dans format, utilisez Integer.toBinaryString()
```

### DecimalFormat (Héritage, mais flexible)
```java
import java.text.DecimalFormat;
DecimalFormat df = new DecimalFormat("#,##0.00");
System.out.println(df.format(12345.6));     // "12 345,60"

DecimalFormat sci = new DecimalFormat("0.###E0");
System.out.println(sci.format(12345));      // "1,235E4"
```

### NumberFormat (Sensible à la Locale)
```java
import java.text.NumberFormat;
import java.util.Locale;

NumberFormat us = NumberFormat.getNumberInstance(Locale.US);
NumberFormat fr = NumberFormat.getNumberInstance(Locale.FRANCE);
NumberFormat curr = NumberFormat.getCurrencyInstance(Locale.GERMANY);

System.out.println(us.format(123456.78));   // "123,456.78"
System.out.println(fr.format(123456.78));   // "123 456,78"
System.out.println(curr.format(123456.78)); // "123.456,78 €"
```

---

## 7. BigInteger & BigDecimal (Précision Arbitraire)

```java
import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.RoundingMode;

// BigInteger - entiers précision arbitraire
BigInteger grand = new BigInteger("123456789012345678901234567890");
BigInteger plusGrand = grand.multiply(BigInteger.valueOf(2));
BigInteger fact = BigInteger.ONE;
for (int i = 2; i <= 50; i++) {
    fact = fact.multiply(BigInteger.valueOf(i));
}
// 50! = 30414093201713378043612608166064768844377641568960512000000000000

// BigDecimal - décimaux précision arbitraire
BigDecimal bd = new BigDecimal("0.1");
BigDecimal somme = bd.add(new BigDecimal("0.2")); // 0,3 exactement

// Division avec échelle & arrondi
BigDecimal div = BigDecimal.ONE.divide(BigDecimal.valueOf(3), 10, RoundingMode.HALF_UP);
// 0,3333333333

// Comparaison
BigDecimal a = new BigDecimal("1.00");
BigDecimal b = new BigDecimal("1.0");
a.equals(b);              // false (échelle diffère !)
a.compareTo(b) == 0;      // true (valeur égale)
```

---

## 8. Pièges Courants & Bonnes Pratiques

| Piège | Solution |
|-------|----------|
| `float f = 3.14;` (erreur compilation) | Utilisez `float f = 3.14f;` |
| `long l = 2147483648;` (débordement) | Utilisez `long l = 2147483648L;` |
| `int / int` tronque | Castez un opérande : `(double)a / b` |
| `==` sur wrappers compare identité | Utilisez `.equals()` ou primitifs |
| `Double.NaN == Double.NaN` est false | Utilisez `Double.isNaN(x)` |
| Virgule flottante pour argent | Utilisez `BigDecimal` |
| Autoboxing NPE | Vérifiez null avant unboxing |
| Cache `Integer.valueOf` seulement -128..127 | Ne comptez pas sur identité hors plage |

---

## 9. Référence Rapide : Résumé Méthodes Wrapper

```java
// Parsing (String → primitif)
Integer.parseInt(s)          // int
Integer.parseInt(s, radix)   // int avec base
Long.parseLong(s)            // long
Float.parseFloat(s)          // float
Double.parseDouble(s)        // double

// ValueOf (String → wrapper)
Integer.valueOf(s)           // Integer
Integer.valueOf(i)           // Integer (boxe primitif, utilise cache)
Long.valueOf(s)              // Long
Float.valueOf(s)             // Float
Double.valueOf(s)            // Double

// toString (primitif → String)
Integer.toString(i)          // String
Integer.toString(i, radix)   // String en base
Long.toString(l)             // String
Float.toString(f)            // String
Double.toString(d)           // String

// Constantes
Integer.MAX_VALUE, MIN_VALUE, SIZE, BYTES
Long.MAX_VALUE, MIN_VALUE, SIZE, BYTES
Float.MAX_VALUE, MIN_VALUE, NaN, POSITIVE_INFINITY, NEGATIVE_INFINITY
Double.MAX_VALUE, MIN_VALUE, NaN, POSITIVE_INFINITY, NEGATIVE_INFINITY

// Utilitaires
Integer.bitCount(i)
Integer.compare(a, b)
Integer.compareUnsigned(a, b)
Integer.highestOneBit(i)
Integer.lowestOneBit(i)
Integer.numberOfLeadingZeros(i)
Integer.numberOfTrailingZeros(i)
Integer.reverse(i)
Integer.rotateLeft(i, dist)
Integer.rotateRight(i, dist)
Integer.toBinaryString(i)
Integer.toHexString(i)
Integer.toOctalString(i)
```

---

*Ce module couvre tous les primitifs numériques, leurs classes wrapper, conversions de types, opérations mathématiques, formatage, et arithmétique de précision arbitraire. Reportez-vous à `Strings.md` pour le traitement de texte et `README.md` pour l'index de la documentation.*