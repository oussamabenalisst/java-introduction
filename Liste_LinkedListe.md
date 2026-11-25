# ArrayList et LinkedList en Java

## Introduction

`ArrayList` et `LinkedList` sont les deux implémentations principales de l'interface `List` en Java. Both implémentent l'interface `List` qui garantit l'ordre d'insertion et permet les doublons.

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
```

---

## 1. ArrayList

### Concept
`ArrayList` utilise un **tableau dynamique** en interne. Le tableau grandit automatiquement quand il est plein (redimensionnement).

- **Accès par index** : O(1) — très rapide
- **Insertion/suppression au milieu** : O(n) — lent (décalage)
- **Insertion en fin** : O(1) amorti
- **Mémoire** : Tableau contigu, peu d'overhead
- **Usage** : Quand accès aléatoire fréquent, peu d'insertions/suppressions milieu

### Déclaration et Initialisation
```java
// Différentes façons de créer
List<Integer> liste1 = new ArrayList<>();              // Capacité défaut (10)
List<Integer> liste2 = new ArrayList<>(50);            // Capacité initiale 50
List<String> liste3 = new ArrayList<>(List.of("a", "b", "c")); // Depuis collection
List<String> liste4 = List.of("a", "b", "c");          // Immuable (Java 9+)
```

### Méthodes Principales

#### Ajout d'Éléments
| Méthode | Description | Exemple |
|---------|-------------|---------|
| `add(E e)` | Ajoute à la fin | `liste.add(10)` |
| `add(int index, E e)` | Insère à l'index | `liste.add(0, 5)` |
| `addAll(Collection<? extends E> c)` | Ajoute tous | `liste.addAll(autreListe)` |
| `addAll(int index, Collection c)` | Insère tous à l'index | `liste.addAll(1, autre)` |

```java
ArrayList<Integer> liste = new ArrayList<>();
liste.add(10);           // [10]
liste.add(20);           // [10, 20]
liste.add(30);           // [10, 20, 30]
liste.add(0, 5);         // [5, 10, 20, 30] (insère au début)
liste.addAll(List.of(40, 50)); // [5, 10, 20, 30, 40, 50]
```

#### Accès et Modification
| Méthode | Description | Exemple |
|---------|-------------|---------|
| `get(int index)` | Retourne élément à l'index | `int x = liste.get(0)` |
| `set(int index, E e)` | Remplace élément | `liste.set(0, 100)` |
| `indexOf(Object o)` | Premier index de l'objet (-1 si absent) | `liste.indexOf(20)` |
| `lastIndexOf(Object o)` | Dernier index (-1 si absent) | `liste.lastIndexOf(20)` |
| `contains(Object o)` | Vrai si présent | `liste.contains(20)` |

```java
int premier = liste.get(0);       // 5
liste.set(0, 100);                // [100, 10, 20, 30, 40, 50]
int index = liste.indexOf(30);    // 3
boolean present = liste.contains(20); // true
```

#### Suppression
| Méthode | Description | Exemple |
|---------|-------------|---------|
| `remove(int index)` | Supprime par index, retourne élément | `int x = liste.remove(0)` |
| `remove(Object o)` | Supprime première occurrence, booléen | `liste.remove(Integer.valueOf(20))` |
| `removeAll(Collection c)` | Supprime tous de la collection | `liste.removeAll(List.of(30, 40))` |
| `retainAll(Collection c)` | Garde seulement ceux de la collection | `liste.retainAll(List.of(10, 20))` |
| `clear()` | Vide la liste | `liste.clear()` |

```java
liste.remove(0);                    // Supprime index 0 (100), retourne 100
liste.remove(Integer.valueOf(20));  // Supprime valeur 20 (booleen retourné)
liste.removeAll(List.of(30, 40));   // Supprime 30 et 40
liste.clear();                      // Liste vide
```

#### Taille et Capacité
| Méthode | Description | Exemple |
|---------|-------------|---------|
| `size()` | Nombre d'éléments | `int n = liste.size()` |
| `isEmpty()` | Vrai si vide | `liste.isEmpty()` |
| `ensureCapacity(int min)` | Assure capacité minimum | `liste.ensureCapacity(100)` |
| `trimToSize()` | Réduit capacité à la taille | `liste.trimToSize()` |

#### Recherche
| Méthode | Description | Exemple |
|---------|-------------|---------|
| `indexOf(Object o)` | Premier index (-1 si absent) | `liste.indexOf("x")` |
| `lastIndexOf(Object o)` | Dernier index (-1 si absent) | `liste.lastIndexOf("x")` |
| `contains(Object o)` | Vrai si présent | `liste.contains("x")` |

#### Conversion et Utilitaires
| Méthode | Description | Exemple |
|---------|-------------|---------|
| `toArray()` | Convertit en tableau `Object[]` | `Object[] arr = liste.toArray()` |
| `toArray(T[] a)` | Convertit en tableau typé | `Integer[] arr = liste.toArray(new Integer[0])` |
| `subList(int from, int to)` | Sous-liste (vue) | `List<Integer> sub = liste.subList(0, 3)` |
| `equals(Object o)` | Compare contenu | `liste1.equals(liste2)` |
| `toString()` | Représentation chaîne | `System.out.println(liste)` |

### Parcours (Iteration)
```java
ArrayList<String> liste = new ArrayList<>(List.of("a", "b", "c"));

