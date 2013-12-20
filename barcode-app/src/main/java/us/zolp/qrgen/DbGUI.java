package us.zolp.qrgen;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import us.zolp.bl2.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.ComboBoxModel;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class DbGUI extends JFrame {
	private static final long serialVersionUID = 6866405205279456457L;
	private JTable table;
	private JComboBox<ItemType> itemCmb;
	private ComboBoxModel<ItemType> itemModel;
	private JComboBox<ItemRarity> rarityCmb;
	private ComboBoxModel<ItemRarity> rarityModel;
	private JComboBox<WeaponType> weaponCmb;
	private ComboBoxModel<WeaponType> weaponModel;
	private JComboBox<ClassType> classCmb;
	private ComboBoxModel<ClassType> classModel;
	private JComboBox<ElementType> elementCmb;
	private ComboBoxModel<ElementType> elementModel;
	private JTextField textField;
	private JTextField textField_1;
	
	public DbGUI() {
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Search", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new GridLayout(0, 6, 0, 0));
		
		JLabel lblName = new JLabel("Name");
		panel_1.add(lblName);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(1);
		
		JLabel lblLevel = new JLabel("Level");
		panel_1.add(lblLevel);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 71, 1));
		panel_1.add(spinner);
		
		JLabel lblType = new JLabel("Type");
		panel_1.add(lblType);
		itemCmb = new JComboBox<ItemType>();
		itemCmb.setModel(itemModel);
		panel_1.add(itemCmb);
		tabbedPane.addTab("Add", null, panel_1, null);
		
		JLabel lblRarity = new JLabel("Rarity");
		panel_1.add(lblRarity);
		rarityCmb = new JComboBox<ItemRarity>();
		rarityCmb.setModel(rarityModel);
		panel_1.add(rarityCmb);
		
		JLabel lblWeapon = new JLabel("Weapon");
		panel_1.add(lblWeapon);
		
		weaponCmb = new JComboBox<WeaponType>();
		weaponCmb.setModel(weaponModel);
		panel_1.add(weaponCmb);
		
		JLabel lblClass = new JLabel("Class");
		panel_1.add(lblClass);
		
		classCmb = new JComboBox<ClassType>();
		classCmb.setModel(classModel);
		panel_1.add(classCmb);
		
		JLabel lblElement = new JLabel("Element");
		panel_1.add(lblElement);
		
		elementCmb = new JComboBox<ElementType>();
		elementCmb.setModel(elementModel);
		panel_1.add(elementCmb);
		
		JLabel lblCode = new JLabel("Code");
		panel_1.add(lblCode);
		
		textField_1 = new JTextField();
		panel_1.add(textField_1);
		textField_1.setColumns(2);
		
		JButton btnAdd = new JButton("Add");
		panel_1.add(btnAdd);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Delete", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Items", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel_3, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Type", "Level", "Rarity", "Weapon Class", "Character", "Element", "Code"
			}
		) {
			private static final long serialVersionUID = -941017661678166933L;
			Class[] columnTypes = new Class[] {
				String.class, ItemType.class, Integer.class, ItemRarity.class, WeaponType.class, ClassType.class, ElementType.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		panel_3.add(table);
	}

}
