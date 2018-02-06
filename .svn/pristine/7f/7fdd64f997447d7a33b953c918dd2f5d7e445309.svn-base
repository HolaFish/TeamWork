package com.heisenberg.base.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.heisenberg.base.model.MyFile;
import com.heisenberg.common.resultmodel.BaseResult;

public interface DocumentController {
	/**
	 * 
	 * @描述：TODO(跳转到个人文档页面)
	 * @param userId
	 * @param topTeamId
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午1:19:20
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午1:19:20
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String redirectToPersonalDoc(String userId, String topTeamId, String path, Model model);
	/**
	 * 
	 * @描述：TODO(上传文件)
	 * @param uploadFile
	 * @param request
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午1:42:56
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午1:42:56
	 * @修改备注：
	 * @version 1.0
	 * @throws Exception 
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult uplodeFile(String userId,String topTeamid,String type,String path,MultipartFile uploadFile ,HttpServletRequest request) throws Exception;
	/**
	 * 
	 * @描述：TODO(获取path目录下的文件)
	 * @param path
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午4:02:04
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午4:02:04
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<MyFile> getFiles(String path);
}
