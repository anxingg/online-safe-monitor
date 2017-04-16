package cn.com.wh.devicesensorbind.domain;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import cn.com.wh.collectdevice.domain.CollectDevice;
import cn.com.wh.collectsensor.domain.CollectSensor;

/**
 * 通道绑定表
 * @author 吴胜光
 * 创建时间： 2017年04月03日
 * 
 */
@Entity
@Table(name="DEVICESENSORBIND")
public class DeviceSensorBind implements java.io.Serializable {

	private static final long serialVersionUID = 4705025193958385486L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;//id
	
    /**
     * 设备对象
     */
    @JoinColumn(name="DEVICEID")
    @ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	private CollectDevice collectDevice;
    
    /**
     * 传感器对象
     */
    @JoinColumn(name="SENSORID")
    @ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	private CollectSensor collectSensor;

    @Column(name="CHANNELNO")
	private Integer channelNo;
    
    @Column(name="CHANNELSTATUS")
	private Integer channelStatus;
    
	
    public Integer getChannelStatus() {
		return channelStatus;
	}

	public void setChannelStatus(Integer channelStatus) {
		this.channelStatus = channelStatus;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CollectDevice getCollectDevice() {
		return collectDevice;
	}

	public void setCollectDevice(CollectDevice collectDevice) {
		this.collectDevice = collectDevice;
	}

	public CollectSensor getCollectSensor() {
		return collectSensor;
	}

	public void setCollectSensor(CollectSensor collectSensor) {
		this.collectSensor = collectSensor;
	}

	
	public Integer getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(Integer channelNo) {
		this.channelNo = channelNo;
	}

	public DeviceSensorBind() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	
}