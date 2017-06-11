package com.taotao.ftp;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

public class TestFTPClient {
	
	@Test
	public void testFTPClient() throws Exception{
		
//		FTPClient client = new FTPClient();
//		
//		//连接fpt
//		client.connect("172.18.39.46", 22);
//		
//		//登录ftp
//		client.login("ftpuser", "ABC123def!");
//		
//		//读取本地文件
//		InputStream input = new FileInputStream("d:\\logo2.png");
//		
//		//设置ftp保存目录
//		client.changeWorkingDirectory("/home/ftpuser");
//		
//		//指定上传文件类型
//		client.setFileType(FTPClient.BINARY_FILE_TYPE);
//		
		//保存到fpt
		//第一个参数：保存的文件名称
		//第二个参数：文件流
//		client.storeFile("hello.png", input);
		
		//退出登录
//		client.logout();
	}
}
