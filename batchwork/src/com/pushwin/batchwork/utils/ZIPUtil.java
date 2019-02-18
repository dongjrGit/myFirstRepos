package com.pushwin.batchwork.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipInputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;


public class ZIPUtil {

	/**
	 * 
	 * @param inputFileName
	 *            输入一个文件夹路径
	 * @param zipFileName
	 *            输出一个压缩文件夹路径
	 * @param name压缩后文件名字
	 * @throws Exception
	 */
	public static void zip(String inputFileName, String zipFileName, String name)
			throws Exception {

		try {
			zip(zipFileName, new File(inputFileName), name);
		} catch (Exception e) {
			// log 记录日志文件
			e.printStackTrace();
		}

	}

	private static void zip(String zipFileName, File inputFile, String name)
			throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFileName));
		zip(out, inputFile, name);
		System.out.println("zip done");
		out.close();
	}

	private static void zip(ZipOutputStream out, File f, String name)
			throws Exception {
		if (f.isDirectory()) { // 判断是否为目录
			File[] fl = f.listFiles();
			out.putNextEntry(new org.apache.tools.zip.ZipEntry(name + "/"));
			name = name.length() == 0 ? "" : name + "/";
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], name + fl[i].getName());
			}
		} else { // 压缩目录中的所有文件
			out.putNextEntry(new org.apache.tools.zip.ZipEntry(name));
			FileInputStream in = new FileInputStream(f);
			int b;
			System.out.println(name);
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
		}
	}

	/**
	 * 压缩文件,文件夹
	 * 
	 * @param srcFilePath	要压缩的文件/文件夹名字
	 * @param zipFilePath	指定压缩的目的和名字
	 * @throws Exception
	 */
	public static void zipFolder(String srcFilePath, String zipFilePath)throws Exception {
		//创建Zip包
		java.util.zip.ZipOutputStream outZip = new java.util.zip.ZipOutputStream(new java.io.FileOutputStream(zipFilePath));
		
		//打开要输出的文件
		java.io.File file = new java.io.File(srcFilePath);

		//压缩
		zipFiles(file.getParent()+java.io.File.separator, file.getName(), outZip);
		
		//完成,关闭
		outZip.finish();
		outZip.close();
	
	}//end of func
	
	/**
	 * 解压一个压缩文档 到指定位置
	 * @param zipFileString	压缩包的名字
	 * @param outPathString	指定的路径
	 * @throws Exception
	 */
	public static void unZipFolder(InputStream input, String outPathString)throws Exception {
		java.util.zip.ZipInputStream inZip = new java.util.zip.ZipInputStream(input);
		java.util.zip.ZipEntry zipEntry = null;
		String szName = "";
		
		while ((zipEntry = inZip.getNextEntry()) != null) {
			szName = zipEntry.getName();
		
			if (zipEntry.isDirectory()) {
		
				// get the folder name of the widget
				szName = szName.substring(0, szName.length() - 1);
				java.io.File folder = new java.io.File(outPathString + java.io.File.separator + szName);
				folder.mkdirs();
		
			} else {
		
				java.io.File file = new java.io.File(outPathString + java.io.File.separator + szName);
				file.createNewFile();
				// get the output stream of the file
				java.io.FileOutputStream out = new java.io.FileOutputStream(file);
				int len;
				byte[] buffer = new byte[1024];
				// read (len) bytes into buffer
				while ((len = inZip.read(buffer)) != -1) {
					// write (len) byte from buffer at the position 0
					out.write(buffer, 0, len);
					out.flush();
				}
				out.close();
			}
		}//end of while
		
		inZip.close();
	}
	
	/**
	 * 压缩文件,文件夹
	 * 生成的压缩文件中不包含文件夹,只压缩文件中的jpg、png格式的图片文件。
	 * 
	 * @param srcFilePath
	 *            要压缩的文件/文件夹名字
	 * @param zipFilePath
	 *            指定压缩的目的和名字
	 * @throws Exception
	 */
	public static void zipFolder1(String srcFilePath, String zipFilePath)
			throws Exception {
		// 创建Zip包
		java.util.zip.ZipOutputStream outZip = new java.util.zip.ZipOutputStream(
				new java.io.FileOutputStream(zipFilePath));
		
		// 打开要输出的文件
		java.io.File file = new java.io.File(srcFilePath);
		String [] files = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				String fileExt = name.substring(name.lastIndexOf(".") + 1);
				if(fileExt.equals("jpg") || fileExt.equals("png")) {
					return true;
				}
				return false;
			}
		});
		for(int i = 0; i < files.length; i++) {
			// 压缩
			zipFiles(srcFilePath + java.io.File.separator, files[i],
					outZip);
		}
		// 完成,关闭
		outZip.finish();
		outZip.close();
	}

	/**
	 * 压缩文件
	 * 
	 * @param folderPath
	 * @param filePath
	 * @param zipOut
	 * @throws Exception
	 */
	private static void zipFiles(String folderPath, String filePath,
			java.util.zip.ZipOutputStream zipOut) throws Exception {
		if (zipOut == null) {
			return;
		}

		java.io.File file = new java.io.File(folderPath + filePath);

		// 判断是不是文件
		if (file.isFile()) {

			java.util.zip.ZipEntry zipEntry = new java.util.zip.ZipEntry(
					filePath);
			java.io.FileInputStream inputStream = new java.io.FileInputStream(
					file);
			zipOut.putNextEntry(zipEntry);

			int len;
			byte[] buffer = new byte[4096];

			while ((len = inputStream.read(buffer)) != -1) {
				zipOut.write(buffer, 0, len);
			}

			zipOut.closeEntry();
		} else {

			// 文件夹的方式,获取文件夹下的子文件
			String fileList[] = file.list();

			// 如果没有子文件, 则添加进去即可
			if (fileList.length <= 0) {
				java.util.zip.ZipEntry zipEntry = new java.util.zip.ZipEntry(
						filePath + java.io.File.separator);
				zipOut.putNextEntry(zipEntry);
				zipOut.closeEntry();
			}

			// 如果有子文件, 遍历子文件
			for (int i = 0; i < fileList.length; i++) {
				zipFiles(folderPath, filePath + java.io.File.separator
						+ fileList[i], zipOut);
			}// end of for

		}// end of if

	}

	/**
	 * 解压
	 * 
	 * @param inputFileName
	 *            输入一个压缩文件夹
	 * @param outputFileName
	 *            输出一个文件夹
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public static void unzip(String zipFile, String outputFileName)
			throws Exception {
		ZipFile zip = new ZipFile(zipFile);
		Enumeration<ZipEntry> en = zip.getEntries();
		ZipEntry entry = null;
		byte[] buffer = new byte[1024];
		int length = -1;
		InputStream input = null;
		BufferedOutputStream bos = null;
		File file = null;

		while (en.hasMoreElements()) {
			entry = (ZipEntry) en.nextElement();
			if (entry.isDirectory()) {
				file = new File(outputFileName, entry.getName());
				if (!file.exists()) {
					file.mkdirs();
				}
				continue;
			}

			input = zip.getInputStream(entry);
			file = new File(outputFileName, entry.getName());
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			bos = new BufferedOutputStream(new FileOutputStream(file));

			while (true) {
				length = input.read(buffer);
				if (length == -1)
					break;
				bos.write(buffer, 0, length);
			}
			bos.close();
			input.close();
		}
		zip.close();
	}

	/***
	 * 解压缩文件
	 * @param： file 压缩文件
	 *          dir 解压缩后目录
	 * @return： true/false
	 */	
	public static boolean unzip(File file, File dir) throws Exception {
		byte[] c = new byte[1024];
		int len;
		File outFile;
		File parentFile;
		ZipInputStream in = null;
		FileOutputStream out = null;
		FileInputStream fis = null;
		try {
			ZipFile zipFile = new ZipFile(file);
			zipFile.close();
			fis = new FileInputStream(file);
			in = new ZipInputStream(fis);
			java.util.zip.ZipEntry zipEntry = in.getNextEntry();

			while (zipEntry != null) {
				outFile = new File(dir.getPath() + File.separator
						+ zipEntry.getName());
				if (zipEntry.isDirectory()) {
					if (!outFile.exists())
						outFile.mkdirs();
				} else {
					parentFile = outFile.getParentFile();
					if (!parentFile.exists())
						parentFile.mkdirs();
					out = new FileOutputStream(outFile);
					while ((len = in.read(c, 0, c.length)) != -1)
						out.write(c, 0, len);
					out.close();
					out = null;
				}
				zipEntry = in.getNextEntry();
			}
			in.close();
			in = null;
		} catch (Exception e) {;
			throw e;
		} finally {
			try {
			if(fis!=null)fis.close();
				if (out != null)
					out.close();
				if (in != null)
					in.close();
				
			} catch (Exception fe) {
				;
			}
		}
		return true;
	}
	/**
	 * 删除指定目录及其中的所有内容。
	 * 
	 * @param dir
	 *            要删除的目录
	 * @return 删除成功时返回true，否则返回false。
	 * @since 1.0
	 */
	public static boolean deleteDirectory(File dir) {
		if ((dir == null) || !dir.isDirectory()) {
			throw new IllegalArgumentException("Argument " + dir
					+ " is not a directory. ");
		}

		File[] entries = dir.listFiles();
		int sz = entries.length;

		for (int i = 0; i < sz; i++) {
			if (entries[i].isDirectory()) {
				if (!deleteDirectory(entries[i])) {
					return false;
				}
			} else {
				if (!entries[i].delete()) {
					return false;
				}
			}
		}

		if (!dir.delete()) {
			return false;
		}
		return true;
	}

	/**
	 * 解压一个压缩文档 到指定位置
	 * @param zipFileString	压缩包的名字
	 * @param outPathString	指定的路径
	 * @throws Exception
	 */
	public static void unZipFolder(String zipFileString, String outPathString)throws Exception {
		unZipFolder(new java.io.FileInputStream(zipFileString),outPathString);
	}
	
	/**
	 * 删除指定目录及其中的所有内容。
	 * 
	 * @param dirName
	 *            要删除的目录的目录名
	 * @return 删除成功时返回true，否则返回false。
	 * @since 1.0
	 */
	public static boolean deleteDirectory(String dirName) {
		return deleteDirectory(new File(dirName));
	}

	/**
	 * 判断指定的文件是否存在。
	 * 
	 * @param fileName
	 *            要判断的文件的文件名
	 * @return 存在时返回true，否则返回false。
	 * @since 1.0
	 */
	public static boolean isFileExist(String fileName) {
		return (new File(fileName)).isDirectory();
	}

	// 拷贝文件
	public static final boolean CopyFile(File in, File out) throws Exception {
		try {
			FileInputStream fis = new FileInputStream(in);
			FileOutputStream fos = new FileOutputStream(out);
			byte[] buf = new byte[1024];
			int i = 0;
			while ((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
			fis.close();
			fos.close();
			return true;
		} catch (IOException ie) {
			ie.printStackTrace();
			return false;
		}
	}

	// 拷贝文件
	public static final boolean CopyFile(String infile, String outfile)
			throws Exception {
		try {
			File in = new File(infile);
			File out = new File(outfile);
			return CopyFile(in, out);
		} catch (IOException ie) {
			ie.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		zip("D:\\Jerry\\tzip", "D:\\Jerry\\tzips", "tzip.zip");
	}
}