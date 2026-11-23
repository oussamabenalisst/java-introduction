# Encapsulation en Java

## Concept
L'encapsulation consiste à **masquer les détails d'implémentation** d'une classe et à **exposer une interface contrôlée** pour interagir avec ses données.

## Modificateurs d'Accès

| Modificateur | Visibilité |
|--------------|------------|
| `private` | Accessible uniquement dans la même classe |
| `public` | Accessible de partout |
| `protected` | Accessible dans le même package + sous-classes (même package ou différent) |
| *(défaut/package-private)* | Accessible uniquement dans le même package |

## Getters & Setters (Accesseurs & Mutateurs)

### Principes
- **Getter** : permet de **lire** une valeur privée (`getNom()` / `isActif()` pour boolean)
- **Setter** : permet de **modifier** une valeur privée avec **validation** (`setNom()`)

### Exemple Complet
```java
public class CompteBancaire {
    // Attributs privés = encapsulation
    private String titulaire;
    private double solde;
    private boolean actif;
    
    // Constructeur
    public CompteBancaire(String titulaire, double soldeInitial) {
        this.titulaire = titulaire;
        this.solde = soldeInitial;
        this.actif = true;
    }
    
    // GETTERS (lecture)
    public String getTitulaire() {
        return titulaire;
    }
    
    public double getSolde() {
        return solde;
    }
    
    public boolean isActif() {  // 'is' pour boolean
        return actif;
    }
    
    // SETTERS (écriture avec validation)
    public void setTitulaire(String titulaire) {
        if (titulaire != null && !titulaire.isBlank()) {
            this.titulaire = titulaire;
        }
    }
    
    public void setSolde(double solde) {
        if (solde >= 0) {
            this.solde = solde;
        }
    }
    
    public void setActif(boolean actif) {
        this.actif = actif;
    }
    
    // Méthodes métier (utilisent les attributs privés)
    public void deposer(double montant) {
        if (actif && montant > 0) {
            solde += montant;
        }
    }
    
    public boolean retirer(double montant) {
        if (actif && montant > 0 && montant <= solde) {
            solde -= montant;
            return true;
        }
        return false;
    }
}
```

### Utilisation
```java
public class TestEncapsulation {
    public static void main(String[] args) {
        CompteBancaire compte = new CompteBancaire("Alice", 1000);
        
        // Lecture via getters
        System.out.println("Titulaire: " + compte.getTitulaire());
        System.out.println("Solde: " + compte.getSolde());
        System.out.println("Actif: " + compte.isActif());
        
        // Écriture via setters (avec validation)
        compte.setTitulaire("Bob");        // OK
        compte.setTitulaire("");           // Ignoré (validation)
        compte.setSolde(-500);             // Ignoré (validation)
        compte.setSolde(2000);             // OK
        
        // Méthodes métier
        compte.deposer(500);
        compte.retirer(200);
        
        System.out.println("Nouveau solde: " + compte.getSolde()); // 2300
    }
}
```

## Avantages de l'Encapsulation

1. **Contrôle des données** : Validation dans setters (ex: solde ≥ 0)
2. **Masquage implémentation** : Changement interne sans casser code client
3. **Sécurité** : Accès restreint aux données sensibles
4. **Maintenabilité** : Logique centralisée dans la classe
5. **Flexibilité** : Ajout de logique (logs, notifications) sans changer l'interface

## Bonnes Pratiques

| Pratique | Exemple |
|----------|---------|
| Attributs toujours `private` | `private double solde;` |
| Getters pour lecture | `public double getSolde()` |
| Setters avec validation | `if (valeur >= 0) this.valeur = valeur;` |
| `is` prefix pour boolean | `public boolean isActif()` |
| Méthodes métier au lieu de setters purs | `deposer()` / `retirer()` vs `setSolde()` |
| Copies défensives pour objets mutables | `return new Date(this.date.getTime());` |

## Exemple avec Objet Mutable (Copie Défensive)
```java
public class Evenement {
    private Date date;  // Date est mutable
    
    public Evenement(Date date) {
        this.date = new Date(date.getTime());  // Copie à l'entrée
    }
    
    public Date getDate() {
        return new Date(date.getTime());  // Copie à la sortie
    }
}
```

---

## Héritage

### Concept
L'héritage permet de créer une **nouvelle classe (fille)** à partir d'une **classe existante (parent)** pour réutiliser et étendre son comportement.

### Syntaxe
```java
public class Animal {
    protected String nom;
    protected int age;
    
    public Animal(String nom, int age) {
        this.nom = nom;
        this.age = age;
    }
    
    public void manger() {
        System.out.println(nom + " mange...");
    }
    
    public void dormir() {
        System.out.println(nom + " dort...");
    }
}

public class Chien extends Animal {
    private String race;
    
    public Chien(String nom, int age, String race) {
        super(nom, age);  // Appel constructeur parent
        this.race = race;
    }
    
    // Surcharge (overriding) - redéfinition méthode parent
    @Override
    public void manger() {
        System.out.println(nom + " mange des croquettes...");
    }
    
    // Nouvelle méthode spécifique à Chien
    public void aboyer() {
        System.out.println("Woof! Woof!");
    }
}
```

