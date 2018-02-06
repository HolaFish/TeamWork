package com.heisenberg.base.action.impl;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.heisenberg.base.action.DocumentController;
import com.heisenberg.base.model.MyFile;
import com.heisenberg.common.resultmodel.BaseResult;

@Controller
public class DocumentControllerImpl implements DocumentController {
	
	/**
	 * 
	 * @描述：TODO(跳转到个人文档页面)
	 * @param userId
	 * @param topTeam
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午1:15:02
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午1:15:02
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("redirectToPersonalDoc/{userId}/{topTeamId}/{path}")
	@Override
	public String redirectToPersonalDoc(@PathVariable String userId,@PathVariable String topTeamId,@PathVariable String path, Model model) {
		model.addAttribute("userId", userId);
		model.addAttribute("teamId",topTeamId);
		model.addAttribute("type", "Personal");
		model.addAttribute("path", path);
		return "document/document_list";
	}
	/**
	 * 
	 * @描述：TODO(上传文件)
	 * @param uploadFile
	 * @param request
	 * @return
	 * @throws Exception
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午1:43:35
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午1:43:35
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("uploadFile/{userId}/{topTeamId}/{type}/{path}")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	public BaseResult uplodeFile(@PathVariable String userId,@PathVariable String topTeamId,@PathVariable String type,@PathVariable String path,MultipartFile uploadFile ,HttpServletRequest request) throws Exception {  
        File targetFile;  
        // 是否上传成功标志  
        boolean flag = false;  
        // 取文件的原始名称、后缀  
        String fileName = uploadFile.getOriginalFilename();  
        if(fileName != null && fileName != ""){     
            // 文件存储位置  
        	String absPath = "" ;
        	if ("personal".equals(type)){
        		absPath = request.getSession().getServletContext().getRealPath("document/personal/"+userId + path);  
        	}else{
        		absPath = request.getSession().getServletContext().getRealPath("document/team/"+topTeamId + path);  
        	}
            File fileToo =new File(absPath);   
            // 如果文件夹不存在则创建      
            if(!fileToo .exists()  && !fileToo .isDirectory()){         
                fileToo .mkdir();    
            }  
            targetFile = new File(fileToo, fileName);  
            try {  
                uploadFile.transferTo(targetFile);  
                flag = true;  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        if(flag){  
            return new BaseResult("0","成功");  
        }  
        return null;  
    }
	/**
	 * 
	 * @描述：TODO(获取path路径下的文件)
	 * @param path
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午4:02:36
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午4:02:36
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public List<MyFile> getFiles(String path) {
		// TODO Auto-generated method stub
		return null;
	}  
	
	
}
