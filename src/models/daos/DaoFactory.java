package models.daos;

public final class DaoFactory {

    private static IDAerienDao aerienDao;
    private static IDTerrestreDao terrestreDao;
    private static IPiloteDao piloteDao;

    private DaoFactory() {}

    public static IDAerienDao getAerienDao() {
        if (aerienDao == null) {
            aerienDao = new DAerienDaoImpl();
        }
        return aerienDao;
    }

    public static IDTerrestreDao getTerrestreDao() {
        if (terrestreDao == null) {
            terrestreDao = new DTerrestreDaoImpl();
        }
        return terrestreDao;
    }

    public static IPiloteDao getPiloteDao() {
        if (piloteDao == null) {
            piloteDao = new PiloteDaoImpl();
        }
        return piloteDao;
    }
}
