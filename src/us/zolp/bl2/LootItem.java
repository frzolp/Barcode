package us.zolp.bl2;

public class LootItem {
	private ItemType itemType;
	private String itemName;
	private int itemLevel;
	private String description;
	private ItemRarity itemRarity;
	private WeaponType weaponType;
	private ClassType classType;
	private ElementType elementType;
	private String barcodeText;
	private int barcodeLen;

	/*
	 * HERE COME CONSTRUCTORS. AND THERE ARE A *TON*
	 */

	/**
	 * The default constructor
	 */
	public LootItem() {
		itemType = ItemType.NONE;
		itemName = "";
		itemLevel = 1;
		description = "";
		itemRarity = ItemRarity.WHITE;
		weaponType = WeaponType.NONE;
		classType = ClassType.NONE;
		elementType = ElementType.NONE;
		barcodeText = "";
		barcodeLen = 0;
	}

	/**
	 * Adds a generic item to the DB
	 * @param type			Item's {@code ItemType}
	 * @param name			Name of item
	 * @param level			Required level
	 * @param barcodeText	String acting as barcode seed
	 * @param barcodeLen	Length of barcode string
	 */
	public LootItem(ItemType type, String name, int level, String barcodeText,
			int barcodeLen) {
		new LootItem(type, name, level, "", ItemRarity.WHITE, WeaponType.NONE,
				ClassType.NONE, ElementType.NONE, barcodeText, barcodeLen);
	}

	/**
	 * Adds a weapon to the DB
	 * @param type			Item's {@code WeaponType}
	 * @param name			Weapon name
	 * @param level			Required level
	 * @param barcodeText	Barcode seed
	 * @param barcodeLen	Length of barcode seed
	 */
	public LootItem(WeaponType type, String name, int level,
			String barcodeText, int barcodeLen) {
		new LootItem(ItemType.WEAPON, name, level, "", ItemRarity.WHITE, type,
				ClassType.NONE, ElementType.NONE, barcodeText, barcodeLen);
	}

	/**
	 * Adds a weapon to the DB
	 * @param type			Item's {@code WeaponType}
	 * @param name			Weapon name
	 * @param element		Weapon's {@code ElementType}
	 * @param level			Required level
	 * @param rarity		Weapon's {@code ItemRarity}
	 * @param barcodeText	Barcode seed
	 * @param barcodeLen	Length of barcode seed
	 */
	public LootItem(WeaponType type, String name, ElementType element,
			int level, ItemRarity rarity, String barcodeText, int barcodeLen) {
		new LootItem(ItemType.WEAPON, name, level, "", rarity, type,
				ClassType.NONE, element, barcodeText, barcodeLen);
	}

	/**
	 * Adds a weapon to the DB
	 * @param type			Item's {@code WeaponType}
	 * @param name			Weapon name
	 * @param element		Weapon's {@code ElementType}
	 * @param level			Required level
	 * @param rarity		Weapon's {@code ItemRarity}
	 * @param desc			Description
	 * @param barcodeText	Barcode seed
	 * @param barcodeLen	Length of barcode seed
	 */
	public LootItem(WeaponType type, String name, ElementType element,
			int level, ItemRarity rarity, String desc, String barcodeText,
			int barcodeLen) {
		new LootItem(ItemType.WEAPON, name, level, desc, rarity, type,
				ClassType.NONE, element, barcodeText, barcodeLen);
	}

	/**
	 * Adds an elemental item to the DB
	 * @param type			Item's {@code ItemType}
	 * @param name			Name of item
	 * @param level			Required level
	 * @param element		Item's {@code ElementType}
	 * @param barcodeText	Barcode seed
	 * @param barcodeLen	Length of barcode seed
	 */
	public LootItem(ItemType type, String name, int level, ElementType element,
			String barcodeText, int barcodeLen) {
		new LootItem(type, name, level, "", ItemRarity.WHITE, WeaponType.NONE,
				ClassType.NONE, element, barcodeText, barcodeLen);
	}

	/**
	 * Adds an elemental item to the DB
	 * @param type			Item's {@code ItemType}
	 * @param name			Name of item
	 * @param level			Required level
	 * @param element		Item's {@code ElementType}
	 * @param rarity		Item's {@code ItemRarity}
	 * @param barcodeText	Barcode seed
	 * @param barcodeLen	Length of barcode seed
	 */
	public LootItem(ItemType type, String name, int level, ElementType element,
			ItemRarity rarity, String barcodeText, int barcodeLen) {
		new LootItem(type, name, level, "", rarity, WeaponType.NONE,
				ClassType.NONE, element, barcodeText, barcodeLen);
	}

	/**
	 * Adds an elemental item to the DB
	 * @param type			Item's {@code ItemType}
	 * @param name			Name of item
	 * @param level			Required level
	 * @param element		Item's {@code ElementType}
	 * @param rarity		Item's {@code ItemRarity}
	 * @param desc			Item description
	 * @param barcodeText	Barcode seed
	 * @param barcodeLen	Length of barcode seed
	 */
	public LootItem(ItemType type, String name, int level, ElementType element,
			ItemRarity rarity, String desc, String barcodeText, int barcodeLen) {
		new LootItem(type, name, level, desc, rarity, WeaponType.NONE,
				ClassType.NONE, element, barcodeText, barcodeLen);
	}

	/**
	 * Adds a class mod to the DB
	 * @param type			{@code ClassType} this mod applies to
	 * @param name			Class mod's name
	 * @param level			Required level
	 * @param element		Class mod's {@code ElementType}
	 * @param barcodeText	Barcode seed
	 * @param barcodeLen	Length of barcode seed
	 */
	public LootItem(ClassType type, String name, int level,
			ElementType element, String barcodeText, int barcodeLen) {
		new LootItem(ItemType.CLASS_MOD, name, level, "", ItemRarity.WHITE,
				WeaponType.NONE, type, element, barcodeText, barcodeLen);
	}