### Points Clés
- **`extends`** : mot-clé pour hériter
- **`super()`** : appelle constructeur parent (doit être 1ère instruction)
- **`@Override`** : annotation indiquant redéfinition intentionnelle
- **`protected`** : accessible dans classe fille (contrairement à `private`)
- Héritage **simple** uniquement (une seule classe parente)
- Tous classes héritent implicitement de `java.lang.Object`

### Accès Membres Parent
```java
public class Enfant extends Parent {
    void exemple() {
        this.methodeEnfant();    // Méthode enfant
        super.methodeParent();   // Méthode parent (même si surchargée)
        super.attributParent;    // Attribut parent (si protected/public)
    }
}
```

---

## Polymorphisme

### Concept
Le polymorphisme permet de **traiter des objets de types différents** via une **interface commune** (classe parent ou interface).

### Polymorphisme de Sous-typage (Liaison Dynamique)
```java
Animal a1 = new Chien("Rex", 3, "Labrador");
Animal a2 = new Chat("Moustache", 5, "Siamois");

a1.manger();  // "Rex mange des croquettes..." (méthode Chien)
a2.manger();  // "Moustache mange du poisson..." (méthode Chat)

// Tableau polymorphe
Animal[] animaux = {new Chien(...), new Chat(...), new Oiseau(...)};
for (Animal a : animaux) {
    a.manger();  // Chaque type son comportement propre
}
```

### `instanceof` et Cast
```java
void nourrir(Animal a) {
    a.manger();
    
    if (a instanceof Chien) {
        Chien c = (Chien) a;  // Cast sûr après vérification
        c.aboyer();
    }
    
    // Pattern matching (Java 16+)
    if (a instanceof Chat chat) {
        chat.ronronner();
    }
}
```

### Surcharge vs Surcharge (Overloading vs Overriding)

| Aspect | Surcharge (Overloading) | Surcharge/Redéfinition (Overriding) |
|--------|------------------------|-------------------------------------|
| **Liaison** | Statique (compilation) | Dynamique (exécution) |
| **Signature** | Même nom, paramètres différents | Même nom, mêmes paramètres |
| **Contexte** | Même classe | Classe parent → classe fille |
| **Annotation** | Aucune requise | `@Override` recommandée |

```java
// Surcharge (overloading) - même classe
public void manger() { ... }
public void manger(String nourriture) { ... }

// Surcharge/redéfinition (overriding) - classe fille
@Override
public void manger() { ... }  // Même signature que parent
```

---

## Interfaces

### Concept
Une interface définit un **contrat** (méthodes abstraites) que les classes implémentantes doivent respecter. Elle permet le **polymorphisme sans héritage d'implémentation**.

### Interface Basique
```java
public interface Dessinable {
    // Constantes implicites: public static final
    String DEFAUT_COULEUR = "noir";
    
    // Méthodes abstraites implicites: public abstract
    void dessiner();
    double getAire();
}
```

### Implémentation
```java
public class Cercle implements Dessinable {
    private double rayon;
    
    public Cercle(double rayon) {
        this.rayon = rayon;
    }
    
    @Override
    public void dessiner() {
        System.out.println("Dessin cercle rayon " + rayon);
    }
    
    @Override
    public double getAire() {
        return Math.PI * rayon * rayon;
    }
}

// Usage polymorphe
Dessinable d = new Cercle(5);
d.dessiner();           // "Dessin cercle rayon 5"
System.out.println(d.getAire());  // 78.54...
```

### Implémentation Multiple
```java
public class Cercle implements Dessinable, Comparable<Cercle>, Serializable {
    // ... implémentation des méthodes des 3 interfaces
}
```

### Méthodes `default` et `static` (Java 8+)
```java
public interface Dessinable {
    void dessiner();           // Abstraite - à implémenter
    
    default void afficherInfo() {  // Méthode par défaut (optionnelle à redéfinir)
        System.out.println("Aire: " + getAire());
    }
    
    static Dessinable creerCercle(double rayon) {  // Méthode utilitaire
        return new Cercle(rayon);
    }
}
```

### Interface vs Classe Abstraite

| Aspect | Interface | Classe Abstraite |
|--------|-----------|------------------|
| Héritage | Multiple (`implements`) | Simple (`extends`) |
| État (champs) | `static final` seulement | Tous modificateurs |
| Constructeurs | Non | Oui |
| Méthodes par défaut | Oui (Java 8+) | Oui |
| Utilisation | Contrat, capacité ("peut-faire") | Base commune ("est-un") |

---

*Ce module couvre : encapsulation, héritage (extends, super, @Override), polymorphisme (liaison dynamique, instanceof, overloading vs overriding), interfaces (implémentation, multiple, default/static).*