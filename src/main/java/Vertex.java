/**
 * Created by marco on 25/10/16.
 */
public class Vertex {
    final private Integer id;
    final private String name;

    public Vertex(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        else if (object == null) return false;
        else if (getClass() != object.getClass()) return false;

        Vertex vortexToCompare = (Vertex) object;
        if (id == null) {
            if (vortexToCompare.id != null) return false;
        }
        else if (!id.equals(vortexToCompare.getId())) return false;

        return true;
    }

    @Override
    public String toString() {
        return name;
    }
}