// 1. Boucle for classique (avec index)
for (int i = 0; i < liste.size(); i++) {
    System.out.println(i + ": " + liste.get(i));
}

// 2. Boucle for-each (recommandé pour lecture)
for (String s : liste) {
    System.out.println(s);
}

// 3. Iterator explicite (pour suppression sûre)
Iterator<String> it = liste.iterator();
while (it.hasNext()) {
    String s = it.next();
    if (s.equals("b")) {
        it.remove();  // Suppression sûre
    }
}

// 4. Java 8+ Stream/forEach
liste.forEach(System.out::println);
liste.stream().filter(s -> s.startsWith("a")).forEach(System.out::println);
```

### Exemple Complet
```java
public class ExempleArrayList {
    public static void main(String[] args) {
        // Création
        ArrayList<Integer> notes = new ArrayList<>();
        
        // Ajout
        notes.add(15);
        notes.add(12);
        notes.add(18);
        notes.add(9);
        notes.add(0, 20);  // Insère au début
        
        // Affichage
        System.out.println("Notes: " + notes);  // [20, 15, 12, 18, 9]
        
        // Accès
        System.out.println("Première note: " + notes.get(0));  // 20
        System.out.println("Taille: " + notes.size());          // 5
        
        // Modification
        notes.set(0, 19);  // Remplace 20 par 19
        
        // Recherche
        System.out.println("Index de 18: " + notes.indexOf(18));  // 3
        System.out.println("Contient 12: " + notes.contains(12)); // true
        
        // Suppression
        notes.remove(Integer.valueOf(9));  // Supprime note 9
        notes.remove(0);                   // Supprime index 0 (19)
        
        // Parcours
        System.out.println("Notes restantes:");
        for (int note : notes) {
            System.out.println("- " + note);
        }
        
        // Calcul moyenne
        double somme = 0;
        for (int note : notes) {
            somme += note;
        }
        System.out.println("Moyenne: " + (somme / notes.size()));
    }
}
```

---

## 2. LinkedList

### Concept
`LinkedList` utilise une **liste doublement chaînée** en interne. Chaque élément (nœud) contient la donnée et des références vers le nœud précédent et suivant.

- **Accès par index** : O(n) — lent (traversée)
- **Insertion/suppression n'importe où** : O(1) si référence connue
- **Insertion/suppression début/fin** : O(1) — très rapide
- **Mémoire** : Overhead pour références (2 pointeurs par nœud)
- **Usage** : Quand insertions/suppressions fréquentes, pas besoin d'accès aléatoire

En plus de `List`, `LinkedList` implémente aussi `Deque` (double-ended queue) et `Queue`.

### Déclaration et Initialisation
```java
LinkedList<Integer> liste1 = new LinkedList<>();
LinkedList<Integer> liste2 = new LinkedList<>(List.of(1, 2, 3));
```

### Méthodes Principales

#### Méthodes List (identiques à ArrayList)
Toutes les méthodes de `List` fonctionnent :
```java
LinkedList<String> liste = new LinkedList<>();
liste.add("a");           // Ajoute fin
liste.add(0, "z");        // Insère début
liste.get(0);             // "z"
liste.set(0, "y");        // Remplace
liste.remove(0);          // Supprime index
liste.remove("a");        // Supprime valeur
liste.contains("y");      // true
liste.size();             // taille
liste.clear();            // Vide
```

#### Méthodes Spécifiques LinkedList (Deque/Queue)

| Méthode | Description | Exception si vide |
|---------|-------------|-------------------|
| `addFirst(E e)` | Ajoute au début | — |
| `addLast(E e)` | Ajoute à la fin | — |
| `offerFirst(E e)` | Ajoute début, retourne booléen | — |
| `offerLast(E e)` | Ajoute fin, retourne booléen | — |
| `removeFirst()` | Supprime/retourne premier | `NoSuchElementException` |
| `removeLast()` | Supprime/retourne dernier | `NoSuchElementException` |
| `pollFirst()` | Supprime/retourne premier ou null | null |
| `pollLast()` | Supprime/retourne dernier ou null | null |
| `getFirst()` | Retourne premier sans supprimer | `NoSuchElementException` |
| `getLast()` | Retourne dernier sans supprimer | `NoSuchElementException` |
| `peekFirst()` | Retourne premier ou null | null |
| `peekLast()` | Retourne dernier ou null | null |
| `push(E e)` | Empile (ajoute début) | — |
| `pop()` | Dépile (supprime début) | `NoSuchElementException` |

```java
LinkedList<String> liste = new LinkedList<>();

