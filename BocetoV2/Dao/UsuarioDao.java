import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    protected List<Usuario> listaUsuarios;

    public UsuarioDao() {
        this.listaUsuarios = new ArrayList<>();
    }

    public void guardar(Usuario usuario) {
        listaUsuarios.add(usuario);
    }

    public Usuario obtenerPorId(String id) {
        return listaUsuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Usuario> obtenerTodos() {
        return new ArrayList<>(listaUsuarios);
    }

    public void actualizar(Usuario usuario) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getId().equals(usuario.getId())) {
                listaUsuarios.set(i, usuario);
                return;
            }
        }
    }

    public void eliminar(String id) {
        listaUsuarios.removeIf(u -> u.getId().equals(id));
    }
}