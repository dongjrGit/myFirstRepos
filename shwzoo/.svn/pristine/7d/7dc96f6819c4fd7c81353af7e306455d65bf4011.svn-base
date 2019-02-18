package com.yinlian.api.app.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yinlian.Enums.FileTypeEnum;
import com.yinlian.Enums.RelationTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.fastdfs.FastDFSFile;
import com.yinlian.wssc.web.fastdfs.FileManager;
import com.yinlian.wssc.web.po.FileRecords;
import com.yinlian.wssc.web.service.FileRecordsService;
import com.yinlian.wssc.web.util.ConfigUtil;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.ImageUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/app/api/img")
public class ImageController {
	/**
	 * 日志输出的类
	 */
	//private static Logger logger = Logger.getLogger(ImageController.class);

	@Autowired
	private FileRecordsService fileRecordsService;

	/**
	 * 上传文件（图片，文件）
	 * 
	 * @param multipartRequest
	 * @param relationid
	 *            关联id
	 * @param relationtype
	 *            关联类型（RelationTypeEnum）
	 * @return
	 */
	@RequestMapping(value = "/upload", produces = "text/html;charset=UTF-8")
	public @ResponseBody String upload(MultipartHttpServletRequest multipartRequest, String relationid,
			String relationtype, String ch, String type, String iskdr, String token, HttpServletResponse response) {
		BaseResult item = new BaseResult();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		try {
			Integer irelationtype = StringUtilsEX.ToIntNull(relationtype);
			if (irelationtype == null) {
				item.setDesc("参数类型不正确！");
				item.setCode(-100);
				return getrsl(item, iskdr);
			}
			boolean bol = false;
			RelationTypeEnum sEnum = RelationTypeEnum.其他;
			RelationTypeEnum[] relationTypeEnums = RelationTypeEnum.values();
			for (RelationTypeEnum iTypeEnum : relationTypeEnums) {
				if (iTypeEnum.getValue() == irelationtype) {
					bol = true;	
					sEnum = iTypeEnum;
					break;
				}
			}
			if (!bol) {
				item.setDesc("参数类型不正确！");
				item.setCode(-100);
				return getrsl(item, iskdr);
			}
			List<String> rpaths = new ArrayList<String>();
			// 获取多个file
			for (Iterator<String> it = multipartRequest.getFileNames(); it.hasNext();) {
				String key = (String) it.next();
				MultipartFile imgFile = multipartRequest.getFile(key);
				if (imgFile.getOriginalFilename().length() > 0) {
					String fileName = imgFile.getOriginalFilename();
					int Index = fileName.lastIndexOf(".");
					String ext = fileName.substring(Index + 1, fileName.length());
					String exts = ConfigUtil.get_instances().getuploadext();
					if (!exts.toLowerCase().contains(ext.toLowerCase())) {
						item.setDesc("上传文件类型不正确，只请允许上文件类型：" + exts + "！");
						item.setCode(-101);
						return getrsl(item, iskdr);
					}
					byte[] file_buff = imgFile.getBytes();
					byte[] file_scale_buff;
					switch (StringUtilsEX.ToInt(type)) {
					case 0:// pc 或wap 产品列表及购物车及其他相关缩略图片
						file_scale_buff = ImageUtils.scale2(file_buff, 500, 500, false);
						if (file_scale_buff != null)
							savefile(fileName + "_p_pc_wap_scale", file_scale_buff, ext, rpaths, sEnum, relationid);
						break;
					default:
						break;
					}
					savefile(fileName, file_buff, ext, rpaths, sEnum, relationid);
					// string+= fileName +imgFile.getContentType() ;
					item.setDesc(Long.toString(imgFile.getSize()));
				}
			}
			item.setData(rpaths);
			
			return getrsl(item, iskdr);
		} catch (Exception e) {
		
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			LogHandle.error(LogType.Other,"上传图片错误：{0}", e, "image/upload");
			return getrsl(item, iskdr);
		}
	}

