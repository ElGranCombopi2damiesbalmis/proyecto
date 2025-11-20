public class UsuarioDaoMock extends UsuarioDao {
    
    public UsuarioDaoMock() {
        super(); // Llama al constructor de UsuarioDao para inicializar la lista
        
        // Datos inicializados de prueba
        Usuario usuario = new Usuario("Juan Pérez", "juan@email.com", "123456789", "Calle Principal 123");
        this.guardar(usuario);
    }
}