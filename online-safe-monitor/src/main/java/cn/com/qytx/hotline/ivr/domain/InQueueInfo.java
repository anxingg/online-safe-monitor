package cn.com.qytx.hotline.ivr.domain;

public class InQueueInfo implements Comparable<InQueueInfo> {
	/**
	 * 日期
	 */
	private String inQueueHour;

	/**
	 * 排队总数
	 */
	private int totleInQueueNum;

	public String getInQueueHour() {
		return inQueueHour;
	}

	public void setInQueueHour(String inQueueHour) {
		this.inQueueHour = inQueueHour;
	}

	public int getTotleInQueueNum() {
		return totleInQueueNum;
	}

	public void setTotleInQueueNum(int totleInQueueNum) {
		this.totleInQueueNum = totleInQueueNum;
	}

	@Override
	public int compareTo(InQueueInfo o) {
		if (Integer.parseInt(inQueueHour) < Integer
				.parseInt(o.getInQueueHour())) {
			return -1;
		} else if (Integer.parseInt(inQueueHour) > Integer.parseInt(o
				.getInQueueHour())) {
			return 1;
		}
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((inQueueHour == null) ? 0 : inQueueHour.hashCode());
		result = prime * result + totleInQueueNum;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		InQueueInfo other = (InQueueInfo) obj;
		if (inQueueHour == null) {
			if (other.inQueueHour != null){
				return false;
			}
		} else if (!inQueueHour.equals(other.inQueueHour)){
			return false;
		}
		if (totleInQueueNum != other.totleInQueueNum){
			return false;
		}
		return true;
	}

	
	
}
