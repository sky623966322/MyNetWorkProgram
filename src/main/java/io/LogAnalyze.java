package io;

import com.duowan.leopard.json.Json;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author:wangyan1
 * @create_time: 2018-03-07 17:17
 */

public class LogAnalyze {

	public static void main(String[] args) throws Exception {
		//analyzeMid("loginMobile_exception_104.txt");
		//analyzeMid("loginMobile_exception_151.txt");
		//analyzeMid("regAcc_exception_104.txt");
		//analyzeMid("regAcc_exception_151.txt");
		//analyzeMid("login_exception.txt");
		//analyzeMid("loginToken_exception.txt");
		//analyzeLoginSuccess("login_success.txt");
		analyzeLoginSuccess("loginToken_success.txt");
	}

	private static void analyzeMid(String srcFile) throws Exception {
		try (
			BufferedReader reader = new BufferedReader(new FileReader(srcFile));
		) {
			Map<String, Set<String>> map = new HashedMap<>();
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (StringUtils.isBlank(line)) continue;

				String resultStr = StringUtils.substringBetween(line, "result", "param");
				if (StringUtils.isBlank(resultStr)) continue;

				String result = resultStr.trim();
				String paramStr = StringUtils.substringBetween(line, "param", " exception[").trim();
				Map<String, Object> paramMap = Json.toMap(paramStr);

				String device = (String) ((List) paramMap.get("device")).get(0);
				Map<String, Object> resultMap = Json.toMap(result);
				Map<String, Object> deviceMap = Json.toMap(device);

				String key = "";
				String value = "";
				if (paramMap.containsKey("account")){
					key = "account_";
					value = (String) ((List) paramMap.get("account")).get(0);
				}else if (paramMap.containsKey("mobile")){
					key = "mobile";
					value = (String) ((List) paramMap.get("mobile")).get(0);
				}else {
					value = (String) deviceMap.get("mid");
				}
				key += "_" + resultMap.get("status");
				Set<String> set = map.get(key);
				if (set == null){
					set = new HashSet<>();
				}
				set.add(value);
				map.put(key, set);
			}

			for (String status : map.keySet()) {
				String prefix = StringUtils.substring(srcFile, 0, srcFile.lastIndexOf("_"));
				String destFile = String.format("%s_%s.txt", prefix, status);
				BufferedWriter writer = null;
				try {
					writer = new BufferedWriter(new FileWriter(destFile, true));//追加模式
					Set<String> set = map.get(status);
					for (String mid : set) {
						writer.append(mid).append("\n").flush();
					}
				} finally {
					writer.close();
				}
			}
		}
	}

	private static void analyzeLoginSuccess(String srcFile) throws Exception {
		try (
				BufferedReader reader = new BufferedReader(new FileReader(srcFile));
		) {
			Map<String, Set<String>> map = new HashedMap<>();
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (StringUtils.isBlank(line)) continue;

				String paramStr = StringUtils.substringBetween(line, "ms ", ">");
				if (StringUtils.isBlank(paramStr)) {
					continue;
				}
				Map<String, Object> paramMap = Json.toMap(paramStr);

				String key = "account";
				Object obj = paramMap.get(key);
				if (obj == null ){
					continue;
				}
				String value = (String) obj;
				Set<String> set = map.get(key);
				if (set == null){
					set = new HashSet<>();
				}
				set.add(value);
				map.put(key, set);
			}

			for (String status : map.keySet()) {
				String prefix = StringUtils.substring(srcFile, 0, srcFile.lastIndexOf("txt"));
				String destFile = String.format("%s_%s.txt", prefix, status);
				BufferedWriter writer = null;
				try {
					writer = new BufferedWriter(new FileWriter(destFile, true));//追加模式
					Set<String> set = map.get(status);
					for (String mid : set) {
						writer.append(mid).append("\n").flush();
					}
				} finally {
					writer.close();
				}
			}
		}
	}
}
