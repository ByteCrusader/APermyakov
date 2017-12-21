package ru.apermyakov.servlets.testtask;

import org.junit.Test;
import ru.apermyakov.servlets.TransferObject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DAOFactoryTest {

    @Test
    public void checkUserDAO() {
        DAOFactory factory = new DAOFactory();
        try (Connection connection = factory.getConnection()) {
            UserDAO userDAO = factory.getUserDAO(connection);
            TransferObject admin = userDAO.choseById(1);
            System.out.println(admin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkMusicDAO() {
        DAOFactory factory = new DAOFactory();
        try (Connection connection = factory.getConnection()) {
            GenericDAO musicDAO = factory.getGenericDAO(connection, "musictype");
            List<TransferObject> musics = musicDAO.choseAll();
            for (TransferObject music : musics) {
                System.out.println(music);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkRoleDAO() {
        DAOFactory factory = new DAOFactory();
        try (Connection connection = factory.getConnection()) {
            GenericDAO roleDAO = factory.getGenericDAO(connection, "role");
            List<TransferObject> roles = roleDAO.choseAll();
            for (TransferObject role : roles) {
                System.out.println(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkAddressDAO() {
        DAOFactory factory = new DAOFactory();
        try (Connection connection = factory.getConnection()) {
            GenericDAO addressDAO = factory.getGenericDAO(connection, "address");
            List<TransferObject> addresses = addressDAO.choseAll();
            for (TransferObject address : addresses) {
                System.out.println(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}