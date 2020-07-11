package com.hyp.blogmaster.service.impl;

import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.pojo.dto.resource.ResourceSimpleDTO;
import com.hyp.blogmaster.pojo.vo.result.MyResultVO;
import com.hyp.blogmaster.service.ResourceService;
import com.hyp.blogmaster.shiro.pojo.modal.AdminUser;
import com.hyp.blogmaster.shiro.pojo.modal.WeixinManagerResource;
import com.hyp.blogmaster.shiro.pojo.modal.WeixinManagerResourceConfig;
import com.hyp.blogmaster.shiro.service.WeixinManagerResourceConfigService;
import com.hyp.blogmaster.shiro.service.WeixinManagerResourceService;
import com.hyp.blogmaster.utils.fileutil.MyFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.Date;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/11 12:16
 * @Description: TODO
 */

@Service
@Slf4j
@PropertySource("classpath:email-imgvideo-res.properties")
public class ResourceServiceImpl implements ResourceService {

    @Value("${manager.resource.base.path}")
    private String resourceBasePath;
    @Value("${manager.resource.email.img.path}")
    private String resourceImgPath;


    @Autowired
    private WeixinManagerResourceService weixinManagerResourceService;
    @Autowired
    private WeixinManagerResourceConfigService weixinManagerResourceConfigService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 保存图片资源
     *
     * @param file
     * @return
     */
    @Override
    public MyResultVO saveEmailRes(MultipartFile file) throws MyDefinitionException {

        if (file == null) {
            throw new MyDefinitionException("上传文件为空");
        }


        String path = null;
        try {
            path = ResourceUtils.getURL("classpath:").getPath();
            //log.info("系统路径：{}", path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new MyDefinitionException("获取项目路径失败");
        }


        ResourceSimpleDTO resourceSimpleDTO = new ResourceSimpleDTO();


        String fileMd5 = MyFileUtil.getFileMd5(file);
        log.info("文件MD5：{}", fileMd5);

        WeixinManagerResource weixinResourceByMD5 = weixinManagerResourceService.getWeixinResourceByMD5(fileMd5);
        // 如果存在则直接返回数据
        if (weixinResourceByMD5 != null) {
            resourceSimpleDTO.setFileUrl(weixinResourceByMD5.getPath());
            resourceSimpleDTO.setFileType(weixinResourceByMD5.getType());
            resourceSimpleDTO.setSaveFileName(weixinResourceByMD5.getName());
            resourceSimpleDTO.setOriginalFileName(weixinResourceByMD5.getRealName());
            resourceSimpleDTO.setFileSize(weixinResourceByMD5.getSize());
            log.info("直接返回数据：{}", weixinResourceByMD5.toString());
            return MyResultVO.buildResult(MyResultVO.Status.OK, resourceSimpleDTO);
        }


        // 文件大小
        resourceSimpleDTO.setFileSize(file.getSize());
        // 文件类型
        // 获得文件类型 image/jpeg
        String fileType = file.getContentType();


        int resource_config_id = 1;
        String savePath = path + resourceBasePath + resourceImgPath;
        // 获得文件后缀名称 jpeg
        fileType = fileType.substring(fileType.indexOf("/") + 1);
        resourceSimpleDTO.setFileType(fileType);

        // 原名称 test.jpg
        String fileName = file.getOriginalFilename();

        // 文件原名称 去除后缀
        String fileOriginalFilename = fileName.substring(0, file.getOriginalFilename().lastIndexOf("."));
        resourceSimpleDTO.setOriginalFileName(fileOriginalFilename);

        String filename = fileName.substring(fileName.lastIndexOf("\\") + 1);
        //得到文件保存的名称 f1a315ae4b7a4c81b5cf1cb396fbfdd2_test.jpg
        String saveFilename = MyFileUtil.makeFileName(filename);
        //log.info("图片最后名字{}", saveFilename);
        resourceSimpleDTO.setSaveFileName(saveFilename.substring(0, saveFilename.lastIndexOf(".")));
        //得到文件的保存目录
        String realSavePath = MyFileUtil.makePath(saveFilename, savePath);
        try {
            MyFileUtil.upload(file, realSavePath, saveFilename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //String dataSavePath = File.separator + realSavePath.substring(realSavePath.indexOf(imgVideResConfig.getActiveImgBasePath()));
        String dataSavePath = "//" + httpServletRequest.getServerName().replace("/", "") + ":" + httpServletRequest.getServerPort() + "/" + realSavePath.substring(realSavePath.indexOf(resourceBasePath));
        // 拼接图片url
        String fileUrl = dataSavePath + "/" + saveFilename;
        resourceSimpleDTO.setFileUrl(fileUrl);


        WeixinManagerResource weixinResource = new WeixinManagerResource();
        weixinResource.setCreateTime(new Date());
        weixinResource.setType(resourceSimpleDTO.getFileType());
        weixinResource.setDescription("用户上传文件");
        weixinResource.setName(resourceSimpleDTO.getSaveFileName());
        weixinResource.setRealName(resourceSimpleDTO.getOriginalFileName());
        weixinResource.setMd5(fileMd5);
        weixinResource.setPath(fileUrl);
        weixinResource.setTitle("资源文件");
        weixinResource.setResourceConfigId(resource_config_id);
        weixinResource.setStatus(0);
        weixinResource.setSize(resourceSimpleDTO.getFileSize());
        WeixinManagerResourceConfig weixinResourceConfigById = weixinManagerResourceConfigService.getWeixinResourceConfigById(resource_config_id);
        if (weixinResourceConfigById != null) {
            weixinResource.setTitle(weixinResourceConfigById.getKeyWord());
            weixinResource.setDescription(weixinResourceConfigById.getDescription());
        } else {
            weixinResource.setTitle("");
            weixinResource.setDescription("");
        }

        AdminUser adminUser = (AdminUser) httpServletRequest.getSession().getAttribute("userSession");
        weixinResource.setCreateAdminUserId(adminUser.getId());

        weixinManagerResourceService.addWeixinResource(weixinResource);

        return MyResultVO.buildResult(MyResultVO.Status.OK, resourceSimpleDTO);
    }


}
