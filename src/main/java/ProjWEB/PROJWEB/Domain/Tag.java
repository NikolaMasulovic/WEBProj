package ProjWEB.PROJWEB.Domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tag {
	
	private long id;
	private String name;
	private int count;
	
	public Tag() {};
	
	public Tag(long id,String name,int count) {
		this.id = id;
		this.name = name;
		this.count = count;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + ", count=" + count + "]";
	};
}
