package ru.apermyakov.mapping.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.apermyakov.mapping.service.AdsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class GetAllAds extends HttpServlet {

    private final AdsService service = new AdsService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        try (PrintWriter writer = new PrintWriter(resp.getWriter())) {
            ObjectMapper mapper = new ObjectMapper();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            mapper.writeValue(outputStream, this.service.findAll());
            writer.append(new String(outputStream.toByteArray()));
            writer.flush();
        }
    }
}
