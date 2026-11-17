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

*Ce module couvre uniquement l'encapsulation : modificateurs d'accès (private, public, protected), getters, setters avec validation, et avantages.*