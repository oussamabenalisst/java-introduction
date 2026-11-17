# Manipulation des Chaînes en Java

## 1. Fondamentaux des Chaînes

### Qu'est-ce qu'une String ?
- **Séquence immuable d'unités de code UTF-16** (valeurs `char`)
- Implémente `CharSequence`, `Comparable<String>`, `Serializable`
- Stockée dans le **String Pool** (littéraux internés) ou sur le tas (`new String()`)
- **Classe finale** — ne peut pas être sous-classée

### Immutabilité
```java
String s = "Hello";
s.toUpperCase();        // Retourne NOUVELLE chaîne "HELLO", originale inchangée
System.out.println(s);  // "Hello"

// Chaînage crée plusieurs objets intermédiaires
String resultat = s.trim().toLowerCase().replace('l', 'r');
```

> **Performance** : Concaténation répétée dans boucles → utilisez `StringBuilder` (mono-thread) ou `StringBuffer` (thread-safe).

---

## 2. Constructeurs & Initialisation

| Constructeur / Syntaxe | Description |
|------------------------|-------------|
| `String()` | Chaîne vide `""` |
| `String(String original)` | Constructeur de copie (rarement nécessaire) |
| `String(char[] value)` | Depuis tableau char complet |
| `String(char[] value, int offset, int count)` | Depuis sous-tableau |
| `String(byte[] bytes)` | Depuis octets (charset par défaut plateforme) |
| `String(byte[] bytes, Charset charset)` | Depuis octets avec charset explicite |
| `String(byte[] bytes, int offset, int length)` | Depuis sous-tableau d'octets |
| `String(byte[] bytes, int offset, int length, Charset charset)` | Sous-tableau + charset |
| `String(StringBuilder builder)` | Depuis StringBuilder (Java 5+) |
| `String(StringBuffer buffer)` | Depuis StringBuffer |
| **Littéral** `String s = "texte"` | Poolé, constante compile-time |

### Exemples
```java
char[] chars = {'J', 'a', 'v', 'a'};
String s1 = new String(chars);                    // "Java"
String s2 = new String(chars, 1, 2);              // "av"
String s3 = new String(s1);                       // copie (inutile)
String litteral = "Java";                         // depuis pool
String fromBytes = new String(new byte[]{74, 97, 118, 97}, StandardCharsets.UTF_8); // "Java"
```

---

## 3. Méthodes d'Instance Principales

### Longueur & Accès Caractères
| Méthode | Retour | Description |
|---------|--------|-------------|
| `length()` | `int` | Nombre d'unités de code `char` (pas points de code Unicode !) |
| `charAt(int index)` | `char` | Caractère à l'index (0-based), lance `IndexOutOfBoundsException` |
| `codePointAt(int index)` | `int` | Point de code Unicode à l'index |
| `codePointBefore(int index)` | `int` | Point de code avant index |
| `codePointCount(int begin, int end)` | `int` | Nombre de points de code dans intervalle |
| `offsetByCodePoints(int index, int cpOffset)` | `int` | Index décalé par points de code |

```java
String s = "Java\uD83D\uDE00"; // "Java😀" (paire de substitution)
s.length();                    // 6 (2 chars pour emoji)
s.codePointCount(0, s.length()); // 5 (vrais caractères Unicode)
s.charAt(4);                   // \uD83D (surrogate haut)
s.codePointAt(4);              // 0x1F600 (😀)
```

### Sous-chaînes & Extraction
| Méthode | Retour | Description |
|---------|--------|-------------|
| `substring(int begin)` | `String` | De `begin` à la fin |
| `substring(int begin, int end)` | `String` | De `begin` (inclusif) à `end` (exclusif) |
| `subSequence(int begin, int end)` | `CharSequence` | Même chose que substring, implémente interface |
| `toCharArray()` | `char[]` | Nouvelle copie du tableau char interne |
| `getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)` | `void` | Copie dans tableau existant |

```java
String s = "Programmation";
s.substring(3);        // "grammation"
s.substring(3, 7);     // "gram"
s.subSequence(0, 4);   // "Prog" (CharSequence)
```

