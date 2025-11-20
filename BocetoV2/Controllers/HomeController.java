public class HomeController {
    private HomeDao homeDao;

    public HomeController(HomeDao homeDao) {
        this.homeDao = homeDao;
    }

    public void actualizarNotificaciones(Home home, int cantidad) {
        home.setNotificacionesPendientes(cantidad);
        // Aquí se llamaría a homeDao.actualizar(home) si existiera DB
        System.out.println("Notificaciones actualizadas.");
    }

    public void cargarDashboard(Home home) {
        System.out.println("Cargando configuración de Home...");
        System.out.println(home.getFraseBienvenida());
        System.out.println("Alertas: " + home.getNotificacionesPendientes());
    }
}