	void savefile(String fileName, byte[] file_buff, String ext, List<String> rpaths, RelationTypeEnum sEnum,
			String relationid) {
		FastDFSFile files = new FastDFSFile(fileName, file_buff, ext);
		String[] fileAbsolutePath = FileManager.upload(files);
		String url = fileAbsolutePath[7].replace("M00", "/data");
		rpaths.add(ConfigUtil.get_instances().fileUrl() + url);// ConfigUtil.get_instances().fileUrl()
																// +;
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		cachedThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					FileRecords imgrecords = new FileRecords();
					if (ext.toLowerCase().indexOf("jpg") != -1 || ext.toLowerCase().indexOf("jepg") != -1) {
						imgrecords.setFiletype(FileTypeEnum.jpg.getValue());
					}
					if (ext.toLowerCase().indexOf("png") != -1) {
						imgrecords.setFiletype(FileTypeEnum.png.getValue());
					}
					if (ext.toLowerCase().indexOf("gif") != -1) {
						imgrecords.setFiletype(FileTypeEnum.gif.getValue());
					}
					if (ext.toLowerCase().indexOf("bmp") != -1) {
						imgrecords.setFiletype(FileTypeEnum.bmp.getValue());
					}
					if (imgrecords.getFiletype() == null || imgrecords.getFiletype() == 0) {
						imgrecords.setFiletype(FileTypeEnum.other.getValue());
					}
					imgrecords.setCreatetime(new Date());
					imgrecords.setGroupname(fileAbsolutePath[5]);
					imgrecords.setServername(fileAbsolutePath[7]);
					imgrecords.setLocalname(fileName);
					imgrecords.setRelationid(StringUtilsEX.ToIntNull(relationid));

					imgrecords.setRelationtype(sEnum.getValue());
					imgrecords.setUrl(ConfigUtil.get_instances().fileUrl() + url);
					imgrecords.setStatus(0);
					fileRecordsService.add(imgrecords);
				} catch (Exception e) {
					LogHandle.error(LogType.Other, "img:{0} ", e, "image/upload");
				}
			}
		});
		cachedThreadPool.shutdown();
	}

	/**
	 * 上传文件（图片,生成缩略图片）
	 * 
	 * @param multipartRequest
	 * @param relationid
	 *            关联id
	 * @param relationtype
	 *            关联类型（RelationTypeEnum）
	 * @return
	 */
	@RequestMapping(value = "/uploadabb", produces = "text/html;charset=UTF-8")
	public @ResponseBody String uploadabb(MultipartHttpServletRequest multipartRequest, String relationid,
			String relationtype, String ch, String iskdr, String num, String token, HttpServletResponse response) {
		BaseResult item = new BaseResult();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		try {
			Integer irelationtype = StringUtilsEX.ToIntNull(relationtype);
			if (irelationtype == null) {
				item.setDesc("参数类型不正确！");
				item.setCode(-100);
				return getrsl(item, iskdr);
			}
			boolean bol = false;
			RelationTypeEnum[] relationTypeEnums = RelationTypeEnum.values();
			for (RelationTypeEnum iTypeEnum : relationTypeEnums) {
				if (iTypeEnum.getValue() == irelationtype) {
					bol = true;
					break;
				}
			}
			if (!bol) {
				item.setDesc("参数类型不正确！");
				item.setCode(-100);
				return getrsl(item, iskdr);
			}
			RelationTypeEnum sEnum = relationTypeEnums[irelationtype];
			List<String> rpaths = new ArrayList<String>();
			// 获取多个file
			for (Iterator<String> it = multipartRequest.getFileNames(); it.hasNext();) {
				String key = (String) it.next();
				MultipartFile imgFile = multipartRequest.getFile(key);
				if (imgFile.getOriginalFilename().length() > 0) {
					String fileName = imgFile.getOriginalFilename();
					int Index = fileName.lastIndexOf(".");
					String ext = fileName.substring(Index + 1, fileName.length());
					String exts = ConfigUtil.get_instances().getuploadext();
					if (!exts.toLowerCase().contains(ext.toLowerCase())) {
						item.setDesc("上传文件类型不正确，只请允许上文件类型：" + exts + "！");
						item.setCode(-101);
						return getrsl(item, iskdr);
					}
					byte[] file_buff = imgFile.getBytes();
					FastDFSFile files = new FastDFSFile(fileName, file_buff, ext);
					String[] fileAbsolutePath = FileManager.upload(files);
					String url = fileAbsolutePath[7].replace("M00", "/data");
					rpaths.add(ConfigUtil.get_instances().fileUrl() + url);// ConfigUtil.get_instances().fileUrl()
																			// +;
					ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
					cachedThreadPool.execute(new Runnable() {
						@Override
						public void run() {
							try {
								FileRecords imgrecords = new FileRecords();
								if (ext.toLowerCase().indexOf("jpg") != -1 || ext.toLowerCase().indexOf("jepg") != -1) {
									imgrecords.setFiletype(FileTypeEnum.jpg.getValue());
								}
								if (ext.toLowerCase().indexOf("png") != -1) {
									imgrecords.setFiletype(FileTypeEnum.png.getValue());
								}
								if (ext.toLowerCase().indexOf("gif") != -1) {
									imgrecords.setFiletype(FileTypeEnum.gif.getValue());
								}
								if (ext.toLowerCase().indexOf("bmp") != -1) {
									imgrecords.setFiletype(FileTypeEnum.bmp.getValue());
								}
								if (imgrecords.getFiletype() == null || imgrecords.getFiletype() == 0) {
									imgrecords.setFiletype(FileTypeEnum.other.getValue());
								}
								imgrecords.setCreatetime(new Date());
								imgrecords.setGroupname(fileAbsolutePath[5]);
								imgrecords.setServername(fileAbsolutePath[7]);
								imgrecords.setLocalname(fileName);
								imgrecords.setRelationid(StringUtilsEX.ToIntNull(relationid));

								imgrecords.setRelationtype(sEnum.getValue());
								imgrecords.setUrl(ConfigUtil.get_instances().fileUrl() + url);
								imgrecords.setStatus(0);
								fileRecordsService.add(imgrecords);
							} catch (Exception e) {
								LogHandle.error(LogType.Other, "img:{0} ", e, "image/upload");
							}
						}
					});
					cachedThreadPool.shutdown();
					// string+= fileName +imgFile.getContentType() ;
				}
			}
			item.setData(rpaths);
			return getrsl(item, iskdr);
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			LogHandle.error(LogType.Other,"上传图片错误：{0}", e, "image/upload");
			return getrsl(item, iskdr);
		}
	}

	/**
	 * 上传文件（图片，文件）
	 * 
	 * @param multipartRequest
	 * @param relationid
	 *            关联id
	 * @param relationtype
	 *            关联类型（RelationTypeEnum）
	 * @return
	 */
	@RequestMapping(value = "/uploadbybytes", produces = "text/html;charset=UTF-8")
	public @ResponseBody String uploadbybytes(byte[] bytes, String filename, String relationid, String relationtype,
			String ch, String iskdr, String token, HttpServletResponse response) {
		BaseResult item = new BaseResult();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		try {
			Integer irelationtype = StringUtilsEX.ToIntNull(relationtype);
			if (irelationtype == null) {
				item.setDesc("参数类型不正确！");
				item.setCode(-100);
				return getrsl(item, iskdr);
			}
			boolean bol = false;
			RelationTypeEnum[] relationTypeEnums = RelationTypeEnum.values();
			for (RelationTypeEnum iTypeEnum : relationTypeEnums) {
				if (iTypeEnum.getValue() == irelationtype) {
					bol = true;
					break;
				}
			}
			if (!bol) {
				item.setDesc("参数类型不正确！");
				item.setCode(-100);
				return getrsl(item, iskdr);
			}
			RelationTypeEnum sEnum = relationTypeEnums[irelationtype];
			List<String> rpaths = new ArrayList<String>();

			if (filename.length() <= 0) {
				item.setDesc("上传文件名不能为空！");
				item.setCode(-101);
				return getrsl(item, iskdr);
			}
			// String fileName = imgFile.getOriginalFilename();
			int Index = filename.lastIndexOf(".");
			String ext = filename.substring(Index + 1, filename.length());
			String exts = ConfigUtil.get_instances().getuploadext();
			if (!exts.toLowerCase().contains(ext.toLowerCase())) {
				item.setDesc("上传文件类型不正确，只请允许上文件类型：" + exts + "！");
				item.setCode(-102);
				return getrsl(item, iskdr);
			}
			if (bytes.length <= 0) {
				item.setDesc("上传文件不能为空！");
				item.setCode(-103);
				return getrsl(item, iskdr);
			}
			byte[] file_buff = bytes;
			FastDFSFile files = new FastDFSFile(filename, file_buff, ext);
			String[] fileAbsolutePath = FileManager.upload(files);
			String url = fileAbsolutePath[7].replace("M00", "/data");
			rpaths.add(ConfigUtil.get_instances().fileUrl() + url);// ConfigUtil.get_instances().fileUrl()
																	// +;
			ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						FileRecords imgrecords = new FileRecords();
						if (ext.toLowerCase().indexOf("jpg") != -1 || ext.toLowerCase().indexOf("jepg") != -1) {
							imgrecords.setFiletype(FileTypeEnum.jpg.getValue());
						}
						if (ext.toLowerCase().indexOf("png") != -1) {
							imgrecords.setFiletype(FileTypeEnum.png.getValue());
						}
						if (ext.toLowerCase().indexOf("gif") != -1) {
							imgrecords.setFiletype(FileTypeEnum.gif.getValue());
						}
						if (ext.toLowerCase().indexOf("bmp") != -1) {
							imgrecords.setFiletype(FileTypeEnum.bmp.getValue());
						}
						if (imgrecords.getFiletype() == null || imgrecords.getFiletype() == 0) {
							imgrecords.setFiletype(FileTypeEnum.other.getValue());
						}
						imgrecords.setCreatetime(new Date());
						imgrecords.setGroupname(fileAbsolutePath[5]);
						imgrecords.setServername(fileAbsolutePath[7]);
						imgrecords.setLocalname(filename);
						imgrecords.setRelationid(StringUtilsEX.ToIntNull(relationid));

						imgrecords.setRelationtype(sEnum.getValue());
						imgrecords.setUrl(ConfigUtil.get_instances().fileUrl() + url);
						imgrecords.setStatus(0);
						fileRecordsService.add(imgrecords);
					} catch (Exception e) {
						LogHandle.error(LogType.Other, "img:{0} ", e, "image/upload");
					}
				}
			});
			cachedThreadPool.shutdown();
			// string+= fileName +imgFile.getContentType() ;
			item.setData(rpaths);
			return getrsl(item, iskdr);
		} catch (Exception e) {
			
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			LogHandle.error(LogType.Other, MessageFormat.format("上传图片错误：{0}", e.toString()), "image/upload");
			return getrsl(item, iskdr);
		}
	}

	public class kdr {
		private int error;
		private String message;
		private String url;

		public int getError() {
			return error;
		}

		public void setError(int error) {
			this.error = error;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}

	@SuppressWarnings("unchecked")
	private String getrsl(BaseResult js, String iskdr) {
		if (!StringUtilsEX.IsNullOrWhiteSpace(iskdr)) {
			if (iskdr.equals("1")) {
				kdr s = new kdr();
				if (js.getCode() != 0) {
					s.setError(1);
					s.setMessage(js.getDesc());
				} else {
					s.setError(0);
					// s.setUrl(ConfigUtil.get_instances().fileUrl() +
					// ((List<String>) js.getData()).get(0));
					s.setUrl(((List<String>) js.getData()).get(0));
				}
				JSONObject json;
				json = JSONObject.fromObject(s);
				return json.toString();
			}
		}
		return js.toJson();
	}

	public static void main(String[] args) {
		String exts = ConfigUtil.get_instances().getuploadext();
		System.out.println(exts.contains("jpg"));
	}
}
