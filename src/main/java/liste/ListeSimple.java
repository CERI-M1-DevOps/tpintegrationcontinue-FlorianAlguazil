package liste;

public class ListeSimple {
    private long size;
    Noeud tete;

    /**
     * Retourne la taille actuelle de la liste.
     * @return La taille de la liste.
     */
    public long getSize() {
        return size;
    }

    /**
     * Ajoute un nouvel élément au début de la liste.
     * @param element L'élément à ajouter à la liste.
     */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /**
     * Modifie le premier élément trouvé dans la liste correspondant à l'élément donné.
     * @param element L'élément à modifier.
     * @param nouvelleValeur La nouvelle valeur à attribuer à l'élément.
     */
    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && courant.getElement() != element)
            courant = courant.getSuivant();
        if (courant != null)
            courant.setElement(nouvelleValeur);
    }

    /**
     * Modifie tous les éléments de la liste qui correspondent à l'élément donné.
     * @param element L'élément à modifier.
     * @param nouvelleValeur La nouvelle valeur à attribuer à chaque élément correspondant.
     */
    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() == element)
                courant.setElement(nouvelleValeur);
            courant = courant.getSuivant();
        }
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de la liste.
     * @return Une chaîne représentant la liste, sous la forme "ListeSimple(element1, element2, ...)".
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Supprime le premier élément correspondant à l'élément donné.
     * @param element L'élément à supprimer de la liste.
     */
    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement() == element) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && courant.getElement() != element) {
                precedent = precedent.getSuivant();
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }

    /**
     * Supprime tous les éléments correspondant à l'élément donné.
     * @param element L'élément à supprimer de la liste.
     */
    public void supprimeTous(int element) {
        tete = supprimeTousRecurs(element, tete);
    }

    /**
     * Supprime récursivement tous les éléments correspondant à l'élément donné.
     * @param element L'élément à supprimer.
     * @param tete Le début de la sous-liste à partir de laquelle commencer la suppression.
     * @return La nouvelle tête de la sous-liste après suppression.
     */
    public Noeud supprimeTousRecurs(Object element, Noeud tete) {
        if (tete != null) {
            Noeud suiteListe = supprimeTousRecurs(element, tete.getSuivant());
            if (tete.getElement() == element) {
                size--;
                return suiteListe;
            } else {
                tete.setSuivant(suiteListe);
                return tete;
            }
        } else return null;
    }

    /**
     * Retourne l'élément juste avant le dernier de la liste.
     * @return L'élément avant le dernier, ou null si la liste est vide ou a un seul élément.
     */
    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null)
            return null;
        else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
        }
    }

    /**
     * Inverse l'ordre des éléments dans la liste.
     */
    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    /**
     * Retourne l'élément précédent celui donné dans la liste.
     * @param r Le noeud dont on cherche l'élément précédent.
     * @return Le noeud précédent à celui donné, ou null si l'élément est au début de la liste.
     */
    public Noeud getPrecedent(Noeud r) {
        // la liste n'est pas vide puisqu'on transmet un Node de la liste et le Node existe obligatoirement
        Noeud precedent = tete;
        Noeud courant = precedent.getSuivant();
        while (courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return precedent;
    }

    /**
     * Échange les positions de deux éléments dans la liste.
     * @param r1 Le premier noeud à échanger.
     * @param r2 Le deuxième noeud à échanger.
     */
    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == r2)
            return;
        Noeud precedentR1;
        Noeud precedentR2;
        if (r1 != tete && r2 != tete) {
            precedentR1 = getPrecedent(r1);
            precedentR2 = getPrecedent(r2);
            precedentR1.setSuivant(r2);
            precedentR2.setSuivant(r1);
        } else if (r1 == tete) {
            precedentR2 = getPrecedent(r2);
            precedentR2.setSuivant(tete);
            tete = r2;
        }
        else {
            precedentR1 = getPrecedent(r1);
            precedentR1.setSuivant(tete);
            tete = r1;
        }
        Noeud temp = r2.getSuivant();
        r2.setSuivant(r1.getSuivant());
        r1.setSuivant(temp);
    }
}
