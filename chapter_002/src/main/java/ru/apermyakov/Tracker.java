package ru.apermyakov;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for create tracker.
 *
 * @author apermyakov
 * @version 1.1
 * @since 13.10.2017
 */
public class Tracker {

	/**
	 * Field for url of database.
	 */
	private String urlOfDb;

	/**
	 * Field for container of scripts.
	 */
	private Map<String, String> scripts = new HashMap<>();

	/**
	 * Method for set url of database.
	 *
	 * @param urlOfDb url of database
	 */
	public void setUrlOfDb(String urlOfDb) {
		this.urlOfDb = urlOfDb;
	}

	/**
	 * Method for get utl of database.
	 *
	 * @return url of database
	 */
	public String getUrlOfDb() {
		return urlOfDb;
	}

	/**
	 * Method for activate main action of class's methods.
	 *
	 * @param clause clause of chose
	 * @param item item to methods
	 * @param resultList resultList to methods
	 * @param field field to methods
	 * @param <T> generic param
	 */
	private <T> void mainAction(String clause, Item item, List<Item> resultList, T field) {
		Connection conn = null;
		try {
			conn = getConnectToDb();
			if (("dropandinitial").equals(clause)) {
				initialDb(conn, true);
			}
			if (("initial").equals(clause)) {
				initialDb(conn, false);
			}
			if (("add").equals(clause)) {
				addToDb(conn, item);
			}
			if (("update").equals(clause)) {
				updateDb(conn, item);
			}
			if (("delete").equals(clause)) {
				deleteFromDb(conn, item);
			}
			if (("find").equals(clause)) {
				findFromDb(conn, resultList);
			}
			if (("findby").equals(clause)) {
				findFromDb(conn, field, resultList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Method for get connection to database.
	 *
	 * @return connection
	 */
	private Connection getConnectToDb() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + this.getUrlOfDb());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * Method for parse xml file with sql scripts.
	 *
	 * @return map of sql scripts in xml file
	 */
	private Map<String, String> parseXMLFile() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = null;
		try {
			parser = factory.newSAXParser();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		TrackerSAXParser ownSAXParser = new TrackerSAXParser();
		try {
			parser.parse(this.getClass().getClassLoader().getResourceAsStream("Scripts.xml"), ownSAXParser);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ownSAXParser.getResult();
	}

	/**
	 * Method for initial database.
	 *
	 * @param conn connection
	 * @throws SQLException sql e
	 */
	private void initialDb(Connection conn, boolean erase) throws SQLException {
		Statement statement = conn.createStatement();
		if (erase) {
			statement.executeUpdate(this.scripts.get("dropscript"));
		}
		statement.executeUpdate(this.scripts.get("createscript"));
		statement.close();
	}

	/**
	 * Method for initial class.
	 */
	public void initial(boolean erase) {
		this.scripts.putAll(parseXMLFile());
		this.setUrlOfDb(this.scripts.get("connectscript"));
		if (!(new File(this.getUrlOfDb())).exists()) {
			try {
				new File(this.getUrlOfDb()).createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String clause;
		if (erase) {
			clause = "dropandinitial";
		} else {
			clause = "initial";
		}
		mainAction(clause, null, null, null);
	}

	/**
	 * Method for add item to database.
	 *
	 * @param conn connection
	 * @param item item
	 * @throws SQLException sql e
	 */
	private void addToDb(Connection conn, Item item) throws SQLException {
		PreparedStatement insert = conn.prepareStatement(this.scripts.get("insertscript"));
		insert.setString(1, item.getName());
		insert.setString(2, item.getDescription());
		insert.setLong(3, item.getCreated());
		insert.executeUpdate();
		insert.close();

		PreparedStatement select = conn.prepareStatement(this.scripts.get("selectbyallfieldscript"));
		select.setString(1, item.getName());
		select.setString(2, item.getDescription());
		select.setLong(3, item.getCreated());
		ResultSet result = select.executeQuery();
		item.setId(String.valueOf(result.getInt("id")));
		select.close();
	}

	/**
	 * Method add item for tracker.
	 *
	 * @param item new item
	 * @return new item
	 */
	public Item add(Item item) {
		mainAction("add", item, null, null);
		return item;
	}

	/**
	 * Method for update item in database.
	 *
	 * @param conn connection
	 * @param item item
	 * @throws SQLException sql e
	 */
	private void updateDb(Connection conn, Item item) throws SQLException {
		PreparedStatement statement = conn.prepareStatement(this.scripts.get("updatescript"));
		statement.setString(1, item.getName());
		statement.setString(2, item.getDescription());
		statement.setLong(3, item.getCreated());
		statement.setInt(4, Integer.valueOf(item.getId()));
		statement.executeUpdate();
		statement.close();
	}

	/**
	 * Method update item.
	 *
	 * @param item new item
	 */
	public void update(Item item) {
		mainAction("update", item, null, null);
	}

	/**
	 * Method for delete item out from database.
	 *
	 * @param conn connection
	 * @param item item
	 * @throws SQLException sql e
	 */
	private void deleteFromDb(Connection conn, Item item) throws SQLException {
		PreparedStatement statement = conn.prepareStatement(this.scripts.get("deletescript"));
		statement.setInt(1, Integer.valueOf(item.getId()));
		statement.executeUpdate();
		statement.close();
	}

	/**
	 * Method delete item.
	 *
	 * @param item chosen item
	 */
	public void delete(Item item) {
		mainAction("delete", item, null, null);
	}

	/**
	 * Method for add found cortege to list.
	 *
	 * @param found found cortege
	 * @param resultList list of result
	 * @throws SQLException sql e
	 */
	private void addFoundCortegeToList(ResultSet found, List<Item> resultList) throws SQLException {
		while (found.next()) {
			Item item = new Item(found.getString("itemname"), found.getString("itemdescription"), found.getLong("itemcreated"));
			item.setId(String.valueOf(found.getInt("id")));
			resultList.add(item);
		}
	}

	/**
	 * Method for find data from database.
	 *
	 * @param conn connection
	 * @param resultList list of result
	 * @throws SQLException sql e
	 */
	private void findFromDb(Connection conn, List<Item> resultList) throws SQLException {
		Statement statement = conn.createStatement();
		addFoundCortegeToList(statement.executeQuery(this.scripts.get("selectallscript")), resultList);
		statement.close();
	}

	/**
	 * Method find all item without null.
	 *
	 * @return all item
	 */
	public List<Item> findAll() {
		List<Item> resultList = new ArrayList<>();
		mainAction("find", null, resultList, null);
		return resultList;
	}

	/**
	 * Overload findFromDb.
	 *
	 * @param conn connection
	 * @param field field for find
	 * @param resultList result of search
	 * @throws SQLException sql e
	 */
	private <T> void findFromDb(Connection conn, T field, List<Item> resultList) throws SQLException {
		Integer cons = 0;
		PreparedStatement statement;
		if (cons.getClass().equals(field.getClass())) {
			statement = conn.prepareStatement(this.scripts.get("selectbyidscript"));
		} else {
			statement = conn.prepareStatement(this.scripts.get("selectbynamescript"));
		}
		statement.setObject(1, field);
		addFoundCortegeToList(statement.executeQuery(), resultList);
		statement.close();
	}

	/**
	 * Method for find by field.
	 *
	 * @param field field
	 * @param <T> type of field
	 * @return list of result
	 */
	private <T> List<Item> findByField(T field) {
		List<Item> resultList = new ArrayList<>();
		mainAction("findby", null, resultList, field);
		if (resultList.size() == 0) {
			throw new IllegalArgumentException("Wrong input data!");
		}
		return resultList;
	}

	/**
	 * Method find item by name.
	 *
	 * @param key item's name
	 * @return array of found names
	 */
	public List<Item> findByName(String key) {
		return findByField(key);
	}

	/**
	 * Method find item by Id.
	 *
	 * @param id item's id
	 * @return found item
	 */
	public Item findById(int id) {
		return findByField(id).get(0);
	}
}