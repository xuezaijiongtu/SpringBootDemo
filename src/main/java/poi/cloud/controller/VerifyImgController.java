package poi.cloud.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * @Description 验证码 Contrlller
 * @Author xing.liu
 * @Date 2016-11-25
 * */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

import util.SessionUtil;

/**
 * @Description 验证码 Controller
 * @Author xing.liu
 * @Date 2016-11-28
 * */
@Controller
public class VerifyImgController {
	@Autowired
	private Producer captchaProducer;
	
	
	@ResponseBody
    @RequestMapping(value="/VarifyImg/code", method = RequestMethod.GET)
	public void varifyImg(){
		ServletOutputStream out = null;
		try {
			HttpServletResponse rsp = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
	        rsp.setDateHeader("Expires", 0);
	        rsp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
	        rsp.addHeader("Cache-Control", "post-check=0, pre-check=0");
	        rsp.setHeader("Pragma", "no-cache");
	        rsp.setContentType("image/jpeg");
	        String capText = captchaProducer.createText();
	        SessionUtil.setSession(Constants.KAPTCHA_SESSION_KEY, capText);
	        BufferedImage image = captchaProducer.createImage(capText);
	        out = rsp.getOutputStream();
	        ImageIO.write(image, "jpg", out);
	        out.flush();
        }catch(IOException e){
			e.printStackTrace();
		} finally {
            try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
