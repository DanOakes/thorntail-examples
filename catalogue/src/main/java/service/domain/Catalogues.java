package service.domain;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Table(name = "CATALOGUES")
@NamedQueries({
    @NamedQuery(name = "Catalogues.findAll", query = "SELECT e FROM Catalogues e")
})
public class Catalogues {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
    @Column(length = 40)
    private String name;
    
    @Column
    private String code;
    
    @Column
    private String type;
    
    @Column
    private String url;
    
    @Column
    private String status;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