### Recherche & Index
| Méthode | Retour | Description |
|---------|--------|-------------|
| `indexOf(int ch)` | `int` | Premier index du char, -1 si non trouvé |
| `indexOf(int ch, int fromIndex)` | `int` | Recherche depuis `fromIndex` |
| `indexOf(String str)` | `int` | Premier index de sous-chaîne |
| `indexOf(String str, int fromIndex)` | `int` | Recherche sous-chaîne depuis index |
| `lastIndexOf(int ch)` | `int` | Dernier index du char |
| `lastIndexOf(int ch, int fromIndex)` | `int` | Dernier index avant `fromIndex` |
| `lastIndexOf(String str)` | `int` | Dernier index de sous-chaîne |
| `lastIndexOf(String str, int fromIndex)` | `int` | Dernier index sous-chaîne avant index |
| `contains(CharSequence s)` | `boolean` | Vrai si sous-chaîne existe (Java 5+) |

```java
String s = "banane";
s.indexOf('a');           // 1
s.indexOf("na");          // 2
s.indexOf('z');           // -1
s.lastIndexOf('a');       // 5
s.contains("nan");        // true
```

### Comparaison & Égalité
| Méthode | Retour | Description |
|---------|--------|-------------|
| `equals(Object obj)` | `boolean` | Égalité contenu (sensible casse) |
| `equalsIgnoreCase(String other)` | `boolean` | Égalité contenu ignorant casse |
| `compareTo(String other)` | `int` | Lexicographique : <0, 0, >0 |
| `compareToIgnoreCase(String str)` | `int` | Comparaison insensible casse |
| `regionMatches(boolean ignoreCase, int toffset, String other, int ooffset, int len)` | `boolean` | Comparaison région |
| `startsWith(String prefix)` | `boolean` | Vérification préfixe |
| `startsWith(String prefix, int toffset)` | `boolean` | Préfixe à offset |
| `endsWith(String suffix)` | `boolean` | Vérification suffixe |
| `contentEquals(StringBuffer sb)` | `boolean` | Compare à StringBuffer |
| `contentEquals(CharSequence cs)` | `boolean` | Compare à CharSequence |

```java
String s1 = "Java";
String s2 = "java";
s1.equals(s2);                    // false
s1.equalsIgnoreCase(s2);          // true
s1.compareTo(s2);                 // -32 ('J' - 'j')
"pomme".compareTo("banane");      // négatif
"banane".compareTo("pomme");      // positif
"test".startsWith("te");          // true
"test".endsWith("st");            // true
```

> **Critique** : Utilisez `equals()` pour comparaison contenu. `==` compare références (vrai seulement pour même littéral pool).

### Conversion de Casse
| Méthode | Retour | Description |
|---------|--------|-------------|
| `toLowerCase()` | `String` | Locale par défaut |
| `toLowerCase(Locale locale)` | `String` | Locale spécifiée |
| `toUpperCase()` | `String` | Locale par défaut |
| `toUpperCase(Locale locale)` | `String` | Locale spécifiée |

```java
"İstanbul".toLowerCase(Locale.forLanguageTag("tr")); // "i̇stanbul" (turc i sans point)
"İstanbul".toLowerCase();                            // "istanbul" (locale par défaut)
```

### Nettoyage & Strip (Java 11+)
| Méthode | Retour | Description |
|---------|--------|-------------|
| `trim()` | `String` | Supprime espaces début/fin (≤ U+0020) |
| `strip()` | `String` | Supprime espaces Unicode début/fin |
| `stripLeading()` | `String` | Supprime espaces Unicode début |
| `stripTrailing()` | `String` | Supprime espaces Unicode fin |
| `isBlank()` | `boolean` | Vrai si vide ou seulement espaces (Java 11+) |

```java
"  bonjour  ".trim();        // "bonjour"
"  \u2000bonjour\u2000  ".strip(); // "bonjour" (gère espaces Unicode)
"   ".isBlank();           // true
"".isBlank();              // true
```

### Remplacement
| Méthode | Retour | Description |
|---------|--------|-------------|
| `replace(char old, char new)` | `String` | Remplace toutes occurrences char |
| `replace(CharSequence target, CharSequence replacement)` | `String` | Remplace toutes occurrences sous-chaîne (littéral, pas regex) |
| `replaceFirst(String regex, String replacement)` | `String` | Remplace premier match regex |
| `replaceAll(String regex, String replacement)` | `String` | Remplace tous matches regex |