// Ajout début/fin
liste.addFirst("début");    // ["début"]
liste.addLast("fin");       // ["début", "fin"]
liste.add("milieu");        // ["début", "fin", "milieu"]

// Accès (avec exceptions)
String premier = liste.getFirst();  // "début"
String dernier = liste.getLast();   // "milieu"

// Accès (sans exception)
String p = liste.peekFirst();       // "début"
String d = liste.peekLast();        // "milieu"
String vide = liste.pollFirst();    // "début" (supprime et retourne)

// Suppression
String supprime = liste.removeFirst(); // Supprime "fin" (maintenant premier)
String supprime2 = liste.removeLast(); // Supprime "milieu"

// Stack (push/pop)
liste.push("A");   // Ajoute début
liste.push("B");   // Ajoute début
String top = liste.pop();  // "B" (retourne et supprime début)
```

### Exemple Complet
```java
import java.util.LinkedList;
import java.util.Iterator;

public class ExempleLinkedList {
    public static void main(String[] args) {
        // Création comme Queue (FIFO)
        LinkedList<String> file = new LinkedList<>();
        
        // Ajout en fin
        file.addLast("Client1");
        file.addLast("Client2");
        file.addLast("Client3");
        System.out.println("File: " + file);  // [Client1, Client2, Client3]
        
        // Traitement FIFO
        while (!file.isEmpty()) {
            System.out.println("Servi: " + file.removeFirst());
        }
        
        // Création comme Stack (LIFO)
        LinkedList<Integer> pile = new LinkedList<>();
        pile.push(1);  // Empile
        pile.push(2);
        pile.push(3);
        System.out.println("Pile: " + pile);  // [3, 2, 1]
        System.out.println("Dépile: " + pile.pop());  // 3
        
        // Utilisation comme List classique
        LinkedList<String> noms = new LinkedList<>();
        noms.add("Alice");
        noms.add("Bob");
        noms.add("Charlie");
        noms.add(1, "David");  // Insère index 1
        
        // Parcours
        System.out.println("Noms:");
        for (String nom : noms) {
            System.out.println("- " + nom);
        }
        
        // Iterator pour suppression sûre
        Iterator<String> it = noms.iterator();
        while (it.hasNext()) {
            if (it.next().length() < 5) {
                it.remove();  // Supprime noms courts
            }
        }
        System.out.println("Après filtre: " + noms);
    }
}
```

---

## 3. Comparaison ArrayList vs LinkedList

| Critère | ArrayList | LinkedList |
|---------|-----------|------------|
| **Structure interne** | Tableau dynamique | Liste doublement chaînée |
| **Accès par index** | O(1) ✅ | O(n) ❌ |
| **Insertion fin** | O(1) amorti | O(1) ✅ |
| **Insertion début/milieu** | O(n) ❌ | O(1) ✅ (si référence) |
| **Suppression début/milieu** | O(n) ❌ | O(1) ✅ (si référence) |
| **Recherche** | O(n) | O(n) |
| **Mémoire** | Moins (tableau contigu) | Plus (2 pointeurs/nœud) |
| **Cache-friendly** | Oui (contigu) | Non (dispersion mémoire) |
| **Implémente** | `List` | `List`, `Deque`, `Queue` |
| **Thread-safe** | Non (`Vector` = oui) | Non |
| **Quand utiliser** | Accès aléatoire fréquent | Insertions/suppressions fréquentes |

### Règle de Choix
```
Besoin d'accès par index fréquent? → ArrayList
Beaucoup d'insertions/suppressions au milieu/début? → LinkedList
File FIFO / Pile LIFO? → LinkedList (ou ArrayDeque)
Simplicité et usage général? → ArrayList (défaut)
```

---

## 4. Méthodes Communes (interface List)

Toutes ces méthodes fonctionnent pour **ArrayList ET LinkedList** :

| Catégorie | Méthodes |
|-----------|----------|
| **Ajout** | `add(E)`, `add(int, E)`, `addAll(Collection)`, `addAll(int, Collection)` |
| **Accès** | `get(int)`, `set(int, E)` |
| **Suppression** | `remove(int)`, `remove(Object)`, `removeAll(Collection)`, `retainAll(Collection)`, `clear()` |
| **Recherche** | `contains(Object)`, `indexOf(Object)`, `lastIndexOf(Object)` |
| **Taille** | `size()`, `isEmpty()` |
| **Conversion** | `toArray()`, `toArray(T[])`, `toString()` |
| **Sous-liste** | `subList(int, int)` |
| **Comparaison** | `equals(Object)` |
| **Iterator** | `iterator()`, `listIterator()`, `listIterator(int)` |
| **Tri (Java 8+)** | `sort(Comparator)` |

### Exemple Tri (Java 8+)
```java
List<Integer> liste = new ArrayList<>(List.of(3, 1, 4, 1, 5, 9, 2, 6));

