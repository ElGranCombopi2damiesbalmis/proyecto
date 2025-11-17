public class Main {
    public static void main(String[] args) {
        // 1. Instanciar DAOs (Mock)
        UsuarioDao usuarioDao = new UsuarioDaoMock();
        TareaDao tareaDao = new TareaDaoMock();
        TransaccionDao transaccionDao = new TransaccionDaoMock();
        EconomiaDao economiaDao = new EconomiaDaoMock();
        EstadoAnimoDao estadoAnimoDao = new EstadoAnimoDaoMock();
        HomeDao homeDao = new HomeDaoMock();

        // 2. Instanciar Controllers inyectando los DAOs
        UsuarioController usuarioCtrl = new UsuarioController(usuarioDao);
        TareaController tareaCtrl = new TareaController(tareaDao);
        // Nótese que EconomiaController usa dos DAOs
        EconomiaController economiaCtrl = new EconomiaController(economiaDao, transaccionDao);
        EstadoAnimoController animoCtrl = new EstadoAnimoController(estadoAnimoDao);
        HomeController homeCtrl = new HomeController(homeDao);

        // 3. Ejecutar lógica usando los Controllers
        // (Obtenemos los objetos base de los Mocks para probar)
        Economia economia = economiaDao.obtenerPrincipal();
        EstadoAnimo animo = estadoAnimoDao.obtenerPrincipal();
        
        System.out.println("--- Test Economía ---");
        economiaCtrl.verBalance(economia);
        
        System.out.println("\n--- Test Ánimo ---");
        animoCtrl.mostrarCalendario(animo);
        
        System.out.println("\n--- Test Transacciones ---");
        TransaccionController transaccionCtrl = new TransaccionController(transaccionDao);
        transaccionCtrl.listarTodas();
    }
}