```java
"foo bar foo".replace("foo", "baz");     // "baz bar baz" (littéral)
"foo bar foo".replaceAll("f.o", "baz");  // "baz bar baz" (regex)
"foo".replaceFirst("o", "a");            // "fao"
```

### Découpage & Jointure
| Méthode | Retour | Description |
|---------|--------|-------------|
| `split(String regex)` | `String[]` | Découpe par regex, chaînes vides finales ignorées |
| `split(String regex, int limit)` | `String[]` | Découpe avec contrôle limite |
| `join(CharSequence delim, CharSequence... elements)` | `String` | Statique: joint éléments avec délimiteur (Java 8+) |
| `join(CharSequence delim, Iterable<? extends CharSequence>)` | `String` | Statique: joint itérable (Java 8+) |

```java
// Comportement split limit
"a,b,c".split(",");           // ["a", "b", "c"]
"a,b,c,".split(",");          // ["a", "b", "c"] (vides finales ignorées)
"a,b,c,".split(",", -1);      // ["a", "b", "c", ""] (garde vides finales)
"a,b,c".split(",", 2);        // ["a", "b,c"] (max 2 parties)

// join
String.join("-", "a", "b", "c");        // "a-b-c"
String.join(", ", List.of("a", "b"));   // "a, b"
```

### Formatage
| Méthode | Retour | Description |
|---------|--------|-------------|
| `format(String format, Object... args)` | `String` | Statique: `String.format()` |
| `formatted(Object... args)` | `String` | Instance: `str.formatted(args)` (Java 15+) |
| `format(Locale l, String format, Object... args)` | `String` | Statique avec locale |

```java
String.format("Nom: %s, Âge: %d", "Alice", 30);  // "Nom: Alice, Âge: 30"
"Nom: %s, Âge: %d".formatted("Bob", 25);         // "Nom: Bob, Âge: 25" (Java 15+)
String.format(Locale.FRANCE, "%.2f", 1234.5);     // "1234,50"
```

### Correspondance Expression Régulière
| Méthode | Retour | Description |
|---------|--------|-------------|
| `matches(String regex)` | `boolean` | Chaîne entière match regex |
| `split(String regex)` | `String[]` | Découpe par regex (voir ci-dessus) |
| `replaceAll(String regex, String repl)` | `String` | Remplace tous matches regex |
| `replaceFirst(String regex, String repl)` | `String` | Remplace premier match regex |

```java
// Validation email
String email = "utilisateur@exemple.com";
String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
email.matches(regex);  // true

// Réutilisation Pattern (meilleure performance)
Pattern p = Pattern.compile(regex);
Matcher m = p.matcher(email);
m.matches();           // true
```

### Autres Méthodes Utiles
| Méthode | Retour | Description |
|---------|--------|-------------|
| `isEmpty()` | `boolean` | `length() == 0` (Java 6+) |
| `chars()` | `IntStream` | Stream de valeurs char (Java 9+) |
| `codePoints()` | `IntStream` | Stream de points de code (Java 9+) |
| `lines()` | `Stream<String>` | Stream de lignes (Java 11+) |
| `indent(int n)` | `String` | Ajuste indentation (Java 12+) |
| `transform(Function<String, R> f)` | `R` | Applique fonction (Java 12+) |
| `repeat(int count)` | `String` | Répète chaîne (Java 11+) |
| `describeConstable()` | `Optional<String>` | Pour pool constantes (Java 12+) |
| `resolveConstantDesc(MethodHandles.Lookup)` | `String` | Pour pool constantes (Java 12+) |

```java
"abc".repeat(3);              // "abcabcabc"
"bonjour".chars().forEach(System.out::print); // 104101108108111
"ligne1\nligne2".lines().toList(); // ["ligne1", "ligne2"]
"  texte".indent(2);           // "    texte"
"  texte".indent(-2);          // "texte"
```

---

## 4. Comparaison de Chaînes : `==` vs `equals()`

```java
String a = "bonjour";
String b = "bonjour";
String c = new String("bonjour");
String d = new String("bonjour");

a == b;        // true (même littéral pool)
a == c;        // false (objets différents)
c == d;        // false (objets différents)
a.equals(b);   // true
a.equals(c);   // true
c.equals(d);   // true

// Interning
String e = c.intern();  // Retourne référence pool
a == e;                 // true
```