// Tri croissant
liste.sort(null);  // Tri naturel
// [1, 1, 2, 3, 4, 5, 6, 9]

// Tri décroissant
liste.sort((a, b) -> b - a);
// [9, 6, 5, 4, 3, 2, 1, 1]

// Ou avec Comparator
liste.sort(Comparator.reverseOrder());
```

---

## 5. Bonnes Pratiques

### ArrayList
- **Pré-allouer capacité** si taille connue : `new ArrayList<>(1000)`
- **Éviter** insertions/suppressions milieu fréquentes
- **Préférer** pour la majorité des cas

### LinkedList
- **Utiliser comme Queue/Deque** : `addLast()` / `removeFirst()`
- **Éviter** accès par index dans boucles
- **Ne pas utiliser** comme List standard sans raison

### Général
- Déclarer avec **type interface** : `List<Integer> liste = new ArrayList<>();`
- Utiliser **for-each** pour lecture
- Utiliser **Iterator** pour suppression pendant parcours
- Attention aux **ConcurrentModificationException** :
  ```java
  // MAUVAIS - lance ConcurrentModificationException
  for (Integer i : liste) {
      if (i == 3) liste.remove(i);
  }
  
  // BON - Iterator
  Iterator<Integer> it = liste.iterator();
  while (it.hasNext()) {
      if (it.next() == 3) it.remove();
  }
  
  // BON - removeIf (Java 8+)
  liste.removeIf(i -> i == 3);
  ```

---

*Ce module couvre ArrayList (tableau dynamique) et LinkedList (liste chaînée) : déclaration, méthodes (add, get, set, remove, contains, indexOf, size, etc.), parcours, comparaison, et bonnes pratiques.*