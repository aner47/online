package com.online.util.datapush;

import javax.persistence.Column;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 数据源描述
 * @author DEV2
 *
 */

public class DataSourceBean {
	
	/**
     * 
     */
    private static final long serialVersionUID = -4996506209638505444L;
    private Integer project;
  
    private String pushName;
    private String url;
    @NotBlank(message="用户名不得为空")
	private String username;
	
	private String password;
	
	private String driverClass;
	private String message;
	
	@Column(columnDefinition = "VARCHAR(255) COMMENT '推送名称'")
	public String getPushName() {
        return pushName;
    }

    public void setPushName(String pushName) {
        this.pushName = pushName;
    }
    @Column(columnDefinition = "INT(11) COMMENT '清单'")
    public Integer getProject() {
        return project;
    }

    public void setProject(Integer project) {
        this.project = project;
    }
    @Column(columnDefinition = "VARCHAR(255) COMMENT '数据源信息'")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @Column(columnDefinition = "VARCHAR(255) COMMENT '数据源地址'")
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(columnDefinition = "VARCHAR(255) COMMENT '数据源用户名'")
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@Column(columnDefinition = "VARCHAR(255) COMMENT '数据源密码'")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(columnDefinition = "VARCHAR(255) COMMENT '驱动信息'")
	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
	public JDBCHelp generalJDBCHelp(){
		return new JDBCHelp(url, username, password);
	}

	public DataSourceBean() {
        super();
    }

	public DataSourceBean(String url, String username, String password) {
		super();
		this.url = url;
		this.username = username;
		this.password = password;
	}
	

}
