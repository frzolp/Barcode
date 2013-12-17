package us.zolp.barcode;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import us.zolp.barcode.bl2.*;

/**
 * 
 * @author ctz8636
 * 
 */
public class CodeStore {
	private final String SQLITE_CLASS = "org.sqlite.JDBC";
	private final String DB_PATH = "jdbc:sqlite:barcode.db";
	private final String QUERY_CREATE = "CREATE TABLE IF NOT EXISTS loothunt("
			+ "id INTEGER PRIMARY KEY AUTOINCREMENT, type TEXT, name TEXT, level INTEGER"
			+ "desc TEXT, rarity TEXT, weapon TEXT, class TEXT,"
			+ "element TEXT, seed TEXT, length INTEGER)";

	private final String QUERY_REMOVE = "DELETE FROM loothunt WHERE id=";
	private final String LIST_ALL = "SELECT * FROM loothunt;";
	private final int TIMEOUT = 30;
	private Connection dbConnection;
	private Statement dbStatement;

	/**
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public CodeStore() throws SQLException, ClassNotFoundException {
		Class.forName(SQLITE_CLASS);
		dbConnection = DriverManager.getConnection(DB_PATH);
		try {
			dbStatement = dbConnection.createStatement();
			try {
				dbStatement.setQueryTimeout(TIMEOUT);
				dbStatement.executeUpdate(QUERY_CREATE);
			} finally {
				// I don't do anything here.
			}
		} finally {
			// I don't do anything here, either.
		}
	}

	/**
	 * 
	 * @param item
	 */
	public void addItem(LootItem item) {
		String myQuery = "INSERT INTO loothunt(type, name, level, desc, rarity, weapon, class, element, seed, length) "
				+ "VALUES("
				+ item.getItemType().name()
				+ ","
				+ item.getItemName()
				+ ","
				+ item.getItemLevel()
				+ ","
				+ item.getDescription()
				+ ","
				+ item.getItemRarity().name()
				+ ","
				+ item.getWeaponType().name()
				+ ","
				+ item.getClassType().name()
				+ ","
				+ item.getElementType().name()
				+ ","
				+ item.getBarcodeText()
				+ "," + item.getBarcodeLen() + ");";
		try {
			dbStatement.executeUpdate(myQuery);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 
	 * @param id
	 */
	public void removeItem(int id) {
		String myQuery = QUERY_REMOVE + id + ";";
		try {
			dbStatement.executeUpdate(myQuery);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 */
	public List<LootItem> getItems() {
		List<LootItem> dbList = new ArrayList<LootItem>();
		try {
			ResultSet rs = dbStatement.executeQuery(LIST_ALL);
			try {
				while (rs.next()) {
					LootItem tmpItem = new LootItem(ItemType.valueOf(rs.getString("type")),
							rs.getString("name"),
							rs.getInt("level"),
							rs.getString("desc"),
							ItemRarity.valueOf(rs.getString("rarity")),
							WeaponType.valueOf(rs.getString("weapon")),
							ClassType.valueOf(rs.getString("class")),
							ElementType.valueOf(rs.getString("element")),
							rs.getString("seed"), 
							rs.getInt("length"));
					dbList.add(tmpItem);
				}
			} catch (SQLException ex) {

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return dbList;
	}

	/**
	 * 
	 */
	public void disconnect() {
		try {
			dbStatement.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			dbStatement = null;
			try {
				dbConnection.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				dbConnection = null;
			}
		}
	}
}
