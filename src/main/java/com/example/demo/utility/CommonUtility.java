package com.example.demo.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

/**
 * よく使う、DBに絡まない共通処理
 * @author towelman
 *
 */
@Component
public class CommonUtility {
	private static final TimeZone TIME_ZONE = TimeZone.getTimeZone("Asia/Tokyo");

	/**
	 * 文字列から日付を取得する。
	 * フォーマットはデフォルトで"yyyy-M-d"となる。
	 * 
	 * @param str 日付にしたい文字列
	 * @return 日付
	 */
	public Date stringToDate(String str) {
		return this.stringToDate(str, "yyyy-M-d");
	}
	
	/**
	 * 文字列から日付を取得する。
	 * 
	 * @param str 日付にしたい文字列
	 * @param formatString フォーマット
	 * @return 日付
	 */
	public Date stringToDate(String str, String formatString) {
		try {
			var format = new SimpleDateFormat(formatString);
			format.setTimeZone(TIME_ZONE);
			return format.parse(str);
		}catch(ParseException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 日付を文字列に変換する
	 * フォーマットはデフォルトで"yyyy-M-d"となる。
	 * @param date 文字列に変換したい日付
	 * @return 文字列
	 */
	public String dateToString(Date date) {
		return this.dateToString(date, "yyyy-M-d");
	}
	
	/**
	 * 日付を文字列に変換する
	 * @param date 文字列に変換したい日付
	 * @param formatString フォーマット
	 * @return 文字列
	 */
	public String dateToString(Date date, String formatString) {
		var format = new SimpleDateFormat(formatString);
		format.setTimeZone(TIME_ZONE);
		return format.format(date);
	}
}
