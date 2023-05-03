package structures;
public class KeyValuePair<K,V>{

    protected K key;
    protected V value;
    /**
     * setting the KeyValuePair
     * @param k - the key
     * @param v - the value
     */
    public KeyValuePair(K k, V v) {
        key = k;
        value = v;
    }
    /**
     * 
     * @return - the key of the instance
     */
    public K getKey() {
        return key;
    }
    /**
     * 
     * @return - the value of the instance
     */
    public V getValue() {
        return value;
    }
}
