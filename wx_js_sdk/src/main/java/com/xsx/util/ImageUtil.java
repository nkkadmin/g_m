package com.xsx.util;

import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


/**
 * 
 * @Title: ImageUtil.java 
 * @Package com.xsx.util 
 * @Description: 图片处理工具类
 * @author xsx
 * @date 2018年1月17日 上午9:10:36 
 * @version V1.0
 */
public class ImageUtil {

	
	private ImageUtil(){}
	
	public static void main(String[] args) {
//		zipImageFile(new File("c:\\study\\aaa.jpg"), new File("c:\\study\\bbb.jpg"), 0, 0, 3f);
		readFolerImage("C:\\study\\attached");
	}
	
	/**
	 * 判断文件是否是图片
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private static boolean isIamge(File file){
		BufferedImage image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image != null ? true : false;
	}
	
	/**
	 * 读取指定文件夹下的所有图片,并压缩200k一下的文件
	 * @param folerPath
	 */
	private static void readFolerImage(String folerPath){
		File file = new File(folerPath);
		File[] files = file.listFiles();
		for(File f : files){
			if(isIamge(f) && (f.length() / 1024) >= 200){
//				System.out.println(f.getName() + ":" + f.length() / 1024);
				zipImageFile(f, f, 0, 0, 3f);
			}
		}
	}
	
	/**
	 * 根据设置的宽高等比例压缩图片文件，先保存原文件，再压缩，上传
	 * @param oldFile  要进行压缩的文件
	 * @param newFile  新文件
	 * @param width    宽度   --> 设置宽度是（高度传入0，等比缩放）
	 * @param height   高度   --> 设置高度时（宽度传入0，等比例缩放）  
	 * @param quality  质量
	 * @return
	 */
	public static String zipImageFile(File oldFile,File newFile,int width,int height,float quality){
		if(oldFile == null){
			return null;
		}
		try {
			//对原文件进行处理
			Image srcFile = ImageIO.read(oldFile);
			int w = srcFile.getWidth(null);
			int h = srcFile.getHeight(null);
			double bili;
			if(width > 0 ){
				bili = width / (double)w;
				height = (int)(h * bili);
			}else{
				if(height > 0){
					bili = height / (double)h;
					width = (int)(w * bili);
				}
			}
			//如果width和height都为0，则按等比例一倍缩放
			int resize = 2; //缩放倍数
			if(width == 0 && height == 0){
				width = w / resize;
				height = h / resize;
			}
			
			String srcImgPath = newFile.getAbsoluteFile().toString();
			String subfix = "jpg";
			subfix = srcImgPath.substring(srcImgPath.lastIndexOf(".")+1, srcImgPath.length());
			BufferedImage buffImg = null;
			if(subfix.equals("png")){
				buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			}else{
				buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			}
			Graphics2D graphics = buffImg.createGraphics();
			graphics.setBackground(new Color(255,255,255));
			graphics.setColor(new Color(255,255,255));
			graphics.fillRect(0, 0, width, height);
			graphics.drawImage(srcFile.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
			File saveFile = new File(srcImgPath);
			/*if(saveFile.isFile()){
				long fileLength = saveFile.length() / 1024; //单位kb
				if(fileLength > 100){
					zipImageFile(saveFile, newFile, 0, 0, quality);
				}else{
					return newFile.getAbsolutePath();
				}
			}*/
			ImageIO.write(buffImg, subfix, saveFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newFile.getAbsolutePath();
	}
	
}
