/*
   DOCO:  
*/
import java.util.Objects;

/**
 * Container to ease passing around a tuple of two objects. This object provides a sensible
 * implementation of equals(), returning true if equals() is true on each of the contained
 * objects.
 */
public class Asteroid<F, S> {
    public F first;
    public S second;
    public int dx = 0;
    public int dy = 0;

    /**
     * Constructor for a Pair.
     *
     * @param first the first object in the Pair
     * @param second the second object in the pair
     */
    public Asteroid(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public String toString()
    {
        return "(" + this.first.toString() + "," + this.second.toString() + ")";
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Asteroid)) {
            return false;
        }
        Asteroid<?, ?> p = (Asteroid<?, ?>) o;
        return Objects.equals(p.first, first) && Objects.equals(p.second, second);
    }

    /**
     * Compute a hash code using the hash codes of the underlying objects
     *
     * @return a hashcode of the Pair
     */
    @Override
    public int hashCode() {
        return (first == null ? 0 : first.hashCode()) ^ (second == null ? 0 : second.hashCode());
    }

    /**
     * Convenience method for creating an appropriately typed pair.
     * @param a the first object in the Pair
     * @param b the second object in the pair
     * @return a Pair that is templatized with the types of a and b
     */
    public static <A, B> Asteroid<A, B> create(A a, B b) {
        return new Asteroid<A, B>(a, b);
    }
}