> **Règle** : Utilisez toujours `equals()` pour comparaison contenu. Utilisez `==` seulement pour chaînes internées connues (ex: noms enum, constantes).

---

## 5. StringBuilder & StringBuffer (Mutables)

| Fonctionnalité | StringBuilder | StringBuffer |
|----------------|---------------|--------------|
| Thread-safe | Non | Oui (synchronisé) |
| Performance | Plus rapide | Plus lent |
| Depuis | Java 5 | Java 1 |

### Méthodes Courantes
```java
StringBuilder sb = new StringBuilder("Bonjour");
sb.append(" Monde");        // "Bonjour Monde"
sb.insert(7, ", ");         // "Bonjour, Monde"
sb.delete(7, 9);            // "BonjourMonde"
sb.replace(0, 7, "Salut");  // "SalutMonde"
sb.reverse();               // "ednoMtuolS"
sb.charAt(0);               // 'e'
sb.setCharAt(0, 'E');       // "EdnoMtuolS"
sb.length();                // 10
sb.capacity();              // capacité buffer actuelle
sb.ensureCapacity(100);     // assure capacité minimum
sb.trimToSize();            // rétrécit pour ajustement
String resultat = sb.toString(); // "EdnoMtuolS"
```

### Chaînage
```java
new StringBuilder()
    .append("Éléments: ")
    .append(42)
    .append(", ")
    .append("Fini")
    .toString(); // "Éléments: 42, Fini"
```

---

## 6. StringTokenizer (Héritage)

```java
import java.util.StringTokenizer;

StringTokenizer st = new StringTokenizer("a,b,c;d", ",;");
while (st.hasMoreTokens()) {
    System.out.println(st.nextToken());
}
// Sortie: a, b, c, d
```

> **Préférez** `String.split()` ou `Pattern.split()` — `StringTokenizer` ne supporte pas regex et est hérité.

---

## 7. Parsing Classes Wrapper (String ↔ Primitif)

| Primitif | Wrapper | Parse (String → Primitif) | ValueOf (String → Wrapper) | ToString (Primitif → String) |
|----------|---------|---------------------------|----------------------------|------------------------------|
| `int` | `Integer` | `Integer.parseInt(s)` | `Integer.valueOf(s)` | `Integer.toString(i)` |
| `long` | `Long` | `Long.parseLong(s)` | `Long.valueOf(s)` | `Long.toString(l)` |
| `float` | `Float` | `Float.parseFloat(s)` | `Float.valueOf(s)` | `Float.toString(f)` |
| `double` | `Double` | `Double.parseDouble(s)` | `Double.valueOf(s)` | `Double.toString(d)` |
| `byte` | `Byte` | `Byte.parseByte(s)` | `Byte.valueOf(s)` | `Byte.toString(b)` |
| `short` | `Short` | `Short.parseShort(s)` | `Short.valueOf(s)` | `Short.toString(s)` |
| `boolean` | `Boolean` | `Boolean.parseBoolean(s)` | `Boolean.valueOf(s)` | `Boolean.toString(b)` |

```java
// Parsing avec base
Integer.parseInt("FF", 16);     // 255
Integer.parseInt("1010", 2);    // 10

// Formatage primitifs vers chaîne
Integer.toString(255, 16);      // "ff"
Integer.toHexString(255);       // "ff"
Integer.toBinaryString(10);     // "1010"
Integer.toOctalString(255);     // "377"

// String.valueOf (universel)
String.valueOf(42);             // "42"
String.valueOf(3.14);           // "3.14"
String.valueOf(true);           // "true"
String.valueOf(new Object());   // "java.lang.Object@hash"
```

---

## 8. Arrays.toString & DeepToString

```java
import java.util.Arrays;

int[] arr = {1, 2, 3};
Arrays.toString(arr);           // "[1, 2, 3]"

Object[] objArr = {"a", "b", null};
Arrays.toString(objArr);        // "[a, b, null]"

int[][] matrice = {{1, 2}, {3, 4}};
Arrays.toString(matrice);        // "[[I@hash, [I@hash]" (superficiel !)
Arrays.deepToString(matrice);    // "[[1, 2], [3, 4]]"
```

---

