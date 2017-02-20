/**
 * <p>Title: NvrIpc.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年8月19日
 * @version 1.0.0
 */
package cn.ittc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <p>Title: NvrIpc</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年8月19日
 */
@Entity
@Table(name = "t_nvr_ipc")
public class NvrIpc {
	private Integer id;
	private Nvr nvr;
	private Ipc ipc;
	private Integer channel;//通道号

	@Id
    @GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="nvrId")
	public Nvr getNvr() {
		return nvr;
	}

	public void setNvr(Nvr nvr) {
		this.nvr = nvr;
	}
	@ManyToOne
	@JoinColumn(name="cameraId")
	public Ipc getIpc() {
		return ipc;
	}

	public void setIpc(Ipc ipc) {
		this.ipc = ipc;
	}

	@Column(name = "channelId", length = 30, nullable = true, unique = false)
	public Integer getChannel() {
		return channel;
	}


	public void setChannel(Integer channel) {
		this.channel = channel;
	}
	
	
	

}
