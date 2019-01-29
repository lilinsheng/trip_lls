package cn.wolfcode.trip.app.filter;

import cn.wolfcode.trip.base.util.UploadUtil;
import org.apache.commons.io.FileUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * 图片过滤器
 */
public class ImageFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 过滤/upload/*的图片资源
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        //获取请求的uri
        String uri = request.getRequestURI();
        File file = new File(UploadUtil.PATH + uri);
        //判断文件是否存在
        if (file.exists()){
            //将磁盘下的图片资源写出来
            response.getOutputStream().write(FileUtils.readFileToByteArray(file));
        }else {
            //放行
            filterChain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
