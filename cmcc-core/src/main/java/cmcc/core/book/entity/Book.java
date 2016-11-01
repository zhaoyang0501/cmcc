package cmcc.core.book.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cmcc.common.entity.BaseEntity;
import cmcc.core.book.enums.BookType;
import cmcc.core.sys.entity.User;
@Entity
@Table(name = "t_book_Book")
public class Book  extends BaseEntity<Long> {
	
	private String title;
	
	@Column(name="desc_")
	private String desc;
	
	private String img;
	
	private String filepath;
	
	private String fileType;
	
	private User craeter;
	
	@Enumerated(value = EnumType.STRING)
	private BookType type = BookType.TXT;
	
	@ManyToOne
	private BookCategory bookCategory;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public User getCraeter() {
		return craeter;
	}

	public void setCraeter(User craeter) {
		this.craeter = craeter;
	}

	public BookType getType() {
		return type;
	}

	public void setType(BookType type) {
		this.type = type;
	}

	public BookCategory getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}
	
	
}
