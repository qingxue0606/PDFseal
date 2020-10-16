package com.example.demo;

import com.zhuozhengsoft.pageoffice.FileSaver;
import com.zhuozhengsoft.pageoffice.PDFCtrl;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.util.Map;

@RestController
public class PDFInsertSealController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView showIndex() {
        ModelAndView mv = new ModelAndView("Index");
        return mv;
    }

    @RequestMapping(value = "/AddSeal", method = RequestMethod.GET)
    public ModelAndView showWord(HttpServletRequest request, Map<String, Object> map) {
        PDFCtrl pdfCtrl1 = new PDFCtrl(request);
        pdfCtrl1.setServerPage(request.getContextPath() + "/poserver.zz"); //此行必须
        //设置保存页面
        pdfCtrl1.setSaveFilePage("/AddSeal/save");
        // Create custom toolbar
        pdfCtrl1.addCustomToolButton("保存", "Save()", 1);
        pdfCtrl1.addCustomToolButton("加盖印章", "InsertSeal()", 2);
        pdfCtrl1.webOpen("D:\\doc\\AddSeal\\test1.pdf");
        map.put("pageoffice", pdfCtrl1.getHtmlCode("PDFCtrl1"));
        ModelAndView mv = new ModelAndView("/AddSeal/PDF1");
        return mv;
    }


    @RequestMapping(value = "AddSign", method = RequestMethod.GET)
    public ModelAndView showWord11(HttpServletRequest request, Map<String, Object> map) {
        PDFCtrl pdfCtrl1 = new PDFCtrl(request);
        pdfCtrl1.setServerPage(request.getContextPath() + "/poserver.zz"); //此行必须
        //设置保存页面
        pdfCtrl1.setSaveFilePage("/AddSign/save");
        // Create custom toolbar
        pdfCtrl1.addCustomToolButton("保存", "Save()", 1);
        pdfCtrl1.addCustomToolButton("签字", "AddHandSign()", 3);
        pdfCtrl1.webOpen("D:\\doc\\AddSign\\test1.pdf");
        map.put("pageoffice", pdfCtrl1.getHtmlCode("PDFCtrl1"));
        ModelAndView mv = new ModelAndView("/AddSign/PDF1");
        return mv;
    }


    @RequestMapping("/AddSeal/save")
    public void save(HttpServletRequest request, HttpServletResponse response) {
        FileSaver fs = new FileSaver(request, response);
        fs.saveToFile(  "D:/doc/AddSeal/" + fs.getFileName());
        fs.close();
    }

    @RequestMapping("/AddSign/save")
    public void save2(HttpServletRequest request, HttpServletResponse response) {
        FileSaver fs = new FileSaver(request, response);
        fs.saveToFile(  "D:/doc/AddSign/" + fs.getFileName());
        fs.close();
    }

}
