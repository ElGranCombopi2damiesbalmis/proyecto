public class HomeDaoMock extends HomeDao {
    public HomeDaoMock() {
        super();
        // Inicializamos un Home de prueba para Juan
        Home home = new Home("Juan Pérez");
        home.setNotificacionesPendientes(5);
        this.guardar(home);
    }
}