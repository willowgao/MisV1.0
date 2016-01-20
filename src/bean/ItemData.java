package bean;

public class ItemData {

	public String code;
	public String name;

	public ItemData(String code, String value) {
		this.code = code;
		this.name = value;
	}

	public String toString() {
		return name;
	}
}
