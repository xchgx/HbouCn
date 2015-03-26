/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xchgx.controller.jiuye.hukou;

import com.xchgx.domain.jiuye.hukou.Hukou;
import com.xchgx.service.jiuye.hukou.HukouService;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author cg
 */
@Controller
@RequestMapping("/")
public class HukouController {

    @Autowired
    private HukouService hukouService;
//    @Autowired(required=true)
    private CommonsMultipartResolver multipartResolver;

    public void setMultipartResolver(CommonsMultipartResolver multipartResolver) {
		this.multipartResolver = multipartResolver;
	}
    public CommonsMultipartResolver getMultipartResolver() {
		return multipartResolver;
	}
    @RequestMapping("/hukou/get")
    public ModelAndView getHukou() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("result");
        mav.addObject("obj", "oobbjj");
        System.out.println("mavViewName is result");
        return mav;
    }

    @RequestMapping("/hukou/getHukouBySfz/{sfz}")
    public @ResponseBody
    Hukou getHukouBySfz(@PathVariable String sfz, HttpServletRequest request) {
//        System.out.println("进入到/hukou/getHukouBySfz/" + sfz);
        return hukouService.findHukouBySfz(request.getServletContext().getRealPath("/WEB-INF/views/function/jiuye/hukou/upload/"), sfz, true);
//        return new Hukou("123", "张三", "男", "会计", "420704199308120571", "回原籍", "湖北省鄂州市人民政府大中专毕业生分配办公室");
    }

    @RequestMapping("/hukou/importExcelView")
    public ModelAndView importExcelView() {
        ModelAndView mav = new ModelAndView("/function/jiuye/hukou/importExcelView");
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        List<Integer> year = new ArrayList<>();
        for (int i = 2002; i <= Integer.parseInt(df.format(new Date())) + 1; i++) {
            year.add(i);
        }
        mav.addObject("year", year);
        return mav;
    }

//    /**
//     * 处理文件上传1
//     *
//     * @param file
//     * @return mav
//     */
//    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
//    public ModelAndView fileUpload(@RequestParam("fileUpload") CommonsMultipartFile file) {
//        //获取文件类型
//        System.out.println(file.getContentType());
//        //获取文件大小
//        System.out.println(file.getSize());
//        //获取文件名称
//        System.out.println(file.getOriginalFilename());
//        //判断文件是否存在
//        if (!file.isEmpty()) {
//            String path = "/home/cg/" + file.getOriginalFilename();
//            File localFile = new File(path);
//            try {
//                file.transferTo(localFile);
//            } catch (IllegalStateException e) {
//                e.printStackTrace();
//            } catch (IOException ex) {
//                Logger.getLogger(HukouController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return new ModelAndView("dataSuccess");
//    }
    /**
     * 进入后台主页
     * @return 
     */
    @RequestMapping("/manager/hukou/index")
    public ModelAndView index() {//首页
        ModelAndView mav = new ModelAndView();
        mav.setViewName("manager/function/jiuye/hukou/main");
        return mav;
    }

    /**
     * 执行上传操作
     *
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/hukou/doImportExcel", method = RequestMethod.POST)
    public ModelAndView doImportExcel(HttpServletRequest request) throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/function/jiuye/hukou/dataSuccess");
        String dbName = request.getParameter("dbName");
        //设置上下文
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //检查form是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multipartHttpServletRequest.getFileNames();
            while (iter.hasNext()) {
                //由CommonsMultipartFile继承而来，拥有上面的方法
                MultipartFile multipartFile = multipartHttpServletRequest.getFile(iter.next());
                if (multipartFile != null) {
                    //保存上传文件名
                    String fileName = multipartFile.getOriginalFilename();
//                    System.out.println("multipartFile.getName: "+multipartFile.getName());
//                    System.out.println("multipartFile.getSize: "+multipartFile.getSize());
//                    System.out.println("multipartFile.getOriginalFilename(): "+multipartFile.getOriginalFilename());
//                    System.out.println(fileName.substring( fileName.lastIndexOf(".")+1,fileName.length()));
                    //获取后缀名
                    String type = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
                    if (!type.equals("xls") && !type.equals("xlsx")) {
                        mav.addObject("error", multipartFile.getOriginalFilename() + " 文件上传失败");
                        return mav;
                    }
                    //获取web路径
                    String webPath = request.getServletContext().getRealPath("/WEB-INF/views");
                    ///WEB-INF/views/function/jiuye/hukou/upload/
                    String path = webPath + "/function/jiuye/hukou/upload/";
                    File parentDirectory = new File(path);
                    if (!parentDirectory.exists() || !parentDirectory.isDirectory()) {
                        parentDirectory.mkdir();
                    }
//                    String uploadFile = path + dbName+".xls";
                    String uploadFile = path + dbName + ".xls";
                    mav.addObject("path", uploadFile + " 文件上传成功");
                    File localFile = new File(uploadFile);
                    multipartFile.transferTo(localFile);
                    mav.addObject("support", hukouService.uploadExcelFileIsSupport(uploadFile));
                }
            }
        }
        return mav;
    }
}
