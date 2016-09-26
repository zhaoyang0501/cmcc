package cmcc.core.exam.enums;

public enum LevelEnum {
	LEVEL1(1,"一颗星"),
	LEVEL2(2,"二颗星"),
	LEVEL3(3,"三颗星"),
	LEVEL4(4,"四颗星"),
	LEVEL5(5,"五颗星");
	
 	private int value;
 	private String lable;

    private LevelEnum(int value,String lable) {
        this.value = value;
        this.lable = lable;
    }

	public int getValue() {
		return value;
	}

	public String getLable() {
		return lable;
	}

	

	
    
}
