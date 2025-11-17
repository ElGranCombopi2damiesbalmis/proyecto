public class UsuarioController {
    private UsuarioDao usuarioDao;

    public UsuarioController(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public void editarPerfil(String id, String nombre, String correo, String telefono, String calle) {
        Usuario usuario = usuarioDao.obtenerPorId(id);
        if (usuario != null) {
            if (nombre != null) usuario.setNombre(nombre);
            if (correo != null) usuario.setCorreo(correo);
            if (telefono != null) usuario.setTelefono(telefono);
            if (calle != null) usuario.setCalle(calle);
            usuarioDao.actualizar(usuario);
            System.out.println("Perfil actualizado correctamente");
        }
    }

    public void verAjustes(String id) {
        Usuario usuario = usuarioDao.obtenerPorId(id);
        if (usuario != null) {
            System.out.println("Mostrando ajustes de " + usuario.getNombre());
            System.out.println("Correo: " + usuario.getCorreo());
            System.out.println("Teléfono: " + usuario.getTelefono());
            System.out.println("Dirección: " + usuario.getCalle());
        }
    }
}