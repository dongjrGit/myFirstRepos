package com.yinlian.wssc.web.fastdfs;

import java.io.File;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.ServerInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yinlian.Extended.LogType;
import com.yl.soft.log.LogHandle;

public class FileManager implements FileManagerConfig {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(FileManager.class);

	private static TrackerClient trackerClient;
	private static TrackerServer trackerServer;
	private static StorageServer storageServer;
	private static StorageClient storageClient;

	static { // Initialize Fast DFS Client configurations

		try {
			String classPath = new File(FileManager.class.getResource("/")
					.getFile()).getCanonicalPath();

			String fdfsClientConfigFilePath = classPath + File.separator
					+ CLIENT_CONFIG_FILE;

			logger.info("Fast DFS configuration file path:"
					+ fdfsClientConfigFilePath);
			ClientGlobal.init(fdfsClientConfigFilePath);

			trackerClient = new TrackerClient();
			trackerServer = trackerClient.getConnection();

			storageClient = new StorageClient(trackerServer, storageServer);

		} catch (Exception e) {
			logger.error(logger+"", e);

		}
	}

	public static String[] upload(FastDFSFile file) {
		LogHandle.info(LogType.FastDFS, "File Name: " + file.getName()
				+ "		File Length: " + file.getContent().length);
		NameValuePair[] meta_list = new NameValuePair[3];
		meta_list[0] = new NameValuePair("width", "120");
		meta_list[1] = new NameValuePair("heigth", "120");
		meta_list[2] = new NameValuePair("author", "Diandi");

		long startTime = System.currentTimeMillis();
		String[] uploadResults = null;
		try {
			uploadResults = storageClient.upload_file(file.getContent(),
					file.getExt(), meta_list);
		} catch (IOException e) {
			LogHandle.error(LogType.FastDFS,
					"IO Exception when uploadind the file: " + file.getName()
							+ "e:" + e.toString());
		} catch (Exception e) {
			LogHandle.error(
					LogType.FastDFS,
					"Non IO Exception when uploadind the file: "
							+ file.getName() + "e:" + e.toString());
		}
		LogHandle.info(LogType.FastDFS,
				"upload_file time used: "
						+ (System.currentTimeMillis() - startTime) + " ms");

		if (uploadResults == null) {
			LogHandle.error(LogType.FastDFS, "upload file fail, error code: "
					+ storageClient.getErrorCode());
		}

		String groupName = uploadResults[0];
		String remoteFileName = uploadResults[1];
		String[] rsl = new String[8];
		rsl[0] = PROTOCOL;
		try {
			rsl[1] = trackerServer.getInetSocketAddress().getHostName();
		} catch (Exception e) {
			LogHandle.error(LogType.FastDFS, "upload file fail, error code: "
					+ "trackerServer");
		}
		rsl[2] = SEPARATOR;
		rsl[3] = TRACKER_NGNIX_PORT;
		rsl[4] = SEPARATOR;
		rsl[5] = groupName;
		rsl[6] = SEPARATOR;
		rsl[7] = remoteFileName;
		LogHandle.info(LogType.FastDFS, "upload file successfully!!!  "
				+ "group_name: " + groupName + ", remoteFileName:" + " "
				+ remoteFileName);
		return rsl;

	}
	
	public static byte[] downLoadFile(String groupName,String fileName){
		try {
			return	storageClient.download_file(groupName, fileName);
		} catch (IOException e) {
			logger.error("IOException: downLoad File from Fast DFS failed", e);
		} catch (MyException e) {
			logger.error("MyException: downLoad File from Fast DFS failed", e);
		}
		return null;
	}

	public static FileInfo getFile(String groupName, String remoteFileName) {
		try {
			return storageClient.get_file_info(groupName, remoteFileName);
		} catch (IOException e) {
			logger.error("IO Exception: Get File from Fast DFS failed", e);
		} catch (Exception e) {
			logger.error("Non IO Exception: Get File from Fast DFS failed", e);
		}
		return null;
	}

	public static void deleteFile(String groupName, String remoteFileName)
			throws Exception {
		storageClient.delete_file(groupName, remoteFileName);
	}

	public static StorageServer[] getStoreStorages(String groupName)
			throws IOException {
		return trackerClient.getStoreStorages(trackerServer, groupName);
	}

	public static ServerInfo[] getFetchStorages(String groupName,
			String remoteFileName) throws IOException {
		return trackerClient.getFetchStorages(trackerServer, groupName,
				remoteFileName);
	}
}
