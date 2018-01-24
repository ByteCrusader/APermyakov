package ru.apermyakov.mapping.servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.apermyakov.mapping.ad.Ad;
import ru.apermyakov.mapping.car.Car;
import ru.apermyakov.mapping.service.AdsService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class AddAd extends HttpServlet {

    private final AdsService service = new AdsService();

    private String downloadFile(HttpServletRequest req) throws Exception {
        DiskFileItemFactory factory = new DiskFileItemFactory();

        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);

        ServletFileUpload upload = new ServletFileUpload(factory);

        List<FileItem> items = upload.parseRequest(req);

        Random random = new Random();
        String result = "";

        for (FileItem item : items) {
            result = String.format("%s.PNG", random.nextInt(9999));
            item.write(new File(result));
        }

        return result;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Car car = new Car(req.getParameter("carMake"), req.getParameter("carModel"), Integer.parseInt(req.getParameter("carYear")));
        Ad ad = null;
        try {
            ad = new Ad(req.getParameter("description"), Boolean.parseBoolean(req.getParameter("status")), this.downloadFile(req));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        this.service.create(car, req.getParameter("engineId"), req.getParameter("gearboxId"), req.getParameter("transmissionId"), req.getParameter("userName"), ad);
    }
}
