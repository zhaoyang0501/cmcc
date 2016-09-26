package cmcc.core.exam.enums;

public enum QuestionTypeEnum {
	
	SINGLECHOICE("单选题") ,MULTISELECT("多选题"), TRUEORFALSE("判断题"),SUBJECTIVE("问答题");
	
	private String lable;
	
	private QuestionTypeEnum(String lable){
		this.lable=lable;
	}
	public String getLable() {
		return lable;
	}
	
	
}
