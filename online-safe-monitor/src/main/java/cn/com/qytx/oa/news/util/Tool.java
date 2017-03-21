package cn.com.qytx.oa.news.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

/**
 * User: hp Date: 11-5-30
 */
public class Tool {
//	private static final Logger logger = Logger.getLogger(MeetingAction.class);
	public static String httpUrl = "";
	public static String formateLong="yyyy-MM-dd HH:mm:ss";
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获取两个时间差
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static long getIntTimeSpan(String startTime, String endTime) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			java.util.Date now = df.parse(startTime);
			java.util.Date date = df.parse(endTime);
			long l = date.getTime() - now.getTime();
			long day = l / (24 * 60 * 60 * 1000);
			long hour = (l / (60 * 60 * 1000) - day * 24);
			long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
			if (hour < 0) {
				hour = 0;
			}
			if (min < 0) {
				min = 0;
			}
			if (s < 0) {
				s = 0;
			}
			if (day < 0) {
				day = 0;
			}
			return day * 24 * 3600 + hour * 3600 + min * 60 + s;

		} catch (Exception ex) {
			return 0;
		}
	}

	/**
	 * 获取两个时间差
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getIntTimeSpanMin(String startTime, String endTime) {
		SimpleDateFormat df = new SimpleDateFormat(formateLong);
		try {
			java.util.Date now = df.parse(startTime);
			java.util.Date date = df.parse(endTime);
			long l = date.getTime() - now.getTime();
			long day = l / (24 * 60 * 60 * 1000);
			long hour = (l / (60 * 60 * 1000) - day * 24);
			long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
			if (hour < 0) {
				hour = 0;
			}
			if (min < 0) {
				min = 0;
			}
			if (s < 0) {
				s = 0;
			}
			if (day < 0) {
				day = 0;
			}
			int timeLong = 0;
			if (s == 0) {
				timeLong = (int) (day * 24 * 60 + hour * 60 + min);
			} else if (s != 0) {
				timeLong = (int) (day * 24 * 60 + hour * 60 + min + 1);
			}
			return timeLong;
		} catch (Exception ex) {
			return 0;
		}
	}

	/**
	 * 当月第一天
	 * 
	 * @return
	 */
	public static String getCurrentMonthFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 0);// 减一个月，变为下月的1号
		str = sdf.format(lastDate.getTime());
		return str;
	}

	public static String getCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat(formateLong);
		Date date = new Date();
		String datestr = df.format(date);
		return datestr;
	}

	/**
	 * 压缩字符串为 byte[] 储存可以使用new sun.misc.BASE64Encoder().encodeBuffer(byte[] b)方法
	 * 保存为字符串
	 * 
	 * @param str
	 *            压缩前的文本
	 * @return
	 */
	public static final byte[] compress(String str) {
		if (str == null){
			return null;
		}

		byte[] compressed;
		ByteArrayOutputStream out = null;
		ZipOutputStream zout = null;

		try {
			out = new ByteArrayOutputStream();
			zout = new ZipOutputStream(out);
			zout.putNextEntry(new ZipEntry("0"));
			zout.write(str.getBytes("ISO-8859-1"));
			zout.closeEntry();
			compressed = out.toByteArray();
		} catch (IOException e) {
			compressed = null;
		} finally {
			if (zout != null) {
				try {
					zout.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}

		return compressed;
	}

	/**
	 * 将压缩后的 byte[] 数据解压缩
	 * 
	 * @param compressed
	 *            压缩后的 byte[] 数据
	 * @return 解压后的字符串
	 */
	public static final String decompress(byte[] compressed) {
		if (compressed == null){
			return null;
		}

		ByteArrayOutputStream out = null;
		ByteArrayInputStream in = null;
		ZipInputStream zin = null;
		String decompressed;
		try {
			out = new ByteArrayOutputStream();
			in = new ByteArrayInputStream(compressed);
			zin = new ZipInputStream(in);
			byte[] buffer = new byte[1024];
			int offset = -1;
			offset = zin.read(buffer);
			while (offset != -1) {
				out.write(buffer, 0, offset);
				offset = zin.read(buffer);
			}
			decompressed = out.toString("utf-8");
		} catch (IOException e) {
			decompressed = null;
		} finally {
			if (zin != null) {
				try {
					zin.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return decompressed;
	}


	/**
	 * 转换时间为 日期 星期几 时间格式
	 * @param openDate
	 * @return
	 */
	public static String getOpenDateStr(String openDate){
		SimpleDateFormat df = new SimpleDateFormat(formateLong);
		if(openDate!=null){
			SimpleDateFormat dataFormat = new SimpleDateFormat(
					"yyyy-MM-dd E HH:mm:ss");
			Date date = null;
			try {
				date = df.parse(openDate);
			} catch (ParseException e) {
			}
			String datestr ="";
			if(date!=null){
				 datestr = dataFormat.format(date);
			}
			return datestr;
			
		}else{
			return "";
		}
	}
	/**
	 * 秒转换时分秒
	 * @param compareMiniSec
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String getTimeStr(int compareMiniSec) {
		int compareMinite = 0;
		int compareSec = 0;
		int compareHour = 0;
		String sTimeLong = "";
		try {
			compareHour = (int) (compareMiniSec / 1000 / 60 / 60);
			if (compareHour == 0)// 小于一个小时
			{
				compareMinite = (int) (compareMiniSec / 1000 / 60);
				if (compareMinite == 0)// 小于一分钟
				{
					compareSec = (int) (compareMiniSec / 1000);
				} else // 大于一分钟小于一个小时
				{
					compareSec = (int) ((compareMiniSec - (compareMinite * 1000 * 60)) / 1000);
				}
			} else// 大于一个小时
			{
				compareMinite = (int) ((compareMiniSec - (compareHour * 1000 * 60 * 60)) / 1000 / 60);
				if (compareMinite == 0)// 大于一个小时小于1分钟
				{
					compareSec = (int) ((compareMiniSec - (compareHour * 1000 * 60 * 60)) / 1000);
				} else// 大于一个小时大于一分钟
				{
					compareSec = (int) ((compareMiniSec
							- (compareMinite * 1000 * 60) - (compareHour * 1000 * 60 * 60)) / 1000);
				}
			}
		} catch (Exception e) {

		}
		if (compareHour > 0) {
			sTimeLong += compareHour + "时";
		}
		if (compareMinite > 0) {
			sTimeLong += compareMinite + "分";
		}
		if (compareSec > 0) {
			sTimeLong += compareSec + "秒";
		}

		return sTimeLong;
	}

	@SuppressWarnings("unused")
	private static int daysBetween(Date now, Date returnDate) {
		Calendar cNow = Calendar.getInstance();
		Calendar cReturnDate = Calendar.getInstance();
		cNow.setTime(now);
		cReturnDate.setTime(returnDate);
		setTimeToMidnight(cNow);
		setTimeToMidnight(cReturnDate);
		long todayMs = cNow.getTimeInMillis();
		long returnMs = cReturnDate.getTimeInMillis();
		long intervalMs = todayMs - returnMs;
		return millisecondsToDays(intervalMs);
	}

	private static int millisecondsToDays(long intervalMs) {
		return (int) (intervalMs / (1000 * 86400));
	}

	private static void setTimeToMidnight(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
	}
	/**
	 * 
	 * 功能：得到非空字符串
	 * @param str
	 * @return
	 */
	public static String getNotNullString(String str){
		if(str==null){
			str="";
		}
		return str;
	}
}
