# Guide Complet des Méthodes et Constructeurs du Type String en Java

La manipulation des chaînes de caractères constitue un pilier fondamental de la programmation orientée objet en Java. Représentées principalement par la classe `String`, les chaînes de caractères bénéficient d'un ensemble structuré de constructeurs, de méthodes d'instance et de classes utilitaires connexes destinées à faciliter leur création, leur analyse et leur transformation.

## 1. Constructeurs et Initialisation de la Classe String

La classe `String` offre plusieurs formes de construction permettant d'instancier des chaînes de caractères à partir de tableaux de caractères ou de chaînes existantes.

### Synthèse des Constructeurs

Le tableau suivant récapitule les principaux constructeurs de la classe `String` présentés dans le support de cours.

| Constructeur / Mode d'instanciation | Description |
| :--- | :--- |
| `String(char[] tabChars)` | Crée une nouvelle chaîne en copiant l'intégralité des éléments d'un tableau de caractères. |
| `String(char[] tabChars, int indiceDeb, int nbChars)` | Construit une chaîne à partir d'un sous-ensemble d'un tableau de caractères, en débutant à l'indice `indiceDeb` pour une longueur de `nbChars` caractères. |
| `String(String original)` | Constructeur de recopie créant une nouvelle instance de `String` identique à l'objet transmis. |
| Littéral de chaîne (`String s = "abc";`) | Syntaxe abrégée permettant d'initialiser directement une référence de chaîne. |

### Mécanique d'Instanciation

Lorsqu'une chaîne est créée à partir d'un tableau de caractères, le contenu du tableau est intégralement copié dans l'instance de `String`. Par conséquent, toute modification ultérieure apportée au tableau source n'altère en rien la chaîne déjà construite.

```java
char desChars[] = {'a', 'b', 'c', 'd', 'e', 'f'};
String s1 = new String(desChars);       // s1 contient "abcdef"
String s2 = new String(desChars, 2, 3); // s2 contient "cde" (début à l'index 2, 3 caractères)
```

---

## 2. Méthodes d'Instance Essentielles de la Classe String

La classe `String` fournit des méthodes d'instance permettant d'examiner et de manipuler le contenu des chaînes de caractères.

### Mesure de Longueur et Extraction

L'évaluation de la taille d'une chaîne ainsi que l'extraction de caractères individuels s'effectuent via des méthodes dédiées.

* **`int length()`** : Renvoie le nombre total de caractères contenus dans la chaîne.
  ```java
  String s = "abc";
  System.out.println(s.length()); // Affiche 3
  ```
* **`char charAt(int index)`** : Extrait le caractère situé à la position spécifiée par l'indice `index` (le premier caractère se trouvant à l'indice 0).
  ```java
  char c = "abc".charAt(1); // Renvoie 'b'
  ```

---

## 3. Comparaison et Égalité : Méthode `equals` vs Opérateur `==`

En Java, la comparaison de deux chaînes de caractères nécessite une distinction claire entre la comparaison de leurs références en mémoire et la comparaison de leurs contenus textuels.

### Différence entre `==` et `equals()`

L'opérateur `==` vérifie si deux références désignent le même emplacement mémoire. À l'inverse, la méthode `equals(Object obj)` redéfinie dans la classe `String` compare la séquence exacte des caractères contenus dans les objets.

```java
String s1 = new String("Java");
String s2 = new String("Java");

System.out.println(s1 == s2);      // false (deux objets distincts en mémoire)
System.out.println(s1.equals(s2)); // true (contenus textuels identiques)
```

Le seul cas où `s1 == s2` renvoie `true` pour des chaînes distinctes survient lors de l'utilisation de littéraux identiques sans instanciation explicite par `new` (`String s1 = "java"; String s2 = "java";`), où Java réutilise la référence issue du pool de chaînes.

---

## 4. Découpage, Nettoyage et Validation par Expressions Régulières

Pour le traitement textuel avancé, la classe `String` intègre des méthodes basées sur le nettoyage de caractères et la correspondance de motifs.

### Nettoyage des Espaces

* **`String trim()`** : Supprime l'ensemble des espaces blancs (espaces, tabulations) situés au début et à la fin de la chaîne de caractères.

### Découpage de Chaînes avec `split`