	/**
	 * Adds a class mod to the DB
	 * @param type			{@code ClassType} this mod applies to
	 * @param name			Class mod's name
	 * @param level			Required level
	 * @param element		Class mod's {@code ElementType}
	 * @param rarity		{@code ItemRarity} of the class mod
	 * @param barcodeText	Barcode seed
	 * @param barcodeLen	Length of barcode seed
	 */
	public LootItem(ClassType type, String name, int level,
			ElementType element, ItemRarity rarity, String barcodeText,
			int barcodeLen) {
		new LootItem(ItemType.CLASS_MOD, name, level, "", rarity,
				WeaponType.NONE, type, element, barcodeText, barcodeLen);
	}

	/**
	 * Adds a class mod to the DB
	 * @param type			{@code ClassType} this mod applies to
	 * @param name			Class mod's name
	 * @param level			Required level
	 * @param element		Class mod's {@code ElementType}
	 * @param rarity		{@code ItemRarity} of the class mod
	 * @param desc			Description
	 * @param barcodeText	Barcode seed
	 * @param barcodeLen	Length of barcode seed
	 */
	public LootItem(ClassType type, String name, int level,
			ElementType element, ItemRarity rarity, String desc,
			String barcodeText, int barcodeLen) {
		new LootItem(ItemType.CLASS_MOD, name, level, desc, rarity,
				WeaponType.NONE, type, element, barcodeText, barcodeLen);
	}

	/**
	 * Adds all fields to the DB. The big kahuna. 
	 * @param type			Item's {@code ItemType}
	 * @param name			Name of item
	 * @param level			Required level
	 * @param description	Description
	 * @param rarity		Item's {@code ItemRarity}
	 * @param weaponType	Item's {@code WeaponType}
	 * @param classType		Item's {@code ClassType}
	 * @param element		Item's {@code ElementType}
	 * @param barcode		Barcode seed
	 * @param barcodeLen	Length of barcode seed
	 */
	public LootItem(ItemType type, String name, int level, String description,
			ItemRarity rarity, WeaponType weaponType, ClassType classType,
			ElementType element, String barcode, int barcodeLen) {
		itemType = type;
		itemName = name;
		itemLevel = level;
		this.description = description;
		itemRarity = rarity;
		this.weaponType = weaponType;
		this.classType = classType;
		elementType = element;
		barcodeText = barcode;
		this.barcodeLen = barcodeLen;
	}

	/**
	 * Gets the ItemType of the current instance
	 * @return the type of item
	 */
	public ItemType getItemType() {
		return itemType;
	}

	/**
	 * Sets the instance's ItemType
	 * @param itemType	The new ItemType
	 */
	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	/**
	 * Gets the name of the current instance
	 * @return the name of the item
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Sets the instance's name
	 * @param itemName 	The new name
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * Gets the level required by the item
	 * @return the minimum level (1 - 71)
	 */
	public int getItemLevel() {
		return itemLevel;
	}

	/**
	 * Sets the item's required level
	 * @param itemLevel	The new minimum level
	 */
	public void setItemLevel(int itemLevel) {
		this.itemLevel = itemLevel;
	}

	/**
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return
	 */
	public ItemRarity getItemRarity() {
		return itemRarity;
	}

	/**
	 * 
	 * @param itemRarity
	 */
	public void setItemRarity(ItemRarity itemRarity) {
		this.itemRarity = itemRarity;
	}

	/**
	 * 
	 * @return
	 */
	public WeaponType getWeaponType() {
		return weaponType;
	}

	/**
	 * 
	 * @param weaponType
	 */
	public void setWeaponType(WeaponType weaponType) {
		this.weaponType = weaponType;
	}

	/**
	 * 
	 * @return
	 */
	public ClassType getClassType() {
		return classType;
	}

	/**
	 * 
	 * @param classType
	 */
	public void setClassType(ClassType classType) {
		this.classType = classType;
	}

	/**
	 * 
	 * @return
	 */
	public ElementType getElementType() {
		return elementType;
	}

	/**
	 * 
	 * @param elementType
	 */
	public void setElementType(ElementType elementType) {
		this.elementType = elementType;
	}

	/**
	 * 
	 * @return
	 */
	public String getBarcodeText() {
		return barcodeText;
	}

	/**
	 * 
	 * @param barcodeText
	 */
	public void setBarcodeText(String barcodeText) {
		this.barcodeText = barcodeText;
	}

	/**
	 * 
	 * @return
	 */
	public int getBarcodeLen() {
		return barcodeLen;
	}

	/**
	 * 
	 * @param barcodeLen
	 */
	public void setBarcodeLen(int barcodeLen) {
		this.barcodeLen = barcodeLen;
	}

	@Override
	public String toString() {
		String returnMsg = "";
		returnMsg = "Item Type:    " + itemType.name() + "\n"
				+ "Item Name:    " + itemName + "\n" + "Item Level:   "
				+ itemLevel + "\n" + "Item Rarity:  " + itemRarity.name()
				+ "\n";
		switch (itemType) {
		case WEAPON:
			returnMsg += "Weapon Type:  " + weaponType.name() + "\n";
			break;
		case CLASS_MOD:
			returnMsg += "Class Type:   " + classType.name() + "\n";
			break;
		}
		returnMsg += "Description:  " + description + "\n" + "Barcode Seed: "
				+ barcodeText + "\n" + "Barcode Len:  " + barcodeLen + "\n";

		return returnMsg;
	}
}
