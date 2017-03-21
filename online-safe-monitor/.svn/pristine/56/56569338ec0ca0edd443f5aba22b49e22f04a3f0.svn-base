package cn.com.qytx.hotline.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;

import cn.com.qytx.hotline.ivr.domain.CallCenterConst;
import cn.com.qytx.platform.utils.datetime.DateTimeUtil;

public class CallCenterUtil {
	public static final String STR_FORMAT_THREE = "000";
	public static final String STR_FORMAT_FOUR = "0000";
	public static final String STR_FORMAT_FIVE = "00000";

	public static String getFormatNumByBits(Integer num, String strFormat) {
		DecimalFormat df = new DecimalFormat(strFormat);
		return df.format(num);
	}

	public static Timestamp getFormatZeroTime(Timestamp source) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		if (null != source) {
			now = new Timestamp(source.getTime());
		}
		String nowStr = DateTimeUtil.timestampToString(now,
				CallCenterConst.TIME_FORMAT_STR_NOT_MIN);
		now = DateTimeUtil.stringToTimestamp(nowStr,
				CallCenterConst.TIME_FORMAT_STR_NOT_MIN);
		return now;
	}

	public static Timestamp getTodayBeginTime() {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		String nowStr = DateTimeUtil.timestampToString(now,
				CallCenterConst.DATE_FORMAT_STR);
		now = DateTimeUtil.stringToTimestamp(nowStr,
				CallCenterConst.DATE_FORMAT_STR);
		return now;
	}

	// 计算交办率
	public double getCompletionRate(int completeNum, int assignedNum) {
		double completeDouble = completeNum * 100d;
		double assignedDouble = assignedNum;

		if (0 == assignedDouble) {
			return 0;
		}
		BigDecimal b = new BigDecimal(completeDouble / assignedDouble);
		double completionRate = b.setScale(1, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
		return completionRate;
	}
}