La méthode `split` divise une chaîne en sous-chaînes selon un séparateur défini et rassemble les résultats dans un tableau de chaînes (`String[]`).

| Méthode | Comportement |
| :--- | :--- |
| `String[] split(String sep)` | Découpe la chaîne en fonction du séparateur `sep`. Si aucun séparateur spécifique n'est défini, elle utilise par défaut les espaces blancs. |
| `String[] split(String sep, int limite)` | Applique le séparateur avec un contrôle du nombre maximal de découpages selon la valeur de `limite`. |

#### Contrôle par le Paramètre `limite`

1. **Si `limite > 0`** : Le motif séparateur est appliqué au maximum `limite - 1` fois.
   ```java
   "J'aime beaucoup Java".split(" ", 2);
   // Résultat : ["J'aime", "beaucoup Java"]
   ```
2. **Si `limite < 0`** : Le motif séparateur est appliqué autant de fois que possible sans restriction de taille sur le tableau résultant.

### Validation par Motif avec `matches`

* **`boolean matches(String regex)`** : Évalue si l'intégralité de la chaîne de caractères correspond à l'expression régulière spécifiée.

```java
public boolean isValidEmail(String email) {
    String regExp = "^[A-Za-z0-9._-]+@[A-Za-z0-9._-]+\\.[a-z]{2,}$";
    return email.matches(regExp);
}
```

---

## 5. Opérateurs, Classes Connexes et Méthodes Utilitaires

Outre les méthodes internes à la classe `String`, l'environnement Java propose plusieurs outils et classes permettant d'interagir avec les chaînes de caractères.

### Concaténation et Immuabilité (`String` vs `StringBuffer`)

L'opérateur `+` permet d'assembler des chaînes de caractères. Cependant, les objets de type `String` étant immuables, chaque concaténation génère une nouvelle instance en mémoire. Pour des modifications fréquentes, la classe `StringBuffer` offre une alternative mutable via sa méthode `append()`.

```java
// Approche avec String (Immuable)
String str = "Bonjour";
str += " tout le monde"; // Instancie un nouvel objet

// Approche avec StringBuffer (Mutable)
StringBuffer sb = new StringBuffer("Bonjour");
sb.append(" tout le monde"); // Modifie l'objet existant directement
```

Lors de l'utilisation de l'opérateur `+` avec des valeurs numériques, il convient d'utiliser des parenthèses pour éviter des concaténations inattendues du fait de la priorité des opérateurs :

```java
String s1 = "quatre : " + 2 + 2;   // Résultat : "quatre : 22"
String s2 = "quatre : " + (2 + 2); // Résultat : "quatre : 4"
```

### Analyse Syntaxique avec `StringTokenizer`

Appartenant au package `java.util`, la classe `StringTokenizer` permet de décomposer une chaîne en sous-chaînes (tokens) successifs.

* **`StringTokenizer(String s, String delim)`** : Constructeur acceptant la chaîne à découper ainsi que la liste des délimiteurs.
* **`boolean hasMoreTokens()`** : Indique s'il reste des mots à extraire dans la chaîne.
* **`String String nextToken()`** : Renvoie le mot suivant et avance dans le flux de découpage.

### Conversion entre Chaînes et Types Primitifs via les Classes Wrappers

Les classes d'enveloppe (Wrappers) fournissent des méthodes statiques permettant d'analyser une chaîne `String` pour la convertir en valeur numérique primitive ou en objet Wrapper.

| Type Ciblé | Méthode Précise (Primitif) | Méthode Précise (Objet Wrapper) |
| :--- | :--- | :--- |
| `int` / `Integer` | `Integer.parseInt(String s)` | `Integer.valueOf(String s)` |
| `double` / `Double` | `Double.parseDouble(String s)` | `Double.valueOf(String s)` |
| `float` / `Float` | `Float.parseFloat(String s)` | `Float.valueOf(String s)` |

### Formateur de Tableau avec `Arrays.toString()`

La classe utilitaire `java.util.Arrays` met à disposition une méthode statique pour obtenir une représentation lisible sous forme de chaîne à partir d'un tableau d'objets :

* **`static String toString(Object[] a)`** : Construit une chaîne de caractères listant l'ensemble des éléments du tableau séparés par des virgules et encadrés par des crochets.
