package ru.apermyakov.JDBC;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

/**
 * Class for initial database and two xml files for data base bean.
 *
 * @author apermyakov
 * @version 1.0
 * @since 28.11.2017
 */
public class Initiator {

    /**
     * Field for connection.
     */
    private Connection connection;

    /**
     * Field for number of records.
     */
    private int numberOfRecords;

    /**
     * Design initiator.
     *
     * @param connection connection to sqlite
     * @param numberOfRecords number of records
     */
    public Initiator(Connection connection, int numberOfRecords) {
        this.connection = connection;
        this.numberOfRecords = numberOfRecords;
    }

    /**
     * Method for initial sqlite table.
     *
     * @throws SQLException sql e
     */
    public void initialTable() throws SQLException {
        Statement statement = this.connection.createStatement();
        statement.execute("PRAGMA synchronous = 0");
        statement.execute("PRAGMA journal_mode = OFF");
        statement.executeUpdate("DROP TABLE TEST");
        statement.executeUpdate("CREATE TABLE TEST (FIELD INTEGER)");
        for (int index = 1; index <= numberOfRecords; index++) {
            PreparedStatement st = this.connection.prepareStatement("INSERT INTO TEST(FIELD) VALUES(?)");
            st.setInt(1, index);
            st.executeUpdate();
            st.close();
        }
        statement.execute("PRAGMA synchronous = FULL");
        statement.execute("PRAGMA journal_mode = DELETE");
        statement.close();
    }

    /**
     * Method for create entry to build first xml file.
     *
     * @param field field
     * @return entry
     */
    private FieldEntry createEntry(int field) {
        FieldEntry fieldEntry = new FieldEntry();
        fieldEntry.setField(field);
        return fieldEntry;
    }

    /**
     * Method for get data from database.
     *
     * @param entries container
     * @throws SQLException sql e
     */
    private void getDataFromDb(Entries entries) throws SQLException {
        Statement statement = this.connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM TEST");
        while (result.next()) {
            entries.getEntry().add(createEntry(result.getInt("FIELD")));
        }
        statement.close();
    }

    /**
     * Method for initial first xml file.
     *
     * @throws ParserConfigurationException parser e
     * @throws SQLException sql e
     * @throws JAXBException jax e
     */
    public void initialFirstXML() throws ParserConfigurationException, SQLException, JAXBException {
        Entries entries = new Entries();
        File file = new File("1.xml");

        getDataFromDb(entries);

        JAXBContext context = JAXBContext.newInstance(Entries.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(entries, file);
    }

    /**
     * Method for initial second xml file.
     *
     * @throws IOException io e
     * @throws TransformerException transformer e
     */
    public void initialSecondXML() throws IOException, TransformerException {
        TransformerFactory tFactory = TransformerFactory.newInstance();
        File file = new File("2.xml");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream outputStream = new FileOutputStream(file);
        Transformer transformer = tFactory.newTransformer(new StreamSource(this.getClass().getClassLoader().getResourceAsStream("Source.xsl")));
        transformer.transform(new StreamSource("1.xml"), new StreamResult(outputStream));
    }
}
