# Documentation des Fondamentaux Java

## Vue d'ensemble

Cette documentation fournit une référence complète pour les concepts Java de base abordés dans le cours. Elle est organisée en trois modules ciblés, chacun traitant un aspect fondamental de la programmation Java :

| Fichier | Sujet | Description |
|-------|-------|-------------|
| `Numbers_Primitives.md` | Types Primitifs Numériques & Classes Wrapper | Couvre `int`, `float`, `double`, `long`, leurs limites, transtypage, opérations mathématiques et utilitaires des classes wrapper (`Integer`, `Double`, `Float`, `Long`). |
| `Strings.md` | Manipulation des Chaînes | Couverture approfondie de la classe `String` : constructeurs, méthodes essentielles (`length`, `charAt`, `substring`, `indexOf`, `equals`, `split`, `matches`, `trim`, `format`), immutabilité, comparaison, validation regex, et utilitaires connexes (`StringBuffer`, `StringTokenizer`, parsing wrapper). |
| `README.md` | Ce Fichier | Point d'entrée et résumé du cours avec exemples introductifs. |

---

## Exemples de Code Introductifs

### Hello World & Structure de Base
```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
    }
}
```

### Variables & Types Primitifs
```java
public class VariablesDemo {
    public static void main(String[] args) {
        // Types entiers
        int age = 25;
        long population = 8_000_000_000L;  // suffixe 'L' requis pour les littéraux long
        
        // Types à virgule flottante
        float prix = 19.99f;  // suffixe 'f' requis pour les littéraux float
        double pi = 3.141592653589793;
        
        // Caractère & Booléen
        char note = 'A';
        boolean estEtudiant = true;
        
        System.out.println("Âge : " + age);
        System.out.println("Prix : " + prix);
        System.out.println("Note : " + note);
    }
}
```

### Bases des Chaînes
```java
public class StringDemo {
    public static void main(String[] args) {
        // Littéral vs constructeur
        String literal = "Java";
        String construit = new String("Java");
        
        // Opérations courantes
        String message = "Hello, World!";
        System.out.println("Longueur : " + message.length());        // 13
        System.out.println("Majuscules : " + message.toUpperCase()); // HELLO, WORLD!
        System.out.println("Sous-chaîne : " + message.substring(7));  // World!
        
        // Comparaison
        String s1 = "java";
        String s2 = "java";
        System.out.println(s1 == s2);       // true (pool de chaînes)
        System.out.println(s1.equals(s2));  // true (contenu)
    }
}
```

### Classes Wrapper & Parsing
```java
public class WrapperDemo {
    public static void main(String[] args) {
        // Parsing chaînes vers primitives
        int num = Integer.parseInt("42");
        double taux = Double.parseDouble("3.14");
        
        // Conversion primitives vers chaînes
        String str = Integer.toString(num);   // "42"
        String str2 = String.valueOf(taux);   // "3.14"
        
        // Autoboxing / Unboxing
        Integer boxed = num;      // autoboxing: int -> Integer
        int unboxed = boxed;      // unboxing: Integer -> int
    }
}
```

---

## Comment Utiliser Cette Documentation

1. **Commencez ici** (`README.md`) pour une vue d'ensemble et des extraits de référence rapide.
2. **Plongez dans `Numbers_Primitives.md`** lors du travail sur calculs numériques, conversions de types, ou utilitaires wrapper.
3. **Référez-vous à `Strings.md`** pour toutes les tâches de traitement de texte : parsing, formatage, validation, découpage, et manipulation.

Chaque fichier est autonome avec :
- Explications conceptuelles claires
- Tableaux de syntaxe pour consultation rapide
- Exemples de code annotés démontrant l'usage réel
- Notes de bonnes pratiques et pièges courants

---

## Sujets du Cours Couverts

- **Types de Données Primitifs** : `byte`, `short`, `int`, `long`, `float`, `double`, `char`, `boolean`
- **Classes Wrapper** : `Byte`, `Short`, `Integer`, `Long`, `Float`, `Double`, `Character`, `Boolean`
- **Transtypage** : Implicite (élargissement) vs Explicite (rétrécissement)
- **Classe String** : Immutabilité, constructeurs, méthodes API principales
- **Comparaison de Chaînes** : `==` vs `equals()` vs `compareTo()`
- **Manipulation de Chaînes** : `substring`, `split`, `trim`, `replace`, `format`
- **Expressions Régulières** : `matches()`, bases `Pattern`/`Matcher`
- **Alternatives Mutables** : `StringBuffer`, `StringBuilder`
- **Tokenisation** : `StringTokenizer`, `split()`
- **Parsing/Formatage** : Wrapper `parseXxx` / `valueOf`, `String.format`, `Arrays.toString`

---

## Prérequis

- Java 8+ (les exemples utilisent des fonctionnalités disponibles depuis Java 8)
- Compréhension basique des concepts POO (classes, objets, méthodes)
- Familiarité avec la compilation en ligne de commande (`javac`) et l'exécution (`java`)

---

## Référence Rapide Compilation & Exécution

```bash
# Compiler
javac Main.java

# Exécuter
java Main

# Compiler un package (si utilisation de packages)
javac -d . com/example/Main.java
java com.example.Main
```

---

*Généré à partir des notes de cours. Chaque module peut être lu indépendamment ou en séquence.*