package com.example.springboot.springbootdemo.util;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
public class FileUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取上传的文件对象
        Part filePart = request.getPart("file");
        String fileName = filePart.getName();
        long fileSize = filePart.getSize();
        String fileType = filePart.getContentType();

        // 根据文件类型和大小进行校验
        if (!fileType.equals("image/jpeg") || fileSize > 1024 * 1024) {
            response.getWriter().println("文件类型或大小不符合要求！");
            return;
        }

        // 将文件保存到指定的位置
        String savePath = "D:/uploads/" + fileName;
        filePart.write(savePath);
        response.getWriter().println("文件上传成功！");
    }

//    public String uploadFile(MultipartFile file) throws Exception {
//        File file1 = new File(file.getName());
//        try {
//            //使用此方法保存必须要绝对路径且文件夹必须已存在,否则报错
//            String fileUrl = "D:\\uploadFiles\\report\\" + fileAdd + File.separator + System.currentTimeMillis() + "." + uploadFileSuffix;
//            File localFile = new File(fileUrl);
//            File Parentlocaltion=localFile.getParentFile();
//
//            if (!Parentlocaltion.exists()) { //判断文件父目录是否存在
//                Parentlocaltion.mkdirs();
//            }
//            //FileUtils.copyInputStreamToFile(file.getInputStream(), localFile);
//            file.transferTo(localFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return dealResultMap(false, "上传失败");
//        }
//        return dealResultMap(true, "上传成功");
//    }

    public static void main(String[] args) throws Exception {
        File file = new File("D:\\答案\\胜任力测试题目.docx");
        MultipartFile multipartFile = new MockMultipartFile(
                "th.docx", //文件名
                "th.docx", //originalName 相当于上传文件在客户机上的文件名
                "docx", //文件类型
                new FileInputStream(file) //文件流
        );
        uploadFile1(multipartFile);
//        try {
//            //使用此方法保存必须要绝对路径且文件夹必须已存在,否则报错
//            String fileUrl = "D:\\uploadFiles\\report\\test.docx";
//            File localFile = new File(fileUrl);
//            File Parentlocaltion=localFile.getParentFile();
//
//            if (!Parentlocaltion.exists()) { //判断文件父目录是否存在
//                Parentlocaltion.mkdirs();
//            }
//            FileOutputStream out = new FileOutputStream(file);
//            //FileUtils.copyInputStreamToFile(file.getInputStream(), localFile);
//        }  finally {
//            // 8. 关闭DataOutputStream对象和FileInputStream对象
//        }
    }

    public static String uploadFile1(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        long size = file.getSize();
        String type = file.getContentType();
        try {
            //获取文件字节数组
            byte [] bytes = file.getBytes();
            //文件存储路径(/fileupload1/ 这样会在根目录下创建问价夹)
            File pfile = new File("D:\\uploadFiles\\report\\");
            //判断文件夹是否存在
            if(!pfile.exists()){
                //不存在时,创建文件夹
                pfile.mkdirs();
            }
            //创建文件
            File file1 = new File(pfile, fileName);
            //写入指定文件夹
            OutputStream out = new FileOutputStream(file1);
            out.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }
}