## 9. Bonnes Pratiques Performance

### Évitez dans Boucles
```java
// MAUVAIS: Crée nouvelle String chaque itération
String s = "";
for (int i = 0; i < 1000; i++) {
    s += i;  // O(n²) allocations
}

// BON: StringBuilder
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append(i);
}
String resultat = sb.toString();

// BON: Pré-dimensionnez si longueur connue
StringBuilder sb2 = new StringBuilder(4000);
```

### Réutilisez Pattern/Matcher
```java
// MAUVAIS: Compile regex chaque appel
public boolean estValide(String s) {
    return s.matches(regex);
}

// BON: Compile une fois
private static final Pattern EMAIL_PATTERN = Pattern.compile(regex);
public boolean estValide(String s) {
    return EMAIL_PATTERN.matcher(s).matches();
}
```

### Utilisez `String.join` / `Collectors.joining`
```java
// Java 8+
List<String> liste = List.of("a", "b", "c");
String joint = String.join(", ", liste);  // "a, b, c"

// Streams
String joint2 = liste.stream().collect(Collectors.joining(", "));
```

---

## 10. Pièges Courants

| Piège | Approche Correcte |
|-------|-------------------|
| `s == "littéral"` | `s.equals("littéral")` ou `"littéral".equals(s)` (null-safe) |
| `s.trim()` modifie `s` | `s = s.trim()` (strings immuables) |
| `split(".")` sépare chaque char | `split("\\.")` (échappement regex) |
| `split("|")` sépare chaque char | `split("\\|")` |
| `replaceAll("$", "!")` | `replace("$", "!")` (littéral) ou `replaceAll("\\$", "!")` |
| Fuite mémoire `substring` (pré-Java 7) | Pas de problème Java 7+ (copie tableau) |
| Bugs locale `toLowerCase()` | Utilisez `toLowerCase(Locale.ROOT)` pour chaînes programmatiques |
| `new String("littéral")` | Inutile — utilisez littéral directement |
| `StringBuilder` en multi-thread | Utilisez `StringBuffer` ou synchronisez |

---

## 11. Référence Rapide : Méthodes Les Plus Utilisées

```java
// Création
String s = "texte";
String s2 = new String(bytes, StandardCharsets.UTF_8);

// Inspection
s.length()
s.isEmpty()
s.isBlank()           // Java 11+
s.charAt(i)
s.codePointAt(i)

// Recherche
s.indexOf("sous")
s.lastIndexOf("sous")
s.contains("sous")
s.startsWith("pre")
s.endsWith("suf")

// Extraction
s.substring(debut)
s.substring(debut, fin)
s.split(regex)
s.split(regex, limite)

// Transformation
s.toUpperCase()
s.toLowerCase()
s.trim()
s.strip()             // Java 11+
s.replace(vieux, nouv)
s.replaceAll(regex, repl)
s.replaceFirst(regex, repl)

// Comparaison
s.equals(autre)
s.equalsIgnoreCase(autre)
s.compareTo(autre)
s.compareToIgnoreCase(autre)

// Formatage
String.format(fmt, args)
s.formatted(args)     // Java 15+

// Conversion
Integer.parseInt(s)
Integer.toString(i)
String.valueOf(primitif)
Arrays.toString(tableau)

// Mutable
new StringBuilder().append(...).toString()
```

---

## 12. Matrice Fonctionnalités par Version Java

| Fonctionnalité | Version |
|----------------|---------|
| `StringBuilder` | Java 5 |
| `isEmpty()` | Java 6 |
| `String.join()` | Java 8 |
| `chars()`, `codePoints()` | Java 9 |
| `strip()`, `isBlank()`, `lines()`, `repeat()` | Java 11 |
| `indent()`, `transform()` | Java 12 |
| `formatted()` | Java 15 |
| Blocs de Texte (`"""..."""`) | Java 13 (preview), 15 (standard) |

```java
// Blocs de Texte (Java 15+)
String json = """
    {
        "nom": "Jean",
        "age": 30
    }
    """;
```

---

*Ce module couvre l'API complète `String`, la sémantique de comparaison, alternatives mutables, parsing/formatage, intégration regex, et directives de performance. Reportez-vous à `Numbers_Primitives.md` pour les types numériques et `README.md` pour l'index de la documentation.*