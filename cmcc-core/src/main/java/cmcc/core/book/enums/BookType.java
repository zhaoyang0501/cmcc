package cmcc.core.book.enums;

public enum BookType {
	TXT("1","书籍"),
	VIDEO("2","视频"),
	VIICE("3","音频");
	private String code;
	private String name;

	private BookType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
