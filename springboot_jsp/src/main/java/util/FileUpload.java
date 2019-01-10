package util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@Component
public class FileUpload {

	@Value("${imgUrl}")
	private String imgUrl;

	@Value("${urlPre}")
	private String urlPre;

	public JSONObject upload(String httpurl, String fileName, InputStream inputStream) {
		String result = "";
		try {

			String BOUNDARY = "---------7d4a6d158c9"; // 定义数据分隔线
			URL url = new URL(httpurl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setRequestProperty("Charsert", "UTF-8");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			StringBuilder sb = new StringBuilder();
			sb.append("--");
			sb.append(BOUNDARY);
			sb.append("\r\n");
			sb.append("Content-Disposition: form-data;name=\"file" + 1 + "\";filename=\"" + fileName + "\"\r\n");
			sb.append("Content-Type:application/octet-stream\r\n\r\n");
			byte[] data = sb.toString().getBytes();
			out.write(data);
			DataInputStream in = new DataInputStream(inputStream);
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			in.close();
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
		}
		return JSONObject.parseObject(result);
	}

	// base64字符串转化成图片
	public String GenerateImage(String imgStr) { // 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return null;
		imgStr = imgStr.substring(imgStr.indexOf(",")+1, imgStr.length());
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss_SSS");
			Date date = new Date();
			String imgFilePath = imgUrl + simpleDateFormat.format(date) + ".jpg";// 新生成的图片
//			String imgFilePath = "C:\\u01\\images\\" + simpleDateFormat.format(date) + ".jpg";// 新生成的图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return urlPre + simpleDateFormat.format(date) + ".jpg";
//			return "http://192.168.3.31/images/"+simpleDateFormat.format(date) + ".jpg";
		} catch (Exception e) {
			return null;
		}
	}

	// 图片转化成base64字符串
	public String GetImageStr() {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		String imgFile = "C:\\Users\\EDZ\\Desktop\\01f9ea56e282836ac72531cbe0233b.jpg@2o.jpg";// 待处理的图片
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}
}
