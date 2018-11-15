package util;

import java.util.Objects;

public class HashMap<clePrimaire, cleSecondaire, V> {

    private static final int CAPACITE_MAXIMUM = 1 << 20;

    private int taille;

    private transient Node[] table;

    /**
     * class privé permettant de pouvoir gérer plusieurs valeur.
     * @param <clePrimaire> la clé primaire (pour un code c'est 'a','b', etc)
     * @param <cleSecondaire> la clé secondaire (pour un code c'est 'morse','l33t', etc)
     * @param <V> la valeur en fonction des deux clé (ex : (a, l33t) -> @)
     */
    static class Node<clePrimaire, cleSecondaire, V>{
        final int hash;
        final clePrimaire key;
        final cleSecondaire key2;
        V value;
        Node<clePrimaire, cleSecondaire, V> next;

        Node(int hash, clePrimaire key, cleSecondaire key2, V value, Node<clePrimaire, cleSecondaire, V> next) {
            this.hash = hash;
            this.key = key;
            this.key2 = key2;
            this.value = value;
            this.next = next;
        }
        public clePrimaire getClePrimaire(){
            return this.key;
        }

        final V get(cleSecondaire cS) throws Exception {
            if(this.key2.equals(cS)){
                return this.value;
            } else if (this.next == null){
                throw new Exception("Non existant");
            } else {
                return this.next.get(cS);
            }
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("(")
                    .append(key)
                    .append(" , ")
                    .append(key2)
                    .append(" = ")
                    .append(value);
            return sb.toString();
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?, ?> node = (Node<?, ?, ?>) o;
            return hash == node.hash &&
                    Objects.equals(key, node.key) &&
                    Objects.equals(key2, node.key2) &&
                    Objects.equals(value, node.value) &&
                    Objects.equals(next, node.next);
        }

        boolean contient(V val){
            if(this.value.equals(val)){
                return true;
            } else if(this.next == null){
                return false;
            } else {
                return this.next.contient(val);
            }
        }

        boolean contient(cleSecondaire cS, V val){
            if (this.key2.equals(cS)) {
                return this.value.equals(val);
            } else if(this.next == null){
                return false;
            } else {
                return this.next.contient(cS, val);
            }
        }

        V set(cleSecondaire cle, V val){
            if (cle.equals(this.key2)){
                V v = this.value;
                this.value = val;
                return val;
            } else if(this.next == null){
                return null;
            } else {
                return this.next.set(cle, val);
            }
        }

        void add(clePrimaire cP, cleSecondaire cS, V val) throws Exception {
            if(this.key2.equals(cS)){
                throw new Exception("la cle secondaire est déja prise. pour changé de valeur veuillé faire set(...)");
            } else if(this.next == null){
                this.next = new Node<>(HashMap.hash(cP), cP, cS, val, null);
            } else {
                this.next.add(cP, cS, val);
            }
        }
    }

    public HashMap(){
        taille = 0;
        table = new Node[CAPACITE_MAXIMUM];
    }

    /**
     * hash fait maison et pensé pour les les caractère
     * @param key la cléPrimaire
     * @return la nouvelle valeur de hash (pour la place dans la hashMap
     */
    static int hash(Object key) {
        return key.hashCode() + (key.hashCode()<<11)^(key.hashCode()<<7);
    }

    /**
     * @return la taille
     */
    public int taille(){
        return taille;
    }

    /**
     * @return vrai si la liste est vide
     */
    public boolean estVide(){
        return taille == 0;
    }

    /**
     * @param v la valeur T cherché
     * @return vrai si la liste contient l'objet
     */
    @SuppressWarnings("unchecked")
    public boolean contient(V v){
        for(Node node : table){
            if (node != null && node.contient(v)){
               return true;
            }
        }
        return false;
    }

    /**
     * change la valeur d'un element
     * @param p le lieu de l'élément dans la liste
     * @param s le lieu de l'élément dans la liste
     * @param nouveauElement la nouvelle valeur
     * @throws NullPointerException si la clé n'existe pas
     */
    @SuppressWarnings("unchecked")
    public void set(clePrimaire p, cleSecondaire s, V nouveauElement) throws NullPointerException{
        try{
            if(this.table[HashMap.hash(p)].set(s, nouveauElement) == null){
                throw new NullPointerException("la clé primaire ou secondaire n'est pas dans la table");
            }
        }catch (NullPointerException e) {
            throw new NullPointerException("la clé primaire ou secondaire n'est pas dans la table");
        }
    }

    /**
     * ajoute une valeur à la liste
     * @param clePrimaire la cle primaire
     * @param cleSecondaire la cle secondaire
     * @param val la valeur à ajouter
     */
    @SuppressWarnings("unchecked")
    public void put(clePrimaire clePrimaire, cleSecondaire cleSecondaire, V val){
        try {
            int hash = HashMap.hash(clePrimaire);
            if (this.table[hash] == null){
                this.table[hash]= new Node<>(hash, clePrimaire, cleSecondaire, val, null);
                this.taille++;
            } else{
                this.table[hash].add(clePrimaire, cleSecondaire, val);
                this.taille++;
            }
        } catch (Exception e){
            System.out.println("ecxeption : " + e);
        }
    }

    /**
     * recupérer une valeur
     * @param cP cle primaire
     * @param cS cle secondaire
     * @return la valeur
     */
    @SuppressWarnings("unchecked")
    public V getVal(clePrimaire cP, cleSecondaire cS){
        try {
            return (V) this.table[HashMap.hash(cP)].get(cS);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * récupère la clé primaire
     * @param cS la cle secondaire
     * @param val la valeur
     * @return la clé primaire
     */
    @SuppressWarnings("unchecked")
    public clePrimaire getClePrimaire(cleSecondaire cS, V val){
        for (Node node: table){
            if(node!=null && node.contient(cS, val)){
                return (clePrimaire) node.getClePrimaire();
            }
        }
        return null;
    }
}
